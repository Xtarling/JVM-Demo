package cn.wyx.demo.jvm.runtimedataarea.heap.methodarea;

/**
 * @author WYX
 * @date 2021-4-20 - 17:35
 * --------------------------------
 * 对象（类的实例）
 */
public class _Object {

    private final Class clazz;
    private final Slots fields;

    public _Object(Class clazz) {
        this.clazz = clazz;
        this.fields = new Slots(clazz.instanceSlotCount);
    }

    /*此方法的原书写法和参考源码写法以及解释有一些区别*/
    public boolean isInstanceOf(Class c) {
        return c.isAssignableFrom(this.clazz);
    }

    public Class clazz() {
        return clazz;
    }
    public Slots fields() {
        return fields;
    }
}
