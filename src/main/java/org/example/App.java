package org.example;

/**
 * Hello world!
 */
public final class App {
    private static final String INVALID_INPUT_PARAMETERS_MESSAGE = "Неправильно использованы параметры ввода. Команда для запуска приложения: java -cp target/DoczillaFirstTask-1.0-SNAPSHOT.jar org.example.App \"абсолютный путь итогового файла\" ";

    public static void main(String[] args) {
        validateSystemInput(args);
    }


    private static void validateSystemInput(String[] args){
        if (args.length!=2) {
            System.out.println(INVALID_INPUT_PARAMETERS_MESSAGE);
        }
    }
}
