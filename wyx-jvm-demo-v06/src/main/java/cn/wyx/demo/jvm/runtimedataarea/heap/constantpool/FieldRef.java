package cn.wyx.demo.jvm.runtimedataarea.heap.constantpool;

import cn.wyx.demo.jvm.classfile.constantpool.impl.ConstantFieldRefInfo;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Field;

/**
 * @author WYX
 * @date 2021-4-20 - 17:33
 * --------------------------------
 * 字段引用
 */
public class FieldRef extends MemberRef {

    private Field field;

    static FieldRef newFieldRef(RuntimeConstantPool runtimeConstantPool, ConstantFieldRefInfo refInfo) {
        FieldRef ref = new FieldRef();
        ref.runtimeConstantPool = runtimeConstantPool;
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public Field resolvedField() {
        if (null == field) {
            resolveFieldRef();
        }
        return field;
    }

    private void resolveFieldRef() {
        Class d = runtimeConstantPool.getClazz();
        Class c = resolvedClass();

        Field field = lookupField(c, name, descriptor);
        if (null == field) {
            throw new NoSuchFieldError();
        }
        if (!field.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }
        this.field = field;
    }

    private Field lookupField(Class c, String name, String descriptor) {
        for (Field field : c.fields()) {
            if (field.name().equals(name) && field.descriptor().equals(descriptor)) {
                return field;
            }
        }
        for (Class iface : c.interfaces) {
            Field field = lookupField(iface, name, descriptor);
            if (null != field) return field;
        }
        if (c.superClass != null) {
            return lookupField(c.superClass, name, descriptor);
        }
        return null;
    }
}
