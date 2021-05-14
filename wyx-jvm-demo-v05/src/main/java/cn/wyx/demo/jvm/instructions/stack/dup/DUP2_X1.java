package cn.wyx.demo.jvm.instructions.stack.dup;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.Slot;

/**
 * @author WYX
 * @date 2021-3-14 - 19:56
 * --------------------------------
 * bottom -> top
 * [...][c][b][a]
 *        _/ __/
 *       |  |
 *       V  V
 * [...][b][a][c][b][a]
 */
public class DUP2_X1 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
