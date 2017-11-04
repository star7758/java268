package cn.itcast.solr.first;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class SolrSecond {
	/*@Test
	public  void queryIndex() throws Exception{
	SolrServer solrserver=new  HttpSolrServer("http://localhost:8080/solr/collection1");
	SolrQuery query=new SolrQuery();
	query.set("q","台灯");
	query.addFacetQuery("product_catalog_name:时尚卫浴");
	query.setSort("product_price",ORDER.asc);
	query.setStart(0);
	query.setRows(10);
	query.set("df", "product_keywords");
	query.setHighlight(true);
	query.addHighlightField("product_name");
	query.setHighlightSimplePre("<font>");
	query.setHighlightSimplePost("</font>");
	QueryResponse queryResponse = solrserver.query(query);
	SolrDocumentList solrDocumentList = queryResponse.getResults();
	System.out.println("总记录数:"+solrDocumentList.getNumFound());
	//Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
	for (SolrDocument solrDocument : solrDocumentList) {
	System.out.println(solrDocument.get("id"));	
	System.out.println(solrDocument.get("product_picture"));	
	System.out.println(solrDocument.get("product_catalog_name"));	
	System.out.println(solrDocument.get("product_name"));	
	System.out.println(solrDocument.get("product_price"));	
	}
	}*/
	@Test
	public void queryIndex() throws Exception {
		//创建一个SolrServer对象
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
		//创建一个SolrQuery对象
		SolrQuery query = new SolrQuery();
		//设置查询参数，可以参考solr的后台设置
		query.setQuery("台灯");
		//query.set("q", "台灯");
		//设置过滤条件
		query.addFilterQuery("product_catalog_name:时尚卫浴");
		//排序条件
		query.setSort("product_price", ORDER.asc);
		//分页条件
		query.setStart(0);
		query.setRows(10);
		//设置默认搜索域
		query.set("df", "product_keywords");
		//开启高亮显示
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<font>");
		query.setHighlightSimplePost("</font>");
		//执行查询
		QueryResponse queryResponse = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		//取查询结果的总记录数
		System.out.println("总记录数："+ solrDocumentList.getNumFound());
		//取高亮结果
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		//遍历查询结果
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
		/*	String productName= "";
			//取高亮结果
			List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");
			if (list != null && list.size() > 0) {
				productName = list.get(0);
			} else {
				productName = solrDocument.get("product_name").toString();
			}*/
			String productName="";
			List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");
				if(list!=null&&list.size()>0){
					productName=list.get(0);
			}else{
				solrDocument.get("product_name").toString();
			}
			
			
			System.out.println(productName);
			System.out.println(solrDocument.get("product_price"));
			System.out.println(solrDocument.get("product_catalog_name"));
			System.out.println(solrDocument.get("product_picture"));
		}
}
}