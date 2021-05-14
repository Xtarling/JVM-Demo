package cn.wyx.demo.jvm.classfile.constantpool.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantInfo;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;

/**
 * @author WYX
 * @date 2021/3/9 - 9:56
 * --------------------------------
 */
public class ConstantClassInfo implements ConstantInfo {

    public ConstantPool constantPool; //private?
    public int nameIdx; //private?

    public ConstantClassInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIdx = reader.readUint16();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_CLASS;
    }

    public String name() {
        return this.constantPool.getUTF8(this.nameIdx);
    }
}
