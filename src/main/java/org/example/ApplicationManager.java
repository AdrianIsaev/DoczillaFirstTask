package org.example;

import org.example.concatenation.FileConcatenator;
import org.example.dependencyanalyzer.DependencyIdentifier;
import org.example.exceptions.CyclicDependencyException;
import org.example.filescanning.TextFilePathScanner;
import org.example.sorting.PathSort;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public final class ApplicationManager {
    private final TextFilePathScanner textFilePathScanner;
    private final DependencyIdentifier dependencyIdentifier;
    private final PathSort pathSort;
    private final FileConcatenator fileConcatenator;

    public ApplicationManager(TextFilePathScanner textFilePathScanner, DependencyIdentifier dependencyIdentifier,
                              PathSort pathSort, FileConcatenator fileConcatenator){
        this.dependencyIdentifier = dependencyIdentifier;
        this.pathSort = pathSort;
        this.fileConcatenator = fileConcatenator;
        this.textFilePathScanner = textFilePathScanner;
    }

    public void executeMainAlgorithm(String rootDirectory, String outputFileName) throws CyclicDependencyException {
        List<Path> paths = textFilePathScanner.scanRootDirectory(rootDirectory);
        Map<Path, List<Path>> dependencies =  dependencyIdentifier.identifyDependencies(paths);
        List<Path> sortedPaths = pathSort.sort(dependencies);
        fileConcatenator.concatenateFiles(sortedPaths, outputFileName);
    }
}
