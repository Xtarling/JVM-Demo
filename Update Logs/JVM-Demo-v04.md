## v04：运行时数据区（部分）

**更新内容**

- 实现运行时数据区的基本功能。

**新增源文件**

- cn.wyx.demo.jvm.runtimedataarea包。内含运行时数据区的部分实现类：栈帧、虚拟机栈、局部变量表、操作数栈、Slot、线程等

**项目结构**

```shell
wyx-jvm-demo-v04
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
    |                       |   Main.java
    |                       |
    |                       +---classfile
    |                       |   \...（无新增源文件）
    |                       |
    |                       +---classpath
    |                       |   \...（无新增源文件）
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
                                HelloWorld.java
                                TestClass.java
```

**配置参数与测试结果**

```java
//-------------测试1-------------
//配置参数
-Xjre
D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
-classpath
D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v04\target\test-classes
cn.wyx.demo.test.HelloWorld

//测试结果
LocalVars:
100
-100
OperandStack:
null
-100
100
```

