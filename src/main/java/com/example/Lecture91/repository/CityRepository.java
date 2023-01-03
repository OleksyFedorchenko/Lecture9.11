package com.example.Lecture91.repository;

import com.example.Lecture91.entity.CityEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    CityEntity findByName(String name);

    @Query("SELECT c FROM CityEntity c JOIN c.country ccntr WHERE c.population<:cpopulation AND ccntr.name=:ccountryname")
    List<CityEntity> findByPopulationAndCountryName(@Param("cpopulation") int population, @Param("ccountryname") String countryName, Pageable pageable);
}
