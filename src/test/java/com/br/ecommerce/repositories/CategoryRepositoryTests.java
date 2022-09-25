//package com.br.ecommerce.repositories;
//
//import com.br.ecommerce.entities.Category;
//import com.br.ecommerce.tests.Factory;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@DataJpaTest
//public class CategoryRepositoryTests {
//
//    @Autowired
//    private CategoryRepository repository;
//
//    private Integer existingId;
//    private Integer nonExistingId;
//    private Integer countTotalCategories;
//    private List<Category> list;
//
//    @BeforeEach
//    void Setup() throws Exception {
//        existingId = 1;
//        nonExistingId = 999;
//        countTotalCategories = 3;
//        list = new ArrayList<>();
//    }
//
//    // Teste do método save
//    @Test
//    public void saveDeveriaPersistirNoBD() {
//        Category category = Factory.criarCategoria();
//        category = repository.save(category);
//        Assertions.assertNotNull(category.getId());
//        Assertions.assertEquals(countTotalCategories + 1, category.getId());
//    }
//
//    // O método update não existe na camada de repository.
//
//
//    // Teste do método findAll
//    @Test
//    public void findAllDeveriaRetornarUmaLista() {
//        list = repository.findAll();
//        Assertions.assertNotNull(list);
//    }
//
//    // Teste do método findById
//    @Test
//    public void findByIdDeveriaRetornarUmRegistroDoBD() {
//        Optional<Category> result = repository.findById(existingId);
//        Assertions.assertTrue(result.isPresent());
//    }
//
//    // Teste do método findById retornando um Opptional vazio
//    @Test
//    public void findByIdDeveriaRetornarUmRegistroDoBDVazio() {
//        Optional<Category> result = repository.findById(nonExistingId);
//        Assertions.assertTrue(result.isEmpty());
//    }
//
//    // Teste do método delete
//    @Test
//    public void deleteDeveriaExcluirUmRegistroDoBD() {
//        repository.deleteById(existingId);
//        Optional<Category> result = repository.findById(existingId);
//        Assertions.assertTrue(result.isEmpty());
//    }
//
//    // Teste do método delete com exceção
//    @Test
//    public void deleteDeveriaNaoEncontrarORegistroNoBD() {
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
//            repository.deleteById(nonExistingId);
//        });
//    }
//
//}
