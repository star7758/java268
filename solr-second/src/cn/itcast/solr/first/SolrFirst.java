package cn.itcast.solr.first;

import org.apache.log4j.chainsaw.Main;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

public class SolrFirst {
	@Test
	public void addDocument() throws Exception {
		// 1）创建一个SolrServer对象，HttpSolrServer。参数solr服务的url
		//如果不指定使用哪个solrcore，默认是Collection1
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
		// 2）创建一个SolrInputDocument对象
		SolrInputDocument document = new SolrInputDocument();
		// 3）向文档对象中添加域
		document.addField("id", "4");
		document.addField("title", "文档4");
		document.addField("content", "文档的内容");
		// 4）把文档对象写入索引库
		solrServer.add(document);
		// 5）提交
		solrServer.commit();
	}
	@Test
	public void deleteById() throws Exception{
		SolrServer solrserver=new HttpSolrServer("http://localhost:8080/solr/collection1");
		solrserver.deleteById("1");
		solrserver.commit();
	}
	
	@Test
	public void deleteducomentByQuery() throws Exception{
		SolrServer solrserver=new HttpSolrServer("http://localhost:8080/solr/collection1");
		solrserver.deleteByQuery("title:修改");
		solrserver.commit();
	}
	@Test
	public void queryDocument() throws Exception{
		SolrServer solrserver=new HttpSolrServer("http://localhost:8080/solr/collection1");
		SolrQuery solrquery=new SolrQuery();
		solrquery.set("q","*:*");
		solrquery.setStart(0);
		solrquery.setRows(5);
		QueryResponse queryResponse = solrserver.query(solrquery);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		System.out.println("总记录数:"+solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("title"));
			System.out.println(solrDocument.get("content"));						
		}				
	}
	public static void main(String[] args) {
		System.out.println("hello");
	}
}
