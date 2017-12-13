/**
 * 
 */
package com.lakala.mini.server.core.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 全伟(QW)
 * @date 2012-5-10
 * @time 下午05:57:37
 */
public class HibernateExtendedJpaDialect extends HibernateJpaDialect {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8213256274748480171L;

	private Logger logger = LoggerFactory
            .getLogger(HibernateExtendedJpaDialect.class);
 
    private ThreadLocal<Integer> previousIsolationLevelHolder = new ThreadLocal<Integer>();
 
    /**
     * This method is overridden to set custom isolation levels on the
     * connection
     *
     * @param entityManager
     * @param definition
     * @return
     * @throws PersistenceException
     * @throws SQLException
     * @throws TransactionException
     */
    @Override
    public Object beginTransaction(final EntityManager entityManager,
            final TransactionDefinition definition)
            throws PersistenceException, SQLException, TransactionException {
 
        if (definition.getTimeout() != TransactionDefinition.TIMEOUT_DEFAULT) {
            getSession(entityManager).getTransaction().setTimeout(
                    definition.getTimeout());
        }
 
        entityManager.getTransaction().begin();
        logger.debug("Transaction started");
 
        getSession(entityManager).doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                logger.debug("The connection instance is {}", connection);
                logger.debug(
                        "The isolation level of the connection is {} and the isolation level set on the transaction is {}",
                        connection.getTransactionIsolation(),
                        definition.getIsolationLevel());
                Integer previousIsolationLevel = DataSourceUtils
                        .prepareConnectionForTransaction(connection, definition);
                previousIsolationLevelHolder.set(previousIsolationLevel);
            }
        });
 
        return prepareTransaction(entityManager, definition.isReadOnly(),
                definition.getName());
    }
 
    @Override
    public Object prepareTransaction(EntityManager entityManager,
            boolean readOnly, String name) throws PersistenceException {
        Session session = getSession(entityManager);
        FlushMode flushMode = session.getFlushMode();
        FlushMode previousFlushMode = null;
        if (readOnly) {
            // We should suppress flushing for a read-only transaction.
            session.setFlushMode(FlushMode.MANUAL);
            previousFlushMode = flushMode;
        } else {
            // We need AUTO or COMMIT for a non-read-only transaction.
            if (flushMode.lessThan(FlushMode.COMMIT)) {
                session.setFlushMode(FlushMode.AUTO);
                previousFlushMode = flushMode;
            }
        }
 
        return new SessionTransactionData(session, previousFlushMode,
                previousIsolationLevelHolder.get());
    }
 
    private static class SessionTransactionData {
 
        private final Session session;
 
        private final FlushMode previousFlushMode;
 
        private final Integer previousIsolationLevel;
 
        public SessionTransactionData(Session session,
                                      FlushMode previousFlushMode, Integer previousIsolationLevel) {
            this.session = session;
            this.previousFlushMode = previousFlushMode;
            this.previousIsolationLevel = previousIsolationLevel;
        }
 
        public void resetFlushMode() {
            if (this.previousFlushMode != null) {
                this.session.setFlushMode(this.previousFlushMode);
            }
        }
 
        public void resetIsolationLevel() {
            this.session.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    DataSourceUtils.resetConnectionAfterTransaction(connection,
                            previousIsolationLevel);
                }
            });
        }
    }
 
    @Override
    public void cleanupTransaction(Object transactionData) {
        ((SessionTransactionData) transactionData).resetIsolationLevel();
        ((SessionTransactionData) transactionData).resetFlushMode();
    }

}
