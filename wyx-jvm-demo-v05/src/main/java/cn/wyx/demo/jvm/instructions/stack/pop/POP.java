package cn.wyx.demo.jvm.instructions.stack.pop;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;

/**
 * @author WYX
 * @date 2021-3-14 - 18:14
 * --------------------------------
 * bottom -> top
 * [...][c][b][a]
 *             |
 *             V
 * [...][c][b]
 */
public class POP extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        stack.popSlot();
    }
}
