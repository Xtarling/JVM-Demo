## v01：命令行解析器

**更新内容**

- 借助JCommander实现简单的命令行参数解析。

**新增源文件**

- Main.java：程序主入口类。其main(String[] args)方法作为整个程序的主入口，传入参数为命令行参数。
- Cmd.java：命令行参数类。内部借助JCommander实现命令行参数的解析，并存放相关参数。

**项目结构**

```shell
wyx-jvm-demo-v01
|   pom.xml
|
\---src
    +---main
    |   \---java
    |       \---cn
    |           \---wyx
    |               \---demo
    |                   \---jvm
    |                           Cmd.java
    |                           Main.java
    |
    \---test
        \---java
            \---cn
                \---wyx
                    \---demo
                        \---test
                                HelloWorld.java
```

**配置参数与测试结果**

```java
/*
参数配置位置：Run/Debug Configurations -> Program arguments
即：Main.main(String[] args)的args参数
测试结果为控制台输出结果
*/

//-------------测试1-------------
//配置参数
-version

//测试结果
java version "1.8.0"


//-------------测试2-------------
//配置参数
-?

//测试结果
Usage: <main class> [-options] class [args...]
```

