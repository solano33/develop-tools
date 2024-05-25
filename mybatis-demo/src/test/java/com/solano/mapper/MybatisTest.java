package com.solano.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * @author github.com/solano33
 * @date 2024/5/24 22:31
 */
@Slf4j
public class MybatisTest {

    private static SqlSessionFactory factory;

    @Before
    public void before() throws IOException {
        // 读取配置文件
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
        // 通过SqlSessionFactoryBuilder创建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void testSelectOne() {
        // 获取到SqlSession
        SqlSession session = factory.openSession();
        // 调用Mapper中的指定方法 com.solano.mapper.UserMapper.selectAll是statementId
        Object result = session.selectList("com.solano.mapper.UserMapper.selectAll");
        log.info("result: {}", result);
        session.close();
    }

}
