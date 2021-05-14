package cn.wyx.demo.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author WYX
 * @date 2021-4-7 - 15:04
 * --------------------------------
 */
public class NIOTest {

    public static void test1() {
        Path path = Paths.get("D:\\Develop_Workspace\\JavaDevelopWorkspace\\IDEAWorkspace\\wyx-jvm-demo\\TestNIOFiles");
        System.out.println(path.toString());

        Path path1 = path.resolve("Hello.txt");

        System.out.println(path1);
        try {
            byte[] bytes = Files.readAllBytes(path1);
            System.out.println(Arrays.toString(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        Path path = Paths.get("D:\\Develop_Workspace\\JavaDevelopWorkspace\\IDEAWorkspace\\wyx-jvm-demo\\TestNIOFiles\\HelloZip.zip");
        try {
            FileSystem fs = FileSystems.newFileSystem(path, null);
            Path path1 = fs.getPath("Hello.txt");
            System.out.println(path1);
            byte[] bytes = Files.readAllBytes(path1);
            System.out.println(Arrays.toString(bytes));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test3() {
        String[] strings = {"123", "abc", "xxx"};
        System.out.println(String.join(File.pathSeparator, strings));
    }

    public static void test4() {
        String baseDir = "D:\\Develop_Workspace\\JavaDevelopWorkspace\\IDEAWorkspace\\wyx-jvm-demo\\TestNIOFiles\\";
        try {
            String s = Files.walk(Paths.get(baseDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))
                    .collect(Collectors.joining(File.pathSeparator));
            System.out.println(s);
        } catch (IOException e) {

        }
    }

    public static String test5(String wildcardPath) {
        String baseDir = wildcardPath.replace("*", ""); //去掉通配符”*“
        try {
            return Files.walk(Paths.get(baseDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))
                    .collect(Collectors.joining(File.pathSeparator));
        } catch (IOException e) {
            return "";
        }
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4(); //为何s为空串？
        System.out.println(test5("D:\\Develop_Workspace\\JavaDevelopWorkspace\\IDEAWorkspace\\wyx-jvm-demo\\*"));
    }

}
