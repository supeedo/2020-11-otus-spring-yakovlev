package ru.quizapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ConfigurationProperties(prefix = "localization")
public class LocalizationConfig {

    private String format;
    private String patch;

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(patch);
        ms.setDefaultEncoding(format);
        return ms;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }
}
