package org.samwan.admin.configs;


import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * YamlPropertiesFactoryBean to convert the resources in YAML format to the java.util.Properties object.
 *
 * Then, return a new instance of the PropertiesPropertySource, which is a wrapper that allows Spring to read the parsed properties.
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String s, EncodedResource encodedResource) throws IOException {

        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(encodedResource.getResource());

        Properties properties = factoryBean.getObject();

        return new PropertiesPropertySource(encodedResource.getResource().getFilename(),properties);
    }
}
