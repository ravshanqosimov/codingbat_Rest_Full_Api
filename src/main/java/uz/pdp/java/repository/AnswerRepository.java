package uz.pdp.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.java.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
