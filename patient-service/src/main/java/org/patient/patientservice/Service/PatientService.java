package org.patient.patientservice.Service;

import org.patient.patientservice.Repository.PatientRepo;
import org.patient.patientservice.dto.PateintRequestDTO;
import org.patient.patientservice.dto.PatientResponseDTO;
import org.patient.patientservice.exception.EmailAlreadyExists;
import org.patient.patientservice.exception.PatientNotFoundException;
import org.patient.patientservice.grpc.BillingServiceGrpcClient;
import org.patient.patientservice.kafka.KafkaProducer;
import org.patient.patientservice.mapper.PatientMapper;
import org.patient.patientservice.model.Patient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
// business logic are like having unique email id
public class PatientService {
    private final PatientRepo patientRepo;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public PatientService(PatientRepo patientrepo, BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer) { //Dependency Injection
        this.patientRepo = patientrepo;
        this.billingServiceGrpcClient=billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepo.findAll();
        return patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();

    }

    public PatientResponseDTO createPatient(PateintRequestDTO pateintRequestDTO) {
        if (patientRepo.existsByEmail(pateintRequestDTO.getEmail())) {
            throw new EmailAlreadyExists("A patient is already exists with this mail id " + pateintRequestDTO.getEmail());
        }
        Patient newPatient = patientRepo.save(PatientMapper.toModel(pateintRequestDTO));
        billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),newPatient.getName(),newPatient.getEmail());
        kafkaProducer.sendEvent(newPatient);
        return PatientMapper.toDTO(newPatient);

    }

    public PatientResponseDTO updatePatient(UUID id, PateintRequestDTO pateintRequestDTO) {
        Patient patient = patientRepo.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient Not found with ID:"+id));

        if (patientRepo.existsByEmailAndIdNot(pateintRequestDTO.getEmail(),id)) {
            throw new EmailAlreadyExists("A patient is already exists with this mail id " + pateintRequestDTO.getEmail());
        }
        patient.setName(pateintRequestDTO.getName());
        patient.setAddress(pateintRequestDTO.getAddress());
        patient.setEmail(pateintRequestDTO.getEmail());
        patient.setDate_of_birth(LocalDate.parse(pateintRequestDTO.getDateOfBirth()));

        Patient updatedPatient =patientRepo.save(patient);
        return PatientMapper.toDTO(updatedPatient);





    }
    public void deletePatient(UUID id){
        patientRepo.deleteById(id);
    }

}
