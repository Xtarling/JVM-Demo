package cn.wyx.demo.jvm.runtimedataarea;

/**
 * @author WYX
 * @date 2021/3/12 - 21:56
 * --------------------------------
 * 局部变量表
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

    public void setFloat(int idx, float val) {
        slots[idx].num = Float.valueOf(val).intValue();
    }

    public Float getFloat(int idx) {
        int num = slots[idx].num;
        return (float) num;
    }

    public void setLong(int idx, long val) {
        slots[idx].num = (int) val;
        slots[idx + 1].num = (int) (val >> 32);
    }

    public Long getLong(int idx) {
        int low = slots[idx].num;
        int high = slots[idx + 1].num;
        return ((long) (high << 32)) | (long) low;
    }

    public void setDouble(int idx, double val) {
        setLong(idx, (long) val);
    }

    public Double getDouble(int idx) {
        return Double.valueOf(getLong(idx)); //不需要强制类型转换吗？
    }

    public void setRef(int idx, Object ref) {
        slots[idx].ref = ref;
    }

    public Object getRef(int idx) {
        return slots[idx].ref;
    }
}
