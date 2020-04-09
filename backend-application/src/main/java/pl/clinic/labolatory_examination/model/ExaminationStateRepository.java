package pl.clinic.labolatory_examination.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationStateRepository extends PagingAndSortingRepository<ExaminationState, Long> {
}