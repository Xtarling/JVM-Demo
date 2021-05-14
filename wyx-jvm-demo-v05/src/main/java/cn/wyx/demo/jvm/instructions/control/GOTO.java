package cn.wyx.demo.jvm.instructions.control;

import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.instructions.base.InstructionBranch;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-15 - 20:02
 * --------------------------------
 */
public class GOTO extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame, offset);
    }
}
