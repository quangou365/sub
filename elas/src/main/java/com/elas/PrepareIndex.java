package com.elas;

import java.net.InetAddress;
import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class PrepareIndex {

	public static void main(String[] args) {
		try {

			// 设置集群名称
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			// 创建client
			TransportClient client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			System.out.println("==================");

			// 搜索数据
			List<String> jsonData = DataFactory.getInitJsonData();

			for (int i = 0; i < jsonData.size(); i++) {
				IndexResponse response = client.prepareIndex("blog", "article",i+"").setSource(jsonData.get(i)).get();
				// 输出结果
				System.out.println(response.getResult());
			}
			// 关闭client
			client.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
