package cn.wyx.demo.jvm.runtimedataarea;

import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Method;

/**
 * @author WYX
 * @date 2021/3/12 - 21:56
 * --------------------------------
 * 线程。内含线程私有内存结构：程序计数器pc、虚拟机栈、本地方法栈（暂未实现）
 */
public class Thread {

    private int pc;
    private final JVMStack stack;

    public Thread() {
        this.stack = new JVMStack(1024); //虚拟机栈最大栈深度暂时默认设为1024.单位：Frame
    }

    public int pc() {
        return pc;
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Frame frame) {
        stack.push(frame);
    }

    public Frame popFrame() {
        return stack.pop();
    }

    public Frame currentFrame() {
        return stack.top();
    }

    public Frame topFrame() {
        return stack.top();
    }

    public Frame newFrame(Method method) {
        return new Frame(this, method);
    }
}
