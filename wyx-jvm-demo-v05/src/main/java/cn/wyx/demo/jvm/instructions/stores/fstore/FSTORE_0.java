package cn.wyx.demo.jvm.instructions.stores.fstore;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:49
 * --------------------------------
 */
public class FSTORE_0 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _fstore(frame, 0);
    }
}
