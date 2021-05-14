package cn.wyx.demo.jvm.instructions.stack.dup;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.Slot;

/**
 * @author WYX
 * @date 2021-3-14 - 18:27
 * --------------------------------
 * bottom -> top
 * [...][c][b][a]
 *           __/
 *          |
 *          V
 * [...][c][a][b][a]
 */
public class DUP_X1 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
