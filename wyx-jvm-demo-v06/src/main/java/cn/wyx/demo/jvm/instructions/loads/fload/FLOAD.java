package cn.wyx.demo.jvm.instructions.loads.fload;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex8;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:35
 * --------------------------------
 */
public class FLOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        float val = frame.localVars().getFloat(idx);
        frame.operandStack().pushFloat(val);
    }
}
