package cn.wyx.demo.jvm.instructions.loads.lload;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:31
 * --------------------------------
 */
public class LLOAD_1 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        long val = frame.localVars().getLong(1);
        frame.operandStack().pushLong(val);
    }
}
