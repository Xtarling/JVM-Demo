package cn.wyx.demo.jvm.instructions.stores.istore;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:51
 * --------------------------------
 */
public class ISTORE_3 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _istore(frame, 3);
    }
}
