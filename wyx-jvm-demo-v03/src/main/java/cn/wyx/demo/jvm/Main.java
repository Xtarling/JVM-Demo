package cn.wyx.demo.jvm;

import cn.wyx.demo.jvm.classfile.ClassFile;
import cn.wyx.demo.jvm.classfile.MemberInfo;
import cn.wyx.demo.jvm.classpath.Classpath;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author WYX
 * @date 2021/3/4 - 16:02
 * --------------------------------
 * 程序主入口
 */
public class Main {

    public static void main(String[] args) {
        Cmd cmd = Cmd.parse(args);
        if (!cmd.ok || cmd.helpFlag) {
            System.out.println("Usage: <main class> [-options] class [args...]");
            return;
        }
        if (cmd.versionFlag) {
            System.out.println("java version \"1.8.0\"");
            return;
        }
        startJVM(cmd);
    }

    private static void startJVM(Cmd cmd) {
        Classpath classpath = new Classpath(cmd.jre, cmd.classpath);
        System.out.println("JREpath: " + cmd.jre
                + "\nuserclasspath: " + cmd.classpath
                + "\nclass: " + cmd.getMainClass()
                + "\nargs: " + cmd.getAppArgs());
        String className = cmd.getMainClass().replace(".", "/"); //windows和linux路径分隔符不一样，不知道会不会导致问题？
        ClassFile classFile = loadClass(className, classpath);
//        assert classFile != null; //?
        printClassInfo(classFile);
    }

    private static ClassFile loadClass(String className, Classpath classpath) {
        try {
            byte[] classData = classpath.readClass(className);
            return new ClassFile(classData);
        } catch (IOException e) {
            System.out.println("Could not find or load main class: " + className);
//            e.printStackTrace();
            return null;
        }
    }

    private static void printClassInfo(ClassFile cf) {
        System.out.println("###########################[Class Info]###########################");
        System.out.println("version: " + cf.majorVersion() + "." + cf.minorVersion());
        System.out.println("constants count: " + cf.constantPool().getSiz());
        System.out.format("access flags: 0x%x\n", cf.accessFlags());
        System.out.println("this class: " + cf.className());
        System.out.println("super class: " + cf.superClassName());
        System.out.println("interfaces: " + Arrays.toString(cf.interfaceNames()));
        System.out.println("fields count: " + cf.fields().length);
        for (MemberInfo memberInfo : cf.fields()) {
            System.out.format("%s \t\t %s\n", memberInfo.name(), memberInfo.descriptor());
        }
        System.out.println("methods count: " + cf.methods().length);
        for (MemberInfo memberInfo : cf.methods()) {
            System.out.format("%s \t\t %s\n", memberInfo.name(), memberInfo.descriptor());
        }
    }
}
