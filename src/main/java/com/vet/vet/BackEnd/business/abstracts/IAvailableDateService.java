package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.AvailableDateSaveDTO;
import com.vet.vet.BackEnd.entities.AvailableDate;

import java.util.List;

public interface IAvailableDateService {
    List<AvailableDate> findAll();
    AvailableDate findById(Long id);
    Boolean save(AvailableDateSaveDTO availableDateSaveDTO);
    Boolean update(AvailableDateSaveDTO availableDateSaveDTO,Long id);
    Boolean delete(Long id);
}
