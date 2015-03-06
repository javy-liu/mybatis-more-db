package org.oyach.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/4
 * @since 0.0.1
 * sql解析基准测试
 * 测试了3个sql解析框架  druid fdb-sql-parser JSqlParser
 * 速度
 * druid 大约是 JSqlParser 的10倍
 * JSqlParser 大约是 fdb-sql-parser 的两倍
 * 全部框架使用cache后都有明显改善
 */
@Fork(2) // 迭代集合次数
@Warmup(iterations = 2) // 预热迭代次数
@Measurement(iterations = 3) // 迭代次数
@BenchmarkMode(value = Mode.AverageTime)
@OutputTimeUnit(value = TimeUnit.MILLISECONDS)
@Timeout(time = 60)
@Threads(1)
public class SqlParserBenchmark {

    private static final String select = "select" +
            "\t\t\tid,wm_order_id,wm_food_id,food_price,unit,count,box_num,box_price,food_name,order_time,origin_food_price\n" +
            "\t\tfrom \n" +
            "\t\t\twm_order_detail\n" +
            "\t\twhere\n" +
            "			wm_order_id in (?,?,?) ORDER BY id DESC limit 0,1";


    @Benchmark
    public void druidParserBenchMark() throws ClassNotFoundException {
        int i = 1;

    }


}
