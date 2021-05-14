package cn.wyx.demo.jvm.instructions.base;

import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea._Object;

/**
 * @author WYX
 * @date 2021-3-13 - 23:47
 * --------------------------------
 * 单字节操作数指令
 */
public class InstructionIndex8 implements Instruction {

    public int idx;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        idx = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {

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
