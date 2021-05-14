package cn.wyx.demo.jvm.runtimedataarea.heap.constantpool;

import cn.wyx.demo.jvm.classfile.constantpool.impl.ConstantClassInfo;

/**
 * @author WYX
 * @date 2021-4-20 - 17:33
 * --------------------------------
 * 类引用
 */
public class ClassRef extends SymRef {

    static ClassRef newClassRef(RuntimeConstantPool runtimeConstantPool, ConstantClassInfo classInfo) {
        ClassRef ref = new ClassRef();
        ref.runtimeConstantPool = runtimeConstantPool;
        ref.className = classInfo.name();
        return ref;
    }
}
