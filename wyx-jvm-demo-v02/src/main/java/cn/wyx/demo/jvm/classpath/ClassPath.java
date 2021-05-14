package cn.wyx.demo.jvm.classpath;

import cn.wyx.demo.jvm.classpath.impl.WildcardEntry;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author WYX
 * @date 2021/3/5 - 15:18
 * --------------------------------
 * 类路径
 */
public class ClassPath {

    private Entry bootstrapClasspath;   //启动类路径
    private Entry extensionClasspath;   //扩展类路径
    private Entry userClasspath;        //用户类路径

    //获得启动类、扩展类、用户类的路径
    public ClassPath(String jreOption, String cpOption) {
        bootstrapAndExtensionClasspath(jreOption);
        parseUserClasspath(cpOption);
    }

    private void bootstrapAndExtensionClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);

        //启动类路径：../jre/lib/*
        String jreLibPath = Paths.get(jreDir, "lib") + File.separator + "*";
        bootstrapClasspath = new WildcardEntry(jreLibPath);

        //扩展类路径：../jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir, "lib", "ext") + File.separator + "*";
        extensionClasspath = new WildcardEntry(jreExtPath);
    }

    private static String getJreDir(String jreOption) {
        //搜寻JRE路径的优先级：用户输入路径 -> 当前目录 -> 系统环境变量JAVA_HOME目录 -> 搜寻失败
        if (jreOption != null && Files.exists(Paths.get(jreOption))) {
            return jreOption;
        }
        if (Files.exists(Paths.get("./jre"))) { //Windows和Linux的路径分隔符不一样
            return "./jre";
        }
//        if (Files.exists(Paths.get(".\\jre"))) { //Windows和Linux的路径分隔符不一样
//            return ".\\jre";
//        }
        String jh = System.getenv("JAVA_HOME");
        if (jh != null) {
            return Paths.get(jh, "jre").toString();
        }
        throw new RuntimeException("Cannot find JRE folder!");
    }

    private void parseUserClasspath(String cpOption) {
        //若用户未提供该参数，则使用当前目录作为用户类路径
        if (cpOption == null) {
            cpOption = ".";
        }
        userClasspath = Entry.create(cpOption);
    }

    public byte[] readClass(String className) throws Exception {
        //依次从启动类路径、扩展类路径、用户类路径搜寻并加载.class文件
        className = className + ".class";
        try {
            return bootstrapClasspath.readClass(className);
        } catch (Exception ignored) {
            //ignored
        }
        try {
            return extensionClasspath.readClass(className);
        } catch (Exception ignored) {
            //ignored
        }
        return userClasspath.readClass(className);
    }
}
