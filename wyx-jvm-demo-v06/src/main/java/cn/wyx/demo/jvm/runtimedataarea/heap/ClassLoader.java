package cn.wyx.demo.jvm.runtimedataarea.heap;

import cn.wyx.demo.jvm.classfile.ClassFile;
import cn.wyx.demo.jvm.classpath.Classpath;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.RuntimeConstantPool;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Field;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Slots;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WYX
 * @date 2021-4-20 - 17:20
 * --------------------------------
 * 类加载器
 * 类的加载过程：
 * 1.加载
 * 2.链接：验证 -> 准备 -> 解析
 * 3.初始化
 */
public class ClassLoader {

    private final Classpath classpath;
    private final Map<String, Class> classMap;

    public ClassLoader(Classpath classpath) {
        this.classpath = classpath;
        this.classMap = new HashMap<>();
    }

    public Class loadClass(String className) {
        Class clazz = classMap.get(className);
        if (null != clazz) return clazz;
        try {
            return loadNonArrayClass(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class loadNonArrayClass(String className) throws Exception {
        byte[] data = classpath.readClass(className);
        if (null == data) {
            throw new ClassNotFoundException(className);
        }
        Class clazz = defineClass(data);
        link(clazz);
        return clazz;
    }

    private Class defineClass(byte[] data) {
        Class clazz = parseClass(data);
        clazz.loader = this;
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        classMap.put(clazz.name(), clazz);
        return clazz;
    }

    //链接。含验证、准备两个过程
    private void link(Class clazz) {
        verify(clazz);
        prepare(clazz);
    }

    private Class parseClass(byte[] data) {
        ClassFile classFile = new ClassFile(data);
        return new Class(classFile);
    }

    private void resolveSuperClass(Class clazz) {
        if (!clazz.name().equals("java/lang/Object")) {
            clazz.superClass = clazz.loader.loadClass(clazz.superClassName());
        }
    }

    private void resolveInterfaces(Class clazz) {
        int interfaceCount = clazz.interfaceNames().length;
        if (interfaceCount > 0) {
            clazz.interfaces = new Class[interfaceCount];
            for (int i = 0; i < interfaceCount; i++) {
                clazz.interfaces[i] = clazz.loader.loadClass(clazz.interfaceNames()[i]);
            }
        }
    }

    //验证
    private void verify(Class clazz) {
        //字节码验证。原书未实现
    }

    //准备
    private void prepare(Class clazz) {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    //给实例字段赋slotId值，同时计算实例字段数量
    private void calcInstanceFieldSlotIds(Class clazz) {
        int slotId = 0;
        if (clazz.superClass != null) {
            slotId = clazz.superClass.instanceSlotCount;
        }
        for (Field field : clazz.fields()) {
            if (!field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) slotId++;
            }
        }
        clazz.instanceSlotCount = slotId;
    }

    //给静态字段赋slotId值，同时计算静态字段数量
    private void calcStaticFieldSlotIds(Class clazz) {
        int slotId = 0;
        for (Field field : clazz.fields()) {
            if (field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) slotId++;
            }
        }
        clazz.staticSlotCount = slotId;
    }

    private void allocAndInitStaticVars(Class clazz) {
        clazz.staticVars = new Slots(clazz.staticSlotCount);
        for (Field field : clazz.fields()) {
            /* 《深入理解Java虚拟机 第2版》 6.3.7属性表集合-6.ConstantValue属性 其中提到：
            * 如果同时使用final和static修饰一个变量，且此变量类型为基本数据类型或java.lang.String，
            * 则生成ConstantValue属性进行初始化；不满足上述条件的static变量则在<clinit>方法中初始化。
            * */
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz, field);
            }
        }
    }

    private void initStaticFinalVar(Class clazz, Field field) {
        Slots staticVars = clazz.staticVars;
        RuntimeConstantPool cp = clazz.constantPool();
        int cpIndex = field.constValueIndex();
        int slotId = field.slotId();

        if (cpIndex > 0) {
            Object val = cp.getConstant(cpIndex);
            switch (field.descriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    staticVars.setInt(slotId, (Integer) val);
                    break;
                case "J":
                    staticVars.setLong(slotId, (Long) val);
                    break;
                case "F":
                    staticVars.setFloat(slotId, (Float) val);
                    break;
                case "D":
                    staticVars.setDouble(slotId, (Double) val);
                    break;
                case "Ljava/lang/String;":
                    System.out.println("todo"); //第8章实现
                    break;
                default:
                    break;
            }
        }
    }
}
