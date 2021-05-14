package cn.wyx.demo.jvm.runtimedataarea.heap.methodarea;

import cn.wyx.demo.jvm.classfile.MemberInfo;
import cn.wyx.demo.jvm.classfile.attributes.impl.ConstantValueAttribute;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.AccessFlags;

/**
 * @author WYX
 * @date 2021-4-20 - 17:35
 * --------------------------------
 * 类的字段
 * 字段的实际值放在slots中，Field类存放slotId序号
 * 静态字段的实际值存放在Class.staticVars中，实例字段的实际值存放在_Object.fields中
 */
public class Field extends ClassMember {

    private int constValueIndex;
    public int slotId;

    /*参考源码中此方法不是static*/
    static Field[] newFields(Class clazz, MemberInfo[] cfFields) {
        Field[] fields = new Field[cfFields.length];
        for (int i = 0; i < cfFields.length; i++) {
            fields[i] = new Field();
            fields[i].clazz = clazz;
            fields[i].copyMemberInfo(cfFields[i]);
            fields[i].copyAttributes(cfFields[i]);
        }
        return fields;
    }

    /*《深入理解Java虚拟机 第2版》中，字段的属性表不只有ConstantValue一种属性，但这里的实现仅读取这一种。为何呢？*/
    private void copyAttributes(MemberInfo cfField) {
        ConstantValueAttribute valAttr = cfField.constantValueAttribute();
        if (null != valAttr) {
            this.constValueIndex = valAttr.constantValueIdx();
        }
    }

    public boolean isLongOrDouble() {
        return descriptor.equals("J") || descriptor.equals("D");
    }

    public int constValueIndex() {
        return constValueIndex;
    }
    public int slotId() {
        return slotId;
    }

    public boolean isVolatile() {
        return 0 != (accessFlags & AccessFlags.ACC_VOLATILE);
    }
    public boolean isTransient() {
        return 0 != (accessFlags & AccessFlags.ACC_TRANSIENT);
    }
    public boolean isEnum() {
        return 0 != (accessFlags & AccessFlags.ACC_ENUM);
    }
}
