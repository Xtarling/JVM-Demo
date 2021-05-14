package cn.wyx.demo.jvm.instructions.math.sh;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;

/**
 * @author WYX
 * @date 2021-3-14 - 20:44
 * --------------------------------
 */
public class LSHR extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        int v2 = stack.popInt();
        long v1 = stack.popLong();
        int s = v2 & 0x3f;
        long res = v1 >> s;
        stack.pushLong(res);
    }
}
