package cn.wyx.demo.jvm.instructions;

import cn.wyx.demo.jvm.instructions.base.Instruction;
import cn.wyx.demo.jvm.instructions.comparisons.dcmp.DCMPG;
import cn.wyx.demo.jvm.instructions.comparisons.dcmp.DCMPL;
import cn.wyx.demo.jvm.instructions.comparisons.fcmp.FCMPG;
import cn.wyx.demo.jvm.instructions.comparisons.fcmp.FCMPL;
import cn.wyx.demo.jvm.instructions.comparisons.if_acmp.IF_ACMPEQ;
import cn.wyx.demo.jvm.instructions.comparisons.if_acmp.IF_ACMPNE;
import cn.wyx.demo.jvm.instructions.comparisons.if_icmp.*;
import cn.wyx.demo.jvm.instructions.comparisons.ifcond.*;
import cn.wyx.demo.jvm.instructions.comparisons.lcmp.LCMP;
import cn.wyx.demo.jvm.instructions.constants.consts.*;
import cn.wyx.demo.jvm.instructions.constants.ipush.BIPUSH;
import cn.wyx.demo.jvm.instructions.constants.ipush.SIPUSH;
import cn.wyx.demo.jvm.instructions.constants.ldc.*;
import cn.wyx.demo.jvm.instructions.constants.nop.NOP;
import cn.wyx.demo.jvm.instructions.control.GOTO;
import cn.wyx.demo.jvm.instructions.control.LOOKUP_SWITCH;
import cn.wyx.demo.jvm.instructions.control.TABLE_SWITCH;
import cn.wyx.demo.jvm.instructions.conversions.d2x.D2F;
import cn.wyx.demo.jvm.instructions.conversions.d2x.D2I;
import cn.wyx.demo.jvm.instructions.conversions.d2x.D2L;
import cn.wyx.demo.jvm.instructions.conversions.f2x.F2D;
import cn.wyx.demo.jvm.instructions.conversions.f2x.F2I;
import cn.wyx.demo.jvm.instructions.conversions.f2x.F2L;
import cn.wyx.demo.jvm.instructions.conversions.i2x.*;
import cn.wyx.demo.jvm.instructions.conversions.l2x.L2D;
import cn.wyx.demo.jvm.instructions.conversions.l2x.L2F;
import cn.wyx.demo.jvm.instructions.conversions.l2x.L2I;
import cn.wyx.demo.jvm.instructions.extended.GOTO_W;
import cn.wyx.demo.jvm.instructions.extended.WIDE;
import cn.wyx.demo.jvm.instructions.extended.ifnull.IFNONNULL;
import cn.wyx.demo.jvm.instructions.extended.ifnull.IFNULL;
import cn.wyx.demo.jvm.instructions.loads.aload.*;
import cn.wyx.demo.jvm.instructions.loads.dload.*;
import cn.wyx.demo.jvm.instructions.loads.fload.*;
import cn.wyx.demo.jvm.instructions.loads.iload.*;
import cn.wyx.demo.jvm.instructions.loads.lload.*;
import cn.wyx.demo.jvm.instructions.math.add.DADD;
import cn.wyx.demo.jvm.instructions.math.add.FADD;
import cn.wyx.demo.jvm.instructions.math.add.IADD;
import cn.wyx.demo.jvm.instructions.math.add.LADD;
import cn.wyx.demo.jvm.instructions.math.and.IAND;
import cn.wyx.demo.jvm.instructions.math.and.LAND;
import cn.wyx.demo.jvm.instructions.math.div.DDIV;
import cn.wyx.demo.jvm.instructions.math.div.FDIV;
import cn.wyx.demo.jvm.instructions.math.div.IDIV;
import cn.wyx.demo.jvm.instructions.math.div.LDIV;
import cn.wyx.demo.jvm.instructions.math.iinc.IINC;
import cn.wyx.demo.jvm.instructions.math.mul.DMUL;
import cn.wyx.demo.jvm.instructions.math.mul.FMUL;
import cn.wyx.demo.jvm.instructions.math.mul.IMUL;
import cn.wyx.demo.jvm.instructions.math.mul.LMUL;
import cn.wyx.demo.jvm.instructions.math.neg.DNEG;
import cn.wyx.demo.jvm.instructions.math.neg.FNEG;
import cn.wyx.demo.jvm.instructions.math.neg.INEG;
import cn.wyx.demo.jvm.instructions.math.neg.LNEG;
import cn.wyx.demo.jvm.instructions.math.or.IOR;
import cn.wyx.demo.jvm.instructions.math.or.LOR;
import cn.wyx.demo.jvm.instructions.math.rem.DREM;
import cn.wyx.demo.jvm.instructions.math.rem.FREM;
import cn.wyx.demo.jvm.instructions.math.rem.IREM;
import cn.wyx.demo.jvm.instructions.math.rem.LREM;
import cn.wyx.demo.jvm.instructions.math.sh.*;
import cn.wyx.demo.jvm.instructions.math.sub.DSUB;
import cn.wyx.demo.jvm.instructions.math.sub.FSUB;
import cn.wyx.demo.jvm.instructions.math.sub.ISUB;
import cn.wyx.demo.jvm.instructions.math.sub.LSUB;
import cn.wyx.demo.jvm.instructions.math.xor.IXOR;
import cn.wyx.demo.jvm.instructions.math.xor.LXOR;
import cn.wyx.demo.jvm.instructions.references.*;
import cn.wyx.demo.jvm.instructions.stack.dup.*;
import cn.wyx.demo.jvm.instructions.stack.pop.POP;
import cn.wyx.demo.jvm.instructions.stack.pop.POP2;
import cn.wyx.demo.jvm.instructions.stack.swap.SWAP;
import cn.wyx.demo.jvm.instructions.stores.astore.*;
import cn.wyx.demo.jvm.instructions.stores.dstore.*;
import cn.wyx.demo.jvm.instructions.stores.fstore.*;
import cn.wyx.demo.jvm.instructions.stores.istore.*;
import cn.wyx.demo.jvm.instructions.stores.lstore.*;

/**
 * @author WYX
 * @date 2021-3-13 - 23:15
 * --------------------------------
 */
public class Factory {

    public static Instruction newInstruction(byte opcode) {
        switch (opcode) {

            //constants 常量指令
            case 0x00:
                return new NOP();
            case 0x01:
                return new ACONST_NULL();
            case 0x02:
                return new ICONST_M1();
            case 0x03:
                return new ICONST_0();
            case 0x04:
                return new ICONST_1();
            case 0x05:
                return new ICONST_2();
            case 0x06:
                return new ICONST_3();
            case 0x07:
                return new ICONST_4();
            case 0x08:
                return new ICONST_5();
            case 0x09:
                return new LCONST_0();
            case 0x0a:
                return new LCONST_1();
            case 0x0b:
                return new FCONST_0();
            case 0x0c:
                return new FCONST_1();
            case 0x0d:
                return new FCONST_2();
            case 0x0e:
                return new DCONST_0();
            case 0x0f:
                return new DCONST_1();
            case 0x10:
                return new BIPUSH();
            case 0x11:
                return new SIPUSH();
            case 0x12:
                return new LDC();
            case 0x13:
                return new LDC_W();
            case 0x14:
                return new LDC2_W();

            //loads 加载指令
            case 0x15:
                return new ILOAD();
            case 0x16:
                return new LLOAD();
            case 0x17:
                return new FLOAD();
            case 0x18:
                return new DLOAD();
            case 0x19:
                return new ALOAD();
            case 0x1a:
                return new ILOAD_0();
            case 0x1b:
                return new ILOAD_1();
            case 0x1c:
                return new ILOAD_2();
            case 0x1d:
                return new ILOAD_3();
            case 0x1e:
                return new LLOAD_0();
            case 0x1f:
                return new LLOAD_1();
            case 0x20:
                return new LLOAD_2();
            case 0x21:
                return new LLOAD_3();
            case 0x22:
                return new FLOAD_0();
            case 0x23:
                return new FLOAD_1();
            case 0x24:
                return new FLOAD_2();
            case 0x25:
                return new FLOAD_3();
            case 0x26:
                return new DLOAD_0();
            case 0x27:
                return new DLOAD_1();
            case 0x28:
                return new DLOAD_2();
            case 0x29:
                return new DLOAD_3();
            case 0x2a:
                return new ALOAD_0();
            case 0x2b:
                return new ALOAD_1();
            case 0x2c:
                return new ALOAD_2();
            case 0x2d:
                return new ALOAD_3();
//            case 0x2e:
//                return new IALOAD();
//            case 0x2f:
//                return new LALOAD();
//            case 0x30:
//                return new FALOAD();
//            case 0x31:
//                return new DALOAD();
//            case 0x32:
//                return new AALOAD();
//            case 0x33:
//                return new BALOAD();
//            case 0x34:
//                return new CALOAD();
//            case 0x35:
//                return new SALOAD();

            //stores 存储指令
            case 0x36:
                return new ISTORE();
            case 0x37:
                return new LSTORE();
            case 0x38:
                return new FSTORE();
            case 0x39:
                return new DSTORE();
            case 0x3a:
                return new ASTORE();
            case 0x3b:
                return new ISTORE_0();
            case 0x3c:
                return new ISTORE_1();
            case 0x3d:
                return new ISTORE_2();
            case 0x3e:
                return new ISTORE_3();
            case 0x3f:
                return new LSTORE_0();
            case 0x40:
                return new LSTORE_1();
            case 0x41:
                return new LSTORE_2();
            case 0x42:
                return new LSTORE_3();
            case 0x43:
                return new FSTORE_0();
            case 0x44:
                return new FSTORE_1();
            case 0x45:
                return new FSTORE_2();
            case 0x46:
                return new FSTORE_3();
            case 0x47:
                return new DSTORE_0();
            case 0x48:
                return new DSTORE_1();
            case 0x49:
                return new DSTORE_2();
            case 0x4a:
                return new DSTORE_3();
            case 0x4b:
                return new ASTORE_0();
            case 0x4c:
                return new ASTORE_1();
            case 0x4d:
                return new ASTORE_2();
            case 0x4e:
                return new ASTORE_3();
//            case 0x4f:
//                return new IASTORE();
//            case 0x50:
//                return new LASTORE();
//            case 0x51:
//                return new FASTORE();
//            case 0x52:
//                return new DASTORE();
//            case 0x53:
//                return new AASTORE();
//            case 0x54:
//                return new BASTORE();
//            case 0x55:
//                return new CASTORE();
//            case 0x56:
//                return new SASTORE();

            //stack 栈指令
            case 0x57:
                return new POP();
            case 0x58:
                return new POP2();
            case 0x59:
                return new DUP();
            case 0x5a:
                return new DUP_X1();
            case 0x5b:
                return new DUP_X2();
            case 0x5c:
                return new DUP2();
            case 0x5d:
                return new DUP2_X1();
            case 0x5e:
                return new DUP2_X2();
            case 0x5f:
                return new SWAP();

            //math 数学指令
            case 0x60:
                return new IADD();
            case 0x61:
                return new LADD();
            case 0x62:
                return new FADD();
            case 0x63:
                return new DADD();
            case 0x64:
                return new ISUB();
            case 0x65:
                return new LSUB();
            case 0x66:
                return new FSUB();
            case 0x67:
                return new DSUB();
            case 0x68:
                return new IMUL();
            case 0x69:
                return new LMUL();
            case 0x6a:
                return new FMUL();
            case 0x6b:
                return new DMUL();
            case 0x6c:
                return new IDIV();
            case 0x6d:
                return new LDIV();
            case 0x6e:
                return new FDIV();
            case 0x6f:
                return new DDIV();
            case 0x70:
                return new IREM();
            case 0x71:
                return new LREM();
            case 0x72:
                return new FREM();
            case 0x73:
                return new DREM();
            case 0x74:
                return new INEG();
            case 0x75:
                return new LNEG();
            case 0x76:
                return new FNEG();
            case 0x77:
                return new DNEG();
            case 0x78:
                return new ISHL();
            case 0x79:
                return new LSHL();
            case 0x7a:
                return new ISHR();
            case 0x7b:
                return new LSHR();
            case 0x7c:
                return new IUSHR();
            case 0x7d:
                return new LUSHR();
            case 0x7e:
                return new IAND();
            case 0x7f:
                return new LAND();
            case (byte) 0x80:
                return new IOR();
            case (byte) 0x81:
                return new LOR();
            case (byte) 0x82:
                return new IXOR();
            case (byte) 0x83:
                return new LXOR();
            case (byte) 0x84:
                return new IINC();

            //conversions 类型转换指令
            case (byte) 0x85:
                return new I2L();
            case (byte) 0x86:
                return new I2F();
            case (byte) 0x87:
                return new I2D();
            case (byte) 0x88:
                return new L2I();
            case (byte) 0x89:
                return new L2F();
            case (byte) 0x8a:
                return new L2D();
            case (byte) 0x8b:
                return new F2I();
            case (byte) 0x8c:
                return new F2L();
            case (byte) 0x8d:
                return new F2D();
            case (byte) 0x8e:
                return new D2I();
            case (byte) 0x8f:
                return new D2L();
            case (byte) 0x90:
                return new D2F();
            case (byte) 0x91:
                return new I2B();
            case (byte) 0x92:
                return new I2C();
            case (byte) 0x93:
                return new I2S();

            //comparisons 比较指令
            case (byte) 0x94:
                return new LCMP();
            case (byte) 0x95:
                return new FCMPL();
            case (byte) 0x96:
                return new FCMPG();
            case (byte) 0x97:
                return new DCMPL();
            case (byte) 0x98:
                return new DCMPG();
            case (byte) 0x99:
                return new IFEQ();
            case (byte) 0x9a:
                return new IFNE();
            case (byte) 0x9b:
                return new IFLT();
            case (byte) 0x9c:
                return new IFGE();
            case (byte) 0x9d:
                return new IFGT();
            case (byte) 0x9e:
                return new IFLE();
            case (byte) 0x9f:
                return new IF_ICMPEQ();
            case (byte) 0xa0:
                return new IF_ICMPNE();
            case (byte) 0xa1:
                return new IF_ICMPLT();
            case (byte) 0xa2:
                return new IF_ICMPGE();
            case (byte) 0xa3:
                return new IF_ICMPGT();
            case (byte) 0xa4:
                return new IF_ICMPLE();
            case (byte) 0xa5:
                return new IF_ACMPEQ();
            case (byte) 0xa6:
                return new IF_ACMPNE();

            //control 控制指令
            case (byte) 0xa7:
                return new GOTO();
//            case (byte) 0xa8:
//                return new
//            case (byte) 0xa9:
//                return new
            case (byte) 0xaa:
                return new TABLE_SWITCH();
            case (byte) 0xab:
                return new LOOKUP_SWITCH();
//            case (byte) 0xac:
//                return new
//            case (byte) 0xad:
//                return new
//            case (byte) 0xae:
//                return new
//            case (byte) 0xaf:
//                return new
//            case (byte) 0xb0:
//                return new
//            case (byte) 0xb1:
//                return new
            case (byte) 0xb2:
                return new GET_STATIC();
            case (byte) 0xb3:
                return new PUT_STATIC();
            case (byte) 0xb4:
                return new GET_FIELD();
            case (byte) 0xb5:
                return new PUT_FIELD();
            case (byte) 0xb6:
                return new INVOKE_VIRTUAL();
            case (byte) 0xb7:
                return new INVOKE_SPECIAL();
//            case (byte) 0xb8:
//                return new
//            case (byte) 0xb9:
//                return new
//            case (byte) 0xba:
//                return new
            case (byte) 0xbb:
                return new NEW();
//            case (byte) 0xbc:
//                return new
//            case (byte) 0xbd:
//                return new
//            case (byte) 0xbe:
//                return new
//            case (byte) 0xbf:
//                return new
            case (byte) 0xc0:
                return new CHECK_CAST();
            case (byte) 0xc1:
                return new INSTANCE_OF();
//            case (byte) 0xc2:
//                return new
//            case (byte) 0xc3:
//                return new

            //extended 扩展指令
            case (byte) 0xc4:
                return new WIDE();
//            case (byte) 0xc5:
//                return new
            case (byte) 0xc6:
                return new IFNULL();
            case (byte) 0xc7:
                return new IFNONNULL();
            case (byte) 0xc8:
                return new GOTO_W();
//            case (byte) 0xc9:
//                return new

            default:
                return null;
        }
    }
}
