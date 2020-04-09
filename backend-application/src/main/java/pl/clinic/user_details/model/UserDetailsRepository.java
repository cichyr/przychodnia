package pl.clinic.user_details.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends PagingAndSortingRepository<UserDetails, Long> {
}