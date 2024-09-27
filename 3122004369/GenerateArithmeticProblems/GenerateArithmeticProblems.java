import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class GenerateArithmeticProblems {
    /**
     *  �ڳ�������֮ǰӦ��ʹ�ò����޶������еı��ʽ�����Լ���ֵ�ķ�Χ
     *
     *  ��ֵ�У�����Ӧ�þӶ࣬������֮�����ò�ͬȨ��������ֵ
     *  ���ɷ���ʱһ������������ֵ����ߵ���Ȼ�����ұߵķ��ӷ�ĸ��һ�����Ե���������Ȼ���ĺ���
     *
     *  ���ɱ��ʽʱ��Ӧ����ȷ����������������������Ȳ�������һ������ȷ�����ŵ�λ��
     *  ������������Ϊ2,3,4�����������Ϊ1,2,3 �ո���Ҫ���������ǰ�����ŵ�λ��Ӧ��Ϊ��������ӵĲ��������ߣ�ֻ����2,3��������ʱ����Ҫ����
     *  2�������ʱ���Լ���0,1 ��������1,2 �������ϣ�3����������Լ���0,1  1,2  2,3  0,2  1,3  0,3  �����λ�ã���Ȼʹ��������������λ��
     *  ��󽫱��ʽ���������������
     *  1 + 2 + 3 + 4
     *
     *  ������ʽ���
     *  �޳����Ϊ�����ı��ʽ���ظ��ı��ʽ
     *  ��֤���ʽ�𰸣������𰸵ı��ʽ������Ϊ����ʱͬʱ�������Ϣ
     *  �����ʽ�Ž�HashSet�У�ÿ�ζ�������֤�������ɵ���+�����ʱ����Ҫ���һ����֤��������������߱��ʽ��ͬ�����
     *  ���ظ��ı��ʽд�����ɵ��ĵ��У������������ɽ��
     */
    static int culculate = 1;
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
            while(denominator != 0) denominator = generateNaturalNumber(range);
            fraction oneFraction = new fraction(generateNaturalNumber(range), denominator, generateNaturalNumber(range));
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
        }
        Random random = new Random();
        if (operatorNumber == 2) {
            times = random.nextInt(2);
        } else {
            times = random.nextInt(6);
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
                locate[0] = 0;
                locate[1] = 2;
                break;
            case 4:
                locate[0] = 1;
                locate[1] = 3;
                break;
            case 5:
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
        System.out.println(String.valueOf(culculate++) + "." + sb.toString());
        return sb.toString();
    }


    // �ú�����ɶԱ��ʽ�Ĳ��غ���⣬���ظ�������Ϊ�����򷵻� flase�����򷵻� true
    // ������д��Answers.txt�ļ������ʽд�������б�
    public static boolean check(String oneQuestion)
    {
        return true;
    }

    // ���������ж�ȡ����������ʹ�� -n ��������������Ŀ�ĸ�����ʹ�� -r ����������Ŀ����ֵ�ķ�Χ
    public static void main(String[] args) {
        int number = 1;
        int range = 1;
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
        // ����� for ѭ���У���ѭ������ n �����ʽ�����غ󽫱��ʽд���ļ���
        for(int i = 0;i < number;i++)
        {
            String oneQuestion = generateOne(range);
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
        while(this.numerator > this.denominator) {
            this.numerator -= this.denominator;
            this.natural++;
        }
    }
}
