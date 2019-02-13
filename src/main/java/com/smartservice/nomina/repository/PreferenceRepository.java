package com.smartservice.nomina.repository;


import com.smartservice.nomina.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

}
