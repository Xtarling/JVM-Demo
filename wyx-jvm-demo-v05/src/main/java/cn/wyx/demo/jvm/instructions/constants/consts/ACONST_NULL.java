package cn.wyx.demo.jvm.instructions.constants.consts;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 11:22
 * --------------------------------
 */
public class ACONST_NULL extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushRef(null);
    }
}
