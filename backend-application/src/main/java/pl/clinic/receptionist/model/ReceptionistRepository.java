package pl.clinic.receptionist.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends PagingAndSortingRepository<Receptionist, Long> {
}
