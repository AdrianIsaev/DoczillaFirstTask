package org.example.sorting;

import org.example.exceptions.CyclicDependencyException;

import java.nio.file.Path;
import java.util.*;

public final class PathSort {
    public List<Path> sort(Map<Path, List<Path>> dependencies) throws CyclicDependencyException {
        List<Path> sortedPaths = new ArrayList<>();
        Map<Path, Integer> dependencyWeights = new HashMap<>();
        List<Path> queue = new ArrayList<>();

        for (Path path: dependencies.keySet()) dependencyWeights.put(path, 0);

        for (Path path: dependencies.keySet()){
            for (Path dependency: dependencies.get(path)){
                dependencyWeights.put(path, dependencyWeights.get(path) + 1);
            }
        }

        Iterator<Map.Entry<Path, Integer>> iterator = dependencyWeights.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Path, Integer> entry = iterator.next();
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
                iterator.remove();
            }
        }

        while (!queue.isEmpty()){
            Path currentPath = queue.remove(0);
            sortedPaths.add(currentPath);

            Iterator<Map.Entry<Path, Integer>> iterator2 = dependencyWeights.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry<Path, Integer> entry = iterator2.next();
                List<Path> pathDependencies = dependencies.get(entry.getKey());
                if (pathDependencies != null) {
                    for (Path researchPath : pathDependencies) {
                        if (researchPath.equals(currentPath)) {
                            dependencyWeights.put(entry.getKey(), dependencyWeights.get(entry.getKey()) - 1);
                            if (dependencyWeights.get(entry.getKey()) == 0) {
                                queue.add(entry.getKey());
                                iterator2.remove();
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (dependencyWeights.size() > 0) {
            throw new CyclicDependencyException("Обнаружена циклическая зависимость");
        }

        return sortedPaths;
    }

//    public static void main(String[] args) throws CyclicDependencyException{
//        Map<Path, List<Path>> map = new HashMap<>();
//        map.put(Paths.get("Folder 1/File 1-1"), new ArrayList<>(){{add(Paths.get("Folder 2/File 2-1"));}});
//        map.put(Paths.get("Folder 2/File 2-1"),new ArrayList<>());
//        map.put(Paths.get("Folder 2/File 2-2"),new ArrayList<>(){{add(Paths.get("Folder 1/File 1-1"));
//        add(Paths.get("Folder 2/File 2-1"));}});
//        System.out.println(sort(map));
//    }
}
