package cn.wyx.demo.jvm;

import cn.wyx.demo.jvm.classpath.ClassPath;

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
        ClassPath cp = new ClassPath(cmd.jre, cmd.classpath);
        System.out.println("JREpath: " + cmd.jre);
        System.out.println("userclasspath: " + cmd.classpath);
        System.out.println("class: " + cmd.getMainClass() + " args: " + cmd.getAppArgs());
//        System.out.printf("classpath: %s class: %s args: %s\n", cmd.classpath, cmd.getMainClass(), cmd.getAppArgs());

        String className = cmd.getMainClass().replace(".", "/"); //windows和linux路径分隔符不一样，不知道会不会导致问题？
//        String className = cmd.getMainClass().replace(".", "\\");
        try {
            byte[] classData = cp.readClass(className);

//            System.out.println(Arrays.toString(classData));
            //以16进制打印输出
            System.out.println("classData:");
            for (byte b : classData) {
                System.out.print(String.format("%02x", b & 0xff) + " ");
//                System.out.printf("%02x ", b);
            }
        } catch (Exception e) {
            System.out.println("Could not find or load main class " + cmd.getMainClass());
            e.printStackTrace();
        }
    }
}
