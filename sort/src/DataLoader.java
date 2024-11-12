import java.io.*;
import java.util.*;

public class DataLoader {
    public static int[] loadData(String filePath) throws IOException {
        List<Integer> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.matches("-?\\d+")) {
                    dataList.add(Integer.parseInt(line));
                }
            }
        }
        return dataList.stream().mapToInt(i -> i).toArray();
    }
}
