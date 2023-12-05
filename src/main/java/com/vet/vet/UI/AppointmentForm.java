package com.vet.vet.UI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import com.vet.vet.BackEnd.dto.responseDto.AppointmentFrontendDTO;
import com.vet.vet.BackEnd.entities.Doctor;

import java.util.List;

public class AppointmentForm extends FormLayout {

    TextField animalID = new TextField("Animal ID");
    DateTimePicker appointmentDate = new DateTimePicker("Appointment date and time");
    ComboBox<Doctor> doctor = new ComboBox<>("Doctor Name");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");

    Binder<AppointmentFrontendDTO> binder = new BeanValidationBinder<>(AppointmentFrontendDTO.class);

    public AppointmentForm(List<Doctor> doctorList){
        addClassName("appointment-form");

        binder.bindInstanceFields(this);
        doctor.setItems(doctorList);
        doctor.setItemLabelGenerator(Doctor::getName);

        add(
                animalID,
                appointmentDate,
                doctor,
                createButtonLayout()
        );
    }

    public void setAppointments(AppointmentFrontendDTO appointmentFrontendDTO){
        binder.setBean(appointmentFrontendDTO);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        delete.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this,binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save,delete,close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class AppointmentFormEvent extends ComponentEvent<AppointmentForm> {
        private AppointmentFrontendDTO appointmentFrontendDTO;

        protected AppointmentFormEvent(AppointmentForm source, AppointmentFrontendDTO appointmentFrontendDTO) {
            super(source, false);
            this.appointmentFrontendDTO = appointmentFrontendDTO;
        }

        public AppointmentFrontendDTO getContact() {
            return appointmentFrontendDTO;
        }
    }

    public static class SaveEvent extends AppointmentFormEvent {
        SaveEvent(AppointmentForm source, AppointmentFrontendDTO appointmentFrontendDTO) {
            super(source, appointmentFrontendDTO);
        }
    }

    public static class DeleteEvent extends AppointmentFormEvent {
        DeleteEvent(AppointmentForm source, AppointmentFrontendDTO appointmentFrontendDTO) {
            super(source, appointmentFrontendDTO);
        }

    }

    public static class CloseEvent extends AppointmentFormEvent {
        CloseEvent(AppointmentForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
