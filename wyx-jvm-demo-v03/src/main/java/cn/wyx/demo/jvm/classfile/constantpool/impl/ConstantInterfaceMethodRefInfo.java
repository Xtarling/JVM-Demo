package cn.wyx.demo.jvm.classfile.constantpool.impl;

import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;

/**
 * @author WYX
 * @date 2021/3/9 - 9:57
 * --------------------------------
 */
public class ConstantInterfaceMethodRefInfo extends ConstantMemberRefInfo {

    public ConstantInterfaceMethodRefInfo(ConstantPool constantPool) {
        super(constantPool);
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_INTERFACEMETHODREF;
    }
}
