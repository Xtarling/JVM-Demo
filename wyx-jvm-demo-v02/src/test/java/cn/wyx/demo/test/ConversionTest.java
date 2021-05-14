package cn.wyx.demo.test;

/**
 * @author WYX
 * @date 2021-3-14 - 22:03
 * --------------------------------
 */
public class ConversionTest {

    public static void test1() {
        int i = 192;
        byte b1 = (byte) i;
        int b2 = (byte) i;
        System.out.println(b1);
        System.out.println(b2);
    }

    public static void test2() {
        int i = 36500;
        char c1 = (char) i;
        int c2 = (short) i;
        System.out.println(c1);
        System.out.println((char) c2);
        System.out.println((int) c1);
        System.out.println(c2);
    }

    public static void test3() {
        int i = 36500;
        char c1 = (char) i;
        short c2 = (short) i;
        System.out.println(c1);
        System.out.println((short) c1);
        System.out.println((int) c1);
        System.out.println(c2);
        System.out.println((char) c2);
    }

    public static void main(String[] args) {
        test1();
//        test2();
//        System.out.println("-----------");
//        test3();
    }
}
