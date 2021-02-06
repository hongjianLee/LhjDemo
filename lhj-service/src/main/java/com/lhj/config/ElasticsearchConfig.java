package com.lhj.config;

import com.lhj.utils.EsClientUtil;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Elasticsearch config.
 *
 * @author Zero
 */
@Configuration
public class ElasticsearchConfig {
    @Value("${es.http.url}")
    private String esUrl;

    @Bean
    RestHighLevelClient restHighLevelClient() {
        return EsClientUtil.createRestHighLevelClient(esUrl);
    }

    @Bean
    IndicesClient indicesClient() {
        return restHighLevelClient().indices();
    }
}
