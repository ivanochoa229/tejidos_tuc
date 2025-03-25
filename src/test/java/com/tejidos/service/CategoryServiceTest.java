package com.tejidos.service;

import com.tejidos.presentation.dto.response.CategoryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/CategoryUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByIdTest_successful(){
        Long idCategory = 1L;
        String expectedNameCategory = "HILO";
        CategoryResponse response = categoryService.findById(idCategory);
        assertEquals(idCategory, response.idCategory());
        assertEquals(expectedNameCategory, response.nameCategory());
    }

    @Test
    public void findByIdTest_whenCategoryDoesNoExist(){
        Long idCategory = 10L;
        assertThrows(NotFoundException.class, () -> categoryService.findById(idCategory));
    }

    @Test
    public void findAllTest_successful(){
        List<Long> expectedIds = Arrays.asList(1L, 2L, 3L);
        List<String> expectedNames = Arrays.asList("HILO", "LANA", "ACCESORIOS");
        List<CategoryResponse> response = categoryService.findAll();
        assertEquals(expectedIds.size(), response.size());
        assertEquals(expectedNames.size(), response.size());
        for (int i = 0; i < response.size(); i++) {
            assertEquals(expectedIds.get(i), response.get(i).idCategory());
            assertEquals(expectedNames.get(i), response.get(i).nameCategory());
        }
    }


}
