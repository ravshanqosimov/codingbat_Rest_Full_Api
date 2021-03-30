package uz.pdp.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.java.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Integer id);
}
