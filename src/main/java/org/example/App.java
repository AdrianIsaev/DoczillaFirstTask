package org.example;

import org.example.concatenation.FileConcatenator;
import org.example.dependencyanalyzer.DependencyIdentifier;
import org.example.exceptions.CyclicDependencyException;
import org.example.exceptions.InvalidInputParametersException;
import org.example.filescanning.RootDirectoryValidator;
import org.example.filescanning.TextFilePathScanner;
import org.example.sorting.PathSort;
import org.example.validation.SystemInputValidator;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {
        try {
            SystemInputValidator.validateInput(args);
        }
        catch (InvalidInputParametersException e){
            System.err.println(e.getMessage());
        }

        ApplicationManager applicationManager = new ApplicationManager(new TextFilePathScanner(new RootDirectoryValidator()),
                new DependencyIdentifier(), new PathSort(), new FileConcatenator());



        try{
            applicationManager.executeMainAlgorithm(args[0], args[1]);
            System.out.println("Успешно создан файл: " + args[1]);
        }
        catch (CyclicDependencyException e){
            System.err.println(e.getMessage());
        }
    }
}
