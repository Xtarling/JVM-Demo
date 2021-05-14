package cn.wyx.demo.jvm.classpath.impl;

import cn.wyx.demo.jvm.classpath.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WYX
 * @date 2021/3/7 - 20:13
 * --------------------------------
 * 复合类路径
 */
public class CompositeEntry implements Entry {

    private final List<Entry> entryList = new ArrayList<>();

    public CompositeEntry(String pathList) {
        //根据路径分隔符拆分字符串
        String[] paths = pathList.split(File.pathSeparator);
        for (String path : paths) {
            entryList.add(Entry.create(path));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry : entryList) {
            try {
                return entry.readClass(className);
            } catch (Exception ignored) {
                //ignored //为何这么写？
            }
        }
        throw new IOException("class not found " + className);
    }

    @Override
    public String toString() {
        String[] strs = new String[entryList.size()];
        for (int i = 0; i < entryList.size(); i++) {
            strs[i] = entryList.get(i).toString();
        }
        return String.join(File.pathSeparator, strs);
    }
}
