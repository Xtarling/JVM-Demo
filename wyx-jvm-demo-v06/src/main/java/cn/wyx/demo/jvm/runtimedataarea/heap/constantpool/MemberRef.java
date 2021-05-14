package cn.wyx.demo.jvm.runtimedataarea.heap.constantpool;

import cn.wyx.demo.jvm.classfile.constantpool.impl.ConstantMemberRefInfo;

import java.util.Map;

/**
 * @author WYX
 * @date 2021-4-20 - 17:31
 * --------------------------------
 * 成员（字段、方法）引用
 */
public class MemberRef extends SymRef {

    String name;
    String descriptor;

     void copyMemberRefInfo(ConstantMemberRefInfo refInfo) {
        className = refInfo.className();
        Map<String, String> map = refInfo.nameAndDescriptor();
        name = map.get("name");
        descriptor = map.get("_type");
    }

    public String name() {
        return name;
    }
    public String descriptor() {
        return descriptor;
    }
}
