package com.optionalServices.optionalServices.service;


import com.optionalServices.optionalServices.dto.FacilityDTO;
import com.optionalServices.optionalServices.entity.facility.Facility;
import com.optionalServices.optionalServices.repository.facility.FacilityRepository;
import com.optionalServices.optionalServices.utils.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacilityService {

    private final FacilityRepository repository;

    public Pagination<Facility> getAllServices(Integer limit, Integer offset) {
        var pageRequest = PageRequest.of(offset, limit);
        var servicesPagination = repository.findAll(pageRequest);
        return new Pagination<>(servicesPagination);
    }

    public FacilityService(FacilityRepository repository) {
        this.repository = repository;
    }

    public Optional<Facility> getFacilityById(long id) {
        return repository.findById(id);
    }


    public Facility resgisterfacility(FacilityDTO facilityDTO) {
        return repository.save(new Facility(facilityDTO));
    }
}
