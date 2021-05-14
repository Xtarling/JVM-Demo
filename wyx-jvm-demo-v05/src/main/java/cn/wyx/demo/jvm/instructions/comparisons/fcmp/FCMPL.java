package cn.wyx.demo.jvm.instructions.comparisons.fcmp;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-15 - 19:21
 * --------------------------------
 */
public class FCMPL extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _fcmp(frame, false);
    }
}
