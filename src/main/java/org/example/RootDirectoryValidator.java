package org.example;

import org.example.exceptions.InvalidRootDirectoryException;

import java.nio.file.Files;
import java.nio.file.Path;

public final class RootDirectoryValidator {
    public void validateRootDirectory(Path rootDirectoryPath) throws InvalidRootDirectoryException {
        if (!Files.exists(rootDirectoryPath)) throw new InvalidRootDirectoryException("Указанная директория не существует");
        if (!Files.isDirectory(rootDirectoryPath)) throw new InvalidRootDirectoryException("Указанный путь не принадлежит директории");
    }
}
