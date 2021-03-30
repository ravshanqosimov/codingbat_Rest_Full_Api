package uz.pdp.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.java.entity.Language;
import uz.pdp.java.payload.LanguageDto;
import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.service.LanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.add(languageDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {

        List<Language> languageList = languageService.getAll();
        return ResponseEntity.ok(languageList);
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Language language = languageService.getOneById(id);
        return ResponseEntity.status(language != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(language);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.edit(id, languageDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping(value = "{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = languageService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }


}
