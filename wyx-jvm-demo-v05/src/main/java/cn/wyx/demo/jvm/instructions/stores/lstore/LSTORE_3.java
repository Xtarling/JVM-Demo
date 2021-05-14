package cn.wyx.demo.jvm.instructions.stores.lstore;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:52
 * --------------------------------
 */
public class LSTORE_3 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _lstore(frame, 3);
    }
}
