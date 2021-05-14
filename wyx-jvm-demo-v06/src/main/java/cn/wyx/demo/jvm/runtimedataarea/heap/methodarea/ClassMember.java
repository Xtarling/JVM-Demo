package cn.wyx.demo.jvm.runtimedataarea.heap.methodarea;

import cn.wyx.demo.jvm.classfile.MemberInfo;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.AccessFlags;

/**
 * @author WYX
 * @date 2021-4-20 - 17:35
 * --------------------------------
 * 类的成员（字段、方法）
 */
public class ClassMember {

    int accessFlags;
    String name;
    String descriptor;
    Class clazz;

    //复制字段和方法的共有属性：访问标志、名字、描述符
    public void copyMemberInfo(MemberInfo memberInfo) {
        this.accessFlags = memberInfo.accessFlags();
        this.name = memberInfo.name();
        this.descriptor = memberInfo.descriptor();
    }

    //该类成员可否被类d访问
    public boolean isAccessibleTo(Class d) {
        if (isPublic()) { //public: 到处都可访问
            return true;
        }
        Class c = this.clazz;
        if (isProtected()) { //protected: 不同包中的子类可访问 /*参考源码没有第二个判定条件*/
            return d == c || d.isSubClassOf(c) || c.getPackageName().equals(d.getPackageName());
        }
        if (!isPrivate()) { //default: 同一包下可访问
            return c.getPackageName().equals(d.getPackageName());
        }
        return d == c; //private: 仅本类可访问
    }

    public String name() {
        return name;
    }
    public String descriptor() {
        return descriptor;
    }
    public Class clazz() {
        return clazz;
    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }
    public boolean isPrivate() {
        return 0 != (accessFlags & AccessFlags.ACC_PRIVATE);
    }
    public boolean isProtected() {
        return 0 != (accessFlags & AccessFlags.ACC_PROTECTED);
    }
    public boolean isStatic() {
        return 0 != (accessFlags & AccessFlags.ACC_STATIC);
    }
    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }
    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlags.ACC_SYNTHETIC);
    }
}
