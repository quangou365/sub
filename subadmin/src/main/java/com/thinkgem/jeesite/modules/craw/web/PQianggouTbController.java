/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.craw.entity.PQianggouTb;
import com.thinkgem.jeesite.modules.craw.model.DealTaoBao;
import com.thinkgem.jeesite.modules.craw.service.PQianggouTbService;
import com.thinkgem.jeesite.modules.craw.service.PushService;

/**
 * 淘宝请购商品Controller
 * @author wuyb
 * @version 2017-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/craw/pQianggouTb")
public class PQianggouTbController extends BaseController {

	@Autowired
	private PQianggouTbService pQianggouTbService;
	
	@ModelAttribute
	public PQianggouTb get(@RequestParam(required=false) String id) {
		PQianggouTb entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pQianggouTbService.get(id);
		}
		if (entity == null){
			entity = new PQianggouTb();
		}
		return entity;
	}
	
	//@RequiresPermissions("craw:pQianggouTb:view")
	@RequestMapping(value = {"list", ""})
	public String list(PQianggouTb pQianggouTb, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PQianggouTb> page = pQianggouTbService.findPage(new Page<PQianggouTb>(request, response), pQianggouTb); 
		model.addAttribute("page", page);
		//pushservice.push();
		return "modules/craw/pQianggouTbList";
	}
	
	/***批量添加数据页面***/
	@RequestMapping(value = {"pQianggouTbBath", ""})
	public String addBath(PQianggouTb pQianggouTb, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<PQianggouTb> page = pQianggouTbService.findPage(new Page<PQianggouTb>(request, response), pQianggouTb); 
//		model.addAttribute("page", page);
//		//pushservice.push();
		model.addAttribute("pQianggouTb", pQianggouTb);
		return "modules/craw/pQianggouTbBath";
	}


	//@RequiresPermissions("craw:pQianggouTb:view")
	@RequestMapping(value = "form")
	public String form(PQianggouTb pQianggouTb, Model model) {
		model.addAttribute("pQianggouTb", pQianggouTb);
		return "modules/craw/pQianggouTbForm";
	}

	//@RequiresPermissions("craw:pQianggouTb:edit")
	@RequestMapping(value = "save")
	public String save(PQianggouTb pQianggouTb, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, pQianggouTb)){
//			return form(pQianggouTb, model);
//		}
		
		String jsonData=pQianggouTb.getJsonData();
		if(null!=jsonData){//批量添加
			jsonData=jsonData.replace("mtopjsonp1(", "").replace(")", "").replace("mtopjsonp3(", "").replace(")", "");
			jsonData=StringEscapeUtils.unescapeHtml4(jsonData);
			List<PQianggouTb> list=DealTaoBao.jsonTx(jsonData);
			for(PQianggouTb bean:list){
				pQianggouTbService.save(bean);
			}
			addMessage(redirectAttributes, "保存抢购成功");
			return "redirect:"+Global.getAdminPath()+"/craw/pQianggouTb/pQianggouTbBath/?repage";
		}else{//单条添加
			pQianggouTbService.save(pQianggouTb);
			addMessage(redirectAttributes, "保存抢购成功");
			return "redirect:"+Global.getAdminPath()+"/craw/pQianggouTb/list/?repage";
		}
		//return "redirect:"+Global.getAdminPath()+"/craw/pQianggouTb/pQianggouTbBath/?repage";
	}
	
	//@RequiresPermissions("craw:pQianggouTb:edit")
	@RequestMapping(value = "delete")
	public String delete(PQianggouTb pQianggouTb, RedirectAttributes redirectAttributes) {
		pQianggouTbService.delete(pQianggouTb);
		addMessage(redirectAttributes, "删除淘宝请购成功");
		return "redirect:"+Global.getAdminPath()+"/craw/pQianggouTb/?repage";
	}

}