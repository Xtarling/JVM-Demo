package cn.wyx.demo.jvm.instructions.loads.aload;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea._Object;

/**
 * @author WYX
 * @date 2021-3-14 - 16:20
 * --------------------------------
 */
public class ALOAD_3 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _Object ref = frame.localVars().getRef(3);
        frame.operandStack().pushRef(ref);
    }
}
