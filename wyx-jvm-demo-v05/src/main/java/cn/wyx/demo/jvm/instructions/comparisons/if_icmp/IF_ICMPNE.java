package cn.wyx.demo.jvm.instructions.comparisons.if_icmp;

import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.instructions.base.InstructionBranch;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;

/**
 * @author WYX
 * @date 2021-3-15 - 19:56
 * --------------------------------
 */
public class IF_ICMPNE extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        int v2 = stack.popInt();
        int v1 = stack.popInt();
        if (v1 != v2) {
            Instruction.branch(frame, offset);
        }
    }
}
