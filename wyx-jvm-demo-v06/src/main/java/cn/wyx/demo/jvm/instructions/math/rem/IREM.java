package cn.wyx.demo.jvm.instructions.math.rem;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;

/**
 * @author WYX
 * @date 2021-3-14 - 20:30
 * --------------------------------
 */
public class IREM extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        int v2 = stack.popInt();
        int v1 = stack.popInt();
        if (v2 == 0) throw new ArithmeticException("/ by zero");
        int res = v1 % v2;
        stack.pushInt(res);
    }
}
