package com.prottonne.rbac.service;

import com.prottonne.rbac.dto.Permission;
import com.prottonne.rbac.entity.PermissionEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class Finder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ROLE = "role";

    @PersistenceContext
    private EntityManager entityManager;

    public Finder() {
        super();
    }

    public List<Permission> findByRole(Integer role) {

        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PermissionEntity> criteriaQuery = criteriaBuilder.createQuery(PermissionEntity.class);
            Root<PermissionEntity> root = criteriaQuery.from(PermissionEntity.class);

            criteriaQuery.
                    select(root)
                    .where(
                            criteriaBuilder.
                                    equal(
                                            root.get(ROLE), role
                                    )
                    );

            TypedQuery<PermissionEntity> query = entityManager.createQuery(criteriaQuery);
            List<PermissionEntity> resultList = query.getResultList();

            return getPermissions(resultList);

        } finally {
            entityManager.close();
        }

    }

    private List<Permission> getPermissions(List<PermissionEntity> resultList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
