## v02：寻找类路径与读取字节码文件

**更新内容**

- 增加寻找.class文件的功能
- 增加从.class文件中读取字节码的功能

**新增源文件**

- Entry.java：类路径接口，用于描述类路径。它的四个实现类CompositeEntry.java、DirEntry.java、WildcardEntry.java、ZipEntry.java分别代表复合类路径、目录类路径、带通配符的类路径、.zip/.jar压缩包形式的类路径。
- ClassPath.java：类路径类。用于存储三类类加载路径：启动类路径、扩展类路径、用户类路径。

**项目结构**

```shell
wyx-jvm-demo-v02
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
    |                       \---classpath
    |                           |   ClassPath.java
    |                           |   Entry.java
    |                           |
    |                           \---impl
    |                                   CompositeEntry.java
    |                                   DirEntry.java
    |                                   WildcardEntry.java
    |                                   ZipEntry.java
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
//-------------测试1-------------
//配置参数 (-Xjre [jre路径] -classpath [用户类路径] 要执行的类)
-Xjre
D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
-classpath
D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v02\target\test-classes
cn.wyx.demo.test.HelloWorld    
    
//测试用例
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
    
//测试结果
JREpath: D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
userclasspath: D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v02\target\test-classes
class: cn.wyx.demo.test.HelloWorld args: null
classData:
ca fe ba be 00 00 00 34 00 22 0a 00 06 00 14 09 00 15 00 16 08 00 17 0a 00 18 00 19 07 00 1a 07 00 1b 01 00 06 3c 69 6e 69 74 3e 01 00 03 28 29 56 01 00 04 43 6f 64 65 01 00 0f 4c 69 6e 65 4e 75 6d 62 65 72 54 61 62 6c 65 01 00 12 4c 6f 63 61 6c 56 61 72 69 61 62 6c 65 54 61 62 6c 65 01 00 04 74 68 69 73 01 00 1d 4c 63 6e 2f 77 79 78 2f 64 65 6d 6f 2f 74 65 73 74 2f 48 65 6c 6c 6f 57 6f 72 6c 64 3b 01 00 04 6d 61 69 6e 01 00 16 28 5b 4c 6a 61 76 61 2f 6c 61 6e 67 2f 53 74 72 69 6e 67 3b 29 56 01 00 04 61 72 67 73 01 00 13 5b 4c 6a 61 76 61 2f 6c 61 6e 67 2f 53 74 72 69 6e 67 3b 01 00 0a 53 6f 75 72 63 65 46 69 6c 65 01 00 0f 48 65 6c 6c 6f 57 6f 72 6c 64 2e 6a 61 76 61 0c 00 07 00 08 07 00 1c 0c 00 1d 00 1e 01 00 0d 48 65 6c 6c 6f 2c 20 77 6f 72 6c 64 21 07 00 1f 0c 00 20 00 21 01 00 1b 63 6e 2f 77 79 78 2f 64 65 6d 6f 2f 74 65 73 74 2f 48 65 6c 6c 6f 57 6f 72 6c 64 01 00 10 6a 61 76 61 2f 6c 61 6e 67 2f 4f 62 6a 65 63 74 01 00 10 6a 61 76 61 2f 6c 61 6e 67 2f 53 79 73 74 65 6d 01 00 03 6f 75 74 01 00 15 4c 6a 61 76 61 2f 69 6f 2f 50 72 69 6e 74 53 74 72 65 61 6d 3b 01 00 13 6a 61 76 61 2f 69 6f 2f 50 72 69 6e 74 53 74 72 65 61 6d 01 00 07 70 72 69 6e 74 6c 6e 01 00 15 28 4c 6a 61 76 61 2f 6c 61 6e 67 2f 53 74 72 69 6e 67 3b 29 56 00 21 00 05 00 06 00 00 00 00 00 02 00 01 00 07 00 08 00 01 00 09 00 00 00 2f 00 01 00 01 00 00 00 05 2a b7 00 01 b1 00 00 00 02 00 0a 00 00 00 06 00 01 00 00 00 08 00 0b 00 00 00 0c 00 01 00 00 00 05 00 0c 00 0d 00 00 00 09 00 0e 00 0f 00 01 00 09 00 00 00 37 00 02 00 01 00 00 00 09 b2 00 02 12 03 b6 00 04 b1 00 00 00 02 00 0a 00 00 00 0a 00 02 00 00 00 0a 00 08 00 0b 00 0b 00 00 00 0c 00 01 00 00 00 09 00 10 00 11 00 00 00 01 00 12 00 00 00 02 00 13
```

