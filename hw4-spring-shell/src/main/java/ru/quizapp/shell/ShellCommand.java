package ru.quizapp.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import ru.quizapp.service.IOService;
import ru.quizapp.service.IOServiceImpl;
import ru.quizapp.utils.LocaleDataHelper;

@ShellComponent
public class ShellCommand {
    private final IOService ioService;
    private final LocaleDataHelper localeDataHelper;

    @Autowired
    public ShellCommand( IOServiceImpl ioService, LocaleDataHelper localeDataHelper ) {
        this.ioService = ioService;
        this.localeDataHelper = localeDataHelper;
    }
}
