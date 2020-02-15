package com.liao.jvm;

/**
 * 方法的静态分派 @see {@link MethodStaticAssign}
 * 方法的动态分派
 *  涉及到一个重要的概念：方法的接收者
 *
 * invokevitrual字节码指令的动态查找流程（运行期才能查找）
 * 1. 找到操作栈顶的第一个元素，它所指向的实际类型
 * 2. 在实际对象中找到对应的方法（例如test()）,检查方法是否可以访问，找到了就调用；否则继续往父类上找。
 *
 *
 * 方法重载（overload）和方法重写（override）相比较，可以得出以下结论
 *
 * 方法重载是静态的，静态分派的，是编译期行为；方法重写是动态的，动态分派的，是运行期才能确定的行为。
 *
 *
 *
 *
 */
public class MethodDynamicAssign {

    public static void main(String[] args) {

        Fruit apple = new Apple();
        Fruit orange = new Orange();

        apple.test();
        //可以看到apple.test() 对应的指令为  invokevirtual #6 <com/liao/jvm/Fruit.test>  在编译的时候使用的类型为Fruit，并不知道真正的类型为Apple
        orange.test();

        apple = new Orange();

        apple.test();


    }


}

class Fruit {

    public void test() {
        System.out.println("fruit");
    }
}


class Apple extends Fruit {
    @Override
    public void test() {
        System.out.println("apple");
    }
}

class Orange extends Fruit {

    @Override
    public void test() {
        System.out.println("orange");
    }
}