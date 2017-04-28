package com.yn;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * Created by yangnan on 17/4/19.
 */
public class ProxyTest1 {

        public static void main(String[] args) {

            testCglib();

            System.out.println("--------------------------------");

            //		testJDKProxy();
        }

        public static void testJDKProxy() {

            Person person = new Person();
            Talk talk =  (Talk)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Talk.class}, new MyInvocationHandler(person));
            talk.say();

        }


        public static void testCglib() {

            //		Person proxyPerson=(Person) CglibObjectProxy.ceateProxtObject(new Person(),Person.class);
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Person.class);
            //回调方法的参数为代理类对象CglibProxy，最后增强目标类调用的是代理类对象CglibProxy中的intercept方法
            enhancer.setCallback(new MyMethodInterceptor());
            Person person = (Person) enhancer.create();
            person.say();
            //		person.sayHello();
        }
    }

    class MyMethodInterceptor implements MethodInterceptor {

        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            // 添加切面逻辑（advise），此处是在目标类代码执行之前，即为MethodBeforeAdviceInterceptor。
            System.out.println(method.getName() + " : before");

            Object ret = proxy.invokeSuper(obj, args);

//            Object ret = proxy.invoke(new Person(), args);

            // 添加切面逻辑（advise），此处是在目标类代码执行之后，即为MethodAfterAdviceInterceptor。
            System.out.println(method.getName() + " : after");
            return ret;
        }
    }


    class MyInvocationHandler implements InvocationHandler{
        private Object target;
        public MyInvocationHandler(Object target) {
            this.target=target;
        }
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName() + " : before");
            Object obj = method.invoke(target, args);
            System.out.println(method.getName() + " : after");
            return obj;
        }
    }



    interface Talk {
        public void sayHello() ;

        public void say();
    }

    class Person implements Talk{

        public void sayHello() {
            System.out.println("hello");
        }

        public void say() {
            sayHello();
            System.out.println("en");
        }
}
