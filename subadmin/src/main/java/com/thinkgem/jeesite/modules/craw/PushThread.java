package com.thinkgem.jeesite.modules.craw;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.craw.service.PushService;

public class PushThread extends Thread{
	
	
	private static PushService service = SpringContextHolder.getBean(PushService.class);
	

	public void run(){
		service.push();
	}
}
