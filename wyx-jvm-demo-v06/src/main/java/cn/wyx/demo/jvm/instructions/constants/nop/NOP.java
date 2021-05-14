package cn.wyx.demo.jvm.instructions.constants.nop;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-14 - 11:24
 * --------------------------------
 */
public class NOP extends InstructionNoOperands {

    //优化手段：可创建单例对象，从而不需要在Factory.neInstruction()方法中每次都创建新对象
    @Override
    public void execute(Frame frame) {

    }
}
