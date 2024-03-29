package linhVu.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@NamedStoredProcedureQuery(
        name = "getAllProducts",
        procedureName = "getAllProducts",
        resultClasses = {Product.class}
)

@NamedStoredProcedureQuery(
        name = "addProduct",
        procedureName = "sp_insert_product",
        parameters = {
                @StoredProcedureParameter(name = "active", mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(name = "createDate", mode = ParameterMode.IN, type = java.sql.Timestamp.class),
                @StoredProcedureParameter(name = "description", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "image", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "name", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "price", mode = ParameterMode.IN, type = Double.class),
                @StoredProcedureParameter(name = "quantity", mode = ParameterMode.IN, type = Double.class)

        }
)
@NamedStoredProcedureQuery(
        name = "findById",
        procedureName = "sp_findById",
        parameters = {
                @StoredProcedureParameter(name = "searchId", mode = ParameterMode.IN, type = Long.class)
        },
        resultClasses = {Product.class}

)

@NamedStoredProcedureQuery(
        name = "editProduct",
        procedureName = "sp_updateProduct",
        parameters = {
                @StoredProcedureParameter(name = "editId", mode = ParameterMode.IN, type = Long.class),
                @StoredProcedureParameter(name = "editActive", mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(name = "editCreateDate", mode = ParameterMode.IN, type = java.sql.Timestamp.class),
                @StoredProcedureParameter(name = "editDescription", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "editImage", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "editName", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "editPrice", mode = ParameterMode.IN, type = Double.class),
                @StoredProcedureParameter(name = "editQuantity", mode = ParameterMode.IN, type = Double.class)
        }
)

@NamedStoredProcedureQuery(
        name = "deleteProduct",
        procedureName = "sp_deleteProduct",
        parameters = {
                @StoredProcedureParameter(name = "deleteId", mode = ParameterMode.IN, type = Long.class)
        },
        resultClasses = {Product.class}

)
@NamedStoredProcedureQuery(
        name = "findByName",
        procedureName = "sp_findByName",
        parameters = {
                @StoredProcedureParameter(name = "searchName", mode = ParameterMode.IN, type = String.class)
        },
        resultClasses = {Product.class}

)


public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date createDate;
    private String image;
    private String name;
    private Double price;
    private Double quantity;
    private String description;
    private Integer active;

    public Product() {
    }

    public Product(Long id, Date createDate, String image, String name, Double price, Double quantity, String description, Integer active) {
        this.id = id;
        this.createDate = createDate;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.active = active;
    }

    public Product(Date createDate, String image, String name, Double price, Double quantity, String description, Integer active) {
        this.createDate = createDate;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
