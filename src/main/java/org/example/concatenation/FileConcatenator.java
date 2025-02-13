package org.example.concatenation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class FileConcatenator {
    public void concatenateFiles(List<Path> sortedPaths, String outputFileName){
        Path outputPath = Paths.get(outputFileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath)) {
            for (Path path: sortedPaths){
                List<String> lines = Files.readAllLines(path);
                for (String line:lines){
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
        }
        catch (IOException e){
            System.err.println("Возникла ошибка при записи в файл: " + e.getMessage());
        }
    }
}
