package org.samwan.admin.tests;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.samwan.admin.controller.CategoryController;
import org.samwan.admin.models.Category;
import org.samwan.admin.models.Location;
import org.samwan.admin.payload.request.CategoryRequest;
import org.samwan.admin.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.samwan.admin.repositories.LocationRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class H2IntegrationTest {

    @InjectMocks
    CategoryController categoryController;

    @Autowired
    private LocationRepository locationRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void testLocationRepositoryNotNull(){
        assertNotNull(locationRepository);
    }

    @Test
    public  void testAddLocation(){

        Location location = new Location();
        location.setDescription("Test Description");
        location.setName("kiambu");
        location.setId(1L);
        location.setActive(true);

        locationRepository.save(location);

        assertTrue(location.getName().equals(locationRepository.getByName(location.getName()).getName()));
    }

    @Test
    public void testAddCategory(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));



        Category category = new Category();
        category.setId(new Random().nextLong());
        category.setActive(true);
        category.setDescription("category");
        //category.setCreatedAt(LocalDateTime.now());
        //category.setModifiedAt(LocalDateTime.now());
        category.setName("Category 1");

        //categoryRepository.save(category);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Test");
        categoryRequest.setDescription("Testing Only");

        ResponseEntity<?> responseEntity = categoryController.create(categoryRequest);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        //assertTrue(category.getName().equals(categoryRepository.findByName(category.getName()).getName()));
    }
}
