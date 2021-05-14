package cn.wyx.demo.jvm.classfile.constantpool.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.constantpool.ConstantInfo;

/**
 * @author WYX
 * @date 2021/3/9 - 10:00
 * --------------------------------
 */
public class ConstantUTF8Info implements ConstantInfo {

    private String str;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16();
        byte[] bytes = reader.readBytes(length);
        this.str = new String(bytes); //是否需要指定字符集为UTF8？ //可看String构造器的源码，似乎如果不写此参数，则默认为项目字符集
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_UTF8;
    }

    public String str() {
        return this.str;
    }
}
