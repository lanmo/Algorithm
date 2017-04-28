package com.yn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by yangnan on 17/4/19.
 * 代理测试
 */
public class ProxyTest {

    public static void main(String[] args) {

        ProxyTest test = new ProxyTest();

        UserService userService = test.new UserServiceImpl();

        InvocationHandler handler = test.new ProxyUser(userService);

        UserService u = (UserService) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{UserService.class}, handler);
        String r = u.save("yangnan", userService);

        u.toString();

        System.out.println(r);

    }

    private class ProxyUser implements InvocationHandler {

        private Object target;
        public ProxyUser(Object target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("代理对象:" + proxy.getClass().getName());
            Object object = method.invoke(target, args);
            System.out.println("methodName:" + method.getName() + ",param:" + Arrays.toString(args) + ",res:" + object);
            return object + ":代理";
        }
    }

    private interface UserService {
        String save(String userName, UserService u);
        String delete(String userName);
    }

    private class UserServiceImpl implements UserService {

        public String save(String userName, UserService u) {
            u.delete(userName);
            return userName + ":save成功";
        }

        public String delete(String userName) {
            return userName + ":delete成功";
        }
    }
}
