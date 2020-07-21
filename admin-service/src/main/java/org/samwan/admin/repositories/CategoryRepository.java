package org.samwan.admin.repositories;

import org.samwan.admin.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean isActive(long id);
    Category findByName(String name);
}
