package com.vet.vet.BackEnd.dao;

import com.vet.vet.BackEnd.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {
    List<Animal> findAllByCustomerId(Long id);
}
