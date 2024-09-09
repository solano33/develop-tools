package com.solano.flink;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * todo: 还没调试
 * @author github.com/solano33
 * @date 2024/9/9 21:54
 */
public class FlinkCDCSQL {

    public static void main(String[] args) throws Exception {
        // 1. 获取Flink执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        // 2. 开启CheckPoint

        // 3. 使用FlinkCDC构建MySQLSource
        tableEnv.executeSql("create table t1(" +
                "id string primary key not enforced," +
                "name string)" +
                "with (" +
                "'connector' = 'mysql-cdc'," +
                "'hostname' = 'localhost'," +
                "'port' = '3306'," +
                "'username' = 'root'" +
                "'password' = 'root'" +
                "'database-name' = 'test'," +
                "'table-name' = 't1')");


        // 4. 读取数据
        Table table = tableEnv.sqlQuery("select * from t1");

        // 5. 打印
        table.execute().print();
    }
}
