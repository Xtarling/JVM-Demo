package cn.wyx.demo.jvm.instructions.constants.ldc;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex16;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.RuntimeConstantPool;

/**
 * @author WYX
 * @date 2021-4-26 - 20:52
 * --------------------------------
 */
public class LDC_W extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        RuntimeConstantPool cp = frame.method().clazz().constantPool();
        Object c = cp.getConstant(idx);

        if (c instanceof Integer) {
            stack.pushInt((Integer) c);
            return;
        }
        if (c instanceof Float) {
            stack.pushFloat((Float) c);
            return;
        }

        throw new RuntimeException("todo ldc"); //后续在第8、9章实现
    }
}
