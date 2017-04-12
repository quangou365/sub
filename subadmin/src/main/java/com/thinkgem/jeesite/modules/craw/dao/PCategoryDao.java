/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.craw.entity.PCategory;

/**
 * 商品分类实体类DAO接口
 * @author thinkgem
 * @version 2017-03-02
 */
@MyBatisDao
public interface PCategoryDao extends CrudDao<PCategory> {
	
	public List<PCategory> findpList(PCategory entity);
	
	public List<PCategory> findParentList(PCategory entity);
	
}