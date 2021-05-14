package cn.wyx.demo.jvm;

import cn.wyx.demo.jvm.classpath.Classpath;
import cn.wyx.demo.jvm.runtimedataarea.heap.ClassLoader;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Method;

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
        System.out.printf("JREpath: %s\nuserclasspath: %s\nclass: %s\nargs: %s\n", cmd.jre, cmd.classpath, cmd.getMainClass(), cmd.getAppArgs());
        System.out.println("###########################[Starting Java Program...]###########################");

        Classpath classpath = new Classpath(cmd.jre, cmd.classpath);
        ClassLoader classLoader = new ClassLoader(classpath);
        String className = cmd.getMainClass().replace(".", "/");
        Class mainClass = classLoader.loadClass(className);
        Method mainMethod = mainClass.getMainMethod();

        if (null == mainMethod) {
            throw new RuntimeException("Main method not found in class: " + cmd.getMainClass());
        }
        new Interpret(mainMethod);
    }

}
