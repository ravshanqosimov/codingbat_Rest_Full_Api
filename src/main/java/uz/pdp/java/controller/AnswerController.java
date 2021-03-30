package uz.pdp.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.java.entity.Answer;
import uz.pdp.java.entity.Language;
import uz.pdp.java.payload.AnswerDto;
import uz.pdp.java.payload.LanguageDto;
import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.service.AnswerService;
import uz.pdp.java.service.LanguageService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody AnswerDto answerDto) {
        ApiResponse apiResponse = answerService.add(answerDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {

        List<Answer> answerList = answerService.getAll();
        return ResponseEntity.ok(answerList);
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Answer answer = answerService.getOneById(id);
        return ResponseEntity.status(answer != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(answer);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody AnswerDto answerDto) {
        ApiResponse apiResponse = answerService.edit(id, answerDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping(value = "{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = answerService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }


}
