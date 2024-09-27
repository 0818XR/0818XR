import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class GenerateArithmeticProblems {
    /**
     *  在程序运行之前应该使用参数限定程序中的表达式数量以及数值的范围
     *
     *  数值中，正数应该居多，分数次之，采用不同权重生成数值
     *  生成分数时一共填入三个数值，左边的自然数，右边的分子分母，一样可以调用生成自然数的函数
     *
     *  生成表达式时，应该先确定操作符的数量，运算符比操作符少一个，并确定括号的位置
     *  操作符的数量为2,3,4，运算符数量为1,2,3 空格需要放在运算符前后，括号的位置应该为运算符链接的操作符两边，只有在2,3个操作符时才需要括号
     *  2个运算符时可以加在0,1 操作数或1,2 操作数上，3个运算符可以加在0,1  1,2  2,3  0,2  1,3  0,3  这五个位置，依然使用随机数生成随机位置
     *  最后将表达式连接起来生成完毕
     *  1 + 2 + 3 + 4
     *
     *  计算表达式结果
     *  剔除结果为负数的表达式和重复的表达式
     *  验证表达式答案，负数答案的表达式丢弃，为正数时同时保存答案信息
     *  将表达式放进HashSet中，每次都查重验证，当生成的是+运算符时，还要多加一步验证是运算符左右两边表达式相同的情况
     *  不重复的表达式写进生成的文档中，当做最后的生成结果
     */
    static int culculate = 1;
    // 该函数会随机生成一个范围内的正数并返回
    public static int generateNaturalNumber(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    // 该函数会随机生成一个运算符并返回
    public static String generateOperator() {
        Random random = new Random();
        int operatorIndex = random.nextInt(4);
        switch (operatorIndex) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "×";
            default:
                return "÷";
        }
    }

    // 在这个函数中可以设置真分数和正数的生成概率
    // 我们认为小学四则运算应该更注重正数，因此调整了正数的生成概率为 2/3
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
    // 在这个函数中我们遇到了这个问题：GenerateArithmeticProblems.this' cannot be referenced from a static context
    // 查询资料后了解到这是因为 fraction是一个非静态的内部类，而该函数是静态的，要么方法非静态，要么把内部类移出去，本程序采用后者

    // 在测试过程中发现生成随机数时0有可能被当做分母，因此特地先赋值分母后再创建分数

    // 该函数用于确定括号的范围
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

    // 该函数完成生成一个表达式的功能
    public static String generateOne(int range) {
        String space = " ", equal = " = ";
        String expression;
        Queue<String> queue = new LinkedList<String>();

        // 首先确定操作数和运算符的数量
        Random random = new Random();
        int operandNumber = random.nextInt(3) + 2;
        int operatorNumber = operandNumber - 1;

        // 循环开始前确定括号的范围
        int[] locate = returnLocated(operatorNumber);

        // 先生成一个数，在循环中生成表达式
        String firstNumber = returnOneNumber(range);
        if(locate[0] == 0) {
            queue.offer("(");
        }
        queue.offer(firstNumber);

        // for之前第一个数已经进队列，for 中依次进入操作符，下一个数 操作符两边有空格
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


    // 该函数完成对表达式的查重和求解，若重复，或者为负数则返回 flase，否则返回 true
    // 并将答案写进Answers.txt文件，表达式写入生成列表
    public static boolean check(String oneQuestion)
    {
        return true;
    }

    // 从命令行中读取两个参数，使用 -n 参数控制生成题目的个数，使用 -r 参数控制题目中数值的范围
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
                        System.out.println("请使用正数！");
                        return;
                    }
                } else {
                    System.out.println("请使用 -r 参数设置生成的题目中数值的范围");
                    return;
                }
            } else {
                System.out.println("请使用正数！");
                return;
            }
        } else {
            System.out.println("请使用 -n 参数设置生成的表达式数量");
            return;
        }

        System.out.println("正在生成中");
        // 在这个 for 循环中，会循环生成 n 个表达式，查重后将表达式写入文件中
        for(int i = 0;i < number;i++)
        {
            String oneQuestion = generateOne(range);
            while (!check(oneQuestion))
            {
                oneQuestion = generateOne(range);
            }
        }
        System.out.println("生成完毕！");
    }
}

// 下面是一个分数类，重写了toString方法
class fraction {
    int natural;        // 整数
    int numerator;      // 分子
    int denominator;    // 分母
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
