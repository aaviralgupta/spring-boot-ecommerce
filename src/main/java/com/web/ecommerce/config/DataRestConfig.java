package com.web.ecommerce.config;

import com.web.ecommerce.entity.Product;
import com.web.ecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] unsupportedHttpMethods = {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE};

        // code to disable HTTP methods PUT,POST and DELETE
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods));

        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods));
    }
}
