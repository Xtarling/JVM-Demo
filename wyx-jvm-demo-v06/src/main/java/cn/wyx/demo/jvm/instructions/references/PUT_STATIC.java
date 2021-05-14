package cn.wyx.demo.jvm.instructions.references;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex16;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.FieldRef;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.RuntimeConstantPool;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Field;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Method;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Slots;

/**
 * @author WYX
 * @date 2021-4-23 - 16:48
 * --------------------------------
 */
public class PUT_STATIC extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.method();
        Class currentClazz = currentMethod.clazz();
        RuntimeConstantPool cp = currentClazz.constantPool();
        FieldRef fieldRef = (FieldRef) cp.getConstant(idx);
        Field field = fieldRef.resolvedField();
        Class clazz = field.clazz();

        /*参考源码没有static和final的判断*/
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        if (field.isFinal()) {
            if (currentClazz != clazz || !currentMethod.name().equals("<clinit>")) {
                throw new IllegalAccessError();
            }
        }

        String descriptor = field.descriptor();
        int slotId = field.slotId();
        Slots slots = clazz.staticVars();
        OperandStack stack = frame.operandStack();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                slots.setInt(slotId, stack.popInt());
                break;
            case 'J':
                slots.setLong(slotId, stack.popLong());
                break;
            case 'F':
                slots.setFloat(slotId, stack.popFloat());
                break;
            case 'D':
                slots.setDouble(slotId, stack.popDouble());
                break;
            case 'L':
            case '[':
                slots.setRef(slotId, stack.popRef());
                break;
            default:
                break;
        }
    }
}
