package org.example.filescanning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TextFilePathScanner {

    private final RootDirectoryValidator rootDirectoryValidator;

    public TextFilePathScanner(RootDirectoryValidator rootDirectoryValidator) {
        this.rootDirectoryValidator = rootDirectoryValidator;
    }

    public List<Path> scanRootDirectory(String rootDirectoryStringPath) {

        List<Path> textFilePaths = new ArrayList<>();

        Path rootDirectoryPath = Paths.get(rootDirectoryStringPath);
        rootDirectoryValidator.validateRootDirectory(rootDirectoryPath);

        try (Stream<Path> pathStream = Files.walk(rootDirectoryPath)) {
            textFilePaths = pathStream.filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Возникла ошибка при сканировании директории: " + e.getMessage());
        }
        return textFilePaths;
    }
}
