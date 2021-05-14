package cn.wyx.demo.jvm.runtimedataarea;

import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea._Object;

/**
 * @author WYX
 * @date 2021/3/12 - 21:56
 * --------------------------------
 * 局部变量表
 * double、long类型数据需要2个Slot存放，其它类型数据只需要1个Slot存放。
 */
public class LocalVars {

    private Slot[] slots;

    public LocalVars(int maxLocals) {
        if (maxLocals > 0) {
            slots = new Slot[maxLocals];
            for (int i = 0; i < maxLocals; i++) {
                slots[i] = new Slot();
            }
        }
    }

    public void setInt(int idx, int val) {
        slots[idx].num = val;
    }

    public int getInt(int idx) {
        return slots[idx].num;
    }

    /*slot.num是int类型，float和int如何做到相互转换且不丢失精度呢？下同*/
    public void setFloat(int idx, float val) {
        slots[idx].num = (int) val;
    }

    public float getFloat(int idx) {
        return (float) slots[idx].num;
    }

    public void setLong(int idx, long val) {
        slots[idx].num = (int) val;
        slots[idx + 1].num = (int) (val >> 32);
    }

    public long getLong(int idx) {
        int low = slots[idx].num;
        int high = slots[idx + 1].num;
        return ((long) high << 32) | ((long) low);
    }

    public void setDouble(int idx, double val) {
        setLong(idx, (long) val);
    }

    public double getDouble(int idx) {
        return (double) getLong(idx);
    }

    public void setRef(int idx, _Object ref) {
        slots[idx].ref = ref;
    }

    public _Object getRef(int idx) {
        return slots[idx].ref;
    }

    public Slot[] getSlots() {
        return slots;
    }
}
