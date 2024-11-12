import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] algorithms = {"BubbleSort", "InsertionSort", "QuickSort"};
        String[] dataTypes = {"aleatorio_100", "aleatorio_1000", "aleatorio_10000",
                "crescente_100", "crescente_1000", "crescente_10000",
                "decrescente_100", "decrescente_1000", "decrescente_10000"};

        Map<String, Map<String, Long>> results = SortingBenchmark.runBenchmarks(dataTypes, algorithms);

        System.out.println("Resultados por Categoria:");
        String[] categories = {"aleatorio", "crescente", "decrescente"};

        for (String category : categories) {
            System.out.println("\nCategoria: " + category.toUpperCase());
            for (String dataType : dataTypes) {
                if (dataType.startsWith(category)) {
                    System.out.println("Conjunto de dados: " + dataType);
                    Map<String, Long> times = results.get(dataType);
                    for (String algorithm : algorithms) {
                        System.out.printf("   %s: %d ns\n", algorithm, times.get(algorithm));
                    }
                    System.out.println();
                }
            }
        }
    }
}
