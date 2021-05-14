package cn.wyx.demo.jvm.classfile.attributes.impl;

import cn.wyx.demo.jvm.classfile.ClassReader;
import cn.wyx.demo.jvm.classfile.attributes.AttributeInfo;

/**
 * @author WYX
 * @date 2021/3/10 - 19:25
 * --------------------------------
 * 描述字节码指令和Java源码行号的对应关系
 */
public class LineNumberTableAttribute implements AttributeInfo {

    private LineNumberTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readUint16();
        lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(reader.readUint16(), reader.readUint16());
        }
    }

    public int getLineNumber(int pc) {
        for (int i = lineNumberTable.length - 1; i >= 0; i--) { //倒着找的原因？ //可参考反编译的字节码，一条程序代码行号对应多行字节码指令pc
            LineNumberTableEntry entry = lineNumberTable[i];
            if (pc >= entry.startPC) {
                return entry.lineNumber;
            }
        }
        return -1;
    }

    static class LineNumberTableEntry {
        private int startPC;
        private int lineNumber;

        public LineNumberTableEntry(int startPC, int lineNumber) {
            this.startPC = startPC;
            this.lineNumber = lineNumber;
        }
    }
}
