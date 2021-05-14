package cn.wyx.demo.jvm.classfile.constantpool.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantInfo;

/**
 * @author WYX
 * @date 2021/3/9 - 9:59
 * --------------------------------
 */
public class ConstantMethodTypeInfo implements ConstantInfo {

    private int descriptorIdx;

    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIdx = reader.readUint16();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_METHODTYPE;
    }
}
