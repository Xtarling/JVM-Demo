package cn.wyx.demo.jvm.instructions.base;

import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;

/**
 * @author WYX
 * @date 2021-3-13 - 23:36
 * --------------------------------
 * 跳转指令
 */
public class InstructionBranch implements Instruction {

    protected int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {

    }

    protected boolean _acmp(Frame frame) {
        OperandStack stack = frame.operandStack();
        Object ref2 = stack.popRef();
        Object ref1 = stack.popRef();
        return ref1.equals(ref2);
    }
}
