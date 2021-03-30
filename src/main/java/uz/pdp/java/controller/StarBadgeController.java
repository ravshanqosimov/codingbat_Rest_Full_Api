package uz.pdp.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.java.entity.StarBadge;
import uz.pdp.java.payload.StarBadgeDto;
import uz.pdp.java.payload.response.ApiResponse;
import uz.pdp.java.service.StarBadgeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/starBadge")
public class StarBadgeController {

    @Autowired
    StarBadgeService starBadgeService  ;

    @PostMapping
    public HttpEntity<?> add(@Valid   @RequestBody StarBadgeDto     starBadgeDto) {
        ApiResponse apiResponse = starBadgeService.add(starBadgeDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {

        List<StarBadge >  starBadgeList = starBadgeService.getAll();
        return ResponseEntity.ok(starBadgeList);
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        StarBadge  starBadge = starBadgeService.getOneById(id);
        return ResponseEntity.status(starBadge != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(starBadge);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody StarBadgeDto starBadgeDto  ) {
        ApiResponse apiResponse = starBadgeService.edit(id, starBadgeDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping(value = "{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = starBadgeService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }


}