package cn.wyx.demo.jvm.instructions.base;

import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-13 - 23:16
 * --------------------------------
 * 指令接口
 */
public interface Instruction {

    void fetchOperands(BytecodeReader reader);

    void execute(Frame frame);

    static void branch(Frame frame, int offset) {
        int pc = frame.thread().pc();
        int nextPC = pc + offset;
        frame.setNextPC(nextPC);
    }
}
