package samir.spring.tpspring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samir.spring.tpspring.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
