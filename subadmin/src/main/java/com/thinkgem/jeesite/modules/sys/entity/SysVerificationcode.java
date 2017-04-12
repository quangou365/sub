/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 验证码Entity
 * @author wuyb
 * @version 2017-03-04
 */
public class SysVerificationcode extends DataEntity<SysVerificationcode> {
	
	private static final long serialVersionUID = 1L;
	private String verificationCode;		// 验证码
	private String mobile;		// 手机号
	private String number;		// 序号
	private String pushTime;		// 下发日期
	
	public SysVerificationcode() {
		super();
	}

	public SysVerificationcode(String id){
		super(id);
	}

	@Length(min=0, max=255, message="验证码长度必须介于 0 和 255 之间")
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	@Length(min=0, max=255, message="手机号长度必须介于 0 和 255 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=4, message="序号长度必须介于 0 和 4 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
	
}