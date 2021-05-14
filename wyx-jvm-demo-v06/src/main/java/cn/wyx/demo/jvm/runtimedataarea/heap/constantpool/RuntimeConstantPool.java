package cn.wyx.demo.jvm.runtimedataarea.heap.constantpool;

import cn.wyx.demo.jvm.classfile.constantpool.ConstantInfo;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;
import cn.wyx.demo.jvm.classfile.constantpool.impl.*;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;

/**
 * @author WYX
 * @date 2021-4-20 - 17:28
 * --------------------------------
 * 运行时常量池
 * 存放内容：
 * 1. 字面量：整数、浮点数、字符串字面量
 * 2. 符号引用：类、字段、方法、接口方法的符号引用
 */
public class RuntimeConstantPool {

    private Class clazz;
    private Object[] constants;

    public RuntimeConstantPool(Class clazz, ConstantPool constantPool) {
        this.clazz = clazz;
        int cpCount = constantPool.size();
        this.constants = new Object[cpCount];
        ConstantInfo[] constantInfos = constantPool.constantInfos();

        for (int i = 1; i < cpCount; i++) {
            ConstantInfo info = constantInfos[i];
            switch (info.tag()) {
                case ConstantInfo.CONSTANT_TAG_INTEGER:
                    ConstantIntegerInfo integerInfo = (ConstantIntegerInfo) info;
                    constants[i] = integerInfo.value();
                    break;
                case ConstantInfo.CONSTANT_TAG_FLOAT:
                    ConstantFloatInfo floatInfo = (ConstantFloatInfo) info;
                    constants[i] = floatInfo.value();
                    break;
                case ConstantInfo.CONSTANT_TAG_LONG:
                    ConstantLongInfo longInfo = (ConstantLongInfo) info;
                    constants[i] = longInfo.value();
                    i++;
                    break;
                case ConstantInfo.CONSTANT_TAG_DOUBLE:
                    ConstantDoubleInfo doubleInfo = (ConstantDoubleInfo) info;
                    constants[i] = doubleInfo.value();
                    i++;
                    break;
                case ConstantInfo.CONSTANT_TAG_STRING:
                    ConstantStringInfo stringInfo = (ConstantStringInfo) info;
                    constants[i] = stringInfo.string();
                    break;
                case ConstantInfo.CONSTANT_TAG_CLASS:
                    ConstantClassInfo classInfo = (ConstantClassInfo) info;
                    constants[i] = ClassRef.newClassRef(this, classInfo);
                    break;
                case ConstantInfo.CONSTANT_TAG_FIELDREF:
                    ConstantFieldRefInfo fieldRefInfo = (ConstantFieldRefInfo) info;
                    constants[i] = FieldRef.newFieldRef(this, fieldRefInfo);
                    break;
                case ConstantInfo.CONSTANT_TAG_METHODREF:
                    ConstantMethodRefInfo methodRefInfo = (ConstantMethodRefInfo) info;
                    constants[i] = MethodRef.newMethodRef(this, methodRefInfo);
                    break;
                case ConstantInfo.CONSTANT_TAG_INTERFACEMETHODREF:
                    ConstantInterfaceMethodRefInfo interfaceMethodRefInfo = (ConstantInterfaceMethodRefInfo) info;
                    constants[i] = InterfaceMethodRef.newInterfaceMethodRef(this, interfaceMethodRefInfo);
                    break;
                default:
            }
        }
    }

    public Class getClazz() { return clazz; }
    public void setClazz(Class clazz) { this.clazz = clazz; }

    public Object getConstant(int idx) { /*参考源码的方法名为getConstants，原书是getConstant*/
        return constants[idx];
    }
}
