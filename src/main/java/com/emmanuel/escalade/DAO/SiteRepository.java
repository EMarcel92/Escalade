package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.DTO.SiteDto;
import com.emmanuel.escalade.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer>, SiteRepositoryCustom {

//    CE CODE FONCTIONNE, mais je ne peux pas gérer les fonction MIN et MAX avec
    @Query("SELECT new com.emmanuel.escalade.DTO.SiteDto (s.nomSite, s.descriptionSite, s.photo, s.tagOfficiel, r.nomRegion, " +
            " s.photo) " +
            " FROM Site s INNER JOIN s.region r")

    // CE CODE NE FONCIONNE PAS (alors que la requete ne contient pas encore des accès à secteur et voie:
    // No converter found capable of converting from type [org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap] to type [com.emmanuel.escalade.DTO.SiteDto]
//   @Query(value = "SELECT s.nom_site nomSite, s.description_site descriptionSite, s.photo photo, s.tag_officiel tagOfficiel, r.nom_region NomRegion " +
//           "FROM Site s INNER JOIN region r ON s.regionid = r.regionid", nativeQuery = true)


    // MA REQUETTE CIBLE AVEC MIN ET MAX
 //   @Query(value = "SELECT site.nom_site, site.description_site, site.photo, site.tag_officiel " + //, max(voie.cotation_voie), min(voie.cotation_voie) " +
   //         " FROM site " )//+
//            " LEFT JOIN secteur " +
//            " ON site.siteid = secteur.siteid " +
//            " LEFT JOIN voie " +
//            " ON secteur.secteurid = voie.secteurid  " +
//            " JOIN region r " +
//             " ON si.regionid = r.regionid " +
     //       " GROUP BY site.siteid", nativeQuery = true)

    List<SiteDto> listeSiteAvecCotation();

//        trouver la version (la cotation) la plus élevée
//        Document findFirstByNameOrderByVersionDesc(String name);
//        Document findTopByNameOrderByVersionDesc(String name);
}

