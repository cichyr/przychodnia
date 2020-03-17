package pl.clinic.labolatory_examination.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabolatoryExaminationRepository extends PagingAndSortingRepository<LabolatoryExamination, Long> {
}
