package linhVu.service.impl;

import linhVu.model.Product;
import linhVu.model.ProductForm;
import linhVu.repository.ProductRepository;
import linhVu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    Environment env;

    @Override
    public List<Product> findAll() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product findById(Long id) {
        Product productFindById = productRepository.findById(id);
        return productFindById;
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }


    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

//    @Override
//    public void update(ProductForm productForm) {
//
//    }


    @Override
    public void  update(ProductForm productForm, BindingResult result) {
        Product productObject = findById(productForm.getId());
        String fileName = uploadFile(productForm, result);
        if (!fileName.equals("")) {
            productObject.setImage(fileName);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//         {
//            Date lDate = formatter.parse(productForm.getCreateDate());
//            Product product = productRepository.findById(productForm.getId());
//            productForm.setCreateDate(String.valueOf(product.getCreateDate()));
//        }

        try {
            Date lDate = formatter.parse(productForm.getCreateDate());
            if (!lDate.equals(""))
                productObject.setCreateDate(lDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        productObject.setName(productForm.getName());
        productObject.setPrice(productForm.getPrice());
        productObject.setQuantity(productForm.getQuantity());
        productObject.setDescription(productForm.getDescription());
        productObject.setActive(productForm.getActive());
        productRepository.update(productObject);


    }

    public String uploadFile(ProductForm productForm, BindingResult result) {
        // thong bao neu xay ra loi
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }
        //luu file len server
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload");

        // luu file len server

        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }


    @Override
    public void save(ProductForm productForm, BindingResult result) {
        Product productObject = findById(productForm.getId());
        String fileName = uploadFile(productForm, result);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date lDate = formatter.parse(productForm.getCreateDate());
            productObject.setImage(fileName);
            productObject.setCreateDate(lDate);
            productObject.setName(productForm.getName());
            productObject.setPrice(productForm.getPrice());
            productObject.setQuantity(productForm.getQuantity());
            productObject.setDescription(productForm.getDescription());
            productObject.setActive(productForm.getActive());
            productRepository.add(productObject);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
