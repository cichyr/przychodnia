package pl.clinic.visit.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends PagingAndSortingRepository<Visit, Long> {
}
