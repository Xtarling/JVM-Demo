## v06：类和对象

**更新内容**

- 实现了方法区和运行时常量池数据结构，增加一个类加载器，实现类的加载、创建对象和符号引用的解析。
- 实现常量池引用相关的指令操作。
- 对线程私有的运行时数据区结构相关类、Interpret类作了一些修改。

**新增源文件**

- 新增runtimedataarea.heap包，内含方法区、运行时常量池、一个ClassLoader类
- 新增instructions.references包、instructions.constants.ldc包，内含引用相关指令类。

**项目结构**

```shell
wyx-jvm-demo-v06
|   pom.xml
|
\---src
    +---main
    |   \---java
    |       \---cn
    |           \---wyx
    |               \---demo
    |                   \---jvm
    |                       |   Cmd.java
    |                       |   Interpret.java
    |                       |   Main.java
    |                       |
    |                       +---classfile
    |                       +---classpath
    |                       +---instructions
    |                       |   |   Factory.java
    |                       |   |
    |                       |   +---base
    |                       |   +---comparisons
    |                       |   +---constants
    |                       |   |   +---consts
    |                       |   |   +---ipush
    |                       |   |   +---ldc
    |                       |   |   |       LDC.java
    |                       |   |   |       LDC2_W.java
    |                       |   |   |       LDC_W.java
    |                       |   |   |
    |                       |   |   \---nop
    |                       |   |
    |                       |   +---control
    |                       |   +---conversions
    |                       |   +---extended
    |                       |   +---loads
    |                       |   +---math
    |                       |   +---references
    |                       |   |       CHECK_CAST.java
    |                       |   |       GET_FIELD.java
    |                       |   |       GET_STATIC.java
    |                       |   |       INSTANCE_OF.java
    |                       |   |       INVOKE_SPECIAL.java
    |                       |   |       INVOKE_VIRTUAL.java
    |                       |   |       NEW.java
    |                       |   |       PUT_FIELD.java
    |                       |   |       PUT_STATIC.java
    |                       |   |
    |                       |   +---stack
    |                       |   \---stores
    |                       |
    |                       \---runtimedataarea
    |                           |   Frame.java
    |                           |   JVMStack.java
    |                           |   LocalVars.java
    |                           |   OperandStack.java
    |                           |   Slot.java
    |                           |   Thread.java
    |                           |
    |                           \---heap
    |                               |   ClassLoader.java
    |                               |
    |                               +---constantpool
    |                               |       AccessFlags.java
    |                               |       ClassRef.java
    |                               |       FieldRef.java
    |                               |       InterfaceMethodRef.java
    |                               |       MemberRef.java
    |                               |       MethodRef.java
    |                               |       RuntimeConstantPool.java
    |                               |       SymRef.java
    |                               |
    |                               \---methodarea
    |                                       Class.java
    |                                       ClassMember.java
    |                                       Field.java
    |                                       Method.java
    |                                       Slots.java
    |                                       _Object.java
    |
    \---test
        \---java
            \---cn
                \---wyx
                    \---demo
                        \---test
                                ArithmeticTest.java
                                GaussTest.java
                                HelloWorld.java
                                MyObject.java
                                MyObjectTest.java
                                PutFieldTest.java
```

**配置参数与测试结果**

```java
//-------------测试1-------------
//配置参数
-Xjre
D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
-classpath
D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v06\target\test-classes
cn.wyx.demo.test.MyObject

//测试用例
public class MyObject {
    public static int staticVar;
    public int instanceVar;

    public static void main(String[] args) {
        int x = 32768;                      //LDC
        MyObject myObj = new MyObject();    //NEW
        MyObject.staticVar = x;             //PUT_STATIC
        x = MyObject.staticVar;             //GET_STATIC
        myObj.instanceVar = x;              //PUT_FIELD
        x = myObj.instanceVar;              //GET_FIELD
        Object obj = myObj;
        if (obj instanceof MyObject) {      //INSTANCE_OF
            myObj = (MyObject) obj;         //CHECK_CAST
            System.out.println(myObj.instanceVar);
        }
    }
}


//测试结果
JREpath: D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
userclasspath: D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v06\target\test-classes
class: cn.wyx.demo.test.MyObject
args: null
###########################[Starting Java Program...]###########################
pc: 0	-> 寄存器（指令）：0x12 -> LDC => 局部变量表：[{"num":0},{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":0},{"num":0}]
pc: 2	-> 寄存器（指令）：0x3c -> ISTORE_1 => 局部变量表：[{"num":0},{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":32768},{"num":0}]
pc: 3	-> 寄存器（指令）：0xbb -> NEW => 局部变量表：[{"num":0},{"num":32768},{"num":0},{"num":0}] 操作数栈：[{"num":32768},{"num":0}]
pc: 6	-> 寄存器（指令）：0x59 -> DUP => 局部变量表：[{"num":0},{"num":32768},{"num":0},{"num":0}] 操作数栈：[{"num":32768,"ref":{}},{"num":0}]
pc: 7	-> 寄存器（指令）：0xb7 -> INVOKE_SPECIAL => 局部变量表：[{"num":0},{"num":32768},{"num":0},{"num":0}] 操作数栈：[{"num":32768,"ref":{}},{"$ref":"$[0]"}]
pc: 10	-> 寄存器（指令）：0x4d -> ASTORE_2 => 局部变量表：[{"num":0},{"num":32768},{"num":0},{"num":0}] 操作数栈：[{"num":32768,"ref":{}},{"$ref":"$[0]"}]
pc: 11	-> 寄存器（指令）：0x1b -> ILOAD_1 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
pc: 12	-> 寄存器（指令）：0xb3 -> PUT_STATIC => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
pc: 15	-> 寄存器（指令）：0xb2 -> GET_STATIC => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
pc: 18	-> 寄存器（指令）：0x3c -> ISTORE_1 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
pc: 19	-> 寄存器（指令）：0x2c -> ALOAD_2 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
pc: 20	-> 寄存器（指令）：0x1b -> ILOAD_1 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768,"ref":{}},{"$ref":"$[0]"}]
pc: 21	-> 寄存器（指令）：0xb5 -> PUT_FIELD => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768,"ref":{}},{"$ref":"$[0]"}]
pc: 24	-> 寄存器（指令）：0x2c -> ALOAD_2 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
pc: 25	-> 寄存器（指令）：0xb4 -> GET_FIELD => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768,"ref":{}},{"$ref":"$[0]"}]
pc: 28	-> 寄存器（指令）：0x3c -> ISTORE_1 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
pc: 29	-> 寄存器（指令）：0x2c -> ALOAD_2 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
pc: 30	-> 寄存器（指令）：0x4e -> ASTORE_3 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0}] 操作数栈：[{"num":32768,"ref":{}},{"$ref":"$[0]"}]
pc: 31	-> 寄存器（指令）：0x2d -> ALOAD_3 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
pc: 32	-> 寄存器（指令）：0xc1 -> INSTANCE_OF => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":32768,"ref":{}},{"$ref":"$[0]"}]
pc: 35	-> 寄存器（指令）：0x99 -> IFEQ => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":1},{"$ref":"$[0]"}]
pc: 38	-> 寄存器（指令）：0x2d -> ALOAD_3 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":1},{"$ref":"$[0]"}]
pc: 39	-> 寄存器（指令）：0xc0 -> CHECK_CAST => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":1,"ref":{}},{"$ref":"$[0]"}]
pc: 42	-> 寄存器（指令）：0x4d -> ASTORE_2 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":1,"ref":{}},{"$ref":"$[0]"}]
pc: 43	-> 寄存器（指令）：0xb2 -> GET_STATIC => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":1},{"$ref":"$[0]"}]
pc: 46	-> 寄存器（指令）：0x2c -> ALOAD_2 => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":1},{"$ref":"$[0]"}]
pc: 47	-> 寄存器（指令）：0xb4 -> GET_FIELD => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":1,"ref":{}},{"$ref":"$[0]"}]
pc: 50	-> 寄存器（指令）：0xb6 -> INVOKE_VIRTUAL => 局部变量表：[{"num":0},{"num":32768},{"num":0,"ref":{}},{"num":0,"ref":{"$ref":"$[2].ref"}}] 操作数栈：[{"num":32768},{"$ref":"$[0]"}]
32768
寄存器（指令）尚未实现：0xb1
```

**一个注意点**

由于之前没有认真看书，以及小傅哥的参考源码中也是这么写的，原书中的Object结构体（原书第4章）在本项目前几章的实现中均用java.lang.Object类代替。但读到第6章后发现这么做是错的，因此本章另写了_Object类，并对之前误用java.lang.Object类的一些地方做了修改。改动源头是Slot类中ref属性的类型改为\_Object。