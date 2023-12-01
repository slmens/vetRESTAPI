package com.vet.vet.BackEnd.api;

import com.vet.vet.BackEnd.business.concretes.AvailableDateManager;
import com.vet.vet.BackEnd.dto.requestDto.AvailableDateSaveDTO;
import com.vet.vet.BackEnd.entities.AvailableDate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availableDates")
public class AvailableDateController {

    private final AvailableDateManager availableDateManager;

    public AvailableDateController(AvailableDateManager availableDateManager) {
        this.availableDateManager = availableDateManager;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AvailableDate findById(@PathVariable("id") Long id){
        return this.availableDateManager.findById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDate> findAll(){
        return this.availableDateManager.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean save(@RequestBody AvailableDateSaveDTO availableDateSaveDTO){
        return this.availableDateManager.save(availableDateSaveDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean update(@RequestBody AvailableDateSaveDTO availableDateSaveDTO, @PathVariable("id") Long id){
        return this.availableDateManager.update(availableDateSaveDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean delete(@PathVariable("id") Long id){
        return this.availableDateManager.delete(id);
    }
}
