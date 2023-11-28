package com.vet.vet.BackEnd.business.concretes;

import com.vet.vet.BackEnd.business.abstracts.IDoctorService;
import com.vet.vet.BackEnd.dao.DoctorRepository;
import com.vet.vet.BackEnd.dto.requestDto.doctor.DoctorSaveDTO;
import com.vet.vet.BackEnd.dto.responseDto.doctor.DoctorResponseDTO;
import com.vet.vet.BackEnd.entities.Doctor;
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
    public List<DoctorResponseDTO> findAll() {
        List<DoctorResponseDTO> doctorResponseDTOS = new ArrayList<>();
        List<Doctor> doctorList = this.doctorRepository.findAll();
        doctorList.forEach(item -> doctorResponseDTOS.add(modelMapper.map(item,DoctorResponseDTO.class)));

        return doctorResponseDTOS;
    }

    @Override
    public DoctorResponseDTO findById(Long id) {
        try {
            Doctor doctor = this.doctorRepository.findById(id).orElseThrow();
            DoctorResponseDTO doctorResponseDTO = modelMapper.map(doctor, DoctorResponseDTO.class);
            return doctorResponseDTO;
        }catch (Exception e){

        }

        // appointment ve available date çekip buna ver sonra return et
        return null;
    }

    @Override
    public Boolean save(DoctorSaveDTO doctorSaveDTO) {
        Boolean result = false;

        //try {
            Doctor doctor = modelMapper.map(doctorSaveDTO,Doctor.class);
            doctor.setAppointments(null);
            doctor.setAvailableDates(null);

            Doctor pseudoDoc = this.doctorRepository.save(doctor);

            if (pseudoDoc.getClass() == Doctor.class){
                result = true;
            }

            return result;
        //}catch (Exception e){
          //  System.out.println(e.getMessage());
            //return result;
        //}
    }

    @Override
    public Boolean update(DoctorSaveDTO doctorSaveDTO) {
        Boolean result = false;

        try {
            Doctor doctor = modelMapper.map(doctorSaveDTO,Doctor.class);

            Doctor pseudoDoc = this.doctorRepository.save(doctor);

            if (pseudoDoc.getClass() == Doctor.class){
                result = true;
            }

            return result;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return result;
        }
    }

    @Override
    public Boolean delete(Long id) {
        // If the result variable returns true, this means that no error occurred during the deletion process.
        Boolean result = false;

        try {
            this.doctorRepository.deleteById(id);
            result = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

}
