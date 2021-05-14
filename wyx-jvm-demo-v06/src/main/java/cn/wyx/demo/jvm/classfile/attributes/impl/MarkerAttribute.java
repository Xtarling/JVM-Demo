package cn.wyx.demo.jvm.classfile.attributes.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.attributes.AttributeInfo;

/**
 * @author WYX
 * @date 2021/3/10 - 19:26
 * --------------------------------
 */
public class MarkerAttribute implements AttributeInfo {

    @Override
    public void readInfo(ClassReader reader) {
        //read nothing
    }
}
