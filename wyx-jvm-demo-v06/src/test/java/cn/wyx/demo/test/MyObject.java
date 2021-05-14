package cn.wyx.demo.test;

/**
 * @author WYX
 * @date 2021-4-25 - 16:59
 * --------------------------------
 * 一些指令的演示
 */
public class MyObject {
    public static int staticVar;
    public int instanceVar;

    public static void main(String[] args) {
        int x = 32768;                      //LDC
        MyObject myObj = new MyObject();    //NEW
        MyObject.staticVar = x;             //PUT_STATIC
        x = MyObject.staticVar;             //GET_STATIC
        myObj.instanceVar = x;              //PUT_FIELD
        x = myObj.instanceVar;              //GET_FIELD
        Object obj = myObj;
        if (obj instanceof MyObject) {      //INSTANCE_OF
            myObj = (MyObject) obj;         //CHECK_CAST
            System.out.println(myObj.instanceVar);
        }
    }
}
