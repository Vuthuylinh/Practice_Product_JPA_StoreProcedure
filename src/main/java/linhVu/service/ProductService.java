package linhVu.service;

import linhVu.model.Product;
import linhVu.model.ProductForm;
import org.springframework.validation.BindingResult;

public interface ProductService extends GeneralService<Product> {
    void save(ProductForm productForm, BindingResult result);
    void update(ProductForm productForm, BindingResult result);

}
