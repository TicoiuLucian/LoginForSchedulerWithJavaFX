package ro.itschool.scheduler.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.itschool.scheduler.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUserNameIgnoreCase(String username);

    MyUser findByEmail(String username);

    MyUser findByUserNameAndPassword(String userName, String password);

//	@Query("SELECT u FROM User u WHERE u.id = :id")
//	User findUserByUserStatusAndUserName(@Param("id") long id);

}
