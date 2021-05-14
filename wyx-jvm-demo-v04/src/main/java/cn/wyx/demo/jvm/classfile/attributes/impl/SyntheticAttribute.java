package cn.wyx.demo.jvm.classfile.attributes.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;

/**
 * @author WYX
 * @date 2021/3/10 - 19:27
 * --------------------------------
 * 标志型属性。表示此类/字段/方法是编译器自动产生的
 */
public class SyntheticAttribute extends MarkerAttribute {

    @Override
    public void readInfo(ClassReader reader) {

    }
}
