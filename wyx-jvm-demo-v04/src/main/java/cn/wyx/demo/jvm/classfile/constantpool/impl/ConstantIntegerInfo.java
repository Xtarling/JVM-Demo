package cn.wyx.demo.jvm.classfile.constantpool.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantInfo;

/**
 * @author WYX
 * @date 2021/3/9 - 9:55
 * --------------------------------
 */
public class ConstantIntegerInfo implements ConstantInfo {

    private int val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint32TInteger();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_INTEGER;
    }

    public int value() {
        return this.val;
    }
}
