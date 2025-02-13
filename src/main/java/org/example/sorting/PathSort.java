package org.example.sorting;

import org.example.exceptions.CyclicDependencyException;

import java.nio.file.Path;
import java.util.*;

public final class PathSort {
    public List<Path> sort(Map<Path, List<Path>> dependencies) throws CyclicDependencyException {
        List<Path> sortedPaths = new ArrayList<>();
        Map<Path, Integer> dependencyWeights = new HashMap<>();
        List<Path> queue = new ArrayList<>();

        Map<Path, List<Path>> storage = new HashMap<>();

        for (Path path: dependencies.keySet()) {
            dependencyWeights.put(path, 0);
            storage.put(path, new ArrayList<>());
        }

        for (Path path: dependencies.keySet()){
            for (Path dependency: dependencies.get(path)){

                dependencyWeights.put(path, dependencyWeights.get(path) + 1);

                List<Path> storageDependencyList = storage.get(path);
                storageDependencyList.add(dependency);
                storage.put(path, storageDependencyList);
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

                            List<Path> storageDependencies = storage.get(entry.getKey());
                            storageDependencies.remove(researchPath);
                            storage.put(entry.getKey(), storageDependencies);


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

        storage.entrySet().removeIf(entry -> entry.getValue().isEmpty());
        System.out.println();
        System.out.println(sortedPaths);

        if (dependencyWeights.size() > 0) {
            List<String> cyclingDependencies = new ArrayList<>();
            for (Map.Entry<Path, List<Path>> entry: storage.entrySet()){
                cyclingDependencies.add("Файл: " + entry.getKey() + " зависит от " + entry.getValue().toString());
            }
            throw new CyclicDependencyException("Обнаружена циклическая зависимость " + cyclingDependencies);
        }

        return sortedPaths;
    }
}
