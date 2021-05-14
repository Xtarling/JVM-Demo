package cn.wyx.demo.jvm;

import cn.wyx.demo.jvm.classfile.ClassFile;
import cn.wyx.demo.jvm.classfile.MemberInfo;
import cn.wyx.demo.jvm.classpath.Classpath;

import java.io.IOException;

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
        String className = cmd.getMainClass().replace(".", "/");
        ClassFile classFile = loadClass(className, classpath);
        MemberInfo mainMethod = getMainMethod(classFile);
        if (null == mainMethod) {
            System.out.println("Main method not found in class: " + cmd.classpath);
            return;
        }
        System.out.println("###########################[Begin Running...]###########################");
        new Interpret(mainMethod);
    }

    private static ClassFile loadClass(String className, Classpath classpath) {
        try {
            byte[] classData = classpath.readClass(className);
            return new ClassFile(classData);
        } catch (IOException e) {
            System.out.println("Could not find or load main class: " + className);
            e.printStackTrace();
            return null;
        }
    }

    private static MemberInfo getMainMethod(ClassFile cf) {
        if (null == cf) return null;
        MemberInfo[] methods = cf.methods();
        for (MemberInfo m : methods) {
            if ("main".equals(m.name()) && "([Ljava/lang/String;)V".equals(m.descriptor())) {
                return m;
            }
        }
        return null;
    }
}
