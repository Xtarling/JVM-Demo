package cn.wyx.demo.jvm.instructions.control;

import cn.wyx.demo.jvm.instructions.base.BytecodeReader;
import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-15 - 20:04
 * --------------------------------
 * case值连续的switch
 */
public class TABLE_SWITCH implements Instruction {

    private int defaultOffset;
    private int low;
    private int high;
    private int[] jumpOffsets;


    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt();
        low = reader.readInt();
        high = reader.readInt();
        int jumpOffsetsCount = high - low + 1;
        jumpOffsets = reader.readInts(jumpOffsetsCount);
    }

    @Override
    public void execute(Frame frame) {
        int idx = frame.operandStack().popInt();
        int offset;
        if (idx >= low && idx <= high) {
            offset = jumpOffsets[idx - low];
        }
        else {
            offset = defaultOffset;
        }
        Instruction.branch(frame, offset);
    }
}
