package ru.quizapp.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.service.ExamTicketService;
import ru.quizapp.service.StudentRegistrationService;

@ShellComponent
public class ShellCommand {

    private static final Logger logger = LoggerFactory.getLogger(ShellCommand.class);
    private final ExamTicketService questionService;
    private final StudentRegistrationService studentRegistration;
    private StudentDTO student;
    private ExaminationDTO examination;

    @Autowired
    public ShellCommand(ExamTicketService questionService, StudentRegistrationService studentRegistration) {
        this.questionService = questionService;
        this.studentRegistration = studentRegistration;
    }

    @ShellMethod(value = "Регистрация студента", key = {"reg", "registration"})
    public String register(){
         this.student = studentRegistration.studentRegistration();
         return "Приветствие";
    }

    @ShellMethod(value = "Проведение экзамена", key = {"start", "exam"})
    public String exam(){
         this.examination = questionService.studentTesting(student);
         return "Окончание экзамена";
    }

    @ShellMethod(value = "Результаты проведенного экзамена", key = {"result", "outcome"})
    public String result(){
        questionService.resultsOfTheConductedTesting(examination);
        return "Exit";
    }

    @ShellMethodAvailability({"start", "exam"})
    public Availability isExamAvailable(){
      if(student != null){
          return Availability.available();
      }else{
          return Availability.unavailable("Для начала тестирования необходимо зарегистрироваться!");
      }
    }

    @ShellMethodAvailability({"result", "outcome"})
    public Availability isResultAvailable(){
        if(examination != null){
            return Availability.available();
        }else{
            return Availability.unavailable("Для получения результата тестирования, необходимо его завершить!");
        }
    }
}
