package cn.wyx.demo.jvm.instructions.stores.astore;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex8;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:48
 * --------------------------------
 */
public class ASTORE extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        _astore(frame, idx);
    }
}
