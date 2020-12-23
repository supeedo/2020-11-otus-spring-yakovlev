package ru.quizapp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.quizapp.config.AppConfiguration;

@Component
public class LocaleDataHelper {
    private static final Logger logger = LoggerFactory.getLogger(LocaleDataHelper.class);
    private final MessageSource source;
    private final AppConfiguration configuration;


    public LocaleDataHelper(MessageSource source, AppConfiguration configuration) {
        this.source = source;
        this.configuration = configuration;
    }

    public String getLocaleMessage(String code) {
        logger.info("Get a localized string by code = " + code);
        return source.getMessage(code, new String[]{}, configuration.getLocale());
    }
}
