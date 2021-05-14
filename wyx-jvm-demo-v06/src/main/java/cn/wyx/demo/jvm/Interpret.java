package cn.wyx.demo.jvm;

import cn.wyx.demo.jvm.instructions.Factory;
import cn.wyx.demo.jvm.instructions.base.BytecodeReader;
import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.Thread;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Method;
import com.alibaba.fastjson.JSON;

/**
 * @author WYX
 * @date 2021-3-15 - 22:01
 * --------------------------------
 * 指令集解释器
 */
public class Interpret {

    Interpret(Method method) {
        Thread thread = new Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        loop(thread, method.code());
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
                System.out.printf("寄存器（指令）尚未实现：0x%x\n", opcode);
                break;
            }
            inst.fetchOperands(reader);
            frame.setNextPC(reader.pc());
            System.out.printf("pc: %d\t-> 寄存器（指令）：0x%x -> %s => 局部变量表：%s 操作数栈：%s\n",
                    pc,
                    opcode,
                    inst.getClass().getSimpleName(),
                    JSON.toJSONString(frame.localVars().getSlots()),
                    JSON.toJSONString(frame.operandStack().getSlots()));
            inst.execute(frame);
        }
    }
}
