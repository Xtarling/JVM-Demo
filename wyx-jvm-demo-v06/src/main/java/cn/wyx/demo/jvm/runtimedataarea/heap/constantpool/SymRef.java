package cn.wyx.demo.jvm.runtimedataarea.heap.constantpool;

import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;

/**
 * @author WYX
 * @date 2021-4-20 - 17:31
 * --------------------------------
 * 符号引用 Symbolic Reference
 */
public class SymRef {

    RuntimeConstantPool runtimeConstantPool;
    String className;
    Class clazz;

    /*参考源码没有访问权限检查*/
    //解析此符号引用是什么类型
    public Class resolvedClass() {
        if (null == clazz) {
            resolveClassRef();
        }
        return clazz;
    }

    private void resolveClassRef() {
        Class d = runtimeConstantPool.getClazz();
        Class c = d.loader.loadClass(className);
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }
        this.clazz = c;
    }
}
