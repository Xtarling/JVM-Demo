package cn.wyx.demo.jvm.classfile;

import cn.wyx.demo.jvm.classfile.attributes.AttributeInfo;
import cn.wyx.demo.jvm.classfile.attributes.impl.CodeAttribute;
import cn.wyx.demo.jvm.classfile.attributes.impl.ConstantValueAttribute;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;

/**
 * @author WYX
 * @date 2021/3/8 - 19:45
 * --------------------------------
 * 类的成员信息。用于描述ClassFile中的field和method
 */
public class MemberInfo {

    private final ConstantPool constantPool;
    private final int accessFlags;
    private final int nameIdx;
    private final int descriptorIdx;
    private final AttributeInfo[] attributes;

    private MemberInfo(ClassReader reader, ConstantPool constantPool) {
        this.constantPool = constantPool;
        this.accessFlags = reader.readUint16();
        this.nameIdx = reader.readUint16();
        this.descriptorIdx = reader.readUint16();
        this.attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool) {
        int fieldCount = reader.readUint16();
        MemberInfo[] fields = new MemberInfo[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            fields[i] = new MemberInfo(reader, constantPool);
        }
        return fields;
    }

    public int accessFlags() {
        return accessFlags;
    }

    public String name() {
        return constantPool.getUTF8(nameIdx);
    }

    public String descriptor() {
        return constantPool.getUTF8(descriptorIdx);
    }

    public CodeAttribute codeAttribute() {
        for (AttributeInfo attrInfo : attributes) {
            if (attrInfo instanceof CodeAttribute) return (CodeAttribute) attrInfo;
        }
        return null;
    }

    public ConstantValueAttribute constantValueAttribute() {
        for (AttributeInfo attrInfo : attributes) {
            if (attrInfo instanceof ConstantValueAttribute) return (ConstantValueAttribute) attrInfo;
        }
        return null;
    }
}
