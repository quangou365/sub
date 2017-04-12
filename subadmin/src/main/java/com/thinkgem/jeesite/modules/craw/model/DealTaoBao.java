package com.thinkgem.jeesite.modules.craw.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.craw.entity.PQianggouTb;

public class DealTaoBao {

	/**
	 * 解析json格式的数据
	 * @param jsonStr
	 * @return
	 */
	public static List<PQianggouTb> jsonTx(String jsonStr) {
		System.out.println(jsonStr);
		jsonStr=jsonStr.replace("mtopjsonp4(","");
		if(jsonStr.indexOf("mtopjsonp")>=0){
			jsonStr=jsonStr.substring(jsonStr.indexOf("(")+1);
		}
		List<PQianggouTb> list=new ArrayList<PQianggouTb>();
		com.alibaba.fastjson.JSONObject object=JSON.parseObject(jsonStr);
		//String api=object.getString("api");
		JSONObject data=object.getJSONObject("data");
//		//String bgColor=data.getString("bgColor");//
//		//JSONArray topBanners = data.getJSONArray("topBanners");
		JSONArray items = data.getJSONArray("items");
		PQianggouTb bean=new PQianggouTb();
		for(int i=0;i<items.size();i++){
			bean=new PQianggouTb();
			String s = items.getString(i); 
			//System.out.println("对象内容："+s);      
			JSONObject data2 = JSONObject.parseObject(s);
			//System.out.println("activityId: "+data2.getString("activityId"));
			//System.out.println(data2.getString("displayType"));      
			//System.out.println("endTime: "+data2.getString("endTime")); 
			//System.out.println("name: "+data2.getString("name"));
			//System.out.println(bean);
			bean.setPcurl(data2.getString("pcUrl"));
			bean.setName(data2.getString("name"));
			bean.setEndtime(data2.getString("endTime"));
			bean.setPicurl(data2.getString("picUrl"));
			bean.setPrice(data2.getString("price"));
			bean.setSaleprice(data2.getString("salePrice"));
			bean.setStarttime(data2.getString("startTime"));
			bean.setSelfsellingpoint(data2.getString("selfSellingPoint"));
			bean.setUrl(data2.getString("url"));
			list.add(bean);
		}
		return list;
	}
}
