package cn.wyx.demo.jvm;

import cn.wyx.demo.jvm.classfile.MemberInfo;
import cn.wyx.demo.jvm.classfile.attributes.impl.CodeAttribute;
import cn.wyx.demo.jvm.instructions.Factory;
import cn.wyx.demo.jvm.instructions.base.BytecodeReader;
import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.Thread;
import com.alibaba.fastjson.JSON;

/**
 * @author WYX
 * @date 2021-3-15 - 22:01
 * --------------------------------
 * 指令集解释器
 */
public class Interpret {

    Interpret(MemberInfo m) {
        CodeAttribute codeAttr = m.codeAttribute();
        int maxLocals = codeAttr.maxLocals();
        int maxStack = codeAttr.maxStack();
        byte[] bytecode = codeAttr.data();
        Thread thread = new Thread();
        Frame frame = thread.newFrame(maxLocals, maxStack);
        thread.pushFrame(frame);
        loop(thread, bytecode);
    }

    private void loop(Thread thread, byte[] bytecode) {
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();
        while (true) {
            int pc = frame.nextPC();
            thread.setPC(pc);
            reader.reset(bytecode, pc);
            byte opcode = reader.readByte();
            Instruction inst = Factory.newInstruction(opcode);
            if (null == inst) {
                System.out.println("寄存器（指令）尚未实现：" + byteToHexString(new byte[]{opcode}));
                break;
            }
            inst.fetchOperands(reader);
            frame.setNextPC(reader.pc());
            System.out.println("寄存器（指令）：" + byteToHexString(new byte[]{opcode})
                    + " -> " + inst.getClass().getSimpleName()
                    + " => 局部变量表：" + JSON.toJSONString(frame.localVars().getSlots())
                    + " 操作数栈：" + JSON.toJSONString(frame.operandStack().getSlots()));
            inst.execute(frame);
        }
    }

    private static String byteToHexString(byte[] codes) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : codes) {
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            sb.append(strHex);
        }
        return sb.toString();
    }
}
