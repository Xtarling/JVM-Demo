package cn.wyx.demo.jvm.classfile.constantpool.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantInfo;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;

/**
 * @author WYX
 * @date 2021/3/9 - 10:00
 * --------------------------------
 */
public class ConstantStringInfo implements ConstantInfo {

    private ConstantPool constantPool;
    private int strIdx;

    public ConstantStringInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.strIdx = reader.readUint16();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_STRING;
    }

    public String string() {
        return this.constantPool.getUTF8(this.strIdx);
    }
}
