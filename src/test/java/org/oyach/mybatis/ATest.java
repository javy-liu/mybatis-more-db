package org.oyach.mybatis;

import org.junit.Test;

/**
 * @author liuzhenyuan
 * @version Last modified 15/6/16
 * @since 0.0.1
 */
public class ATest {

    public static void doSomething() {
        for(int i = 0; i < 4; i++){                 //Non-Compliant, 4 is a magic number
            System.out.println("--------");
        }
    }
    @Test
    public void testName() throws Exception {
        ATest.doSomething();

    }
}
