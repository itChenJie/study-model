package com.esfilrst.es;

import com.esfilrst.es.pojo.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class EsApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Test
    void contextLoads() {
    }
    @Before
    public void init(){
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setId(1001L);
        user.setAge(22);
        user.setName("陈杰");
        user.setHobby("足球、篮球、听音乐");
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(user).build();
        String index = elasticsearchTemplate.index(indexQuery);
        System.out.println(index);
    }
}
