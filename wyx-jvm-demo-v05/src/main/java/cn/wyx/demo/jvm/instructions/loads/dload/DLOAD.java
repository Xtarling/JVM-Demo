package cn.wyx.demo.jvm.instructions.loads.dload;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex8;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:00
 * --------------------------------
 */
public class DLOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        double val = frame.localVars().getDouble(idx);
        frame.operandStack().pushDouble(val);
    }
}
