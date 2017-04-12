/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.web.front;

import java.util.Date;
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
import com.thinkgem.jeesite.modules.craw.entity.CatgoryMobile;
import com.thinkgem.jeesite.modules.craw.service.CatgoryMobileService;
import com.thinkgem.jeesite.modules.sys.entity.SysVerificationcode;
import com.thinkgem.jeesite.modules.sys.service.SysVerificationcodeService;

/**
 * 手机号与分类关系Controller
 * @author ThinkGem
 * @version 2017-03-01
 */
@Controller
@RequestMapping(value = "${frontPath}/craw/catgoryMobile")
public class CatgoryMobileController extends BaseController {

	@Autowired
	private CatgoryMobileService catgoryMobileService;
	
	@Autowired
	private SysVerificationcodeService sysVerificationcodeService;
	
	@ModelAttribute
	public CatgoryMobile get(@RequestParam(required=false) String id) {
		CatgoryMobile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catgoryMobileService.get(id);
		}
		if (entity == null){
			entity = new CatgoryMobile();
		}
		return entity;
	}
	
	@RequiresPermissions("craw:catgoryMobile:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatgoryMobile catgoryMobile, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatgoryMobile> page = catgoryMobileService.findPage(new Page<CatgoryMobile>(request, response), catgoryMobile); 
		model.addAttribute("page", page);
		return "modules/craw/catgoryMobileList";
	}

	@RequiresPermissions("craw:catgoryMobile:view")
	@RequestMapping(value = "form")
	public String form(CatgoryMobile catgoryMobile, Model model) {
		model.addAttribute("catgoryMobile", catgoryMobile);
		return "modules/craw/catgoryMobileForm";
	}

	//@RequiresPermissions("craw:catgoryMobile:edit")
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(CatgoryMobile catgoryMobile, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catgoryMobile)){
			return form(catgoryMobile, model);
		}
		String mobile=request.getParameter("mb");
		String categoryId=catgoryMobile.getId();
		catgoryMobile.setId(null);
		catgoryMobile.setFlag("0");
		catgoryMobile.setCategoryId(categoryId);
		catgoryMobile.setCreateTime(new Date());
		catgoryMobileService.save(catgoryMobile);
		model.addAttribute("message", "保存手机号与分类关系成功");
		model.addAttribute("mb",catgoryMobile.getMobile());
		
		CatgoryMobile bean=new CatgoryMobile();
		bean.setMobile(catgoryMobile.getMobile());
		List<CatgoryMobile> list=catgoryMobileService.findList(bean);
		model.addAttribute("list",list);
		return "0";
	}
	
	/**
	 * 获取订购列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "fl")
	public List<CatgoryMobile> findList(HttpServletRequest request){
		String mobile=request.getParameter("mb");
		CatgoryMobile catgoryMobile=new CatgoryMobile();
		catgoryMobile.setMobile(mobile);
		List<CatgoryMobile> list=catgoryMobileService.findList(catgoryMobile);
		return list;
	}
	
	@RequiresPermissions("craw:catgoryMobile:edit")
	@RequestMapping(value = "delete")
	public String delete(CatgoryMobile catgoryMobile, RedirectAttributes redirectAttributes) {
		catgoryMobileService.delete(catgoryMobile);
		addMessage(redirectAttributes, "删除手机号与分类关系成功");
		return "redirect:"+Global.getAdminPath()+"/craw/catgoryMobile/?repage";
	}

}