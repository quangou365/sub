/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.SysVerificationcode;

/**
 * 验证码DAO接口
 * @author wuyb
 * @version 2017-03-04
 */
@MyBatisDao
public interface SysVerificationcodeDao extends CrudDao<SysVerificationcode> {
	
	public SysVerificationcode checkCode(SysVerificationcode bean);
	
}