package cn.wyx.demo.jvm.instructions.references;

import cn.wyx.demo.jvm.instructions.base.InstructionIndex16;
import cn.wyx.demo.jvm.runtimedataarea.Frame;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.ClassRef;
import cn.wyx.demo.jvm.runtimedataarea.heap.constantpool.RuntimeConstantPool;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea.Class;
import cn.wyx.demo.jvm.runtimedataarea.heap.methodarea._Object;

/**
 * @author WYX
 * @date 2021-4-23 - 15:27
 * --------------------------------
 */
public class NEW extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool cp = frame.method().clazz().constantPool();
        ClassRef classRef = (ClassRef) cp.getConstant(idx);
        Class clazz = classRef.resolvedClass();
        if (clazz.isInterface() || clazz.isAbstract()) {
            throw new InstantiationError();
        }
        //类的初始化动作暂无，留到第7章解决
        _Object ref = clazz.newObject();
        frame.operandStack().pushRef(ref);
    }
}
