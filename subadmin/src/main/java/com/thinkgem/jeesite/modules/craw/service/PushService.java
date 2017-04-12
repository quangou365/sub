package com.thinkgem.jeesite.modules.craw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.craw.dao.CatgoryMobileDao;
import com.thinkgem.jeesite.modules.craw.dao.PQianggouTbDao;
import com.thinkgem.jeesite.modules.craw.entity.CatgoryMobile;
import com.thinkgem.jeesite.modules.craw.entity.PushBean;

@Service
public class PushService {
	
	@Autowired
	private PQianggouTbDao dao;
	
	@Autowired
	private CatgoryMobileDao catgoryMobileDao;
	
	public void push(){
		//手机号
		List<PushBean> list=dao.findPushList();
		System.out.println("推送信息："+list);
		CatgoryMobile cm=new CatgoryMobile();
		if(null!=list){
			for(PushBean bean:list){
				System.out.println("推送 产品信息："+bean.getPname());
				//产品推送完成后 修改flag标记
				cm=new CatgoryMobile();
				cm.setId(bean.getCategoryid());
				catgoryMobileDao.update(cm);
			}
		}
	}
	
	public static void main(String[] args){
		System.out.println(1111);
		PushService s=new PushService();
		s.push();
	}

}
