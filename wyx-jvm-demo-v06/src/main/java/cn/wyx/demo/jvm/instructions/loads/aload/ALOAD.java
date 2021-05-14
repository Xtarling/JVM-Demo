package cn.wyx.demo.jvm.instructions.loads.aload;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex8;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea._Object;

/**
 * @author WYX
 * @date 2021-3-14 - 16:17
 * --------------------------------
 */
public class ALOAD extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        _Object ref = frame.localVars().getRef(idx);
        frame.operandStack().pushRef(ref);
    }
}
