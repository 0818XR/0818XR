import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

// 下面是使用Jaccard相似度来检测文章相似度的简单Java程序
// Jaccard相似度计算的是两个集合的交集与并集的比率
// 我们将文本分割成单词，然后计算这些单词的Jaccard相似度

public class checking {
    public static void main(String[] args) throws IOException {
        String filePath1 = args[0];
        String filePath2 = args[1];
        String filePath3 = args[2];
        String content1 = readFileToString(filePath1);
        String content2 = readFileToString(filePath2);
        PrintStream out = new PrintStream(Files.newOutputStream(Paths.get(filePath3)));
        PrintStream SysOut = System.out;
        System.setOut(out);
        double similarity = calculateJaccardSimilarity(content1, content2);
        System.out.println("相似度：" + similarity);
        System.setOut(SysOut);
    }

    private static double calculateJaccardSimilarity(String text1, String text2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(text1.toLowerCase().split("\\s+")));
        Set<String> set2 = new HashSet<>(Arrays.asList(text2.toLowerCase().split("\\s+")));

        // 去除标点符号
        set1.removeIf(word -> word.matches("\\p{Punct}"));
        set2.removeIf(word -> word.matches("\\p{Punct}"));

        // 计算交集和并集
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        // 最终计算结果为交集大小除以并集大小得到相似度
        return (double) intersection.size() / union.size();
    }

    public static String readFileToString(String filePath) throws IOException {
        // 使用StringBuilder构建读文章函数
        StringBuilder content = new StringBuilder();
        try (FileReader fr = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }
}
