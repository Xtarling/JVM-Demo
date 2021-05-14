package cn.wyx.demo.jvm.instructions.constants.ipush;

import cn.wyx.demo.jvm.instructions.base.BytecodeReader;
import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 11:53
 * --------------------------------
 * 从操作数中读取一个byte型整数，扩展成int型，推入操作数栈顶
 */
public class BIPUSH implements Instruction {

    private byte val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushInt(val);
    }
}
