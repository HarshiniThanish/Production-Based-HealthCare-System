package org.patient.patientservice.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.patient.patientservice.Service.PatientService;
import org.patient.patientservice.dto.PateintRequestDTO;
import org.patient.patientservice.dto.PatientResponseDTO;
import org.patient.patientservice.dto.Validation.CreatePatientValidationgroup;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name="Patient",description = "api for managing patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Operations")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    @Operation(summary="Create Patients")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationgroup.class}) @RequestBody PateintRequestDTO pateintRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(pateintRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Patients")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PateintRequestDTO pateintRequestDTO){

        PatientResponseDTO patientResponseDTO=patientService.updatePatient(id,pateintRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);

    }
    @Operation(summary = "Delete Patients")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
    return ResponseEntity.noContent().build();}
}
