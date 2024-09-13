import java.io.*;
import java.nio.charset.StandardCharsets;

public class HammingSimilarity {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("�÷��� HammingSimilarity <ԭ�ļ���ַ> <��Ϯ�ļ���ַ> <����ļ���ַ>");
            System.exit(1);
        }

        String originalFile = args[0];
        String plagiarizedFile = args[1];
        String outputFile = args[2];

        try {
            String originalText = readFile(originalFile);
            String plagiarizedText = readFile(plagiarizedFile);

            // ���ı�ת��Ϊ�������ַ���
            String originalBinary = toBinary(originalText);
            String plagiarizedBinary = toBinary(plagiarizedText);

            // ȷ�������������ַ����ĳ�����ͬ
            int maxLength = Math.min(originalBinary.length(), plagiarizedBinary.length());
            String originalBinaryTrimmed = originalBinary.substring(0, maxLength);
            String plagiarizedBinaryTrimmed = plagiarizedBinary.substring(0, maxLength);

            int hammingDistance = computeHammingDistance(originalBinaryTrimmed, plagiarizedBinaryTrimmed);
            double similarity = 1.0 - ((double) hammingDistance / maxLength);

            writeOutput(outputFile, similarity);
            System.out.println("���ƶȽ��д�� " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ��ȡ�ļ�
    private static String readFile(String filePath) throws IOException {
        byte[] bytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    // ���ı�ת��Ϊ�̶����ȵĶ������ַ���
    private static String toBinary(String text) {
        StringBuilder binary = new StringBuilder();
        for (char c : text.toCharArray()) {
            String bin = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binary.append(bin);
        }
        return binary.toString();
    }

    // ���㺺������
    private static int computeHammingDistance(String binary1, String binary2) {
        int distance = 0;
        for (int i = 0; i < binary1.length(); i++) {
            if (binary1.charAt(i) != binary2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    // �����ƶȷ���д������ļ�
    private static void writeOutput(String filePath, double similarity) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.format("���ƶ�: %.2f", similarity));
        }
    }
}
