package cn.wyx.demo.jvm.instructions.stores.istore;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex8;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:50
 * --------------------------------
 */
public class ISTORE extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        _istore(frame, idx);
    }
}
