package cn.wyx.demo.jvm.instructions.math.iinc;

import cn.wyx.demo.jvm.instructions.base.BytecodeReader;
import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.LocalVars;

/**
 * @author WYX
 * @date 2021-3-14 - 21:21
 * --------------------------------
 */
public class IINC implements Instruction { //Q：此操作不在操作数栈中进行？

    public int idx;
    public int constVal;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        idx = reader.readByte();
        constVal = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        LocalVars vars = frame.localVars();
        int val = vars.getInt(idx);
        val += constVal;
        vars.setInt(idx, val);
    }
}
