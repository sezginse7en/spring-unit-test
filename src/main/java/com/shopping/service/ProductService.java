package com.shopping.service;

import com.shopping.model.ProductItem;
import com.shopping.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductItem addProduct(ProductItem productItem){
        if(productItem == null){
            throw new IllegalArgumentException("productItem can not be null");
        }
        return productRepository.save(productItem);
    }

    public ProductItem updateProduct(String updatedProductId,ProductItem newProductItem) throws Exception {

        if(updatedProductId == null){
            throw new IllegalArgumentException("updatedProductId can not be empty");
        }
        if(newProductItem == null){
            throw new IllegalArgumentException("newProductItem can not be null");
        }
        ProductItem productItem = productRepository.findById(updatedProductId).
                                  orElseThrow(() -> new Exception("Product not found"));
        productItem.setTitle(newProductItem.getTitle());
        productItem.setModel(newProductItem.getModel());
        productItem.setPrice(newProductItem.getPrice());
        return productRepository.save(productItem);
    }

    public void deleteProduct(String deletedProductId){
        if(deletedProductId == null){
            throw new IllegalArgumentException("deletedProductId can not be empty");
        }
        productRepository.deleteById(deletedProductId);
    }

    public List<ProductItem> getProducts(){
        return productRepository.findAll();
    }



}
