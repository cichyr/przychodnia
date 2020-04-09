package pl.clinic.lab_supervisor.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabSupervisorRepository extends PagingAndSortingRepository<LabSupervisor, Long> {
}