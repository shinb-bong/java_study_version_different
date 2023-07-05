package proxy;

import proxy.impl.AImpl;

import java.lang.reflect.Proxy;

/**
 * jdk 동적 프록시 예제
 */
public class JdkProxyTest {
    public static void main(String[] args) {
        AImpl target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        proxy.call();
        System.out.println("targetClass = " + target.getClass());
        System.out.println("proxyClass = " + proxy.getClass());
    }
}


