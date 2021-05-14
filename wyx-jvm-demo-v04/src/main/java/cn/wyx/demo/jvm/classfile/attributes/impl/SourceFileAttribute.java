package cn.wyx.demo.jvm.classfile.attributes.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.attributes.AttributeInfo;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;

/**
 * @author WYX
 * @date 2021/3/10 - 19:26
 * --------------------------------
 * 记录生成此Class文件的源码文件名称
 */
public class SourceFileAttribute implements AttributeInfo {

    private ConstantPool constantPool;
    private int sourceFileIdx;

    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        sourceFileIdx = reader.readUint16();
    }

    public String fileName() {
        return constantPool.getUTF8(sourceFileIdx);
    }
}
