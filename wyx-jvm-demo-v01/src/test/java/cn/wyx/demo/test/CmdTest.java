package cn.wyx.demo.test;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * @author WYX
 * @date 2021-3-31 - 11:08
 * --------------------------------
 */
public class CmdTest {

    @Parameter(names = {"--length", "-l"})
    int length;

    @Parameter(names = {"--pattern", "-p"})
    int pattern;

    @Parameter(names = {"--info", "-i"})
    String info;

    @Parameter(names = {"--help", "-h"})
    boolean helpFlag = false;

    public static void main(String[] args) {
        CmdTest test = new CmdTest();
        JCommander.newBuilder().addObject(test).build().parse(args);
        test.run();

    }

    public void run() {
        System.out.printf("length: %d pattern: %d\n", length, pattern);
        System.out.println(info);
        System.out.println(helpFlag);
    }
}
