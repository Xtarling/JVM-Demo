package cn.wyx.demo.jvm.classpath.impl;

import cn.wyx.demo.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author WYX
 * @date 2021/3/7 - 20:13
 * --------------------------------
 * .zip 或 .jar形式的类路径
 */
public class ZipEntry implements Entry {

    private Path absolutePath;

    public ZipEntry(String path) {
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        //将.zip文件解析成一个文件系统？
        try (FileSystem zipFs = FileSystems.newFileSystem(absolutePath, null)) {
            return Files.readAllBytes(zipFs.getPath(className));
        }
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
