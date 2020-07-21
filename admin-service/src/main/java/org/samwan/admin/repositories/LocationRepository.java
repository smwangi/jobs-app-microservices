package org.samwan.admin.repositories;

import org.samwan.admin.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    Location getByName(String name);
    //boolean isActive(long id);
}
