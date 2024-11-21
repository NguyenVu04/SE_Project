package spss.project.backend.configuration.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
@Configuration
public class SPSSMongoConfig extends AbstractMongoClientConfiguration {
    @Bean
    public GridFsTemplate gridFsTemplate(@Lazy MappingMongoConverter converter) throws Exception {
        return new GridFsTemplate(mongoDbFactory(), converter);
    }

    @Override
    protected String getDatabaseName() {
        return "SE_Project";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
