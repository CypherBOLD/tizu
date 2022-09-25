package com.br.ecommerce.repositories;

import com.br.ecommerce.entities.Category;
import com.br.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
