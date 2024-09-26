public class GenerateArithmeticProblems {
    /**
     *  在程序运行之前应该使用参数限定程序中的表达式数量以及数值的范围
     *  生成的四则运算中至多有三个数做运算，最多有两个运算符，可以分成两种情况，一种是三个数，一种是两个数
     *  对于两个数的情况，只需要随机生成一个运算符，随机填入两个数值，数值中，正数应该居多，分数次之，采用不同权重生成数值
     *  对于三个数的情况，需要随机生成三个数字，生成两个运算符
     *  需要随机填入左右括号，左右括号要填的有意义，包含1,2或者2,3的位置
     *  生成分数时分成两种情况，一种填入两个，一种填入三个数值，左边的自然数，右边的分子分母，一样可以调用生成自然数的函数
     *  随机生成完成后，将生成的数值，运算符集合起来形成表达式
     *  计算表达式结果
     *
     *  剔除结果为负数的表达式和重复的表达式
     *  验证表达式答案，负数答案的表达式丢弃，为正数时同时保存答案信息
     *  将表达式放进HashSet中，每次都查重验证，当生成的是+运算符时，还要多加一步验证是运算符左右两边表达式相同的情况
     *  不重复的表达式写进生成的文档中，当做最后的生成结果
     */

    // 该函数会随机生成一个范围内的正数并返回
    public static int generateNaturalNumber(int range) {

    }

    // 该函数完成生成一个表达式的功能
    public static void generateOne(int range) {

    }

    // 从命令行中读取两个参数，使用 -n 参数控制生成题目的个数，使用 -r 参数控制题目中数值的范围
    public static void main(String[] args) {
        int number;
        int range;
        if(args[0].equals("-n"))
        {
            if(Integer.valueOf(args[1]) > 0)
            {
                number = Integer.valueOf(args[1]);
                if (args[2].equals("-r"))
                {
                    if(Integer.valueOf(args[3]) > 0)
                    {
                        range = Integer.valueOf(args[3]);
                    } else {
                        System.out.println("请使用正数！");
                        return;
                    }
                } else {
                    System.out.println("请使用 -r 参数设置生成的题目中数值的范围");
                }
            } else {
                System.out.println("请使用正数！");
                return;
            }
        } else {
            System.out.println("请使用 -n 参数设置生成的表达式数量");
            return;
        }

        // 在这个 for 循环中，会循环生成 n 个表达式，查重后将表达式写入文件中
        for(int i = 0;i < number;i++)
        {

        }

    }


}
