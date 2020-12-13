package ru.quizapp.utils;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.quizapp.config.AppConfiguration;

@Component
public class LocaleDataHelper {
    private final MessageSource source;
    private final AppConfiguration configuration;


    public LocaleDataHelper(MessageSource source, AppConfiguration configuration) {
        this.source = source;
        this.configuration = configuration;
    }

    public String getLocaleMessage(String code) {
        return source.getMessage(code, new String[]{}, configuration.getLocale());
    }
}
