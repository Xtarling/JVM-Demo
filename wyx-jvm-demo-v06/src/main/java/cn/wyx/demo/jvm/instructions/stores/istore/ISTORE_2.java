package cn.wyx.demo.jvm.instructions.stores.istore;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 17:50
 * --------------------------------
 */
public class ISTORE_2 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _istore(frame, 2);
    }
}
