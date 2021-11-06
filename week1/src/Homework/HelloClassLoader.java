package Homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object o = new HelloClassLoader().findClass("Hello").newInstance();
        Class classType=o.getClass();
        System.out.println(classType.getName());
        Method m =   classType.getMethod("hello");
        m.invoke(o);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            FileInputStream fis = new FileInputStream(".\\src\\Homework\\Hello.xlass");
            while ((len = fis.read(bytes))!= -1){
                break;
            }
        bytes = decode(bytes,len);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return defineClass(name,bytes,0,len);
    }
    public byte[] decode(byte[] b,int len){
        byte temp;
       for (int i = 0; i < len; i++) {
           temp = b[i];
           b[i] = (byte)(~temp);
       }
       return b;
    }

}
