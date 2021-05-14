package cn.wyx.demo.jvm.instructions.references;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex16;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.FieldRef;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.RuntimeConstantPool;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Field;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Method;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea._Object;

/**
 * @author WYX
 * @date 2021-4-23 - 20:08
 * --------------------------------
 */
public class PUT_FIELD extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.method();
        Class currentClazz = currentMethod.clazz();
        RuntimeConstantPool cp = currentClazz.constantPool();
        FieldRef fieldRef = (FieldRef) cp.getConstant(idx);
        Field field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        if (field.isFinal()) {
            if (currentClazz != field.clazz() || !currentMethod.name().equals("<init>")) {
                throw new IllegalAccessError();
            }
        }

        String descriptor = field.descriptor();
        int slotId = field.slotId();
        OperandStack stack = frame.operandStack();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                int valInt = stack.popInt();
                _Object refInt = stack.popRef();
                if (null == refInt) throw new NullPointerException();
                refInt.fields().setInt(slotId, valInt);
                break;
            case 'J':
                long valLong = stack.popLong();
                _Object refLong = stack.popRef();
                if (null == refLong) throw new NullPointerException();
                refLong.fields().setLong(slotId, valLong);
                break;
            case 'F':
                float valFloat = stack.popFloat();
                _Object refFloat = stack.popRef();
                if (null == refFloat) throw new NullPointerException();
                refFloat.fields().setFloat(slotId, valFloat);
                break;
            case 'D':
                double valDouble = stack.popDouble();
                _Object refDouble = stack.popRef();
                if (null == refDouble) throw new NullPointerException();
                refDouble.fields().setDouble(slotId, valDouble);
                break;
            case 'L':
            case '[':
                _Object val = stack.popRef();
                _Object ref = stack.popRef();
                if (null == ref) throw new NullPointerException();
                ref.fields().setRef(slotId, val);
                break;
            default:
                break;
        }
    }
}
