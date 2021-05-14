package cn.wyx.demo.jvm.instructions.loads.fload;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:36
 * --------------------------------
 */
public class FLOAD_2 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        float val = frame.localVars().getFloat(2);
        frame.operandStack().pushFloat(val);
    }
}
