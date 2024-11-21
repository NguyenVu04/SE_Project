package spss.project.backend.configuration.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
/**
 * This class is a configuration for the MongoDB database. It is responsible for
 * setting up the database name and the converter for the GridFS template.
 */
@Configuration
public class SPSSMongoConfig extends AbstractMongoClientConfiguration {
    /**
     * A protected constructor to prevent instantiation of this class. This class
     * is a configuration class, and it is not intended to be instantiated.
     */
    protected SPSSMongoConfig() {}

    /**
     * This is a bean that creates a GridFsTemplate for the application. It takes
     * in a MappingMongoConverter and uses it to create a new GridFsTemplate.
     * 
     * @param converter the MappingMongoConverter to use
     * @return a new GridFsTemplate
     * @throws Exception if an error occurs
     */
    @Bean
    public GridFsTemplate gridFsTemplate(@Lazy MappingMongoConverter converter) throws Exception {
        return new GridFsTemplate(mongoDbFactory(), converter);
    }

    /**
     * This is a method that returns the name of the database. In this case, it is
     * set to "SE_Project".
     * 
     * @return the name of the database
     */
    @Override
    protected String getDatabaseName() {
        return "SE_Project";
    }

    /**
     * This is a method that returns whether or not to create indexes
     * automatically. In this case, it is set to true.
     * 
     * @return whether or not to create indexes automatically
     */
    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
