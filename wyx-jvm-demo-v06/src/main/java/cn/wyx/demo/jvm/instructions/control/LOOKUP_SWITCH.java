package cn.wyx.demo.jvm.instructions.control;

import cn.wyx.demo.jvm.instructions.base.BytecodeReader;
import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-15 - 20:19
 * --------------------------------
 * case值不连续的switch
 */
public class LOOKUP_SWITCH implements Instruction {

    private int defaultOffset;
    private int npairs;
    private int[] matchOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt();
        npairs = reader.readInt();
        matchOffsets = reader.readInts(npairs * 2);
    }

    @Override
    public void execute(Frame frame) {
        int key = frame.operandStack().popInt();
        for (int i = 0; i < npairs * 2; i += 2) {
            if (matchOffsets[i] == key) {
                int offset = matchOffsets[i + 1];
                Instruction.branch(frame, offset);
                return; //原文件没有此语句
            }
        }
        Instruction.branch(frame, defaultOffset);
    }
}
