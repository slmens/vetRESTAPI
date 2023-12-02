package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IDoctorService;
import com.vet.vet.BackEnd.dao.DoctorRepository;
import com.vet.vet.BackEnd.dto.requestDto.DoctorSaveDTO;
import com.vet.vet.BackEnd.dto.responseDto.DoctorResponseDTO;
import com.vet.vet.BackEnd.entities.Doctor;
import com.vet.vet.core.result.Result;
import com.vet.vet.core.result.ResultData;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorManager implements IDoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public DoctorManager(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultData<List<DoctorResponseDTO>> findAll() {
        List<DoctorResponseDTO> doctorResponseDTOS = new ArrayList<>();
        List<Doctor> doctorList = this.doctorRepository.findAll();
        doctorList.forEach(item -> doctorResponseDTOS.add(modelMapper.map(item,DoctorResponseDTO.class)));

        return new ResultData<>(true, "Doctor list found!", "200", doctorResponseDTOS);
    }

    @Override
    public ResultData<DoctorResponseDTO> findById(Long id) {
        ResultData<DoctorResponseDTO> resultData = new ResultData<>(false,"Doctor is not found!","404",null);

        try {
            Doctor doctor = this.doctorRepository.findById(id).orElseThrow();
            DoctorResponseDTO doctorResponseDTO = modelMapper.map(doctor, DoctorResponseDTO.class);

            resultData.setStatus(true);
            resultData.setMessage("Doctor is found!");
            resultData.setHttpCode("200");
            resultData.setData(doctorResponseDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return resultData;
    }

    @Override
    public Result save(DoctorSaveDTO doctorSaveDTO) {
        Result result = new Result(false,"Doctor couldn't saved!","400");

        try {
            Doctor doctor = modelMapper.map(doctorSaveDTO,Doctor.class);
            doctor.setAppointments(null);
            doctor.setAvailableDates(null);

            try {
                this.doctorRepository.save(doctor);

                result.setStatus(true);
                result.setMessage("Doctor saved!");
                result.setHttpCode("201");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Result update(DoctorSaveDTO doctorSaveDTO,Long id) {
        Result result = new Result(false,"Doctor couldn't updated!","400");


        if (this.doctorRepository.existsById(id)){
            doctorSaveDTO.setId(id);

            try {
                Doctor doctor = modelMapper.map(doctorSaveDTO,Doctor.class);

                try {
                    this.doctorRepository.save(doctor);

                    result.setStatus(true);
                    result.setMessage("Doctor updated!");
                    result.setHttpCode("201");

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
    public Result delete(Long id) {
        // If the result variable returns true, this means that no error occurred during the deletion process.
        Result result = new Result(false,"Doctor couldn't deleted!","400");

        if (this.doctorRepository.existsById(id)){
            try {
                this.doctorRepository.deleteById(id);
                result.setStatus(true);
                result.setMessage("Doctor deleted!");
                result.setHttpCode("204");

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            throw new RuntimeException(("there is no record with this id in database : " + id));
        }

        return result;
    }

}
