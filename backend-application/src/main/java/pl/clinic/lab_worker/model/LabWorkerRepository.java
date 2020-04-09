package pl.clinic.lab_worker.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabWorkerRepository extends PagingAndSortingRepository<LabWorker, Long> {
}