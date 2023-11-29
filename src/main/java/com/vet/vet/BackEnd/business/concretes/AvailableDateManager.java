package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IAvailableDateService;
import com.vet.vet.BackEnd.dao.AvailableDateRepository;
import com.vet.vet.BackEnd.dao.DoctorRepository;
import com.vet.vet.BackEnd.dto.requestDto.doctor.AvailableDateSaveDTO;
import com.vet.vet.BackEnd.entities.AvailableDate;
import com.vet.vet.BackEnd.entities.Doctor;
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
    public List<AvailableDate> findAll() {
        return this.availableDateRepository.findAll();
    }

    @Override
    public AvailableDate findById(Long id) {

        try {
            return this.availableDateRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("AvailableDate not found with id: " + id));
        }catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());;
        }

        return null;
    }

    @Override
    public Boolean save(AvailableDateSaveDTO availableDateSaveDTO) {
        AvailableDate availableDate = new AvailableDate();
        Boolean result = false;

        if (this.doctorRepository.existsById(availableDateSaveDTO.getDoctorID())){
            try{
                Doctor doctor = this.doctorRepository.findById(availableDateSaveDTO.getDoctorID()).orElseThrow();
                availableDate.setDoctor(doctor);
                availableDate.setAvailableDate(availableDateSaveDTO.getAvailableDate());
                try {
                    this.availableDateRepository.save(availableDate);
                    result = true;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public Boolean update(AvailableDateSaveDTO availableDateSaveDTO,Long id) {
        Boolean result = false;

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
                        result = true;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        return false;
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return result;
    }

    @Override
    public Boolean delete(Long id) {
        Boolean result = false;
        if (this.availableDateRepository.existsById(id)){
            try {
                this.availableDateRepository.deleteById(id);
                result = true;
            }catch (Exception e){
                return null;
            }
        }else{
            throw  new RuntimeException(("there is no record with this id in database : " + id));
        }

        return result;
    }
}
