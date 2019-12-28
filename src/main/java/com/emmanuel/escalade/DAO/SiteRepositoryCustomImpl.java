package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.DTO.SiteCriteres;
import com.emmanuel.escalade.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SiteRepositoryCustomImpl implements SiteRepositoryCustom {

    private static final Logger log = LoggerFactory.getLogger(SiteRepositoryCustomImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Site> findSitesByNomAndRegionAndCotationAndNbSecteurs (SiteCriteres siteCriteres) { // (String nomSite, String region, Integer nbSecteurs){
        log.info("findSitesByNomAndRegionAndCotationAndNbSecteurs - siteCriteres=" + siteCriteres.toString() + ".");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Site> criteriaQuery = cb.createQuery(Site.class);
        Root<Site> root = criteriaQuery.from(Site.class);
        List<Predicate> predicates = new ArrayList<>();

        if (siteCriteres.getNomSite() != null && !siteCriteres.getNomSite().equals("")) {
            log.info("critère nomSite=" + siteCriteres.getNomSite());
            predicates.add(cb.like(root.get("nomSite"), "%" + siteCriteres.getNomSite() + "%"));
        }
        if (siteCriteres.getRegionId() != null && !siteCriteres.getRegionId().equals(0)) {
            log.info("critère regionId=" + siteCriteres.getRegionId());
            predicates.add(cb.equal(root.get("region"), siteCriteres.getRegionId()));
        }
        if (siteCriteres.getCotationMin() != null && !siteCriteres.getCotationMin().equals("")) {
            log.info("critère cotationMin=" + siteCriteres.getCotationMin());
            predicates.add(cb.greaterThanOrEqualTo(root.get("cotationMin"), siteCriteres.getCotationMin()));
        }
        if (siteCriteres.getCotationMax() != null && !siteCriteres.getCotationMax().equals("")) {
            log.info("critère cotationMax=" + siteCriteres.getCotationMax());
            predicates.add(cb.lessThanOrEqualTo(root.get("cotationMax"), siteCriteres.getCotationMax()));
        }
        if (siteCriteres.getNbSecteurs() != null) {
            log.info("critère nbSecteurs=" + siteCriteres.getNbSecteurs());
            predicates.add(cb.equal(cb.size(root.get("secteurs")) , siteCriteres.getNbSecteurs()));
        }
        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
