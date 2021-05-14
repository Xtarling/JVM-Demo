package cn.wyx.demo.jvm.classpath;

import cn.wyx.demo.jvm.classpath.impl.CompositeEntry;
import cn.wyx.demo.jvm.classpath.impl.DirEntry;
import cn.wyx.demo.jvm.classpath.impl.WildcardEntry;
import cn.wyx.demo.jvm.classpath.impl.ZipEntry;

import java.io.File;
import java.io.IOException;

/**
 * @author WYX
 * @date 2021/3/5 - 15:18
 * --------------------------------
 * 类路径接口
 */
public interface Entry {
    byte[] readClass(String className) throws IOException;

    //根据类路径字符串的类型返回相应的路径类对象
    static Entry create(String path) {

        if (path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }

        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }

        if (path.endsWith(".jar") || path.endsWith(".JAR") || path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }
}
