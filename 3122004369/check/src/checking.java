import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

// ������ʹ��Jaccard���ƶ�������������ƶȵļ�Java����
// Jaccard���ƶȼ�������������ϵĽ����벢���ı���
// ���ǽ��ı��ָ�ɵ��ʣ�Ȼ�������Щ���ʵ�Jaccard���ƶ�

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
        System.out.println("���ƶȣ�" + similarity);
        System.setOut(SysOut);
    }

    private static double calculateJaccardSimilarity(String text1, String text2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(text1.toLowerCase().split("\\s+")));
        Set<String> set2 = new HashSet<>(Arrays.asList(text2.toLowerCase().split("\\s+")));

        // ȥ��������
        set1.removeIf(word -> word.matches("\\p{Punct}"));
        set2.removeIf(word -> word.matches("\\p{Punct}"));

        // ���㽻���Ͳ���
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        // ���ռ�����Ϊ������С���Բ�����С�õ����ƶ�
        return (double) intersection.size() / union.size();
    }

    public static String readFileToString(String filePath) throws IOException {
        // ʹ��StringBuilder���������º���
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
