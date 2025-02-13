package org.example.dependencyanalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DependencyIdentifier {
    private static final Pattern DEPENDENCY_PATTERN = Pattern.compile("require\\S+'(.*?)'");

    public Map<Path, List<Path>> identifyDependencies(List<Path> textFilePaths){

        Map<Path, List<Path>> dependencies = new HashMap<>();

        for (Path textFilePath: textFilePaths){
            List<Path> requiredFiles = new ArrayList<>();
            List<String> lines = null;
            try {
                lines = Files.readAllLines(textFilePath);
            } catch (IOException e) {
                System.out.println("Возникла ошибка при чтении файла: " + e.getMessage());
            }
            for (String line: lines){
                Matcher matcher = DEPENDENCY_PATTERN.matcher(line);
                if (matcher.find()){
                    String requiredPathStr = matcher.group(1);
                    Path requiredPath = Paths.get(requiredPathStr);
                    requiredFiles.add(requiredPath);
                }
            }
            dependencies.put(textFilePath, requiredFiles);
        }
        return dependencies;
    }
}
