package cn.wyx.demo.jvm.classpath.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author WYX
 * @date 2021/3/7 - 20:13
 * --------------------------------
 * 通配符类路径，本质上是套了复合类路径的壳子
 */
public class WildcardEntry extends CompositeEntry {

    public WildcardEntry(String path) {
        super(toPathList(path));
    }

    //需要进一步研究
    private static String toPathList(String wildcardPath) {
        String baseDir = wildcardPath.replace("*", ""); //去掉通配符”*“
        try {
            return Files.walk(Paths.get(baseDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))
                    .collect(Collectors.joining(File.pathSeparator));
        } catch (IOException e) {
            return "";
        }
    }
}
