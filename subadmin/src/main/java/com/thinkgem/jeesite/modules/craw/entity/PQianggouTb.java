/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 淘宝请购商品Entity
 * @author wuyb
 * @version 2017-02-27
 */
public class PQianggouTb extends DataEntity<PQianggouTb> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String pcurl;		// pcurl
	private String picurl;		// 图片链接
	private String url;		// 产品连接
	private String starttime;		// starttime
	private String endtime;		// endtime
	private String price;		// 原价
	private String saleprice;		// 实际价格
	private String selfsellingpoint;		// selfsellingpoint
	private String category;		// category
	private String jsonData;
	
	public PQianggouTb() {
		super();
	}

	public PQianggouTb(String id){
		super(id);
	}

	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="pcurl长度必须介于 0 和 255 之间")
	public String getPcurl() {
		return pcurl;
	}

	public void setPcurl(String pcurl) {
		this.pcurl = pcurl;
	}
	
	@Length(min=0, max=255, message="图片链接长度必须介于 0 和 255 之间")
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	@Length(min=0, max=255, message="产品连接长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=255, message="starttime长度必须介于 0 和 255 之间")
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
	@Length(min=0, max=255, message="endtime长度必须介于 0 和 255 之间")
	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	@Length(min=0, max=255, message="原价长度必须介于 0 和 255 之间")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=255, message="实际价格长度必须介于 0 和 255 之间")
	public String getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}
	
	@Length(min=0, max=255, message="selfsellingpoint长度必须介于 0 和 255 之间")
	public String getSelfsellingpoint() {
		return selfsellingpoint;
	}

	public void setSelfsellingpoint(String selfsellingpoint) {
		this.selfsellingpoint = selfsellingpoint;
	}
	
	@Length(min=0, max=255, message="category长度必须介于 0 和 255 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
}