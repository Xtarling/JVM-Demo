package cn.wyx.demo.jvm.classfile.attributes.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.attributes.AttributeInfo;

/**
 * @author WYX
 * @date 2021/3/10 - 19:23
 * --------------------------------
 * static变量信息
 * （但Sun Javac似乎只有static final常量才创建ConstantValue属性，即编译期初始化）
 */
public class ConstantValueAttribute implements AttributeInfo {

    private int constantValueIdx;

    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIdx = reader.readUint16();
    }

    public int constantValueIdx() {
        return constantValueIdx;
    }
}
