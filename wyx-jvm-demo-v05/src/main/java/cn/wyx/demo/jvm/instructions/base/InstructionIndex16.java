package cn.wyx.demo.jvm.instructions.base;

import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 0:20
 * --------------------------------
 * 两字节操作数指令
 * Q：个人认为，两字节操作数不等于两个操作数，而是用两个字节表示一个操作数，如运行时常量池索引是两字节的
 */
public class InstructionIndex16 implements Instruction {

    protected int idx;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        idx = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {

    }
}
