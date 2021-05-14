package cn.wyx.demo.jvm.classfile;

import cn.wyx.demo.jvm.classfile.attributes.AttributeInfo;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantPool;

/**
 * @author WYX
 * @date 2021/3/8 - 19:44
 * --------------------------------
 * 类文件
 */
public class ClassFile {

    //class文件的结构
    private int minorVersion; //次版本号
    private int majorVersion; //主版本号
    private ConstantPool constantPool; //常量池
    private int accessFlags; //访问标志
    private int thisClassIdx; //此类的全限定名索引
    private int superClassIdx; //父类的全限定名索引
    private int[] interfaces; //接口索引集合
    private MemberInfo[] fields; //字段表集合
    private MemberInfo[] methods; //方法表集合
    private AttributeInfo[] attributes; //属性表集合

    public ClassFile(byte[] classData) {
        ClassReader reader = new ClassReader(classData);
        this.readAndCheckMagic(reader);
        this.readAndCheckVersion(reader);
        this.constantPool = this.readConstantPool(reader);
        this.accessFlags = reader.readUint16();
        this.thisClassIdx = reader.readUint16();
        this.superClassIdx = reader.readUint16();
        this.interfaces = reader.readUint16s();
        this.fields = MemberInfo.readMembers(reader, constantPool);
        this.methods = MemberInfo.readMembers(reader, constantPool);
        this.attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    private void readAndCheckMagic(ClassReader reader) {
        long magic = reader.readUint32();
        if (magic != (0xCAFEBABE & 0x0FFFFFFFFL)) { //为何需要按位与操作？
            throw new ClassFormatError("magic!");
        }
    }

    private void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();
        switch (this.majorVersion) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (this.minorVersion == 0)
                    return;
        }
        throw new UnsupportedClassVersionError();
    }

    private ConstantPool readConstantPool(ClassReader reader) {
        return new ConstantPool(reader);
    }

    //Getters
    public int minorVersion() {
        return this.minorVersion;
    }

    public int majorVersion() {
        return this.majorVersion;
    }

    public ConstantPool constantPool() {
        return this.constantPool;
    }

    public int accessFlags() {
        return this.accessFlags;
    }

    public String className() {
        return this.constantPool.getClassName(this.thisClassIdx);
    }

    public String superClassName() {
        if (this.superClassIdx <= 0) return "";
        return this.constantPool.getClassName(this.superClassIdx);
    }

    public String[] interfaceNames() {
        String[] interfaceNames = new String[this.interfaces.length];
        for (int i = 0; i < this.interfaces.length; i++) {
            interfaceNames[i] = this.constantPool.getClassName(interfaces[i]);
        }
        return interfaceNames;
    }

    public MemberInfo[] fields() {
        return this.fields;
    }

    public MemberInfo[] methods() {
        return this.methods;
    }
}
