package cn.wyx.demo.jvm.runtimedataarea.heap.constantpool;

/**
 * @author WYX
 * @date 2021-4-20 - 17:30
 * --------------------------------
 * 访问标志
 */
public class AccessFlags {

    /*参考源码对各访问标志没加final，我认为加final比较好*/
                                                        // 标记作用类型
    public static final int ACC_PUBLIC        = 0x0001; // class field method
    public static final int ACC_PRIVATE       = 0x0002; //       field method
    public static final int ACC_PROTECTED     = 0x0004; //       field method
    public static final int ACC_STATIC        = 0x0008; //       field method
    public static final int ACC_FINAL         = 0x0010; // class field method
    public static final int ACC_SUPER         = 0x0020; // class
    public static final int ACC_SYNCHRONIZED  = 0x0020; //             method
    public static final int ACC_VOLATILE      = 0x0040; //       field
    public static final int ACC_BRIDGE        = 0x0040; //             method
    public static final int ACC_TRANSIENT     = 0x0080; //       field
    public static final int ACC_VARARGS       = 0x0080; //             method
    public static final int ACC_NATIVE        = 0x0100; //             method
    public static final int ACC_INTERFACE     = 0x0200; // class
    public static final int ACC_ABSTRACT      = 0x0400; // class       method
    public static final int ACC_STRICTFP      = 0x0800; //             method //原书写的是ACC_STRICT
    public static final int ACC_SYNTHETIC     = 0x1000; // class field method
    public static final int ACC_ANNOTATION    = 0x2000; // class
    public static final int ACC_ENUM          = 0x4000; // class field
}
