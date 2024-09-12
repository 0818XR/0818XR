import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class PlagiarismChecker {

    // 读取文件内容
    private static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // 预处理文本：去除标点符号和多余的空格，转换为小写
    private static String preprocessText(String text) {
        return text.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
    }

    // 计算文本相似度
    private static double calculateSimilarity(String text1, String text2) {
        // 分词处理
        List<String> words1 = Arrays.asList(preprocessText(text1).split("\\s+"));
        List<String> words2 = Arrays.asList(preprocessText(text2).split("\\s+"));

        // 统计词频
        Map<String, Long> wordCount1 = words1.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        Map<String, Long> wordCount2 = words2.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        // 计算交集的词频
        Set<String> commonWords = new HashSet<>(wordCount1.keySet());
        commonWords.retainAll(wordCount2.keySet());
        long commonCount = commonWords.stream()
                .mapToLong(word -> Math.min(wordCount1.get(word), wordCount2.get(word)))
                .sum();

        // 计算重复率
        long totalWords = words1.size();
        if (totalWords == 0) {
            return 0;
        }
        return (commonCount / (double) totalWords) * 100;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("用法: java PlagiarismChecker <原论文路径> <抄袭论文路径> <输出文件路径>");
            System.exit(1);
        }

        String originalPath = args[0];
        String plagiarizedPath = args[1];
        String outputPath = args[2];

        try {
            // 读取文件
            String originalText = readFile(originalPath);
            String plagiarizedText = readFile(plagiarizedPath);

            // 计算重复率
            double similarity = calculateSimilarity(originalText, plagiarizedText);

            // 输出结果到文件
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                writer.write(String.format("原论文与抄袭论文的重复率为: %.2f%%\n", similarity));
            }

            System.out.println("重复率已保存到 " + outputPath);
        } catch (IOException e) {
            System.err.println("文件处理出现错误: " + e.getMessage());
        }
    }
}
