# 手写JVM—Java实现

**Author: Xtarling**

## 简介

本项目主要参考《自己动手写Java虚拟机》一书，用Java语言实现一个简易的JVM。

原书核心内容共有10章，本项目源码的一个Module对应书中的一个章节，目前源码更新到第6章。

截至第6章，实现了执行单个方法的功能。10章内容全部完成后，可实现在控制台输出`Hello, world!`。

## 开发环境设置

- JDK：JDK 1.8.0_261
- IDE：Intellij IDEA
- 项目构建工具：apache-maven-3.6.3

## 参考资料

- [《自己动手写Java虚拟机》张秀宏著](https://item.jd.com/11935272.html)
- [《深入理解Java虚拟机—JVM高级特性与最佳实践》周志明著](https://item.jd.com/12607299.html)
- [The Java Virtual Machine Specification (Java SE 8 Edition)](https://docs.oracle.com/javase/specs/jvms/se8/html/)
- [itstack-demo-jvm](https://github.com/fuzhengwei/itstack-demo-jvm)

## 致谢

非常感谢[@fuzhengwei (小傅哥) ](https://github.com/fuzhengwei) 前辈大神的开源代码，我从他的源码中学习到了很多优秀的设计思想和工程思维。从他的源码中可以看出一个优秀的计算机工程师应该具有的品质。在研究此源码时，我发现了少量的（自己认为的）细节上的错误和设计缺陷，在本项目中已有一定修改。

