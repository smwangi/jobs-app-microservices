package org.samwan.admin.controller;

import org.samwan.admin.exceptions.CategoryNotFoundException;
import org.samwan.admin.models.Category;
import org.samwan.admin.payload.request.CategoryRequest;
import org.samwan.admin.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private static  final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public ResponseEntity<?>getAll(){

        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    Category findOne(@PathVariable Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CategoryRequest request){

        // return new ResponseEntity<>(new Bazz(id, name), HttpStatus.OK);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setActive(true);
        //category.setCreatedAt(zonedDateTime.toLocalDateTime());

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Category  update(@RequestBody CategoryRequest request, @PathVariable Long id){

        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());

        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(request.getName());
                    category.setDescription(request.getDescription());
                    //category.setModifiedAt(zonedDateTime.toLocalDateTime());
                    return categoryRepository.save(category);
                })
                .orElseGet(() -> {
                   Category category = new Category();
                   category.setName(request.getName());
                   category.setDescription(request.getDescription());
                   //category.setCreatedAt(zonedDateTime.toLocalDateTime());
                   return categoryRepository.save(category);
                });
    }

    @DeleteMapping
    void delete(@PathVariable Long id){
        categoryRepository.deleteById(id);
    }

}
