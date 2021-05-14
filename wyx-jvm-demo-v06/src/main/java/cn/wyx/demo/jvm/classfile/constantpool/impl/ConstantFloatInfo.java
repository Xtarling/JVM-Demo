package cn.wyx.demo.jvm.classfile.constantpool.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantInfo;

/**
 * @author WYX
 * @date 2021/3/9 - 9:57
 * --------------------------------
 */
public class ConstantFloatInfo implements ConstantInfo {

    private float val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint64TFloat();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_FLOAT;
    }

    public float value() {
        return this.val;
    }
}
