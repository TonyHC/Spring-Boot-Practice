package com.springframework.recipesapp.repository;

import com.springframework.recipesapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    void findByDescriptionTablespoon() {
        // Given
        String unitOfMeasure = "Tablespoon";

        // When
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(unitOfMeasure);

        // Then
        assertEquals("Tablespoon", unitOfMeasureOptional.get().getDescription());
    }

    @Test
    void findByDescriptionOunce() {
        // Given
        String unitOfMeasure = "Ounce";

        // When
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(unitOfMeasure);

        // Then
        assertEquals("Ounce", unitOfMeasureOptional.get().getDescription());
    }
}