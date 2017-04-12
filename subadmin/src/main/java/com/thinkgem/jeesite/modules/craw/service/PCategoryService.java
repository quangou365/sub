/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.craw.entity.PCategory;
import com.thinkgem.jeesite.modules.craw.dao.PCategoryDao;

/**
 * 商品分类实体类Service
 * @author thinkgem
 * @version 2017-03-02
 */
@Service
@Transactional(readOnly = true)
public class PCategoryService extends CrudService<PCategoryDao, PCategory> {

	public PCategory get(String id) {
		return super.get(id);
	}
	
	public List<PCategory> findList(PCategory pCategory) {
		return super.findList(pCategory);
	}
	
	public List<PCategory> findpList(PCategory entity){
		return dao.findpList(entity);
	}
	
	public Page<PCategory> findPage(Page<PCategory> page, PCategory pCategory) {
		return super.findPage(page, pCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(PCategory pCategory) {
		super.save(pCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(PCategory pCategory) {
		super.delete(pCategory);
	}
	
	public List<PCategory> findParentList(PCategory entity){
		return dao.findParentList(entity);
	}
	
}