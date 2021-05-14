package cn.wyx.demo.jvm.instructions.base;

/**
 * @author WYX
 * @date 2021-3-13 - 23:17
 * --------------------------------
 * 字节码阅读器 (目前仅用于阅读Code属性中的字节码指令)
 */
public class BytecodeReader {

    private byte[] codes;
    private int pc;

    public void reset(byte[] codes, int pc) {
        this.codes = codes;
        this.pc = pc;
    }

    public int pc() {
        return pc;
    }

    //[go]int8 = [java]byte //?
    public byte readByte() {
        byte code = codes[pc];
        pc++;
        return code;
    }

    //[go]int16 = [java]short //? 这算大端存储还是小端存储？
    public short readShort() {
        byte byte1 = readByte();
        byte byte2 = readByte();
        return (short) ((byte1 << 8) | byte2);
    }

    public int readInt() {
        int byte1 = readByte();
        int byte2 = readByte();
        int byte3 = readByte();
        int byte4 = readByte();
        return (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4;
    }

    //used by lookupswitch and tableswitch
    public int[] readInts(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = readInt();
        }
        return ints;
    }

    //used by lookupswitch and tableswitch //跳过对齐填充
    public void skipPadding() {
        while (pc % 4 != 0) {
            readByte();
        }
    }
}
