package ru.quizapp.shell;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.quizapp.service.IOServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
class ShellCommandTest {

    private static final String FIRST_NAME_STUDENT = "Test-First-Name";
    private static final String LAST_NAME_STUDENT = "Test-Last-Name";
    private static final String GREETING_PATTERN = "Приветствуем, %s!";
    private static final String COMMAND_REG = "registration";
    private static final String SHORT_COMMAND_REG = "reg";

    @Autowired
    private Shell shell;

    @MockBean
    private IOServiceImpl consoleHelper;

    @Test
    void checkGreetings() {
        Mockito.when(consoleHelper.readString()).thenReturn(FIRST_NAME_STUDENT, LAST_NAME_STUDENT);
        String res = (String) shell.evaluate(() -> COMMAND_REG);
        assertThat(res).isEqualTo(String.format(GREETING_PATTERN, String.format("%s %s", FIRST_NAME_STUDENT, LAST_NAME_STUDENT)), "Greetings are different");
    }

    @Test
    void checkGreetingsShort() {
        Mockito.when(consoleHelper.readString()).thenReturn(FIRST_NAME_STUDENT, LAST_NAME_STUDENT);
        String res = (String) shell.evaluate(() -> SHORT_COMMAND_REG);
        assertThat(res).isEqualTo(String.format(GREETING_PATTERN, String.format("%s %s", FIRST_NAME_STUDENT, LAST_NAME_STUDENT)), "Greetings are different");
    }
}