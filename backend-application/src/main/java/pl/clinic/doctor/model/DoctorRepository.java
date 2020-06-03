package pl.clinic.doctor.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends PagingAndSortingRepository<Doctor, Long> {

    @Query(
            value = "SELECT * FROM doctor WHERE doctor.id IN " +
                    "(" +
                    "SELECT d.id FROM doctor AS d, account, role " +
                    "WHERE account.role_id = role.role AND role.name = 'DOC' AND account.status = 'ENABLED' AND account.employee_id = doctor.id" +
                    ")",
            nativeQuery = true
    )
    Iterable<Doctor> findAllWhereAccountEnabled();
}

