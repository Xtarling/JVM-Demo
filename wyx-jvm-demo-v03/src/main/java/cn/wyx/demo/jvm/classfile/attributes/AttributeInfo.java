package cn.wyx.demo.jvm.classfile.attributes;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.attributes.impl.*;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;

/**
 * @author WYX
 * @date 2021/3/8 - 19:52
 * --------------------------------
 * 属性表信息
 */
public interface AttributeInfo {

    void readInfo(ClassReader reader);

    static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool) {
        int attributesCount = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = readAttribute(reader, constantPool);
        }
        return attributes;
    }

    static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        int attrNameIdx = reader.readUint16();
        String attrName = constantPool.getUTF8(attrNameIdx);
        int attrLen = reader.readUint32TInteger(); //attribute_length是u4类型，此函数为何不用readUint32?
        AttributeInfo attrInfo = newAttributeInfo(attrName, attrLen, constantPool);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    static AttributeInfo newAttributeInfo(String attrName, int attrLen, ConstantPool constantPool) {
        switch (attrName) {
            case "BootstrapMethods":
                return new BootstrapMethodsAttribute();
            case "Code":
                return new CodeAttribute(constantPool);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "EnclosingMethod":
                return new EnclosingMethodAttribute(constantPool);
            case "Exceptions":
                return new ExceptionsAttribute();
            case "InnerClasses":
                return new InnerClassesAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable":
                return new LocalVariableTypeTableAttribute();
            case "Signature":
                return new SignatureAttribute(constantPool);
            case "SourceFile":
                return new SourceFileAttribute(constantPool);
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen);
//            case "MethodParameters": //JDK8新增
//            case "RuntimeInvisibleAnnotations": //JDK5新增
//            case "RuntimeInvisibleParameterAnnotations":
//            case "RuntimeInvisibleTypeAnnotations":
//            case "RuntimeVisibleAnnotations":
//            case "RuntimeVisibleParameterAnnotations":
//            case "RuntimeVisibleTypeAnnotations":
//            case "SourceDebugExtension": //JDK5新增
//            case "StackMapTable": //JDK6新增
        }
    }
}
