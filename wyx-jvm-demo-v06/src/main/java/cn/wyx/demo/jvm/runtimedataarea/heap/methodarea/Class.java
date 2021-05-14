package cn.wyx.demo.jvm.runtimedataarea.heap.methodarea;

import cn.wyx.demo.jvm.classfile.ClassFile;
import cn.wyx.demo.jvm.runtimedataarea.heap.ClassLoader;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.AccessFlags;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.RuntimeConstantPool;

/**
 * @author WYX
 * @date 2021-4-20 - 17:26
 * --------------------------------
 * Class类
 */
public class Class {

    //defineClass(...) -> parseClass(...) -> new Class(...)中赋值
    private final int accessFlags;
    private final String name;
    private final String superClassName;
    private final String[] interfaceNames;
    private final RuntimeConstantPool runtimeConstantPool;
    private final Field[] fields;
    private final Method[] methods;

    //defineClass(...)中赋值
    public ClassLoader loader;
    public Class superClass;
    public Class[] interfaces;

    //link(...) -> prepare(...)中赋值
    public int instanceSlotCount;
    public int staticSlotCount;
    public Slots staticVars;

    public Class(ClassFile classFile) {
        this.accessFlags = classFile.accessFlags();
        this.name = classFile.className();
        this.superClassName = classFile.superClassName();
        this.interfaceNames = classFile.interfaceNames();
        this.runtimeConstantPool = new RuntimeConstantPool(this, classFile.constantPool());
        this.fields = Field.newFields(this, classFile.fields()); /*参考源码为new Field().newFields(...)，下同*/
        this.methods = Method.newMethods(this, classFile.methods());
    }

    /*
    * Java基础知识：通常class的访问修饰符只有(public/不加)这两种
    * public表示任意位置可访问该类；不写表示默认访问修饰符，即同一个包中可以访问
    * */
    //表示当前类能否被类other访问
    public boolean isAccessibleTo(Class other) {
        return isPublic() || (this.getPackageName().equals(other.getPackageName()));
    }

    public String getPackageName() {
        int i = name.lastIndexOf("/");
        if (i >= 0) {
//            return name; /*参考源码写法*/
            return name.substring(0, i);
        }
        return "";
    }

    public Method getMainMethod() {
        return getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    /*参考源码没有判断method.isStatic()*/
    private Method getStaticMethod(String name, String descriptor) {
        for (Method method : methods) {
            if (method.isStatic() && method.name().equals(name) && method.descriptor().equals(descriptor)) {
                return method;
            }
        }
        return null;
    }

    public _Object newObject() {
        return new _Object(this);
    }

    //此类的引用可否指向other类的对象（或者说：other类的对象可否赋给此类的引用）（多态的实现）
    /*参考源码写法与原书不一致，反过来了*/
    public boolean isAssignableFrom(Class other) {
        if (this == other) return true;
        if (!this.isInterface()) {
            return other.isSubClassOf(this);
        }
        else {
            return other.isImplements(this);
        }
    }

    //此类是否是other类的子类
    public boolean isSubClassOf(Class other) {
        for (Class c = this.superClass; c != null; c = c.superClass) {
            if (c == other) return true;
        }
        return false;
    }

    //此类是否implements了iface接口
    private boolean isImplements(Class iface) {
        for (Class c = this; c != null; c = c.superClass) {
            for (Class clazz : c.interfaces) {
                if (clazz == iface || clazz.isSubInterfaceOf(iface)) {
                    return true;
                }
            }
        }
        return false;
    }

    //此类是否为iface接口的子接口
    /* 问题：
    1.为何没有对此类进行isInterface()这样的判断？ - 因为仅有isImplements()方法引用了此方法，里面保证调用者是接口，而不是类
    2.在本实现中，接口的继承关系和类的继承关系怎么区分？此方法这么写，是不是指接口可以implements多个接口？（Java语言层面上显然不对）（此问题可能要结合接口的加载过程来看）
    */
    private boolean isSubInterfaceOf(Class iface) {
        for (Class superInterface : interfaces) {
            if (superInterface == iface || superInterface.isSubInterfaceOf(iface)) {
                return true;
            }
        }
        return false;
    }

    public String name() { return name; }
    public String superClassName() { return superClassName; }
    public String[] interfaceNames() { return interfaceNames; }
    public RuntimeConstantPool constantPool() { return runtimeConstantPool; }
    public Field[] fields() { return fields; }
    public Method[] methods() { return methods; }

    public Slots staticVars() { return staticVars; }

    public boolean isPublic() { return 0 != (accessFlags & AccessFlags.ACC_PUBLIC); }
    public boolean isFinal() { return 0 != (accessFlags & AccessFlags.ACC_FINAL); }
    public boolean isSuper() { return 0 != (accessFlags & AccessFlags.ACC_SUPER); }
    public boolean isInterface() { return 0 != (accessFlags & AccessFlags.ACC_INTERFACE); }
    public boolean isAbstract() { return 0 != (accessFlags & AccessFlags.ACC_ABSTRACT); }
    public boolean isSynthetic() { return 0 != (accessFlags & AccessFlags.ACC_SYNTHETIC); }
    public boolean isAnnotation() { return 0 != (accessFlags & AccessFlags.ACC_ANNOTATION); }
    public boolean isEnum() { return 0 != (accessFlags & AccessFlags.ACC_ENUM); }
}
