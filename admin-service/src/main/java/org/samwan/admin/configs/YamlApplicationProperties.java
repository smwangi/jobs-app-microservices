package org.samwan.admin.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring")
@PropertySource(value = "classpath:application.yaml", factory = YamlPropertySourceFactory.class)
public class YamlApplicationProperties {

    private String datasource;

    private List<String> datasourceDetails;

    public List<String> getDatasourceDetails() {
        return datasourceDetails;
    }

    public String getDatasource() {
        return datasource;
    }
}
