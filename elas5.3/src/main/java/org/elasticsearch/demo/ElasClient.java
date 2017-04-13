package org.elasticsearch.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasClient {
	
	private static TransportClient client;
	
	public static TransportClient getClient(){
		try {
			// on startup
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			client = new PreBuiltTransportClient(settings);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
			return client;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static void close(){
		if(null!=client){
			client.close();
		}
	}

}
