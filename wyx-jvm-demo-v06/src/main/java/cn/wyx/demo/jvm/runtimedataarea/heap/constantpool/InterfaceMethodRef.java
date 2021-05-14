package cn.wyx.demo.jvm.runtimedataarea.heap.constantpool;

import cn.wyx.demo.jvm.classfile.constantpool.impl.ConstantInterfaceMethodRefInfo;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Method;

/**
 * @author WYX
 * @date 2021-4-20 - 17:32
 * --------------------------------
 * 接口方法引用
 */
public class InterfaceMethodRef extends MemberRef {

    private Method method;

    static InterfaceMethodRef newInterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, ConstantInterfaceMethodRefInfo refInfo) {
        InterfaceMethodRef ref = new InterfaceMethodRef();
        ref.runtimeConstantPool = runtimeConstantPool;
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public Method resolvedInterfaceMethod() {
        if (method == null) {
            resolveInterfaceMethodRef();
        }
        return method;
    }

    private void resolveInterfaceMethodRef() {
        //第7章处理
    }
}
