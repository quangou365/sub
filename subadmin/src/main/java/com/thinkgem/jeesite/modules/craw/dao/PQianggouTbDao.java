/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.craw.entity.PQianggouTb;
import com.thinkgem.jeesite.modules.craw.entity.PushBean;

/**
 * 淘宝请购商品DAO接口
 * @author wuyb
 * @version 2017-02-27
 */
@MyBatisDao
public interface PQianggouTbDao extends CrudDao<PQianggouTb> {
	
	public List<PushBean> findPushList();
}