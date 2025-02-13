package org.example.validation;

import org.example.exceptions.InvalidInputParametersException;

public final class SystemInputValidator {
    private static final String INVALID_INPUT_PARAMETERS_MESSAGE = "Неправильно использованы параметры ввода. Команда для запуска приложения: java -cp target/DoczillaFirstTask-1.0-SNAPSHOT.jar org.example.App \"корневая директория\" \"абсолютный путь итогового файла\" ";
    public static void validateInput(String[] args) throws InvalidInputParametersException {
        if (args.length != 2){
            throw new InvalidInputParametersException(INVALID_INPUT_PARAMETERS_MESSAGE);
        }
    }
}
