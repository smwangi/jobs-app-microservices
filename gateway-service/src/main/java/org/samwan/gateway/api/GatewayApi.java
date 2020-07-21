package org.samwan.gateway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GatewayApi {

    @Autowired
    ZuulProperties properties;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "GatewayApplication says hello!";
    }

    @GetMapping("/test")
    @ResponseBody
    public String invokeTestService() {
        List<ServiceInstance> testServiceInstances = this.discoveryClient.getInstances("test-service");
        return restTemplate.getForObject(testServiceInstances.get(0).getUri(), String.class);
    }

    @GetMapping("/services")
    public List<String> services() {
        return this.discoveryClient.getServices();
    }

    @GetMapping("/services/{serviceId}")
    public List<ServiceInstance> servicesById(@PathVariable("serviceId") String serviceId) {
        return this.discoveryClient.getInstances(serviceId);
    }

   @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            properties.getRoutes().values().stream()
                    .forEach(route -> resources.add(createResource(route.getId(), "2.0")));
            return resources;
        };
    }

    private SwaggerResource createResource(String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(location);
        swaggerResource.setLocation("/" + location + "/v2/api-docs");
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
