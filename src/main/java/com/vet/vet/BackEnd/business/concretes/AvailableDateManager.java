package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IAvailableDateService;
import com.vet.vet.BackEnd.dao.AvailableDateRepository;
import com.vet.vet.BackEnd.dao.DoctorRepository;
import com.vet.vet.BackEnd.dto.requestDto.AvailableDateSaveDTO;
import com.vet.vet.BackEnd.entities.AvailableDate;
import com.vet.vet.BackEnd.entities.Doctor;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableDateManager implements IAvailableDateService {
    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public AvailableDateManager(AvailableDateRepository availableDateRepository, DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.availableDateRepository = availableDateRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultData<List<AvailableDate>> findAll() {
        ResultData<List<AvailableDate>> resultData = new ResultData<>(true,"Available date list found!","200",this.availableDateRepository.findAll());
        return resultData;
    }

    @Override
    public ResultData<AvailableDate> findById(Long id) {
        ResultData<AvailableDate> resultData = new ResultData<>(false,"Available date couldn't found!","404",null);
        try {
            AvailableDate availableDate = this.availableDateRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("AvailableDate not found with id: " + id));
            resultData.setHttpCode("200");
            resultData.setMessage("Available date found!");
            resultData.setData(availableDate);
            resultData.setStatus(true);
        }catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());;
        }

        return resultData;
    }

    @Override
    public Result save(AvailableDateSaveDTO availableDateSaveDTO) {
        AvailableDate availableDate = new AvailableDate();
        Result result = new Result(false,"Available date couldn't saved!","400");

        if (this.doctorRepository.existsById(availableDateSaveDTO.getDoctorID())){
            try{
                Doctor doctor = this.doctorRepository.findById(availableDateSaveDTO.getDoctorID()).orElseThrow();
                availableDate.setDoctor(doctor);
                availableDate.setAvailableDate(availableDateSaveDTO.getAvailableDate());
                try {
                    this.availableDateRepository.save(availableDate);
                    result.setHttpCode("201");
                    result.setMessage("Available date saved!");
                    result.setStatus(true);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public Result update(AvailableDateSaveDTO availableDateSaveDTO,Long id) {
        Result result = new Result(false,"Available date couldn't updated!","400");

        if (this.availableDateRepository.existsById(id)){
            availableDateSaveDTO.setId(id);
            if (this.doctorRepository.existsById(availableDateSaveDTO.getDoctorID())){
                try{
                    Doctor doctor = this.doctorRepository.findById(availableDateSaveDTO.getDoctorID()).orElseThrow();
                    AvailableDate availableDate = new AvailableDate();

                    availableDate.setDoctor(doctor);
                    availableDate.setAvailableDate(availableDateSaveDTO.getAvailableDate());
                    availableDate.setId(availableDateSaveDTO.getId());
                    System.out.println(availableDate.getDoctor().getId());
                    System.out.println(availableDate.getId());

                    try {
                        this.availableDateRepository.save(availableDate);
                        result.setHttpCode("201");
                        result.setMessage("Available date updated!");
                        result.setStatus(true);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return result;
    }

    @Override
    public Result delete(Long id) {
        Result result = new Result(false,"Available date couldn't deleted!","400");
        if (this.availableDateRepository.existsById(id)){
            try {
                this.availableDateRepository.deleteById(id);
                result.setHttpCode("204");
                result.setMessage("Available date deleted!");
                result.setStatus(true);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            throw  new RuntimeException(("there is no record with this id in database : " + id));
        }

        return result;
    }
}
