package com.solano.user.service;

import com.solano.user.mapper.UserMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;

/**
 * @author github.com/solano33
 * @date 2024/12/22 19:14
 */
@Service
public class UserTranService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Resource
    private TransactionDefinition transactionDefinition;

    public void update() {
        // 开启事务
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);

        try {
            // 执行事务 SQL
            userMapper.update(1, 11);
            userMapper.update(2, 22);

            // 提交事务
            dataSourceTransactionManager.commit(transaction);
        } catch (Exception e) {
            // 回滚事务
            dataSourceTransactionManager.rollback(transaction);
        }

        //
    }
}
