package cn.wyx.demo.jvm.instructions.math.sh;

import cn.wyx.demo.jvm.instructions.base.InstructionNoOperands;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.OperandStack;

/**
 * @author WYX
 * @date 2021-3-14 - 20:43
 * --------------------------------
 */
public class ISHL extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        int v2 = stack.popInt();
        int v1 = stack.popInt();
        /*
        - int类型只有32位，v2的二进制表示前5bit就足够表示位移位数
        五位只能表示0~31，若要移动32位怎么办？
        - JVM规范中提到移位范围总在0~31内
        为什么？
         */
        int s = v2 & 0x1f;
        int res = v1 << s;
        stack.pushInt(res);
    }
}
