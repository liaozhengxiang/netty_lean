package com.liao.jvm;

/**
 * 方法的动态分派 @see {@link MethodDynamicAssign}
 * 方法静态分派
 * <p>
 * Grandpa g=new Father();
 * <p>
 * 以上代码，g的静态类型是Grandpa，但是实际的类型（真正指向）是Father类型
 * <p>
 * 因此，可以得知：变量的静态类型是不会变化的，但是变量的实际类型是可以变化的（多态的体现），实际类型
 * 在运行期才可以确定
 * <p>
 * 方法重载，是一种静态的行为，编译器就可以完全确定
 * <p>
 */
public class MethodStaticAssign {

    //
    public void test(Grandpa grandpa) {
        System.out.println("grandpa");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(Son son) {
        System.out.println("son");
    }


    public static void main(String[] args) {

        Grandpa father = new Father();

        Grandpa son = new Son();

        MethodStaticAssign methodStaticAssign = new MethodStaticAssign();

        methodStaticAssign.test(father);
        methodStaticAssign.test(son);
    }

}

class Grandpa {

}

class Father extends Grandpa {
}

class Son extends Father {
}
