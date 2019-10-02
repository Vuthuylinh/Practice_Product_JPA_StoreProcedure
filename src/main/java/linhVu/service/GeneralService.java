package linhVu.service;

import linhVu.model.ProductForm;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface GeneralService<E> {
    List<E> findAll();
    E findById(Long id);
    void delete (Long id);
    List<E> findByName(String name);



}
