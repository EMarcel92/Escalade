package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.DTO.SiteCriteres;
import com.emmanuel.escalade.model.Site;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepositoryCustom {
    List<Site> findSitesByNomAndRegionAndCotationAndNbSecteurs (SiteCriteres siteCriteres); //(String nomSite, String region, Integer nbSecteurs);
}
