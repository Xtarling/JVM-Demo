package cn.wyx.demo.jvm.classfile.attributes.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;

/**
 * @author WYX
 * @date 2021/3/10 - 19:24
 * --------------------------------
 * 标志型属性，表示某个类/字段/方法不建议使用
 */
public class DeprecatedAttribute extends MarkerAttribute{

    @Override
    public void readInfo(ClassReader reader) {

    }

}
