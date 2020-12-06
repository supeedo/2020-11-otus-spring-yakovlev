package ru.quizapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);

    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static int readOptionAnswerQuestionWithVerification(int answerCount) throws IOException {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            String bufferReadString = reader.readLine();
            try {
                int buf = Integer.parseInt(bufferReadString);
                if (buf <= answerCount && buf > 0) {
                  result = buf;
                  break;
                }else{
                    ConsoleHelper.writeMessage("Ошибка!!! Такого варианта ответа нет! Поробуйте еще раз.");
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Ошибка!!! Необходимо ввести номер одного из вариантов ответа! Попробуйте еще раз!");
            }
        }
        return result;
    }

}