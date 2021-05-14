package cn.wyx.demo.jvm.instructions.constants.consts;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 11:27
 * --------------------------------
 */
public class DCONST_1 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushDouble(1.0);
    }
}
