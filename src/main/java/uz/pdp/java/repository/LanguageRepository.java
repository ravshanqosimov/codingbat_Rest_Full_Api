package uz.pdp.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.java.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);

}
