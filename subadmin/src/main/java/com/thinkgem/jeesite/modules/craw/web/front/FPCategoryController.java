/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.web.front;

import java.util.List;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.craw.entity.PCategory;
import com.thinkgem.jeesite.modules.craw.service.PCategoryService;

/**
 * 商品分类实体类Controller
 * @author thinkgem
 * @version 2017-03-02
 */
@Controller
@RequestMapping(value = "${frontPath}/craw/pCategory")
public class FPCategoryController extends BaseController {

	@Autowired
	private PCategoryService pCategoryService;
	
	@ResponseBody
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
		Page<PCategory> page = pCategoryService.findPage(new Page<PCategory>(request, response), pCategory); 
		model.addAttribute("page", page);
		return "modules/craw/front/pCategoryList";
	}
	
	@ResponseBody
	@RequestMapping(value = {"list4json", ""})
	public List<PCategory> list4json(PCategory pCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		String id=request.getParameter("id");
		PCategory bean=new PCategory();
		if(null==id||"0".equals(id)){
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
		if(null==id||"0".equals(id)){
			id="1";
		}
		bean.setId(id);
		List<PCategory> list = pCategoryService.findpList(bean);
		//System.out.println(list);
		return list;
	}

	@RequiresPermissions("craw:pCategory:view")
	@RequestMapping(value = "form")
	public String form(PCategory pCategory, Model model) {
		model.addAttribute("pCategory", pCategory);
		return "modules/craw/pCategoryForm";
	}

	@RequiresPermissions("craw:pCategory:edit")
	@RequestMapping(value = "save")
	public String save(PCategory pCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pCategory)){
			return form(pCategory, model);
		}
		pCategoryService.save(pCategory);
		addMessage(redirectAttributes, "保存商品分类实体类成功");
		return "redirect:"+Global.getAdminPath()+"/craw/pCategory/?repage";
	}
	
	@RequiresPermissions("craw:pCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(PCategory pCategory, RedirectAttributes redirectAttributes) {
		pCategoryService.delete(pCategory);
		addMessage(redirectAttributes, "删除商品分类实体类成功");
		return "redirect:"+Global.getAdminPath()+"/craw/pCategory/?repage";
	}

}