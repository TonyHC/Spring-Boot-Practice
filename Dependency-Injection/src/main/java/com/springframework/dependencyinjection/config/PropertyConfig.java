package com.springframework.dependencyinjection.config;

import com.springframework.dependencyinjection.examplebeans.FakeDataSource;
import com.springframework.dependencyinjection.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
// @PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties")
})
public class PropertyConfig {
    @Autowired
    private Environment env;

    // Inject the specific Property Value using @Value
    @Value("${com.username}")
    private String user;

    @Value("${com.password}")
    private String password;

    @Value("${com.url}")
    private String url;

    @Value("${com.jms.username}")
    private String jmsUsername;

    @Value("${com.jms.password}")
    private String jmsPassword;

    @Value("${com.jms.url}")
    private String jmsUrl;

    // Gives us ability to read the values from Properties File specific by @PropertySource
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public FakeDataSource fakeDataSource() {
        FakeDataSource fakeDataSource = new FakeDataSource();

        // Get the Property Value from Environment Variable
        // Spring Boot has a Property Hierarchy:
        // Higher precedences can override Property Values from Lower precedences (needs to have same name)
        fakeDataSource.setUser(env.getProperty("USERNAME"));
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(env.getProperty("com.url"));

        return fakeDataSource;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();

        fakeJmsBroker.setUser(env.getProperty("com.test.user"));
        fakeJmsBroker.setPassword(env.getProperty("com.jms.password"));
        fakeJmsBroker.setUrl(jmsUrl);

        return fakeJmsBroker;
    }
}