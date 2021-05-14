## v05：指令集和解释器

**更新内容**

- 修改了运行时数据区中相关的类；实现了JVM字节码指令表中的约150条指令；完成一个简易的字节码解释器，可实现大多数字节码指令的解释执行。

**新增源文件**

- cn.wyx.demo.jvm.instructions包：实现JVM字节码指令表中的约150条指令
- cn.wyx.demo.jvm.Interpret.java：一个简单的字节码解释器，可执行main()函数。目前还不能进行方法的调用。

**项目结构**

```shell
wyx-jvm-demo-v05
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
    |                       |   Interpret.java //字节码指令解释器
    |                       |   Main.java
    |                       |
    |                       +---classfile
    |                       |   \...（无新增源文件）
    |                       |
    |                       +---classpath
    |                       |   \...（无新增源文件）
    |                       |
    |                       +---instructions
    |                       |   |   Factory.java
    |                       |   |
    |                       |   \...（约150条指令的实现类）
    |                       |
    |                       \---runtimedataarea
    |                               Frame.java
    |                               JVMStack.java
    |                               LocalVars.java
    |                               OperandStack.java
    |                               Slot.java
    |                               Thread.java
    |
    \---test
        \---java
            \---cn
                \---wyx
                    \---demo
                        \---test
                                ArithmeticTest.java
                                BytecodeTest.java
                                ConversionTest.java
                                GaussTest.java
                                HelloWorld.java
```

**配置参数与测试结果**

```java
//-------------测试1-------------
//配置参数
-Xjre
D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
-classpath
D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v05\target\test-classes
cn.wyx.demo.test.ArithmeticTest

//测试用例
public class ArithmeticTest {
    public static void main(String[] args) {
        int a = 12, b = 6;
        int sum = a + b;
        int sub = a - b;
        int mul = a * b;
        int div = a / b;
    }
}

//测试结果
JREpath: D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
userclasspath: D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v05\target\test-classes
class: cn.wyx.demo.test.ArithmeticTest
args: null
###########################[Begin Running...]###########################
寄存器（指令）：0x10 -> BIPUSH => 局部变量表：[{"num":0},{"num":0},{"num":0},{"num":0},{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":0},{"num":0}]
寄存器（指令）：0x3c -> ISTORE_1 => 局部变量表：[{"num":0},{"num":0},{"num":0},{"num":0},{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":12},{"num":0}]
寄存器（指令）：0x10 -> BIPUSH => 局部变量表：[{"num":0},{"num":12},{"num":0},{"num":0},{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":12},{"num":0}]
寄存器（指令）：0x3d -> ISTORE_2 => 局部变量表：[{"num":0},{"num":12},{"num":0},{"num":0},{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":6},{"num":0}]
......
寄存器（指令）：0x1b -> ILOAD_1 => 局部变量表：[{"num":0},{"num":12},{"num":6},{"num":18},{"num":6},{"num":72},{"num":0}] 操作数栈：[{"num":72},{"num":6}]
寄存器（指令）：0x1c -> ILOAD_2 => 局部变量表：[{"num":0},{"num":12},{"num":6},{"num":18},{"num":6},{"num":72},{"num":0}] 操作数栈：[{"num":12},{"num":6}]
寄存器（指令）：0x6c -> IDIV => 局部变量表：[{"num":0},{"num":12},{"num":6},{"num":18},{"num":6},{"num":72},{"num":0}] 操作数栈：[{"num":12},{"num":6}]
寄存器（指令）：0x36 -> ISTORE => 局部变量表：[{"num":0},{"num":12},{"num":6},{"num":18},{"num":6},{"num":72},{"num":0}] 操作数栈：[{"num":2},{"num":6}]
寄存器（指令）尚未实现：0xb1

   
//-------------测试2-------------
//配置参数
-Xjre
D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
-classpath
D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v05\target\test-classes
cn.wyx.demo.test.GaussTest
    
//测试用例
public class GaussTest {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}

//测试结果
JREpath: D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
userclasspath: D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v05\target\test-classes
class: cn.wyx.demo.test.GaussTest
args: null
###########################[Begin Running...]###########################
寄存器（指令）：0x03 -> ICONST_0 => 局部变量表：[{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":0},{"num":0}]
寄存器（指令）：0x3c -> ISTORE_1 => 局部变量表：[{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":0},{"num":0}]
寄存器（指令）：0x04 -> ICONST_1 => 局部变量表：[{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":0},{"num":0}]
寄存器（指令）：0x3d -> ISTORE_2 => 局部变量表：[{"num":0},{"num":0},{"num":0}] 操作数栈：[{"num":1},{"num":0}]
寄存器（指令）：0x1c -> ILOAD_2 => 局部变量表：[{"num":0},{"num":0},{"num":1}] 操作数栈：[{"num":1},{"num":0}]
寄存器（指令）：0x10 -> BIPUSH => 局部变量表：[{"num":0},{"num":0},{"num":1}] 操作数栈：[{"num":1},{"num":0}]
寄存器（指令）：0xa3 -> IF_ICMPGT => 局部变量表：[{"num":0},{"num":0},{"num":1}] 操作数栈：[{"num":1},{"num":100}]
寄存器（指令）：0x1b -> ILOAD_1 => 局部变量表：[{"num":0},{"num":0},{"num":1}] 操作数栈：[{"num":1},{"num":100}]
寄存器（指令）：0x1c -> ILOAD_2 => 局部变量表：[{"num":0},{"num":0},{"num":1}] 操作数栈：[{"num":0},{"num":100}]
寄存器（指令）：0x60 -> IADD => 局部变量表：[{"num":0},{"num":0},{"num":1}] 操作数栈：[{"num":0},{"num":1}]
寄存器（指令）：0x3c -> ISTORE_1 => 局部变量表：[{"num":0},{"num":0},{"num":1}] 操作数栈：[{"num":1},{"num":1}]
寄存器（指令）：0x84 -> IINC => 局部变量表：[{"num":0},{"num":1},{"num":1}] 操作数栈：[{"num":1},{"num":1}]
寄存器（指令）：0xa7 -> GOTO => 局部变量表：[{"num":0},{"num":1},{"num":2}] 操作数栈：[{"num":1},{"num":1}]
寄存器（指令）：0x1c -> ILOAD_2 => 局部变量表：[{"num":0},{"num":1},{"num":2}] 操作数栈：[{"num":1},{"num":1}]
......
寄存器（指令）：0x1c -> ILOAD_2 => 局部变量表：[{"num":0},{"num":4950},{"num":100}] 操作数栈：[{"num":4950},{"num":99}]
寄存器（指令）：0x10 -> BIPUSH => 局部变量表：[{"num":0},{"num":4950},{"num":100}] 操作数栈：[{"num":100},{"num":99}]
寄存器（指令）：0xa3 -> IF_ICMPGT => 局部变量表：[{"num":0},{"num":4950},{"num":100}] 操作数栈：[{"num":100},{"num":100}]
寄存器（指令）：0x1b -> ILOAD_1 => 局部变量表：[{"num":0},{"num":4950},{"num":100}] 操作数栈：[{"num":100},{"num":100}]
寄存器（指令）：0x1c -> ILOAD_2 => 局部变量表：[{"num":0},{"num":4950},{"num":100}] 操作数栈：[{"num":4950},{"num":100}]
寄存器（指令）：0x60 -> IADD => 局部变量表：[{"num":0},{"num":4950},{"num":100}] 操作数栈：[{"num":4950},{"num":100}]
寄存器（指令）：0x3c -> ISTORE_1 => 局部变量表：[{"num":0},{"num":4950},{"num":100}] 操作数栈：[{"num":5050},{"num":100}]
寄存器（指令）：0x84 -> IINC => 局部变量表：[{"num":0},{"num":5050},{"num":100}] 操作数栈：[{"num":5050},{"num":100}]
寄存器（指令）：0xa7 -> GOTO => 局部变量表：[{"num":0},{"num":5050},{"num":101}] 操作数栈：[{"num":5050},{"num":100}]
寄存器（指令）：0x1c -> ILOAD_2 => 局部变量表：[{"num":0},{"num":5050},{"num":101}] 操作数栈：[{"num":5050},{"num":100}]
寄存器（指令）：0x10 -> BIPUSH => 局部变量表：[{"num":0},{"num":5050},{"num":101}] 操作数栈：[{"num":101},{"num":100}]
寄存器（指令）：0xa3 -> IF_ICMPGT => 局部变量表：[{"num":0},{"num":5050},{"num":101}] 操作数栈：[{"num":101},{"num":100}]
寄存器（指令）尚未实现：0xb2
```

**一些优化想法**

- 可针对指令集部分的类设计进行优化，提取冗余代码。如将ILOAD、ILOAD_0、ILOAD_1、ILOAD_2、ILOAD_3的代码合并处理。
- 可对于某些指令（如无操作数的指令）设置单例对象，以防止在Factory.newInstruction()方法中重复生成新对象，浪费资源。