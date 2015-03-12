package org.oyach.mybatis.datasource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/12
 * @since 0.0.1
 */
public class DataSourcePartitionTransactionManager extends DataSourceTransactionManager {
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        TransactionSynchronizationManager.setCurrentTransactionReadOnly(definition.isReadOnly());
        super.doBegin(transaction, definition);
    }
}
