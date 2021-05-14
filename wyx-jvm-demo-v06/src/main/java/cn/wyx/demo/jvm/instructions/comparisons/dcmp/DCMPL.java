package cn.wyx.demo.jvm.instructions.comparisons.dcmp;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-15 - 19:17
 * --------------------------------
 */
public class DCMPL extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _dcmp(frame, false);
    }
}
