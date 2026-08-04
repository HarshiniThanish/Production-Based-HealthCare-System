package org.patient.patientservice.mapper;

import org.patient.patientservice.dto.PateintRequestDTO;
import org.patient.patientservice.dto.PatientResponseDTO;
import org.patient.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientResponseDTO=new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setDateOfBirth(patient.getDate_of_birth().toString());

        return patientResponseDTO;
    }
    public static Patient toModel(PateintRequestDTO pateintRequestDTO){
        Patient patient =new Patient();
        patient.setName(pateintRequestDTO.getName());
        patient.setAddress(pateintRequestDTO.getAddress());
        patient.setEmail(pateintRequestDTO.getEmail());
        patient.setDate_of_birth(LocalDate.parse(pateintRequestDTO.getDateOfBirth()));
        patient.setRegistered_date(LocalDate.parse(pateintRequestDTO.getRegisteredDate()));
        return patient;


    }
}
