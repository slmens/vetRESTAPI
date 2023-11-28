package com.vet.vet.BackEnd.dao;

import com.vet.vet.BackEnd.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Long> {
}
