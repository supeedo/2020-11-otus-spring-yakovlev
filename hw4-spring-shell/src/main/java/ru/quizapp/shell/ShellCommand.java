package ru.quizapp.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.service.ExamTicketService;
import ru.quizapp.service.StudentRegistrationService;
import ru.quizapp.utils.LocaleDataHelper;

@ShellComponent
public class ShellCommand {

    private static final Logger logger = LoggerFactory.getLogger(ShellCommand.class);

    private final ExamTicketService questionService;
    private final StudentRegistrationService studentRegistration;
    private final LocaleDataHelper localeDataHelper;

    private StudentDTO student;
    private ExaminationDTO examination;

    @Autowired
    public ShellCommand(@Qualifier("examTicketServiceImpl") ExamTicketService questionService,
                        @Qualifier("studentRegistrationServiceImpl") StudentRegistrationService studentRegistration,
                        LocaleDataHelper localeDataHelper) {
        logger.info("Class initialization");
        this.questionService = questionService;
        this.studentRegistration = studentRegistration;
        this.localeDataHelper = localeDataHelper;
    }

    @ShellMethod(value = "Student registration", key = {"reg", "registration"})
    public String register() {
        this.student = studentRegistration.studentRegistration();
        logger.info("Student registered = {}", student);
        return String.format(localeDataHelper.getLocaleMessage("shell.greeting"), student);
    }

    @ShellMethod(value = "Taking an exam", key = {"start", "exam"})
    public String exam() {
        this.examination = questionService.studentTesting(student);
        logger.info("Testing is over = {}", examination);
        return localeDataHelper.getLocaleMessage("shell.exam.finish");
    }

    @ShellMethod(value = "Results of the conducted exam", key = {"result", "outcome"})
    public String result() {
        logger.info("Output of test results");
        questionService.resultsOfTheConductedTesting(examination);
        return localeDataHelper.getLocaleMessage("shell.exam.result");
    }

    @ShellMethodAvailability({"start", "exam"})
    public Availability isExamAvailable() {
        logger.info("Verification that the student is registered and testing can begin");
        if (student != null) {
            logger.debug("You can start the exam");
            return Availability.available();
        } else {
            logger.debug("Student registration failed");
            return Availability.unavailable(localeDataHelper.getLocaleMessage("shell.error.exam"));
        }
    }

    @ShellMethodAvailability({"result", "outcome"})
    public Availability isResultAvailable() {
        logger.info("Checking that testing has been carried out and you can display the result");
        if (examination != null) {
            logger.debug("Testing is over, the result can be displayed");
            return Availability.available();
        } else {
            logger.debug("Testing not done");
            return Availability.unavailable(localeDataHelper.getLocaleMessage("shell.error.result"));
        }
    }
}
