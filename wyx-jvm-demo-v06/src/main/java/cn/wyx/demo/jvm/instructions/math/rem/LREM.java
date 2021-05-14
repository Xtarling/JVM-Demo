package cn.wyx.demo.jvm.instructions.math.rem;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;

/**
 * @author WYX
 * @date 2021-3-14 - 20:31
 * --------------------------------
 */
public class LREM extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        long v2 = stack.popLong();
        long v1 = stack.popLong();
        if (v2 == 0) throw new ArithmeticException("/ by zero");
        long res = v1 % v2;
        stack.pushLong(res);
    }
}
