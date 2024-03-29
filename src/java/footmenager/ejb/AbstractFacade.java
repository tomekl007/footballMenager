/*
AbstractFacade is an abstract class that receives a Type<T> and implements the common
operations (CRUD)( create, read, update and delete) for this type, where <T> is a JPA entity
 */


package footmenager.ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


/**
 *
 * @author markito
 */
public abstract class AbstractFacade<T> {
     private static Logger logger = Logger.getLogger("footballMenager.ejb.AbstractFacade" );
    private Class<T> entityClass;

    public AbstractFacade() {
    }

    public AbstractFacade(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager()
            .persist(entity);
        logger.info("presisted " + entity.toString() );
    }

    public void edit(T entity) {
        getEntityManager()
            .merge(entity);
    }

    public void remove(T entity) {
        getEntityManager()
            .remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager()
                   .find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
                                                          .getCriteriaBuilder()
                                                          .createQuery();
        cq.select(cq.from(entityClass));

        return getEntityManager()
                   .createQuery(cq)
                   .getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
                                                          .getCriteriaBuilder()
                                                          .createQuery();
        cq.select(cq.from(entityClass));

        javax.persistence.Query q = getEntityManager()
                                        .createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);

        return q.getResultList();
    }

    public List<T> findRange(
        int[] range,
        CriteriaQuery query) {
        javax.persistence.Query q = getEntityManager()
                                        .createQuery(query);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);

        return q.getResultList();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager()
                   .getCriteriaBuilder();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
                                                          .getCriteriaBuilder()
                                                          .createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));

        javax.persistence.Query q = getEntityManager()
                                        .createQuery(cq);

        return ((Long) q.getSingleResult()).intValue();
    }
}
