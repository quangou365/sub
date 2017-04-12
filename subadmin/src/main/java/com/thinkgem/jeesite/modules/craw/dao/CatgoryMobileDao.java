/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.craw.entity.CatgoryMobile;

/**
 * 手机号与分类关系DAO接口
 * @author ThinkGem
 * @version 2017-03-01
 */
@MyBatisDao
public interface CatgoryMobileDao extends CrudDao<CatgoryMobile> {
	
}