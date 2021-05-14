package cn.wyx.demo.jvm.instructions.loads.aload;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 16:20
 * --------------------------------
 */
public class ALOAD_3 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        Object ref = frame.localVars().getRef(3);
        frame.operandStack().pushRef(ref);
    }
}
