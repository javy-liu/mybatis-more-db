package org.oyach.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/3
 * @since 0.0.1
 * 测试基于反射，cache和非cache方式获取方法名的效率
 * 事实证明差别不大
 */
@Fork(2) // 迭代集合次数
@Warmup(iterations = 4) // 预热迭代次数
@Measurement(iterations = 3) // 迭代次数
@BenchmarkMode(value = Mode.AverageTime)
@OutputTimeUnit(value = TimeUnit.NANOSECONDS)
@Timeout(time = 60)
@Threads(1)
public class MethodBenchmark {
    private static final String id = "org.oyach.jmh.mapper.UserMapper.findById";
    private static final String id2 = "org.oyach.jmh.mapper.UserMapper2.findById";

    @Benchmark
    public void stringBenchMark() throws ClassNotFoundException {
        int index = id.lastIndexOf(".");
        String className = id.substring(0, index);
    }


}
