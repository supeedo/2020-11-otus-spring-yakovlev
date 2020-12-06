package ru.quizapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.quizapp.controller.ExaminationController;
import ru.quizapp.controller.ExaminationControllerImpl;
import ru.quizapp.repository.ExamTicketRepository;
import ru.quizapp.repository.ExamTicketRepositoryImpl;
import ru.quizapp.service.ExamTicketService;
import ru.quizapp.service.ExamTicketServiceImpl;

@Configuration
@PropertySource("classpath:appConfig.properties")
public class AppConfiguration {

    @Bean
    public ExaminationController examinationController(ExamTicketService questionService){
        return new ExaminationControllerImpl(questionService);
    }

    @Bean
    public ExamTicketService questionService(ExamTicketRepository repository){
        return new ExamTicketServiceImpl(repository);
    }

    @Bean
    public ExamTicketRepository examTicketRepository(@Value("${db.url}")String dataLink){
        return new ExamTicketRepositoryImpl(dataLink);
    }

}
