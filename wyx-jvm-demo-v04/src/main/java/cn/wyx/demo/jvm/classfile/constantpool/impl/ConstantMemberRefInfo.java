package cn.wyx.demo.jvm.classfile.constantpool.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantInfo;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;

import java.util.Map;

/**
 * @author WYX
 * @date 2021/3/9 - 9:58
 * --------------------------------
 */
public class ConstantMemberRefInfo implements ConstantInfo {

    protected ConstantPool constantPool; //为啥是protected? //因为FieldRefInfo、MethodRefInfo、InterfaceMethodInfo继承自此类
    protected int classIdx;
    private int nameAndTypeIdx;

    ConstantMemberRefInfo(ConstantPool constantPool) { //public?
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIdx = reader.readUint16();
        this.nameAndTypeIdx = reader.readUint16();
    }

    @Override
    public int tag() {
        return 0;
    }

    public String className() {
        return this.constantPool.getClassName(this.classIdx);
    }

    public Map<String, String> nameAndDescriptor() {
        return this.constantPool.getNameAndType(this.nameAndTypeIdx);
    }
}
