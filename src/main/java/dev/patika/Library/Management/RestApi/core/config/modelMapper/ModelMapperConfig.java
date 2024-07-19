package dev.patika.Library.Management.RestApi.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    // NESNEYİ CONTAİNER'A ATAR !
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
