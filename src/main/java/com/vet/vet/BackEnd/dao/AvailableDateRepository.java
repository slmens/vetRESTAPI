package com.vet.vet.BackEnd.dao;

import com.vet.vet.BackEnd.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate,Long> {
}
