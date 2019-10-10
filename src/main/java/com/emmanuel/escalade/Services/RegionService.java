package com.emmanuel.escalade.Services;

import com.emmanuel.escalade.DAO.RegionRepository;
import com.emmanuel.escalade.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    private static final Logger log = LoggerFactory.getLogger(RegionService.class);

    private final RegionRepository regionRepository;

    @Autowired
    RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> findAll() {
        List<Region> maListe = regionRepository.findAll();
        return maListe;
    }
}
