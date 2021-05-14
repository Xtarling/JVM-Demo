package cn.wyx.demo.jvm.classfile.constantpool.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantInfo;

/**
 * @author WYX
 * @date 2021/3/9 - 9:58
 * --------------------------------
 */
public class ConstantLongInfo implements ConstantInfo {

    private long val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint64TLong();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_LONG;
    }

    public long value() {
        return this.val;
    }
}
