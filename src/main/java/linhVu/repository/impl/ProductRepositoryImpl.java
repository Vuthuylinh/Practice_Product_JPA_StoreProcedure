package linhVu.repository.impl;

import linhVu.model.Product;
import linhVu.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
     private EntityManager em;
    //    @Override
//    public void save(Product product) {
//
//    }

    @Override
    public List<Product> findAll() {
        StoredProcedureQuery getAllProductQuery = em.createNamedStoredProcedureQuery("getAllProducts");
        getAllProductQuery.execute();
        List<Product> productList = getAllProductQuery.getResultList();
        return productList;

    }


    @Override
    public void add(Product product) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String createDate = sdf.format(product.getCreateDate());

        StoredProcedureQuery spAddProduct = em.createNamedStoredProcedureQuery("addProduct");
        spAddProduct.setParameter("active", product.getActive());
        spAddProduct.setParameter("createDate", Timestamp.valueOf(createDate));
        spAddProduct.setParameter("description", product.getDescription());
        spAddProduct.setParameter("image", product.getImage());
        spAddProduct.setParameter("name", product.getName());
        spAddProduct.setParameter("price", product.getPrice());
        spAddProduct.setParameter("quantity", product.getQuantity());
        spAddProduct.execute();
    }

    @Override
    public Product findById(Long id) {
        StoredProcedureQuery getProductById = em.createNamedStoredProcedureQuery("findById");
        getProductById.setParameter("searchId", id);
        getProductById.execute();
        Product productFindById = (Product) getProductById.getSingleResult();
        return productFindById;
    }

    @Override
    public void update( Product product) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String editCreateDate = sdf.format(product.getCreateDate());
        StoredProcedureQuery editProduct = em.createNamedStoredProcedureQuery("editProduct");
        editProduct.setParameter("editId",product.getId());
        editProduct.setParameter("editActive", product.getActive());
        editProduct.setParameter("editCreateDate", Timestamp.valueOf(editCreateDate));
        editProduct.setParameter("editDescription", product.getDescription());
        editProduct.setParameter("editImage", product.getImage());
        editProduct.setParameter("editName", product.getName());
        editProduct.setParameter("editPrice", product.getPrice());
        editProduct.setParameter("editQuantity", product.getQuantity());
        editProduct.execute();
    }

    @Override
    public void delete(Long id) {
        StoredProcedureQuery deleteProduct = em.createNamedStoredProcedureQuery("deleteProduct");
        deleteProduct.setParameter("deleteId", id);
        deleteProduct.execute();

    }


    @Override
    public List<Product> findByName(String name) {
        StoredProcedureQuery getProductByName= em.createNamedStoredProcedureQuery("findByName");
        getProductByName.setParameter("searchName", '%'+name+'%');
        getProductByName.execute();
        List<Product> productFindByNameList = getProductByName.getResultList();
        return productFindByNameList;
    }

}
