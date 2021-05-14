package cn.wyx.demo.jvm.instructions.stores.astore;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:48
 * --------------------------------
 */
public class ASTORE_1 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _astore(frame, 1);
    }
}
