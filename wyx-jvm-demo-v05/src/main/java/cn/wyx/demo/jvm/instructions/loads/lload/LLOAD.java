package cn.wyx.demo.jvm.instructions.loads.lload;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex8;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:31
 * --------------------------------
 */
public class LLOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        long val = frame.localVars().getLong(idx);
        frame.operandStack().pushLong(val);
    }
}
