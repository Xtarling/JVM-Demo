package cn.wyx.demo.jvm.runtimedataarea;

import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Method;

/**
 * @author WYX
 * @date 2021/3/12 - 21:55
 * --------------------------------
 * 虚拟机栈栈帧
 */
public class Frame {

    //虚拟机栈以链表形式实现
    Frame lower;
    private final LocalVars localVars;        //局部变量表
    private final OperandStack operandStack;  //操作数栈
    private final Thread thread;              //所属线程
    private final Method method;              //所属方法
    private int nextPC;

    public Frame(Thread thread, Method method) {
        this.thread = thread;
        this.method = method;
        this.localVars = new LocalVars(method.maxLocals());
        this.operandStack = new OperandStack(method.maxStack());
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
    public Method method() {
        return method;
    }
    public int nextPC() {
        return nextPC;
    }
    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }
}
