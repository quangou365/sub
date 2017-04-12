package com.thinkgem.jeesite.modules.craw.tools;

import java.io.IOException;


public class CrawCategory {

	
//	public  void pq(){
//		String url="http://search.zbj.com/t/p[#number]s5.html?kw=%E8%BD%AF%E4%BB%B6";
//		for(int i=1;i<=100;i++){
//			url=url.replace("[#number]", i+"");
//			//HttpClient httpClient=new HttpClient(); 
//			//GetMethod getMethod=new GetMethod(url);
//			try{
//				int statusCode=httpClient.executeMethod(getMethod);
//				//content=getMethod.getResponseBodyAsString();
//				doContent(content);
//				//System.out.println("getResponseBodyAsString:"+content);
//			}catch(Exception e){
//				e.printStackTrace();
//			}finally{
//				getMethod.releaseConnection();
//			}
//		}
//	}
//	
//	public void doContent(String content){
//		String[] ca=content.split("<tr><td><p>");
//		String[] title=null;
//		String tit="";
//		int i=0;
//		for(String str:ca){
//			if(i==0){
//				i++;
//				continue;
//			}
//			//System.out.println(str);
//			title=str.split("title=\"");
//			tit=title[1].substring(0, title[1].indexOf("\" href=\""));
//			
//			//System.out.println(tit+"|");
//			tit=tit.replace("<hl>", "").replace("</hl>","");
//			System.out.println(tit+"||");
//		}
//	}
}
