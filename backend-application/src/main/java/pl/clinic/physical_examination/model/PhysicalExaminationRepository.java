package pl.clinic.physical_examination.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalExaminationRepository extends PagingAndSortingRepository<PhysicalExamination, Long> {
}
