/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.SysVerificationcode;
import com.thinkgem.jeesite.modules.sys.dao.SysVerificationcodeDao;

/**
 * 验证码Service
 * @author wuyb
 * @version 2017-03-04
 */
@Service
@Transactional(readOnly = true)
public class SysVerificationcodeService extends CrudService<SysVerificationcodeDao, SysVerificationcode> {

	public SysVerificationcode get(String id) {
		return super.get(id);
	}
	
	public List<SysVerificationcode> findList(SysVerificationcode sysVerificationcode) {
		return super.findList(sysVerificationcode);
	}
	
	public Page<SysVerificationcode> findPage(Page<SysVerificationcode> page, SysVerificationcode sysVerificationcode) {
		return super.findPage(page, sysVerificationcode);
	}
	
	public SysVerificationcode checkCode(SysVerificationcode bean){
		return dao.checkCode(bean);
	}
	
	@Transactional(readOnly = false)
	public void save(SysVerificationcode sysVerificationcode) {
		super.save(sysVerificationcode);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysVerificationcode sysVerificationcode) {
		super.delete(sysVerificationcode);
	}
	
}