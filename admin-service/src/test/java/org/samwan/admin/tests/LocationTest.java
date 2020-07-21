package org.samwan.admin.tests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.samwan.admin.controller.LocationController;
import org.samwan.admin.models.Location;
import org.samwan.admin.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class LocationTest {

    @InjectMocks
    LocationController locationController;

    @Autowired
    LocationRepository locationRepository;

    @Test
    public void testAddLocation(){

        Location location = new Location("Local 1","location 1",true);
        locationRepository.save(location);
       assertTrue(locationRepository.getByName(location.getName()).getName().equals(location.getName()));
    }
}
