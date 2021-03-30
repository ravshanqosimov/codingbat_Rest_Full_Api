package uz.pdp.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.java.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
