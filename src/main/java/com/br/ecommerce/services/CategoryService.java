package com.br.ecommerce.services;
import com.br.ecommerce.dtos.CategoryDto;
import com.br.ecommerce.entities.Category;
import com.br.ecommerce.repositories.CategoryRepository;
import com.br.ecommerce.services.exceptions.DatabaseWineException;
import com.br.ecommerce.services.exceptions.EntityWineNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDto> searchAll() {
        try {
            List<Category> list = categoryRepository.findAll();
            return list.stream().map(x -> new CategoryDto(x)).collect(Collectors.toList());
        }catch (EntityNotFoundException e){
            throw new EntityWineNotFoundException(
                    "Registros Não Encontrados!"
            );
        }

    }

    // Search by Id
    @Transactional(readOnly = true)
    public CategoryDto searchById(Integer id) {
        Optional<Category> object = categoryRepository.findById(id);
//        Category entity = object.get();
        Category entity = object.orElseThrow(() -> new EntityWineNotFoundException("Registro: " + id + " Não Encontrado"));
        return new CategoryDto(entity);
    }

    // Insert
    @Transactional
    public CategoryDto insert(CategoryDto dto) {
        Category entity = new Category();
        copyDtoForEntity(dto, entity);
        entity = categoryRepository.save(entity);
        return new CategoryDto(entity);
    }

    //Update
    @Transactional
    public CategoryDto update(Integer id, CategoryDto dto) {
        try{
            Category entity = categoryRepository.getReferenceById(id);
            copyDtoForEntity(dto, entity);
            entity = categoryRepository.save(entity);
            return new CategoryDto(entity);
        } catch (EntityNotFoundException e){
            throw new EntityWineNotFoundException(
                    "Registro" + id + "Não encontrado!"
            );
        }
    }

    // delete
    public void delete(Integer id) {
        try{
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntityWineNotFoundException(
                    "Exclusão não possível não realizada!" +
                            id + "Não encontrado!"

            );
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseWineException(
                    "Violação de integridade: Registro" +
                            id + "está em outro registro!"
            );
        }

    }

    //Model
    private void  copyDtoForEntity(CategoryDto dto, Category entity) {
        entity.setName(dto.getName());
    }
}
