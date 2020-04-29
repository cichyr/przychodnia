package pl.clinic.examination_category.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.clinic.doctor.model.Doctor;

public interface ExaminationCategoryRepository extends PagingAndSortingRepository<Doctor, Long> {
}
