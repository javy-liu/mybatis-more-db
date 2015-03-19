package org.oyach.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/17
 * @since 0.0.1
 */
@Fork(1) // 迭代集合次数
@Warmup(iterations = 1, timeUnit = TimeUnit.NANOSECONDS, time = -1) // 预热迭代次数
@Measurement(iterations = 2, timeUnit = TimeUnit.NANOSECONDS, time = -1) // 迭代次数
@BenchmarkMode(value = {Mode.AverageTime})
@OutputTimeUnit(value = TimeUnit.MILLISECONDS)
//@Timeout(time = 60)
//@Threads(1)
@OperationsPerInvocation(2)
public class TestBenchmark {

    private static int i = 0;

    @Benchmark
    public void test01(){
//        System.out.println(i++);

    }

    @Benchmark
    public void test02(){
       say("oaych");
    }


    public String say(String name){
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("hello" + i++);
        return "";
    }
}
