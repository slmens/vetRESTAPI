package com.vet.vet.BackEnd.business.abstracts;

import com.vet.vet.BackEnd.dto.requestDto.AvailableDateSaveDTO;
import com.vet.vet.BackEnd.entities.AvailableDate;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;

import java.util.List;

public interface IAvailableDateService {
    ResultData<List<AvailableDate>> findAll();
    ResultData<AvailableDate> findById(Long id);
    Result save(AvailableDateSaveDTO availableDateSaveDTO);
    Result update(AvailableDateSaveDTO availableDateSaveDTO,Long id);
    Result delete(Long id);
}
