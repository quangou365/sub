package org.elasticsearch.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * 根据ID检索信息
 * @author wuyb
 *
 */
public class PrepareGet {

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

			GetResponse response = client.prepareGet("twitter", "tweet", "AVtTLmukl0lmQxGgEY2j")
			        .setOperationThreaded(false)
			        .get();
			System.out.println("搜索结果："+response.getSourceAsString());
			
			// on shutdown
			System.out.println("on shutdown");
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 

	}

}
