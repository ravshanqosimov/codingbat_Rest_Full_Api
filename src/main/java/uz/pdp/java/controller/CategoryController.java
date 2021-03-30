package uz.pdp.java.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.java.entity.Category;
import uz.pdp.java.entity.Language;
import uz.pdp.java.payload.CategoryDto;
import uz.pdp.java.payload.LanguageDto;
import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.service.CategoryService;
import uz.pdp.java.service.LanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.add(categoryDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {

        List<Category> categoryList = categoryService.getAll();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Category category = categoryService.getOneById(id);
        return ResponseEntity.status(category != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(category);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.edit(id, categoryDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping(value = "{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }


}
