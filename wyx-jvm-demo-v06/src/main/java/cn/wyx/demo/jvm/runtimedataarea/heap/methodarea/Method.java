package cn.wyx.demo.jvm.runtimedataarea.heap.methodarea;

import cn.wyx.demo.jvm.classfile.MemberInfo;
import cn.wyx.demo.jvm.classfile.attributes.impl.CodeAttribute;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.AccessFlags;

/**
 * @author WYX
 * @date 2021-4-20 - 17:35
 * --------------------------------
 * 类的方法
 */
public class Method extends ClassMember {

    private int maxStack;
    private int maxLocals;
    private byte[] code;

    /*参考源码中此方法不是static*/
    static Method[] newMethods(Class clazz, MemberInfo[] cfMethods) {
        Method[] methods = new Method[cfMethods.length];
        for (int i = 0; i < cfMethods.length; i++) {
            methods[i] = new Method();
            methods[i].clazz = clazz;
            methods[i].copyMemberInfo(cfMethods[i]);
            methods[i].copyAttributes(cfMethods[i]);
        }
        return methods;
    }

    //与Field类下的同名方法有类似的问题，即：为何这里复制属性只复制Code，而不获取其他的？
    private void copyAttributes(MemberInfo cfMethod) {
        CodeAttribute codeAttr = cfMethod.codeAttribute();
        if (null != codeAttr) {
            maxStack = codeAttr.maxStack();
            maxLocals = codeAttr.maxLocals();
            code = codeAttr.data();
        }
    }

    public int maxStack() {
        return maxStack;
    }
    public int maxLocals() {
        return maxLocals;
    }
    public byte[] code() {
        return code;
    }

    public boolean isSynchronized() {
        return 0 != (accessFlags & AccessFlags.ACC_SYNCHRONIZED);
    }
    public boolean isBridge() {
        return 0 != (accessFlags & AccessFlags.ACC_BRIDGE);
    }
    public boolean isVarargs() {
        return 0 != (accessFlags & AccessFlags.ACC_VARARGS);
    }
    public boolean isNative() {
        return 0 != (accessFlags & AccessFlags.ACC_NATIVE);
    }
    public boolean isAbstract() {
        return 0 != (accessFlags & AccessFlags.ACC_ABSTRACT);
    }
    public boolean isStrictfp() {
        return 0 != (accessFlags & AccessFlags.ACC_STRICTFP);
    }
}
