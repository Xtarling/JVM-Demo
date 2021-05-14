package cn.wyx.demo.jvm.instructions.references;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex16;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.MethodRef;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.RuntimeConstantPool;

/**
 * @author WYX
 * @date 2021-4-27 - 9:41
 * --------------------------------
 */
public class INVOKE_VIRTUAL extends InstructionIndex16 {

    //hack
    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool cp = frame.method().clazz().constantPool();
        MethodRef methodRef = (MethodRef) cp.getConstant(idx);
        if (methodRef.name().equals("println")) {
            OperandStack stack = frame.operandStack();
            switch (methodRef.descriptor()) {
                case "(Z)V":
                    System.out.println(stack.popInt() != 0);
                    break;
                case "(C)V":
                case "(B)V":
                case "(S)V":
                case "(I)V":
                    System.out.println(stack.popInt());
                    break;
                case "(J)V":
                    System.out.println(stack.popLong());
                    break;
                case "(F)V":
                    System.out.println(stack.popFloat());
                    break;
                case "(D)V":
                    System.out.println(stack.popDouble());
                    break;
                default:
                    System.out.println(methodRef.descriptor());
                    break;
            }
            stack.popRef();
        }
    }
}
