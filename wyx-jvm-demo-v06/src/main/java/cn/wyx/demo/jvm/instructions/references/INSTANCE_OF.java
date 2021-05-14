package cn.wyx.demo.jvm.instructions.references;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex16;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.ClassRef;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.RuntimeConstantPool;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea._Object;

/**
 * @author WYX
 * @date 2021-4-26 - 16:22
 * --------------------------------
 */
public class INSTANCE_OF extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        _Object ref = stack.popRef();

        if (null == ref) {
            stack.pushInt(0);
            return;
        }

        RuntimeConstantPool cp = frame.method().clazz().constantPool();
        ClassRef classRef = (ClassRef) cp.getConstant(idx);
        Class clazz = classRef.resolvedClass();

        if (ref.isInstanceOf(clazz)) {
            stack.pushInt(1);
        }
        else {
            stack.pushInt(0);
        }
    }
}
