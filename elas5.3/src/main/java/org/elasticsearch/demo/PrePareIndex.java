package org.elasticsearch.demo;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * 创建索引文档
 * @author wuyb
 *
 */
public class PrePareIndex {

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

			List<String> jsonData = DataFactory.getInitJsonData();

//			for (int i = 0; i < jsonData.size(); i++) {
//				IndexResponse response = client.prepareIndex("blog", "article").setSource(jsonData.get(i)).get();
//
//				// 输出结果
//				System.out.println(response.getResult());
//			}
			for (int i = 0; i < 10; i++) {
				IndexResponse response = client.prepareIndex("twitter", "tweet")
						.setSource(jsonBuilder().startObject().field("user", "kimchy").field("postDate", new Date())
								.field("message", "trying out Elasticsearch").endObject())
						.get();
				System.out.println("索引结果  id:="+response.getId()+" index:"+response.getIndex()+" type:"+response.getType()+" result:"+response.getResult());
			}

			// on shutdown
			System.out.println("on shutdown");
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
