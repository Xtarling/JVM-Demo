package cn.wyx.demo.jvm.classfile.attributes.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.attributes.AttributeInfo;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WYX
 * @date 2021/3/10 - 19:24
 * --------------------------------
 */
public class EnclosingMethodAttribute implements AttributeInfo {

    private ConstantPool constantPool;
    private int classIdx;
    private int methodIdx;

    public EnclosingMethodAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIdx = reader.readUint16();
        this.methodIdx = reader.readUint16();
    }

    public String className() {
        return this.constantPool.getClassName(this.classIdx);
    }

    public Map<String, String> methodNameAndDescriptor() {
        if (methodIdx <= 0) return new HashMap<>();
        return constantPool.getNameAndType(methodIdx);
    }
}
