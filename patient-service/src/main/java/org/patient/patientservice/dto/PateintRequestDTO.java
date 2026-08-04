package org.patient.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.patient.patientservice.dto.Validation.CreatePatientValidationgroup;

import java.util.Date;

public class PateintRequestDTO {
    @NotBlank
    @Size(max=100,message="Name cant exceed")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message="Email should be valid")
    private String Email;


    @NotBlank(message = "Address is required")
    private String address;


    @NotBlank(message = "DOB is required")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationgroup.class, message = "Registered is required")
    private String registeredDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
