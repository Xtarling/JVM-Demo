package cn.wyx.demo.jvm.classfile.constantpool;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.constantpool.impl.ConstantClassInfo;
import cn.wyx.demo.jvm.classfile.constantpool.impl.ConstantNameAndTypeInfo;
import cn.wyx.demo.jvm.classfile.constantpool.impl.ConstantUTF8Info;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WYX
 * @date 2021/3/8 - 19:48
 * --------------------------------
 * 常量池
 */
public class ConstantPool {

    private final ConstantInfo[] constantInfos;
    private final int size;

    public ConstantPool(ClassReader reader) {
        size = reader.readUint16();
        constantInfos = new ConstantInfo[size];
        for (int i = 1; i < size; i++) { //常量池索引从1开始，0空出来作它用
            constantInfos[i] = ConstantInfo.readConstantInfo(reader, this);
            switch (constantInfos[i].tag()) {
                case ConstantInfo.CONSTANT_TAG_LONG:
                case ConstantInfo.CONSTANT_TAG_DOUBLE:
                    i++;
                    break;
            }
        }
    }

    public Map<String, String> getNameAndType(int idx) {
        ConstantNameAndTypeInfo constantInfo = (ConstantNameAndTypeInfo) this.constantInfos[idx];
        Map<String, String> map = new HashMap<>();
        map.put("name", this.getUTF8(constantInfo.nameIdx));
        map.put("_type", this.getUTF8(constantInfo.descIdx)); //为何"_type"？
        return map;
    }

    public String getClassName(int idx) {
        ConstantClassInfo classInfo = (ConstantClassInfo) this.constantInfos[idx];
        return this.getUTF8(classInfo.nameIdx);
    }

    public String getUTF8(int idx) {
        ConstantUTF8Info utf8Info = (ConstantUTF8Info) this.constantInfos[idx];
        return utf8Info == null ? "" : utf8Info.str();
    }

    //Getter
    public ConstantInfo[] constantInfos() {
        return this.constantInfos;
    }
    public int size() {
        return this.size;
    }
}
