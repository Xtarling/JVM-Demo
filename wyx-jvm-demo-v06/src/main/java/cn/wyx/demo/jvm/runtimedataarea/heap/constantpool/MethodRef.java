package cn.wyx.demo.jvm.runtimedataarea.heap.constantpool;

import cn.wyx.demo.jvm.classfile.constantpool.impl.ConstantMethodRefInfo;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Method;

/**
 * @author WYX
 * @date 2021-4-20 - 17:32
 * --------------------------------
 * 方法引用
 */
public class MethodRef extends MemberRef {

    private Method method;

    static MethodRef newMethodRef(RuntimeConstantPool runtimeConstantPool, ConstantMethodRefInfo refInfo) {
        MethodRef ref = new MethodRef();
        ref.runtimeConstantPool = runtimeConstantPool;
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public Method resolvedMethod() {
        if (null == method) {
            resolveMethodRef();
        }
        return method;
    }

    private void resolveMethodRef() {
        //第7章处理
    }
}
