package cn.wyx.demo.jvm.instructions.base;

import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea._Object;

/**
 * @author WYX
 * @date 2021-3-14 - 11:00
 * --------------------------------
 * 无操作数的指令
 */
public class InstructionNoOperands implements Instruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {

    }

    protected void _dcmp(Frame frame, boolean gFlag) {
        OperandStack stack = frame.operandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
        if (v1 > v2) {
            stack.pushInt(1);
            return;
        }
        if (v1 == v2) {
            stack.pushInt(0);
            return;
        }
        if (v1 < v2) {
            stack.pushInt(-1);
            return;
        }
        if (gFlag) {
            stack.pushInt(1);
            return;
        }
        stack.pushInt(-1);
    }

    protected void _fcmp(Frame frame, boolean gFlag) {
        OperandStack stack = frame.operandStack();
        double v2 = stack.popFloat();
        double v1 = stack.popFloat();
        if (v1 > v2) {
            stack.pushInt(1);
            return;
        }
        if (v1 == v2) {
            stack.pushInt(0);
            return;
        }
        if (v1 < v2) {
            stack.pushInt(-1);
            return;
        }
        if (gFlag) {
            stack.pushInt(1);
            return;
        }
        stack.pushInt(-1);
    }

    protected void _astore(Frame frame, int idx) {
        _Object ref = frame.operandStack().popRef();
        frame.localVars().setRef(idx, ref);
    }

    protected void _dstore(Frame frame, int idx) {
        double val = frame.operandStack().popDouble();
        frame.localVars().setDouble(idx, val);
    }

    protected void _fstore(Frame frame, int idx) {
        float val = frame.operandStack().popFloat();
        frame.localVars().setFloat(idx, val);
    }

    protected void _istore(Frame frame, int idx) {
        int val = frame.operandStack().popInt();
        frame.localVars().setInt(idx, val);
    }

    protected void _lstore(Frame frame, int idx) {
        long val = frame.operandStack().popLong();
        frame.localVars().setLong(idx, val);
    }
}
