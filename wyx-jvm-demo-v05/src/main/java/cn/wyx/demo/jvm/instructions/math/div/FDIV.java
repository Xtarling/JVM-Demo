package cn.wyx.demo.jvm.instructions.math.div;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;

/**
 * @author WYX
 * @date 2021-3-14 - 20:24
 * --------------------------------
 */
public class FDIV extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        float v2 = stack.popFloat();
        float v1 = stack.popFloat();
        float res = v1 / v2;
        stack.pushFloat(res);
    }
}
