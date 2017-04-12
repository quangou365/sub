package org.elasticsearch.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * 按条件删除
 * 
 * @author wuyb
 *
 */
public class DeleteByQuery {

	public static void main(String[] args) {
		try {
			// on startup
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			TransportClient client = new PreBuiltTransportClient(settings);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
			// 集群
			// client.addTransportAddress(new
			// InetSocketTransportAddress(InetAddress.getByName("localhost2"),
			// 9300));
			System.out.println("=" + client);

			// 删除twitter 索引中所有用户名为kimchy的用户
			BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
					.filter(QueryBuilders.matchQuery("user", "user")) // query 条件
					.source("twitter") // index
					.get(); // execute the operation

			long deleted = response.getDeleted();//删除的记录数
			System.out.println("删除的记录数：" + deleted);
			
			//异步执行删除
			DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
		    .filter(QueryBuilders.matchQuery("user", "user"))                  
		    .source("twitter")                                                   
		    .execute(new ActionListener<BulkByScrollResponse>() {           
		        @Override
		        public void onResponse(BulkByScrollResponse response) {
		            long deleted = response.getDeleted();
		            System.out.println("异步执行删除条数："+deleted);
		        }
		        @Override
		        public void onFailure(Exception e) {
		            // Handle the exception
		        }
		    });
			
			// 结束操作

			// on shutdown
			System.out.println("on shutdown");
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
