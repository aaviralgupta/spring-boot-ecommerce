package com.web.ecommerce.config;

import com.web.ecommerce.entity.Country;
import com.web.ecommerce.entity.Product;
import com.web.ecommerce.entity.ProductCategory;
import com.web.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
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

    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }
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

        config.getExposureConfiguration()
                .forDomainType(Country.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods));

        config.getExposureConfiguration()
                .forDomainType(State.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods));

        // call helper method to expose id's
        exposeIds(config);
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
