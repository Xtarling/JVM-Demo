package cn.wyx.demo.jvm.instructions.conversions.d2x;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;

/**
 * @author WYX
 * @date 2021-3-14 - 21:35
 * --------------------------------
 */
public class D2L extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        double d = stack.popDouble();
        long l = (long) d;
        stack.pushLong(l);
    }
}
