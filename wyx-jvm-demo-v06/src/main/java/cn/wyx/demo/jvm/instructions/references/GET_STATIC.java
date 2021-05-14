package cn.wyx.demo.jvm.instructions.references;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex16;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.FieldRef;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.RuntimeConstantPool;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Field;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Slots;

/**
 * @author WYX
 * @date 2021-4-23 - 18:10
 * --------------------------------
 */
public class GET_STATIC extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool cp = frame.method().clazz().constantPool();
        FieldRef ref = (FieldRef) cp.getConstant(idx);
        Field field = ref.resolvedField();

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        Class clazz = field.clazz();
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
                stack.pushInt(slots.getInt(slotId));
                break;
            case 'J':
                stack.pushLong(slots.getLong(slotId));
                break;
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                break;
        }
    }
}
