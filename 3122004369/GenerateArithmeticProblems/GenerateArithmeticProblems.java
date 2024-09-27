import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class GenerateArithmeticProblems {
    /**
     *  �ڳ�������֮ǰӦ��ʹ�ò����޶������еı��ʽ�����Լ���ֵ�ķ�Χ
     *
     *  ��ֵ�У�����Ӧ�þӶ࣬������֮�����ò�ͬȨ��������ֵ
     *  ���ɷ���ʱһ������������ֵ����ߵ���Ȼ�����ұߵķ��ӷ�ĸ��һ�����Ե���������Ȼ���ĺ���
     *
     *  ���ɱ��ʽʱ��Ӧ����ȷ����������������������Ȳ�������һ������ȷ�����ŵ�λ��
     *  ������������Ϊ2,3,4�����������Ϊ1,2,3 �ո���Ҫ���������ǰ�����ŵ�λ��Ӧ��Ϊ��������ӵĲ��������ߣ�ֻ����2,3��������ʱ����Ҫ����
     *  2�������ʱ���Լ���0,1 ��������1,2 �������ϣ�3����������Լ���0,1  1,2  2,3  0,2  1,3  �����λ�ã���Ȼʹ��������������λ��
     *  ��󽫱��ʽ���������������
     *  1 + 2 + 3 + 4
     *
     *  ������ʽ���
     *  �޳����Ϊ�����ı��ʽ���ظ��ı��ʽ
     *  ��֤���ʽ�𰸣������𰸵ı��ʽ������Ϊ����ʱͬʱ�������Ϣ
     *  �����ʽ�Ž�HashSet�У�ÿ�ζ�������֤�������ɵ���+�����ʱ����Ҫ���һ����֤��������������߱��ʽ��ͬ�����
     *  ���ظ��ı��ʽд�����ɵ��ĵ��У������������ɽ��
     */
    static int culculate = 1; // ������
    // �ú������������һ����Χ�ڵ�����������
    public static int generateNaturalNumber(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    // �ú������������һ�������������
    public static String generateOperator() {
        Random random = new Random();
        int operatorIndex = random.nextInt(4);
        switch (operatorIndex) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "��";
            default:
                return "��";
        }
    }

    // ����������п�����������������������ɸ���
    // ������ΪСѧ��������Ӧ�ø�ע����������˵��������������ɸ���Ϊ 2/3
    public static String returnOneNumber(int range) {
        Random random = new Random();
        if (random.nextInt(3) == 0) {
            int denominator = generateNaturalNumber(range);
            while(denominator == 0) denominator = generateNaturalNumber(range);
            fraction oneFraction = new fraction(generateNaturalNumber(range), generateNaturalNumber(range), denominator);
            return oneFraction.toString();
        }
        return String.valueOf(generateNaturalNumber(range));
    }
    // ���������������������������⣺GenerateArithmeticProblems.this' cannot be referenced from a static context
    // ��ѯ���Ϻ��˽⵽������Ϊ fraction��һ���Ǿ�̬���ڲ��࣬���ú����Ǿ�̬�ģ�Ҫô�����Ǿ�̬��Ҫô���ڲ����Ƴ�ȥ����������ú���
    // �ڲ��Թ����з������������ʱ0�п��ܱ�������ĸ������ص��ȸ�ֵ��ĸ���ٴ�������

    // �ú�������ȷ�����ŵķ�Χ
    public static int[] returnLocated(int operatorNumber) {
        int[] locate = new int[2];
        int times;
        if (operatorNumber == 1) {
            locate[0] = 5;
            locate[1] = 5;
            return locate;
        }
        Random random = new Random();
        if (operatorNumber == 2) {
            times = random.nextInt(2);
        } else {
            times = random.nextInt(5);
        }
        switch (times) {
            case 0:
                locate[1] = 1;
                break;
            case 1:
                locate[0] = 1;
                locate[1] = 2;
                break;
            case 2:
                locate[0] = 2;
                locate[1] = 3;
                break;
            case 3:
                locate[1] = 2;
                break;
            case 4:
                locate[0] = 1;
                locate[1] = 3;
        }
        return locate;
    }

    // �ú����������һ�����ʽ�Ĺ���
    public static String generateOne(int range) {
        String space = " ", equal = " = ";
        String expression;
        Queue<String> queue = new LinkedList<String>();

        // ����ȷ���������������������
        Random random = new Random();
        int operandNumber = random.nextInt(3) + 2;
        int operatorNumber = operandNumber - 1;

        // ѭ����ʼǰȷ�����ŵķ�Χ
        int[] locate = returnLocated(operatorNumber);

        // ������һ��������ѭ�������ɱ��ʽ
        String firstNumber = returnOneNumber(range);
        if(locate[0] == 0) {
            queue.offer("(");
        }
        queue.offer(firstNumber);

        // for֮ǰ��һ�����Ѿ������У�for �����ν������������һ���� �����������пո�
        for (int i = 1; i < operandNumber; i++) {
            queue.offer(space);
            String newOperator = generateOperator();
            queue.offer(newOperator);
            queue.offer(space);
            if(locate[0] == i) queue.offer("(");
            String newNumber = returnOneNumber(range);
            queue.offer(newNumber);
            if (locate[1] == i) queue.offer(")");
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        sb.append(equal);
        // System.out.println(String.valueOf(culculate++) + "." + sb);
        return sb.toString();
    }


    // �ú�����ɶԱ��ʽ�Ĳ��غ���⣬���ظ�������Ϊ�����򷵻� false , ���򷵻� true
    // �����ʽд����ǰ�ļ�������Ϊ Exercises.txt ���ļ���
    // ������д��Answers.txt�ļ������ʽд�������б�
    public static boolean check(String oneQuestion)
    {
        // ���ʽ��ֵ ���� 5 - (2'1/5 �� 5'2/6 + 6) = ���ַ���
        // �� x ת��Ϊ * �� �� ת��Ϊ / �Ѻ���� = ȥ�������������
        String newString = oneQuestion.replace("��", "*").replace("��", "/").substring(0, oneQuestion.length()-3);

        double ans = FractionExpressionEvaluator.evaluate(newString);
        if (ans < 0) return false;

        // д����Ŀ
        String filePath = "Exercises.txt"; // �ļ�·��
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(culculate + ". " + oneQuestion); // д������
        } catch (IOException e) {
            e.printStackTrace(); // �����쳣
        }
        // д���
        String filePath2 = "Answers.txt"; // �ļ�·��
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath2, true))) {
            writer.println(culculate++ + ". " + ans); // д������
        } catch (IOException e) {
            e.printStackTrace(); // �����쳣
        }
        return true;
    }

    // ���������ж�ȡ����������ʹ�� -n ��������������Ŀ�ĸ�����ʹ�� -r ����������Ŀ����ֵ�ķ�Χ
    public static void main(String[] args) {
        int number = 1;
        int range = 1;

        // �жϲ����ĺϷ���
        if(args[0].equals("-n"))
        {
            if(Integer.parseInt(args[1]) > 0)
            {
                number = Integer.parseInt(args[1]);
                if (args[2].equals("-r"))
                {
                    if(Integer.parseInt(args[3]) > 0)
                    {
                        range = Integer.parseInt(args[3]);
                    } else {
                        System.out.println("��ʹ��������");
                        return;
                    }
                } else {
                    System.out.println("��ʹ�� -r �����������ɵ���Ŀ����ֵ�ķ�Χ");
                    return;
                }
            } else {
                System.out.println("��ʹ��������");
                return;
            }
        } else {
            System.out.println("��ʹ�� -n �����������ɵı��ʽ����");
            return;
        }

        System.out.println("����������");
        HashSet<String> set = new HashSet<>();
        // ����� for ѭ���У���ѭ������ n �����ʽ�����غ󽫱��ʽд���ļ���
        for(int i = 0;i < number;i++)
        {
            String oneQuestion = generateOne(range);
            while(set.contains(oneQuestion)){
                oneQuestion = generateOne(range);
            }
            set.add(oneQuestion);
            while (!check(oneQuestion))
            {
                oneQuestion = generateOne(range);
            }
        }
        System.out.println("������ϣ�");
    }
}

// ������һ�������࣬��д��toString����
class fraction {
    int natural;        // ����
    int numerator;      // ����
    int denominator;    // ��ĸ
    fraction(int natural, int numerator, int denominator) {
        this.natural = natural;
        this.numerator = numerator;
        this.denominator = denominator;
    }
    @Override
    public String toString(){
        ConvertToTrueFractions();
        if (this.natural == 0) return numerator + String.valueOf(denominator);
        else return natural + "'" + numerator + "/" + denominator;
    }
    public void ConvertToTrueFractions(){
        while(this.numerator >= this.denominator) {
            this.numerator -= this.denominator;
            this.natural++;
        }
    }
}

class FractionExpressionEvaluator {

    public static double evaluate(String expression) {
        expression = expression.replace("'", " "); // �滻������ʽ
        String[] tokens = expression.split(" ");
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            if (isNumeric(token)) {
                values.push(parseValue(token));
            } else if (token.equals("(")) {
                operators.push('(');
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // ���� '('
            } else { // ������
                while (!operators.isEmpty() && hasPrecedence(token.charAt(0), operators.peek())) {
                    if (values.size() < 2) break; // ��ֹջ��
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token.charAt(0));
            }
        }

        while (!operators.isEmpty()) {
            if (values.size() < 2) break; // ��ֹջ��
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return values.isEmpty() ? 0 : values.pop(); // �������ս��
    }

    private static double parseValue(String token) {
        if (token.contains("/")) {
            String[] parts = token.split("/");
            return (double) Integer.parseInt(parts[0]) / Integer.parseInt(parts[1]);
        } else if (token.contains(" ")) {
            String[] parts = token.split(" ");
            int whole = Integer.parseInt(parts[0]);
            String[] fractionParts = parts[1].split("/");
            double fraction = (double) Integer.parseInt(fractionParts[0]) / Integer.parseInt(fractionParts[1]);
            return whole + fraction;
        } else {
            return Double.parseDouble(token);
        }
    }

    private static boolean isNumeric(String token) {
        return token.matches("-?\\d+( \\d+\\d+)?");
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        return !(op1 == '*' || op1 == '/') || (op2 != '*' && op2 != '/');
    }

    private static double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }
}
