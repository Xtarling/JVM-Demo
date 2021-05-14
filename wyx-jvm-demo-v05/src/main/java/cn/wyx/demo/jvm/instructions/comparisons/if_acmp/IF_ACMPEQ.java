package cn.wyx.demo.jvm.instructions.comparisons.if_acmp;

import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.instructions.base.InstructionBranch;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-15 - 19:49
 * --------------------------------
 */
public class IF_ACMPEQ extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        if (_acmp(frame)) {
            Instruction.branch(frame, offset);
        }
    }
}
