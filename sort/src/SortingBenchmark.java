import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class SortingBenchmark {
    public static long measureExecutionTime(int[] array, String algorithm) {
        int[] copy = array.clone();
        long startTime = System.nanoTime();
        switch (algorithm) {
            case "BubbleSort":
                BubbleSort.sort(copy);
                break;
            case "InsertionSort":
                InsertionSort.sort(copy);
                break;
            case "QuickSort":
                QuickSort.sort(copy);
                break;
        }
        return System.nanoTime() - startTime;
    }

    public static Map<String, Map<String, Long>> runBenchmarks(String[] dataTypes, String[] algorithms) {
        Map<String, Map<String, Long>> results = new HashMap<>();

        for (String dataType : dataTypes) {
            try {
                int[] data = DataLoader.loadData(dataType + ".csv");
                Map<String, Long> algorithmTimes = new HashMap<>();

                for (String algorithm : algorithms) {
                    long executionTime = measureExecutionTime(data, algorithm);
                    algorithmTimes.put(algorithm, executionTime);
                }

                results.put(dataType, algorithmTimes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static void saveResultsToCSV(Map<String, Map<String, Long>> results, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("DataType,Algorithm,ExecutionTime(ns)\n");
            for (String dataType : results.keySet()) {
                Map<String, Long> algorithmTimes = results.get(dataType);
                for (String algorithm : algorithmTimes.keySet()) {
                    long time = algorithmTimes.get(algorithm);
                    writer.write(dataType + "," + algorithm + "," + time + "\n");
                }
            }
            System.out.println("Resultados salvos no arquivo: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
