package ru.bojark.conditionalapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bojark.conditionalapp.profiles.DevProfile;
import ru.bojark.conditionalapp.profiles.ProdProfile;
import ru.bojark.conditionalapp.profiles.SystemProfile;


@Configuration
public class JavaConfig {

    @Bean(name = "profile")
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean(name = "profile")
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "false")


    public SystemProfile prodProfile() {
        return new ProdProfile();
    }
}
