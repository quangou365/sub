/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.craw.entity.PCategory;
import com.thinkgem.jeesite.modules.craw.service.PCategoryService;
import com.thinkgem.jeesite.modules.sys.entity.Menu;

/**
 * 商品分类实体类Controller
 * @author thinkgem
 * @version 2017-03-02
 */
@Controller
@RequestMapping(value = "${adminPath}/craw/pCategory")
public class PCategoryController extends BaseController {

	@Autowired
	private PCategoryService pCategoryService;
	
	@ModelAttribute
	public PCategory get(@RequestParam(required=false) String id) {
		PCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pCategoryService.get(id);
		}
		if (entity == null){
			entity = new PCategory();
		}
		return entity;
	}
	
	//@RequiresPermissions("craw:pCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(PCategory pCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<PCategory> list = Lists.newArrayList();
		List<PCategory> sourcelist = pCategoryService.findParentList( pCategory); 
		PCategory.sortList(list, sourcelist, PCategory.getRootId(), true);
		model.addAttribute("list", list);
		return "modules/craw/pCategoryList";
	}
	
	@ResponseBody
	@RequestMapping(value = {"list4json", ""})
	public List<PCategory> list4json(PCategory pCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		String id=request.getParameter("id");
		PCategory bean=new PCategory();
		if(null==id){
			id="1";
		}
		bean.setParent(id);
		List<PCategory> list = pCategoryService.findList(bean);
		System.out.println("列表："+list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = {"getByid", ""})
	public PCategory getByid(PCategory pCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		String id=request.getParameter("id");
		PCategory bean=pCategoryService.get(id);
		return bean;
	}
	
	/**
	 * 获取父类分类信息
	 * @param pCategory
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"plist4json", ""})
	public List<PCategory> plist4json(PCategory pCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		String id=request.getParameter("id");
		PCategory bean=new PCategory();
		if(null==id){
			id="0";
		}
		bean.setId(id);
		List<PCategory> list = pCategoryService.findpList(bean);
		//System.out.println(list);
		return list;
	}
	
	/***分类json数据**/
	@ResponseBody
	@RequestMapping(value = "cd")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,@RequestParam(required=false) String isShowHide, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<PCategory> list = pCategoryService.findParentList(null);
		for (int i=0; i<list.size(); i++){
			PCategory e = list.get(i);
//			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
//				if(isShowHide != null && isShowHide.equals("0") && e.getIsShow().equals("0")){
//					continue;
//				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParent());
				map.put("name", e.getName());
				mapList.add(map);
			//}
		}
		return mapList;
	}

	//@RequiresPermissions("craw:pCategory:view")
	@RequestMapping(value = "form")
	public String form(PCategory pCategory, Model model) {
		if (pCategory.getParent()==null||pCategory.getParent()==null){
			pCategory.setParent(pCategory.getRootId());
		}
		PCategory pc=pCategoryService.get(pCategory.getParent());
		pCategory.setParent(pc.getId());
		pCategory.setPname(pc.getName());
		// 获取排序号，最末节点排序号+30
		if (StringUtils.isBlank(pCategory.getId())){
			List<PCategory> list = Lists.newArrayList();
			List<PCategory> sourcelist = pCategoryService.findList(null);
			PCategory.sortList(list, sourcelist, pCategory.getParent(), false);
//			if (list.size() > 0){
//				pCategory.setSort(list.get(list.size()-1).getSort() + 30);
//			}
		}
		
		model.addAttribute("pCategory", pCategory);
		return "modules/craw/pCategoryForm";
	}

	@RequiresPermissions("craw:pCategory:edit")
	@RequestMapping(value = "save")
	public String save(PCategory pCategory,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pCategory)){
			return form(pCategory, model);
		}
		String parent=request.getParameter("parent.id");
		pCategory.setParent(parent);
		pCategoryService.save(pCategory);
		addMessage(redirectAttributes, "保存商品分类实体类成功");
		return "redirect:"+Global.getAdminPath()+"/craw/pCategory/list/?repage";
	}
	
	@RequiresPermissions("craw:pCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(PCategory pCategory, RedirectAttributes redirectAttributes) {
		pCategoryService.delete(pCategory);
		addMessage(redirectAttributes, "删除商品分类实体类成功");
		return "redirect:"+Global.getAdminPath()+"/craw/pCategory/?repage";
	}

}