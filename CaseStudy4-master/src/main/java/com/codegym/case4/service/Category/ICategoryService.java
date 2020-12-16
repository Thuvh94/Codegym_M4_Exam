package com.codegym.case4.service.Category;

import com.codegym.case4.model.Category;

import java.util.Optional;

public interface ICategoryService{
    Iterable<Category> findAll();

    Category save(Category category);

    Optional<Category> findById(Long id);

    void remove(Long id);
}
