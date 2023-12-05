package com.vet.vet.UI;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vet.vet.BackEnd.business.concretes.AppointmentManager;
import com.vet.vet.BackEnd.dao.DoctorRepository;
import com.vet.vet.BackEnd.dto.responseDto.AppointmentFrontendDTO;
import com.vet.vet.BackEnd.entities.Appointment;
import com.vet.vet.BackEnd.entities.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {
    private final AppointmentManager appointmentManager;

    private final AppointmentForm form;
    TextField animalFilterText = new TextField();
    Grid<AppointmentFrontendDTO> grid = new Grid<>(AppointmentFrontendDTO.class);


    public MainView(AppointmentManager appointmentManager, DoctorRepository doctorRepository) {
        this.appointmentManager = appointmentManager;

        addClassName("appointment-list");
        setSizeFull();
        configureGrid();
        configureFilter();

        List<Doctor> doctorList = doctorRepository.findAll();

        form = new AppointmentForm(doctorList);

        Div content = new Div(grid,form);
        content.addClassName("content");
        content.setSizeFull();

        add(animalFilterText,content);
        updateList(null);
    }

    private void configureFilter() {
        animalFilterText.setPlaceholder("Filter by id...");
        animalFilterText.setClearButtonVisible(true);
        animalFilterText.setValueChangeMode(ValueChangeMode.LAZY);
        animalFilterText.addValueChangeListener(e -> updateList(animalFilterText.getValue()));
    }

    private void updateList(String filteredText) {
        List<AppointmentFrontendDTO> filteredAppointmentList = new ArrayList<>();
        List<Appointment> animalFilteredAppointmentList = new ArrayList<>();
        Long id = null;

       try {
           id = Long.parseLong(filteredText);
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

        List<Appointment> appointmentList = appointmentManager.findAll().getData();
        if (appointmentList.isEmpty()){
            //
        }else if (filteredText == null || filteredText.isEmpty()){

            appointmentList.forEach(appointment -> filteredAppointmentList.add(new AppointmentFrontendDTO(appointment.getId(),appointment.getAppointmentDate(),appointment.getAnimal().getId(),appointment.getDoctor())));
            grid.setItems(filteredAppointmentList);
        } else {
            if (id == null) {
                // error
            }else{
                Long finalId = id;
                animalFilteredAppointmentList = appointmentList.stream().filter(appointment -> Objects.equals(appointment.getAnimal().getId(), finalId)).collect(Collectors.toList());
                animalFilteredAppointmentList.forEach(appointment -> filteredAppointmentList.add(new AppointmentFrontendDTO(appointment.getId(),appointment.getAppointmentDate(),appointment.getAnimal().getId(),appointment.getDoctor())));
                grid.setItems(filteredAppointmentList);
            }
        }
    }

    private void configureGrid(){
        grid.addClassName("appointment-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("doctor");
        grid.setColumns("id","appointmentDate","animalID");
        grid.addColumn(appointment -> {
            Doctor doctor = appointment.getDoctor();

            return doctor == null ? "-" : doctor.getName();
        }).setHeader("Doctor Name");
    }

}
