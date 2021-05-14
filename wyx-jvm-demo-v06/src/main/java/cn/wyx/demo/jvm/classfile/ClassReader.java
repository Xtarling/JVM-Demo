package cn.wyx.demo.jvm.classfile;

import java.math.BigInteger;

/**
 * @author WYX
 * @date 2021/3/8 - 19:45
 * --------------------------------
 * class文件读取器
 * JVM定义了三种数据类型：
 * u1   1字节无符号整数
 * u2   2字节无符号整数
 * u4   4字节无符号整数
 * 对Java实现的JVM来说，由于Java基本数据类型中byte/short/int/long/float/double都是有符号数，
 * 因此对上述三种数据类型需要增位存储，即u1、u2用int存，u4用long存。
 */
public class ClassReader {

    private byte[] data;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    //u1，读1字节
    public int readUint8() {
        byte[] val = readByte(1);
        return byte2int(val);
    }

    //u2，读2字节
    public int readUint16() {
        byte[] val = readByte(2);
        return byte2int(val);
    }

    //u4，读4字节
    public long readUint32() {
        byte[] val = readByte(4);
        String str_hex = new BigInteger(1, val).toString(16);
        return Long.parseLong(str_hex, 16);
    }

    //读4个字节，并转换为Java的int类型数据（32位有符号整数）
    public int readUint32TInteger() {
        byte[] val = readByte(4);
        return new BigInteger(1, val).intValue();
    }

    public long readUint64TLong() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).longValue();
    }

    public float readUint64TFloat() { //为何不是readUint32TFloat？
        byte[] val = readByte(8);
        return new BigInteger(1, val).floatValue();
    }

    public double readUint64TDouble() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).doubleValue();
    }

    public int[] readUint16s() {
        int n = this.readUint16();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = this.readUint16();
        }
        return s;
    }

    public byte[] readBytes(int n) {
        return readByte(n);
    }

    private byte[] readByte(int length) {
        //将data的前length个元素复制到copy里，并将剩余的元素提到前面去，然后返回copy
        byte[] copy = new byte[length];
        System.arraycopy(data, 0, copy, 0, length);
        System.arraycopy(data, length, data, 0, data.length - length);
        return copy;
    }

    private int byte2int(byte[] val) {
        //将val中的每个元素转换成2位16进制后拼接成字符串，再将字符串转换成int类型
        String str_hex = new BigInteger(1, val).toString(16);
        return Integer.parseInt(str_hex, 16);
    }
}
