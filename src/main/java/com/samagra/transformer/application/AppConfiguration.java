package com.samagra.transformer.application;

import com.samagra.transformer.odk.model.Form;
import com.samagra.transformer.odk.persistance.FormsDao;
import io.jsondb.InvalidJsonDbApiUsageException;
import io.jsondb.JsonDBTemplate;
import io.jsondb.crypto.Default1Cipher;
import io.jsondb.crypto.ICipher;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
@EnableAutoConfiguration
public class AppConfiguration {

//    @Bean
//    public FormsDao getFormsDao(){
//        return new FormsDao();
//    }

    @Bean
    @Qualifier("rest")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Qualifier("custom")
    public RestTemplate getCustomTemplate(RestTemplateBuilder builder) {
        Credentials credentials = new UsernamePasswordCredentials("samagra","impact@scale");
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, credentials);

        HttpClient httpClient = HttpClients
                .custom()
                .disableCookieManagement()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        return builder
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient))
                .build();
    }
}
