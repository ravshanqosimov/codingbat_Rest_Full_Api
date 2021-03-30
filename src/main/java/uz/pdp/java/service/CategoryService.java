package uz.pdp.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.java.entity.Category;
import uz.pdp.java.entity.Language;
import uz.pdp.java.payload.CategoryDto;
import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.repository.CategoryRepository;
import uz.pdp.java.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired

    LanguageRepository languageRepository;
    // METHODS

    /**
     * ADD NEW CATEGORY
     *
     * @param categoryDto
     * @return ApiResponse
     */


    public ApiResponse add(CategoryDto categoryDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (optionalLanguage.isPresent()) {
            Category category = new Category();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setLanguage(optionalLanguage.get());
            categoryRepository.save(category);
            return new ApiResponse("new category saved", true);
        }
        return new ApiResponse("language ID not found", false);
    }

    /**
     * GET ALL CATEGORY
     *
     * @return List<Category>
     */

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }


    /**
     * GET ONE CATEGORY BY ID
     *
     * @param id
     * @return Category or null
     */
    public Category getOneById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);

    }

    /**
     * EDIT CATEGORY
     *
     * @param id,CategoryDto
     * @return Apiresponse
     */

    //update
    public ApiResponse edit(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
            if (optionalLanguage.isPresent()) {
                Category category = optionalCategory.get();
                category.setName(categoryDto.getName());
                category.setDescription(categoryDto.getDescription());
                category.setLanguage(optionalLanguage.get());
                categoryRepository.save(category);
                return new ApiResponse("category edited", true);
            }
            return new ApiResponse("language ID not found", false);
        }
        return new ApiResponse("Category ID not found", false);
    }

    /**
     * DELETE CATEGORY BY ID
     *
     * @param id
     * @return Apiresponse
     */
    public ApiResponse delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("CATEGORY deleted ", true);
        } catch (Exception e) {
            return new ApiResponse("ERROR!!!", false);
        }
    }

}
