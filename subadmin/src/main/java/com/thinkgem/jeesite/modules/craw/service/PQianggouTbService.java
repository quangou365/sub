/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.craw.entity.PQianggouTb;
import com.thinkgem.jeesite.modules.craw.dao.PQianggouTbDao;

/**
 * 淘宝请购商品Service
 * @author wuyb
 * @version 2017-02-27
 */
@Service
@Transactional(readOnly = true)
public class PQianggouTbService extends CrudService<PQianggouTbDao, PQianggouTb> {

	public PQianggouTb get(String id) {
		return super.get(id);
	}
	
	public List<PQianggouTb> findList(PQianggouTb pQianggouTb) {
		return super.findList(pQianggouTb);
	}
	
	public Page<PQianggouTb> findPage(Page<PQianggouTb> page, PQianggouTb pQianggouTb) {
		return super.findPage(page, pQianggouTb);
	}
	
	@Transactional(readOnly = false)
	public void save(PQianggouTb pQianggouTb) {
		super.save(pQianggouTb);
	}
	
	@Transactional(readOnly = false)
	public void delete(PQianggouTb pQianggouTb) {
		super.delete(pQianggouTb);
	}
	
}