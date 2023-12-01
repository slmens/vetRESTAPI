package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.DoctorSaveDTO;
import com.vet.vet.BackEnd.dto.responseDto.DoctorResponseDTO;

import java.util.List;

public interface IDoctorService {
    List<DoctorResponseDTO> findAll();
    DoctorResponseDTO findById(Long id);
    Boolean save(DoctorSaveDTO doctorSaveDTO);
    Boolean update(DoctorSaveDTO doctorSaveDTO,Long id);
    Boolean delete(Long id);
}
