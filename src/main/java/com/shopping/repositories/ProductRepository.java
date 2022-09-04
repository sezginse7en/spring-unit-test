package com.shopping.repositories;

import com.shopping.model.ProductItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductItem, String> {
}
