import java.util.Random;

public class GenerateArithmeticProblems {
    /**
     *  �ڳ�������֮ǰӦ��ʹ�ò����޶������еı��ʽ�����Լ���ֵ�ķ�Χ
     *
     *  ��ֵ�У�����Ӧ�þӶ࣬������֮�����ò�ͬȨ��������ֵ
     *  ���ɷ���ʱ�ֳ����������һ������������һ������������ֵ����ߵ���Ȼ�����ұߵķ��ӷ�ĸ��һ�����Ե���������Ȼ���ĺ���
     *  ���������ɺ󣬽����ɵ���ֵ����������������γɱ��ʽ
     *  ������ʽ���
     *
     *  �޳����Ϊ�����ı��ʽ���ظ��ı��ʽ
     *  ��֤���ʽ�𰸣������𰸵ı��ʽ������Ϊ����ʱͬʱ�������Ϣ
     *  �����ʽ�Ž�HashSet�У�ÿ�ζ�������֤�������ɵ���+�����ʱ����Ҫ���һ����֤��������������߱��ʽ��ͬ�����
     *  ���ظ��ı��ʽд�����ɵ��ĵ��У������������ɽ��
     */

    // �ú������������һ����Χ�ڵ�����������
    public static int generateNaturalNumber(int range) {
        Random random = new Random(range);
        return random.nextInt();
    }

    // �ú����������һ�����ʽ�Ĺ���
    public static String generateOne(int range) {
        String s = "1 + 2";
        return s;
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
