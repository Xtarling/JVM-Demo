package cn.wyx.demo.jvm.runtimedataarea;

/**
 * @author WYX
 * @date 2021/3/12 - 21:56
 * --------------------------------
 * 操作数栈
 */
public class OperandStack {

    private int size = 0;
    private Slot[] slots; //操作数栈的数据单位也是slot？

    public OperandStack(int maxStack) {
        if (maxStack > 0) {
            slots = new Slot[maxStack];
            for (int i = 0; i < maxStack; i++) {
                slots[i] = new Slot();
            }
        }
    }

    /*
    几个问题：
    1. 以下操作没有做stackoverflow处理？
    2. pop操作是否需要抹掉栈顶slot存放的数据？目前的实现中，popInt没抹掉，popRef抹掉了。
    3. push和pop的数据类型还不够多
     */
    public void pushInt(int val) {
        slots[size].num = val;
        size++;
    }

    public int popInt() {
        size--;
        return slots[size].num;
    }

    public void pushRef(Object ref) {
        slots[size].ref = ref;
        size++;
    }

    public Object popRef() {
        size--;
        Object ref = slots[size].ref;
        slots[size].ref = null;
        return ref;
    }
}
