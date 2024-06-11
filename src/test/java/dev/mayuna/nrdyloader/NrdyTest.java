package dev.mayuna.nrdyloader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class NrdyTest {

    @Test
    public void testNrdy() {
        TestObject first = new TestObject();
        first.fillData();
        String nrdyString = new Nrdy().convertObjectToNrdyString(first);

        System.out.println("@@@@@@@@: " + nrdyString);

        TestObject second = new Nrdy().convertNrdyStringToObject(nrdyString, TestObject.class);


        System.out.println("First: " + first);
        System.out.println("Second: " + second);
        //assert first.equals(second);

        Assertions.assertThrows(NrdyException.class, () -> new Nrdy().convertNrdyStringToObject("", NoPublicArgsObject.class));
        Assertions.assertThrows(NullPointerException.class, () -> new Nrdy().convertNrdyStringToObject(null, TestObject.class));
        Assertions.assertThrows(NullPointerException.class, () -> new Nrdy().convertNrdyStringToObject(nrdyString, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Nrdy().convertObjectToNrdyString(null));
    }
}
