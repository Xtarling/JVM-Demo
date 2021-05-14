package cn.wyx.demo.jvm.runtimedataarea;

/**
 * @author WYX
 * @date 2021/3/12 - 21:55
 * --------------------------------
 * 虚拟机栈栈帧
 */
public class Frame {

    //虚拟机栈以链表形式实现
    Frame lower;
    private LocalVars localVars; //局部变量表
    private OperandStack operandStack; //操作数栈
    private Thread thread; //所属线程
    private int nextPC;

    public Frame(Thread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    //Getters
    public LocalVars localVars() {
        return localVars;
    }

    public OperandStack operandStack() {
        return operandStack;
    }

    public Thread thread() {
        return thread;
    }

    public int nextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }
}
