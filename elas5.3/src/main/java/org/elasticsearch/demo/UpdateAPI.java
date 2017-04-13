package org.elasticsearch.demo;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;

/**
 * 修改API
 * @author wuyb
 *
 */
public class UpdateAPI {

	public static void main(String[] args) {
		TransportClient client=ElasClient.getClient();
		
		try {
			//修改
			client.prepareUpdate("ttl", "doc", "1")
			.setDoc(jsonBuilder()
				.startObject()
			        .field("gender", "male")
			    .endObject())
			.get();
			
			//save or update
			IndexRequest indexRequest = new IndexRequest("index", "type", "1")
			        .source(jsonBuilder()
			            .startObject()
			                .field("name", "Joe Smith")
			                .field("gender", "male")
			            .endObject());
			UpdateRequest updateRequest = new UpdateRequest("index", "type", "1")
			        .doc(jsonBuilder()
			            .startObject()
			                .field("gender", "male")
			            .endObject())
			        .upsert(indexRequest);              
			try {
				client.update(updateRequest).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		client.close();
	}

}
