package cn.wyx.demo.jvm.instructions.stores.dstore;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex8;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:49
 * --------------------------------
 */
public class DSTORE extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        _dstore(frame, idx);
    }
}
