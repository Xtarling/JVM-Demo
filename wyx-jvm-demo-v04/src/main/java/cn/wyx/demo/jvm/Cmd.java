package cn.wyx.demo.jvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

/**
 * @author WYX
 * @date 2021/3/4 - 16:02
 * --------------------------------
 * 命令行类，存放各类命令行参数与解析操作，依靠JCommander命令行解析器实现
 */
public class Cmd {

    @Parameter(names = {"-?", "-help"}, description = "print help message", order = 3, help = true)
    boolean helpFlag = false;

    @Parameter(names = "-version", description = "print version and exit", order = 2)
    boolean versionFlag = false;

    @Parameter(names = {"-cp", "-classpath"}, description = "classpath", order = 1)
    String classpath;

    @Parameter(names = "-Xjre", description = "path to jre", order = 4)
    String jre;

    @Parameter(description = "main class and args")
    List<String> mainClassAndArgs;

    boolean ok;

    //获取ainClassAndArgs的第一个元素
    String getMainClass() {
        return mainClassAndArgs != null && !mainClassAndArgs.isEmpty()
                ? mainClassAndArgs.get(0)
                : null;
    }

    //获取ainClassAndArgs的其余元素
    List<String> getAppArgs() {
        return mainClassAndArgs != null && mainClassAndArgs.size() > 1
                ? mainClassAndArgs.subList(1, mainClassAndArgs.size())
                : null;
    }

    static Cmd parse(String[] argv) {
        Cmd args = new Cmd();
        JCommander cmd = JCommander.newBuilder().addObject(args).build();
        cmd.parse(argv);
        args.ok = true;
        return args;
    }
}
