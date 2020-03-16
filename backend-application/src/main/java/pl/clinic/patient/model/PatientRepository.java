package pl.clinic.patient.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
}
