package cn.wyx.demo.jvm.instructions.references;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex16;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-4-27 - 9:40
 * --------------------------------
 */
public class INVOKE_SPECIAL extends InstructionIndex16 {

    //hack
    @Override
    public void execute(Frame frame) {
//        frame.operandStack().popRef(); //这么写与DUP指令配合会出错：由于DUP指令的实现是栈顶两元素指向同一slot对象，因此popRef()会同时修改栈顶前两个元素的ref
        frame.operandStack().popSlot();
    }
}
