package cn.wyx.demo.jvm.classpath.impl;

import cn.wyx.demo.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author WYX
 * @date 2021/3/5 - 15:19
 * --------------------------------
 * 目录形式的类路径
 */
public class DirEntry implements Entry {

    private Path absolutePath;

    public DirEntry(String path) {
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    //从本目录(absolutePath)中将给定文件名读取成字节数组
    @Override
    public byte[] readClass(String className) throws IOException {
        return Files.readAllBytes(absolutePath.resolve(className));
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
