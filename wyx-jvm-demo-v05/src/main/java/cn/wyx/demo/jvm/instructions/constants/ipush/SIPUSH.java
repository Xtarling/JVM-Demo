package cn.wyx.demo.jvm.instructions.constants.ipush;

import cn.wyx.demo.jvm.instructions.base.BytecodeReader;
import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 11:53
 * --------------------------------
 * 从操作数中读取一个short型整数，扩展成int型，推入操作数栈顶
 */
public class SIPUSH implements Instruction {

    private short val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushInt(val);
    }
}
