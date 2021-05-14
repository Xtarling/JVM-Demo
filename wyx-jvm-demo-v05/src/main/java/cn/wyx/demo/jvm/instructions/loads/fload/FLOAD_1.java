package cn.wyx.demo.jvm.instructions.loads.fload;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:36
 * --------------------------------
 */
public class FLOAD_1 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        float val = frame.localVars().getFloat(1);
        frame.operandStack().pushFloat(val);
    }
}
