package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.DoctorSaveDTO;
import com.vet.vet.BackEnd.dto.responseDto.DoctorResponseDTO;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;

import java.util.List;

public interface IDoctorService {
    ResultData<List<DoctorResponseDTO>> findAll();
    ResultData<DoctorResponseDTO> findById(Long id);
    Result save(DoctorSaveDTO doctorSaveDTO);
    Result update(DoctorSaveDTO doctorSaveDTO,Long id);
    Result delete(Long id);
}
