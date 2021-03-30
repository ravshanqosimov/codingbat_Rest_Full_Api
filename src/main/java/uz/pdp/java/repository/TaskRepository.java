package uz.pdp.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.java.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
