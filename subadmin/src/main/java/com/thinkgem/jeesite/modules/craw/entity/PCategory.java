/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.craw.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品分类实体类Entity
 * @author thinkgem
 * @version 2017-03-02
 */
public class PCategory extends DataEntity<PCategory> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 类型 1淘宝 2京东
	private String name;		// 分类名称
	private String url;		// url
	private String remark;		// remark
	private String parent;		// parent
	private String level;		// 级别
	private String icon;
	private String pname;
	
	public PCategory() {
		super();
	}
	
	@JsonIgnore
	public static String getRootId(){
		return "1";
	}

	public PCategory(String id){
		super(id);
	}
	
	@JsonIgnore
	public static void sortList(List<PCategory> list, List<PCategory> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			PCategory e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						PCategory child = sourcelist.get(j);
						if (child.getParent()!=null && child.getParent()!=null
								&& child.getParent().equals(e.getId())){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}

	@Length(min=0, max=255, message="类型 1淘宝 2京东长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="分类名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="url长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=20, message="parent长度必须介于 0 和 20 之间")
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=20, message="级别长度必须介于 0 和 20 之间")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
}