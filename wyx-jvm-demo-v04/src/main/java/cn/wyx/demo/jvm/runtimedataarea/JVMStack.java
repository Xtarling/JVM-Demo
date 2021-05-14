package cn.wyx.demo.jvm.runtimedataarea;

/**
 * @author WYX
 * @date 2021/3/12 - 21:55
 * --------------------------------
 * 虚拟机栈
 */
public class JVMStack {

    private int maxSize;
    private int size;
    private Frame _top;

    public JVMStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(Frame frame) {
        if (size > maxSize) throw new StackOverflowError(); //>=?
        if (_top != null) frame.lower = _top;
        _top = frame;
        size++;
    }

    public Frame pop() {
        if (_top == null) throw new RuntimeException("jvm stack is empty!");
        Frame top = _top;
        _top = top.lower;
        top.lower = null;
        size--;
        return top;
    }

    public Frame top() {
        if (_top == null) throw new RuntimeException("jvm stack is empty!");
        return _top;
    }
}
