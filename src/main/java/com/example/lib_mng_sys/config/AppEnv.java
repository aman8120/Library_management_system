package com.example.lib_mng_sys.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app")
@Component
@Getter
@Setter
public class AppEnv {

    private Security security;
    private Domain domain;


    @Getter
    @Setter
    public static class Security{
        private String secret;
        private TokenValidity tokenValidity;

        @Getter
        @Setter
        public static class TokenValidity{
            private Long accessValidity;
            private Long refreshValidity;
        }

    }
    @Getter
    @Setter
    public static class Domain{
        private String name;
        private boolean secure;
        private String sameSite;
    }
}
