package cn.wyx.demo.jvm.runtimedataarea;

/**
 * @author WYX
 * @date 2021/3/12 - 21:56
 * --------------------------------
 * 操作数栈
 * double、long等64位类型数据的栈容量为2，32位类型数据的栈容量为1。
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
    3. pushSlot/popSlot有什么用？
     */
    public void pushInt(int val) {
        slots[size].num = val;
        size++;
    }

    public int popInt() {
        size--;
        return slots[size].num;
    }

    public void pushFloat(float val) {
        slots[size].num = (int) val;
        size++;
    }

    public float popFloat() {
        size--;
        return slots[size].num;
    }

    public void pushLong(long val) {
        slots[size].num = (int) val;
        slots[size + 1].num = (int) (val >> 32);
        size += 2;
    }

    public long popLong() {
        size -= 2;
        int low = slots[size].num;
        int high = slots[size + 1].num;
        return ((long) high) << 32 | (long) low;
    }

    public void pushDouble(double val) {
        pushLong((long) val);
    }

    public double popDouble() {
        return popLong();
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

    public void pushSlot(Slot slot) {
        slots[size] = slot;
        size++;
    }

    public Slot popSlot() {
        size--;
        return slots[size];
    }

    public Slot[] getSlots() {
        return slots;
    }
}
