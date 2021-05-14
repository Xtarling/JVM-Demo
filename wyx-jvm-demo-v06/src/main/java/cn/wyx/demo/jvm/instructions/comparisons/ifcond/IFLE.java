package cn.wyx.demo.jvm.instructions.comparisons.ifcond;

import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.instructions.base.InstructionBranch;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-15 - 19:25
 * --------------------------------
 */
public class IFLE extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        int val = frame.operandStack().popInt();
        if (val <= 0) {
            Instruction.branch(frame, offset);
        }
    }
}
