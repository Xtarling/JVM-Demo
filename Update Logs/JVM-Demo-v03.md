## v03：解析class文件

**更新内容**

- 按照.class文件字节码的编排规则，将读取的.class文件解析成ClassFile类对象

**新增源文件**

- cn.wyx.demo.jvm.classfile类文件包。此包中的类用于描述类文件结构，以及实现解析字节码的功能。

**项目结构**

```shell
wyx-jvm-demo-v03
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
    |                       |   |   ClassFile.java
    |                       |   |   ClassReader.java
    |                       |   |   MemberInfo.java
    |                       |   |
    |                       |   +---attributes
    |                       |   |   |   AttributeInfo.java
    |                       |   |   |
    |                       |   |   \---impl
    |                       |   |           BootstrapMethodsAttribute.java
    |                       |   |           CodeAttribute.java
    |                       |   |           ...（共计15个属性表实现类）
    |                       |   |
    |                       |   \---constantpool
    |                       |       |   ConstantInfo.java
    |                       |       |   ConstantPool.java
    |                       |       |
    |                       |       \---impl
    |                       |               ConstantClassInfo.java
    |                       |               ConstantDoubleInfo.java
    |                       |               ...（共计15个常量池信息实现类）
    |                       |
    |                       \---classpath
    |                           \...（无新增源文件）
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
D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v03\target\test-classes
cn.wyx.demo.test.HelloWorld

//测试结果
JREpath: D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
userclasspath: D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v03\target\test-classes
class: cn.wyx.demo.test.HelloWorld
args: null
###########################[Class Info]###########################
version: 52.0
constants count: 34
access flags: 0x21
this class: cn/wyx/demo/test/HelloWorld
super class: java/lang/Object
interfaces: []
fields count: 0
methods count: 2
<init> 		 ()V
main 		 ([Ljava/lang/String;)V

            
//-------------测试2-------------
//配置参数
-Xjre
D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
-classpath
D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v03\target\test-classes
cn.wyx.demo.test.TestClass

//测试结果
JREpath: D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
userclasspath: D:\Develop_Workspace\JavaDevelopWorkspace\IDEAWorkspace\wyx-jvm-demo\wyx-jvm-demo-v03\target\test-classes
class: cn.wyx.demo.test.TestClass
args: null
###########################[Class Info]###########################
version: 52.0
constants count: 22
access flags: 0x21
this class: cn/wyx/demo/test/TestClass
super class: java/lang/Object
interfaces: []
fields count: 1
m 		 I
methods count: 2
<init> 		 ()V
inc 		 ()I


//-------------测试3-------------
//配置参数
-Xjre
D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
java.lang.String

//测试结果
JREpath: D:\Apps\DevelopTools\Java\jdk1.8.0_261\jre
userclasspath: null
class: java.lang.String
args: null
###########################[Class Info]###########################
version: 52.0
constants count: 540
access flags: 0x31
this class: java/lang/String
super class: java/lang/Object
interfaces: [java/io/Serializable, java/lang/Comparable, java/lang/CharSequence]
fields count: 5
value 		 [C
hash 		 I
serialVersionUID 		 J
serialPersistentFields 		 [Ljava/io/ObjectStreamField;
CASE_INSENSITIVE_ORDER 		 Ljava/util/Comparator;
methods count: 94
<init> 		 ()V
<init> 		 (Ljava/lang/String;)V
<init> 		 ([C)V
<init> 		 ([CII)V
<init> 		 ([III)V
<init> 		 ([BIII)V
<init> 		 ([BI)V
checkBounds 		 ([BII)V
<init> 		 ([BIILjava/lang/String;)V
<init> 		 ([BIILjava/nio/charset/Charset;)V
<init> 		 ([BLjava/lang/String;)V
<init> 		 ([BLjava/nio/charset/Charset;)V
<init> 		 ([BII)V
<init> 		 ([B)V
<init> 		 (Ljava/lang/StringBuffer;)V
<init> 		 (Ljava/lang/StringBuilder;)V
<init> 		 ([CZ)V
length 		 ()I
isEmpty 		 ()Z
charAt 		 (I)C
codePointAt 		 (I)I
codePointBefore 		 (I)I
codePointCount 		 (II)I
offsetByCodePoints 		 (II)I
getChars 		 ([CI)V
getChars 		 (II[CI)V
getBytes 		 (II[BI)V
getBytes 		 (Ljava/lang/String;)[B
getBytes 		 (Ljava/nio/charset/Charset;)[B
getBytes 		 ()[B
equals 		 (Ljava/lang/Object;)Z
contentEquals 		 (Ljava/lang/StringBuffer;)Z
nonSyncContentEquals 		 (Ljava/lang/AbstractStringBuilder;)Z
contentEquals 		 (Ljava/lang/CharSequence;)Z
equalsIgnoreCase 		 (Ljava/lang/String;)Z
compareTo 		 (Ljava/lang/String;)I
compareToIgnoreCase 		 (Ljava/lang/String;)I
regionMatches 		 (ILjava/lang/String;II)Z
regionMatches 		 (ZILjava/lang/String;II)Z
startsWith 		 (Ljava/lang/String;I)Z
startsWith 		 (Ljava/lang/String;)Z
endsWith 		 (Ljava/lang/String;)Z
hashCode 		 ()I
indexOf 		 (I)I
indexOf 		 (II)I
indexOfSupplementary 		 (II)I
lastIndexOf 		 (I)I
lastIndexOf 		 (II)I
lastIndexOfSupplementary 		 (II)I
indexOf 		 (Ljava/lang/String;)I
indexOf 		 (Ljava/lang/String;I)I
indexOf 		 ([CIILjava/lang/String;I)I
indexOf 		 ([CII[CIII)I
lastIndexOf 		 (Ljava/lang/String;)I
lastIndexOf 		 (Ljava/lang/String;I)I
lastIndexOf 		 ([CIILjava/lang/String;I)I
lastIndexOf 		 ([CII[CIII)I
substring 		 (I)Ljava/lang/String;
substring 		 (II)Ljava/lang/String;
subSequence 		 (II)Ljava/lang/CharSequence;
concat 		 (Ljava/lang/String;)Ljava/lang/String;
replace 		 (CC)Ljava/lang/String;
matches 		 (Ljava/lang/String;)Z
contains 		 (Ljava/lang/CharSequence;)Z
replaceFirst 		 (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
replaceAll 		 (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
replace 		 (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
split 		 (Ljava/lang/String;I)[Ljava/lang/String;
split 		 (Ljava/lang/String;)[Ljava/lang/String;
join 		 (Ljava/lang/CharSequence;[Ljava/lang/CharSequence;)Ljava/lang/String;
join 		 (Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
toLowerCase 		 (Ljava/util/Locale;)Ljava/lang/String;
toLowerCase 		 ()Ljava/lang/String;
toUpperCase 		 (Ljava/util/Locale;)Ljava/lang/String;
toUpperCase 		 ()Ljava/lang/String;
trim 		 ()Ljava/lang/String;
toString 		 ()Ljava/lang/String;
toCharArray 		 ()[C
format 		 (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
format 		 (Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
valueOf 		 (Ljava/lang/Object;)Ljava/lang/String;
valueOf 		 ([C)Ljava/lang/String;
valueOf 		 ([CII)Ljava/lang/String;
copyValueOf 		 ([CII)Ljava/lang/String;
copyValueOf 		 ([C)Ljava/lang/String;
valueOf 		 (Z)Ljava/lang/String;
valueOf 		 (C)Ljava/lang/String;
valueOf 		 (I)Ljava/lang/String;
valueOf 		 (J)Ljava/lang/String;
valueOf 		 (F)Ljava/lang/String;
valueOf 		 (D)Ljava/lang/String;
intern 		 ()Ljava/lang/String;
compareTo 		 (Ljava/lang/Object;)I
<clinit> 		 ()V
```

