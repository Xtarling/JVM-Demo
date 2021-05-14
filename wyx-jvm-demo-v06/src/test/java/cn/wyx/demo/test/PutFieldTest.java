package cn.wyx.demo.test;

/**
 * @author WYX
 * @date 2021-4-27 - 10:35
 * --------------------------------
 */
public class PutFieldTest {

    public int instanceVar;

    public static void main(String[] args) {
        PutFieldTest obj = new PutFieldTest();
        obj.instanceVar = 5;
        int x = obj.instanceVar;
    }
}
