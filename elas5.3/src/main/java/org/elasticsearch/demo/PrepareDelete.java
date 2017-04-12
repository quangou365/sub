package org.elasticsearch.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * 根据id删除索引
 * @author wuyb
 *
 */
public class PrepareDelete {

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

			DeleteResponse response = client.prepareDelete("twitter", "tweet", "11").get();
			System.out.println("删除结果：id="+response.getId());
			
			// on shutdown
			System.out.println("on shutdown");
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}

}
