package pl.clinic.user.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends PagingAndSortingRepository<PersonDetails, Long> {
}
