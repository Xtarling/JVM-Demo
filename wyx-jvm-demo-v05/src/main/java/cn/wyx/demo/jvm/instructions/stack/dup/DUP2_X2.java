package cn.wyx.demo.jvm.instructions.stack.dup;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.Slot;

/**
 * @author WYX
 * @date 2021-3-14 - 19:57
 * --------------------------------
 * bottom -> top
 * [...][d][c][b][a]
 *        ____/ __/
 *       |   __/
 *       V  V
 * [...][b][a][d][c][b][a]
 */
public class DUP2_X2 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        Slot slot4 = stack.popSlot();
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
        stack.pushSlot(slot4);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
