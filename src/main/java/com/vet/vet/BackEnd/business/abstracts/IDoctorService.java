package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.doctor.DoctorSaveDTO;
import com.vet.vet.BackEnd.dto.responseDto.doctor.DoctorResponseDTO;

import java.util.List;

public interface IDoctorService {
    List<DoctorResponseDTO> findAll();
    DoctorResponseDTO findById(Long id);
    Boolean save(DoctorSaveDTO doctorSaveDTO);
    Boolean update(DoctorSaveDTO doctorSaveDTO,Long id);
    Boolean delete(Long id);
}
