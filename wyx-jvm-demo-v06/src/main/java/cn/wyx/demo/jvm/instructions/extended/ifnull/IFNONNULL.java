package cn.wyx.demo.jvm.instructions.extended.ifnull;

import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.instructions.base.InstructionBranch;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-15 - 21:50
 * --------------------------------
 */
public class IFNONNULL extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        Object ref = frame.operandStack().popRef();
        if (null != ref) {
            Instruction.branch(frame, offset);
        }
    }
}
