package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.DTO.SiteDto;
import com.emmanuel.escalade.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer>, SiteRepositoryCustom {
    @Query("SELECT new com.emmanuel.escalade.DTO.SiteDto (s.nomSite, s.descriptionSite, s.photo, s.tagOfficiel, r.nomRegion, " +
            " s.photo) " +
            " FROM Site s INNER JOIN s.region r")
    List<SiteDto> listeSiteAvecCotation();

}

