package linhVu.controller;

import linhVu.model.Product;
import linhVu.model.ProductForm;
import linhVu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@PropertySource("classpath:global_config_app.properties")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ModelAndView showProducts() {

        List<Product> productList = productService.findAll();

        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("productList", productList);

        return modelAndView;
    }
    @GetMapping("/create-product")
    public String createProductForm(Model model){
        model.addAttribute("product", new ProductForm());
        return "product/create";
    }

    @PostMapping("/save-product")
    public ModelAndView saveProduct(@ModelAttribute ProductForm productform, BindingResult result) {

        // thong bao neu xay ra loi
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }

        productService.save(productform);



        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new ProductForm());
        modelAndView.addObject("success", "New product created successfully!");
        return modelAndView;
    }

    @GetMapping("/product/{id}/edit")
    public  ModelAndView editProductForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }

    @PostMapping("/update-product")
    public ModelAndView updateProduct(@ModelAttribute ProductForm productform, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }

        productService.update(productform);

        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", new ProductForm());
        modelAndView.addObject("success", "updated product successfully!");
        return modelAndView;
    }
    @GetMapping("/product/{id}/delete")
    public String deleteProductForm(@PathVariable Long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/delete";
    }

    @PostMapping("/delete-product")
    public String deleteProduct(Product product, Model model){
        productService.delete(product.getId());
        model.addAttribute("success","Delete product successfully");
//        return "product/delete";
        return "redirect:/products";  // de quay tro lai list product.
    }

    @RequestMapping("product/{id}/view")
    public String viewDetailProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "product/view";
    }
    @PostMapping("/search-product")
    public ModelAndView searchProductResult(@RequestParam("search-by-name") String name){
        List<Product> productSearchList= productService.findByName(name);
        ModelAndView modelAndView = new ModelAndView("product/searchResult");
        modelAndView.addObject("productSearchList", productSearchList);
        return modelAndView ;
    }
}
