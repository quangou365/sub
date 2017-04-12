/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.craw.entity.CatgoryMobile;
import com.thinkgem.jeesite.modules.craw.dao.CatgoryMobileDao;

/**
 * 手机号与分类关系Service
 * @author ThinkGem
 * @version 2017-03-01
 */
@Service
@Transactional(readOnly = true)
public class CatgoryMobileService extends CrudService<CatgoryMobileDao, CatgoryMobile> {

	public CatgoryMobile get(String id) {
		return super.get(id);
	}
	
	public List<CatgoryMobile> findList(CatgoryMobile catgoryMobile) {
		return super.findList(catgoryMobile);
	}
	
	public Page<CatgoryMobile> findPage(Page<CatgoryMobile> page, CatgoryMobile catgoryMobile) {
		return super.findPage(page, catgoryMobile);
	}
	
	@Transactional(readOnly = false)
	public void save(CatgoryMobile catgoryMobile) {
		super.save(catgoryMobile);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatgoryMobile catgoryMobile) {
		super.delete(catgoryMobile);
	}
	
}