package com.web.ecommerce.config;

import com.web.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] unsupportedHttpMethods = {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE, HttpMethod.PATCH};

        // code to disable HTTP methods PUT,POST and DELETE

        disableHttpMethods(Product.class,config, unsupportedHttpMethods);
        disableHttpMethods(ProductCategory.class,config, unsupportedHttpMethods);

        disableHttpMethods(Country.class,config, unsupportedHttpMethods);
        disableHttpMethods(State.class,config, unsupportedHttpMethods);

        disableHttpMethods(Order.class,config, unsupportedHttpMethods);
        // call helper method to expose id's
        exposeIds(config);

        // configure cors mapping
        cors.addMapping(config.getBasePath()+"/**").allowedOrigins(allowedOrigins);
    }

    private void disableHttpMethods(Class theClass,RepositoryRestConfiguration config, HttpMethod[] unsupportedHttpMethods) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods));
    }

    private void exposeIds(RepositoryRestConfiguration config){

        // get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array ofthe entity types
        List<Class> entityClasses = new ArrayList<>();

        // get ythe entity types for the entities
        for(EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());
        }

        // -expose the entity ids for the array of entity/domain types
        Class[] domainTypes= entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
