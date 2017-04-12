/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.craw.entity.PCategory;
import com.thinkgem.jeesite.modules.sys.entity.SysVerificationcode;
import com.thinkgem.jeesite.modules.sys.service.SysVerificationcodeService;

/**
 * 验证码Controller
 * @author wuyb
 * @version 2017-03-04
 */
@Controller
@RequestMapping(value = "${frontPath}/sys/vc")
public class SysVerificationcodeController extends BaseController {
	
	private Log log=LogFactory.getLog(SysVerificationcodeController.class);

	@Autowired
	private SysVerificationcodeService sysVerificationcodeService;
	
	@ModelAttribute
	public SysVerificationcode get(@RequestParam(required=false) String id) {
		SysVerificationcode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysVerificationcodeService.get(id);
		}
		if (entity == null){
			entity = new SysVerificationcode();
		}
		return entity;
	}
	
	/**
	 * 产生验证码
	 * @param pCategory
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"gc", ""})
	public String list4json(PCategory pCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		String mobile=request.getParameter("mb");
		String pushTime=DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
		Random random = new Random();
		String code="";
		for(int i=0;i<6;i++){
			code+=random.nextInt(10);
		}
		SysVerificationcode bean=new SysVerificationcode();
		bean.setVerificationCode(code);
		bean.setMobile(mobile);
		bean.setPushTime(pushTime);
		sysVerificationcodeService.save(bean);
		//下发验证码
		log.info("下发 验证码："+code+" 对应手机号："+mobile);
		return "success";
	}
	
	/***验证验证码  0失败  1成功  **/
	@ResponseBody
	@RequestMapping(value = {"cc", ""})
	public String checkCode(HttpServletRequest request){
		String vCode=request.getParameter("vc");
		//验证验证码是否正确
		SysVerificationcode bean=new SysVerificationcode();
		bean.setMobile(request.getParameter("mb"));
		bean.setVerificationCode(vCode);
		SysVerificationcode sv=sysVerificationcodeService.checkCode(bean);
		if(null==sv){
			return "0";
		}
		return "1";
	}
	
	@RequiresPermissions("sys:sysVerificationcode:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysVerificationcode sysVerificationcode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysVerificationcode> page = sysVerificationcodeService.findPage(new Page<SysVerificationcode>(request, response), sysVerificationcode); 
		model.addAttribute("page", page);
		return "modules/sys/sysVerificationcodeList";
	}

//	@RequiresPermissions("sys:sysVerificationcode:view")
//	@RequestMapping(value = "form")
//	public String form(SysVerificationcode sysVerificationcode, Model model) {
//		model.addAttribute("sysVerificationcode", sysVerificationcode);
//		return "modules/sys/sysVerificationcodeForm";
//	}

//	@RequiresPermissions("sys:sysVerificationcode:edit")
//	@RequestMapping(value = "save")
//	public String save(SysVerificationcode sysVerificationcode, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, sysVerificationcode)){
//			return form(sysVerificationcode, model);
//		}
//		sysVerificationcodeService.save(sysVerificationcode);
//		addMessage(redirectAttributes, "保存验证码成功");
//		return "redirect:"+Global.getAdminPath()+"/sys/sysVerificationcode/?repage";
//	}
	
//	@RequiresPermissions("sys:sysVerificationcode:edit")
//	@RequestMapping(value = "delete")
//	public String delete(SysVerificationcode sysVerificationcode, RedirectAttributes redirectAttributes) {
//		sysVerificationcodeService.delete(sysVerificationcode);
//		addMessage(redirectAttributes, "删除验证码成功");
//		return "redirect:"+Global.getAdminPath()+"/sys/sysVerificationcode/?repage";
//	}

}