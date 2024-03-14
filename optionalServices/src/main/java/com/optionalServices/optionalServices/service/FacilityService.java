package com.optionalServices.optionalServices.service;


import com.optionalServices.optionalServices.dto.FacilityDTO;
import com.optionalServices.optionalServices.entity.facility.Facility;
import com.optionalServices.optionalServices.repository.facility.FacilityRepository;
import com.optionalServices.optionalServices.utils.Pagination;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    private final FacilityRepository repository;

    public Pagination<Facility> getAllFacility(Integer limit, Integer offset) {
        var pageRequest = PageRequest.of(offset, limit);
        var facilityPagination = repository.findAll(pageRequest);
        return new Pagination<>(facilityPagination);
    }

    public FacilityService(FacilityRepository repository) {
        this.repository = repository;
    }

    public Optional<Facility> getFacilityById(long id) {
        return repository.findById(id);
    }

    public Facility registerFacility(FacilityDTO facilityDTO) {
        return repository.save(new Facility(facilityDTO));
    }

    public List<Facility> getFacilityByName(String name) {
        return repository.findByName(name);

    }

    public void deleteFacility(Long id) {
        var facility = getFacilityById(id);
        facility.ifPresent( it ->
                repository.deleteById(it.getId())
        );
    }

    @Transactional
    public void updateFacility(Long id, FacilityDTO facilityDTO) {
        Optional<Facility> optionalFacility = repository.findById(id);
        if (optionalFacility.isPresent()) {
            Facility facility = optionalFacility.get();
            facility.update(facilityDTO);
            //return repository.save(new Facility(facilityDTO));
        } else {
            throw new EntityNotFoundException("Facility with id " + id + " not found");
        }
    }
}