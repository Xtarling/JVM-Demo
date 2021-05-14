package cn.wyx.demo.jvm.instructions.loads.iload;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex8;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:25
 * --------------------------------
 */
public class ILOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        int val = frame.localVars().getInt(idx);
        frame.operandStack().pushInt(val);
    }
}
