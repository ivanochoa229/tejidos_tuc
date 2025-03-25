package com.tejidos.service;

import com.tejidos.presentation.dto.response.UnitResponse;
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
@Sql(scripts = {"/db_templates_test/UnitUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class UnitServiceTest {
    @Autowired
    private  UnitService unitService;

    @Test
    public void findAllTest_successful(){
        List<Long> expectedIds = Arrays.asList(1L, 2L, 3L);
        List<String> expectedNames = Arrays.asList("KILOGRAM", "METER", "UNIT");
        List<UnitResponse> response = unitService.findAll();
        assertEquals(expectedIds.size(), response.size());
        assertEquals(expectedNames.size(), response.size());
        for (int i = 0; i < response.size(); i++) {
            assertEquals(expectedIds.get(i), response.get(i).idUnit());
            assertEquals(expectedNames.get(i), response.get(i).unitName());
        }
    }

    @Test
    public void findByIdTest_successful(){
        Long idUnit = 3L;
        String unitNameExpected = "UNIT";
        UnitResponse response = unitService.findById(idUnit);
        assertEquals(idUnit, response.idUnit());
        assertEquals(unitNameExpected, response.unitName());
    }

    @Test
    public void findByIdTest_whenUnitDoesNotExist(){
        Long idUnit = 10L;
        assertThrows(NotFoundException.class, () -> unitService.findById(idUnit));
    }

}
