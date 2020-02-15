package com.liao.jvm;

/**
 * stack frame 栈帧
 * 栈帧是一种用于帮助虚拟机执行方法调用和方法执行的数据结构。
 * 栈帧本身是一种数据结构，封装了方法的局部变量表、动态链接信息、方法的返回地址和操作数栈信息。
 *
 * 符号引用和直接引用
 *
 * 有些符号引用在类加载第一阶段或者是第一次使用就可以转换成直接引用，这种转换叫静态解析；另外一
 * 些符号引用时需要在每次运行期时转换成直接引用，这种叫动态链接，提现了java的多态性。如子类强转换为父类的情况。
 *
 * 静态解析的4种情况：
 *  1. 静态方法
 *  2. 父类方法
 *  3. 构造方法
 *  4. 私有方法（无法被重写）
 *
 * 以上4中方法称为非虚方法，它们在类加载阶段就可以将符号引用转换为直接引用
 *
 *
 * jvm字节码Invoke相关指令
 * 1. invokeinterface:调用接口中的方法，实际是由运行期时决定是调用接口中的方法还是子类中覆盖的方法。
 * 2. invokestatic:调用静态方法。
 * 3. invokespecial:调用自己的私有方法、构造方法（<init>）、以及父类的方法
 * 4. invokevirtrual:调用虚方法，运行期动态查找的过程
 * 5. invokedynamic：动态调用方法，用于支持动态语言特性。
 */
public class JvmInvokeCommand {

    public static void testInvokeStatic() {
        System.out.println("invokestatic command");
    }

    public static void main(String[] args) {
        testInvokeStatic();
    }

}
