package cn.wyx.demo.jvm.instructions.extended;

import cn.wyx.demo.jvm.instructions.base.BytecodeReader;
import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.instructions.loads.aload.ALOAD;
import cn.wyx.demo.jvm.instructions.loads.dload.DLOAD;
import cn.wyx.demo.jvm.instructions.loads.fload.FLOAD;
import cn.wyx.demo.jvm.instructions.loads.iload.ILOAD;
import cn.wyx.demo.jvm.instructions.loads.lload.LLOAD;
import cn.wyx.demo.jvm.instructions.math.iinc.IINC;
import cn.wyx.demo.jvm.instructions.stores.astore.ASTORE;
import cn.wyx.demo.jvm.instructions.stores.dstore.DSTORE;
import cn.wyx.demo.jvm.instructions.stores.fstore.FSTORE;
import cn.wyx.demo.jvm.instructions.stores.istore.ISTORE;
import cn.wyx.demo.jvm.instructions.stores.lstore.LSTORE;
import cn.wyx.demo.jvm.runtimedataarea.Frame;

/**
 * @author WYX
 * @date 2021-3-15 - 20:36
 * --------------------------------
 */
public class WIDE implements Instruction {

    private Instruction modifiedInstruction;

    @Override
    public void fetchOperands(BytecodeReader reader) { //Q:每个case后面是不是得加个break？原文件代码没加
        byte opcode = reader.readByte();
        switch (opcode) {
            case 0x15:
                ILOAD inst_iload = new ILOAD();
                inst_iload.idx = reader.readShort();
                modifiedInstruction = inst_iload;
                break;
            case 0x16:
                LLOAD inst_lload = new LLOAD();
                inst_lload.idx = reader.readShort();
                modifiedInstruction = inst_lload;
                break;
            case 0x17:
                FLOAD inst_fload = new FLOAD();
                inst_fload.idx = reader.readShort();
                modifiedInstruction = inst_fload;
                break;
            case 0x18:
                DLOAD inst_dload = new DLOAD();
                inst_dload.idx = reader.readShort();
                modifiedInstruction = inst_dload;
                break;
            case 0x19:
                ALOAD inst_aload = new ALOAD();
                inst_aload.idx = reader.readShort();
                modifiedInstruction = inst_aload;
                break;
            case 0x36:
                ISTORE inst_istore = new ISTORE();
                inst_istore.idx = reader.readShort();
                modifiedInstruction = inst_istore;
                break;
            case 0x37:
                LSTORE inst_lstore = new LSTORE();
                inst_lstore.idx = reader.readShort();
                modifiedInstruction = inst_lstore;
                break;
            case 0x38:
                FSTORE inst_fstore = new FSTORE();
                inst_fstore.idx = reader.readShort();
                modifiedInstruction = inst_fstore;
                break;
            case 0x39:
                DSTORE inst_dstore = new DSTORE();
                inst_dstore.idx = reader.readShort();
                modifiedInstruction = inst_dstore;
                break;
            case 0x3a:
                ASTORE inst_astore = new ASTORE();
                inst_astore.idx = reader.readShort();
                modifiedInstruction = inst_astore;
                break;
            case (byte) 0x84: //源文件的这里好像写得也不对，少读一个参数
                IINC inst_iinc = new IINC();
                inst_iinc.idx = reader.readShort();
                inst_iinc.constVal = reader.readShort();
                modifiedInstruction = inst_iinc;
                break;
            case (byte) 0xa9: //ret指令
                throw new RuntimeException("Unsupported opcode: 0xa9");
        }
    }

    @Override
    public void execute(Frame frame) {
        modifiedInstruction.execute(frame);
    }
}
