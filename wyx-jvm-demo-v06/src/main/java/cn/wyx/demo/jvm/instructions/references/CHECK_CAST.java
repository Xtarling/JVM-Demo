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
 * @date 2021-4-26 - 16:41
 * --------------------------------
 */
public class CHECK_CAST extends InstructionIndex16 {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        _Object ref = stack.popRef();
        stack.pushRef(ref);
        if (null == ref) return;
        RuntimeConstantPool cp = frame.method().clazz().constantPool();
        ClassRef clazzRef = (ClassRef) cp.getConstant(idx);
        Class clazz = clazzRef.resolvedClass();
        if (!ref.isInstanceOf(clazz)) {
            throw new ClassCastException();
        }
    }
}
