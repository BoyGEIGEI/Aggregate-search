package com.my.springbootinit.model.dto.post;


import com.my.springbootinit.model.entity.Post;
import com.mysql.cj.QueryResult;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;

import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.*;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import com.my.springbootinit.esdao.PostEsDao;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@SpringBootTest
public class PostEsDTOTest {

    @Resource
    private PostEsDao postEsDao;

    @Resource
    RestHighLevelClient client;




    @Test
    void testAdd(){
        PostEsDTO postEsDTO = new PostEsDTO();
        postEsDTO.setId(1L);
        postEsDTO.setTitle("鱼皮是狗");
        postEsDTO.setContent("鱼皮的知识星球：https://yupi.icu，直播带大家做项目");
        postEsDTO.setTags(Arrays.asList("java", "python"));
        postEsDTO.setUserId(1L);
        postEsDTO.setCreateTime(new Date());
        postEsDTO.setUpdateTime(new Date());
        postEsDTO.setIsDelete(0);
        postEsDao.save(postEsDTO);
        System.out.println(postEsDTO.getId());
    }

    @Test
    void testSelect() {
        System.out.println(postEsDao.count());
        //分页查询
        Page<PostEsDTO> PostPage = postEsDao.findAll(
                PageRequest.of(0, 5, Sort.by("createTime")));
        List<PostEsDTO> postList = PostPage.getContent();
        System.out.println(postList);
        // 根据 Id 查询
        Optional<PostEsDTO> byId = postEsDao.findById(1L);
        System.out.println(byId);
    }

    @Test
    //搜索分页
    public void testSearchPage() throws IOException {
        //1 构建搜索请求
        SearchRequest searchRequest = new SearchRequest("post_v1");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        //设置分页参数
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        int current = 1;
        int pageSize = 10;
        int from = (current - 1) * pageSize;
        //分页语句
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(10);

        //设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font class='eslight'>");
        highlightBuilder.postTags("</font>");
        //设置高亮字段
        // <font class='eslight'>node</font>
        highlightBuilder.fields().add(new HighlightBuilder.Field("title"));
        searchSourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(searchSourceBuilder);

//        QueryResult<PostEsDTO> queryResult = new QueryResult();

        List<PostEsDTO> list = new ArrayList<PostEsDTO>();
        //2执行搜索
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //3获取响应结果
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits().value;
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            PostEsDTO postEsDTO = new PostEsDTO();
            //源文档
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            //取出id
            Long id = (Long) sourceAsMap.get("id");
            postEsDTO.setId(id);
            //取出title
            String title = (String) sourceAsMap.get("title");
            //取出高亮字段title
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields != null) {
                HighlightField highlightFieldName = highlightFields.get("title");
                if (highlightFieldName != null) {
                    Text[] fragments = highlightFieldName.fragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text text : fragments) {
                        stringBuffer.append(text);
                    }
                    title = stringBuffer.toString();
                }

            }
            postEsDTO.setTitle(title);

            System.out.println( postEsDTO.getTitle());

            //取出content
            String content = (String) sourceAsMap.get("content");
            postEsDTO.setContent(content);
            System.out.println(postEsDTO);
        }
//        queryResult.setList(list);

    }


}