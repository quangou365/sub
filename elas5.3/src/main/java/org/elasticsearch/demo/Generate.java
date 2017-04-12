package org.elasticsearch.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class Generate {

	public static void main(String[] args) {
		System.out.println("on startup");
		try {
			// on startup
			Settings settings = Settings.builder().put("cluster.name", "myClusterName").build();
			TransportClient client= new PreBuiltTransportClient(settings);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			//集群
			//client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost2"), 9300));
			System.out.println("="+client);
			
			// on shutdown
			System.out.println("on shutdown");
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
