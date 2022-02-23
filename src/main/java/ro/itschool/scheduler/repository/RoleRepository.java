package ro.itschool.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.itschool.scheduler.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

//    @Query("SELECT count(r) FROM Role r WHERE r.name = :name")
//    int existsByUsername(@Param("name") String name);

    Role findByName(String name);

}
