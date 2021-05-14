package cn.wyx.demo.jvm.instructions.loads.aload;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex8;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 16:17
 * --------------------------------
 */
public class ALOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        Object ref = frame.localVars().getRef(idx);
        frame.operandStack().pushRef(ref);
    }
}
