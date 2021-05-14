package cn.wyx.demo.jvm.instructions.loads.aload;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 16:19
 * --------------------------------
 */
public class ALOAD_1 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        Object ref = frame.localVars().getRef(1);
        frame.operandStack().pushRef(ref);
    }
}
