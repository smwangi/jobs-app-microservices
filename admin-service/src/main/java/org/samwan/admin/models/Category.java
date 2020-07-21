package org.samwan.admin.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Entity
@Table(name="categories",uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Category extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Categpry name is a required field.")
    private String name;

    @Column
    @Null
    private String description;

//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "modified_at")
//    private LocalDateTime modifiedAt;

    @Column(name = "is_active")
    private boolean isActive;

    public Category(){

    }

    public Category(String name, String description, boolean isActive){
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public LocalDateTime getModifiedAt() {
//        return modifiedAt;
//    }
//
//    public void setModifiedAt(LocalDateTime modifiedAt) {
//        this.modifiedAt = modifiedAt;
//    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

