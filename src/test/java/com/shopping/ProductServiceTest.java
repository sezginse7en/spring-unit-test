package com.shopping;

import com.shopping.model.ProductItem;
import com.shopping.repositories.ProductRepository;
import com.shopping.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

    @Mock
    static ProductRepository productRepository;
    static ProductService productService;

    @BeforeEach
    public void setupTest(){
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @AfterEach
    public void testDown() {
        productService = null;
        productRepository = null;
    }

    @Test
    public void Should_Success_When_AddProduct(){

        //arrange
        ProductItem productItem = new ProductItem("iPhone","13",1000D);
        when(productRepository.save(any())).thenReturn(productItem);

        //act
        ProductItem result = productService.addProduct(productItem);

        //assert
        verify(productRepository,times(1)).save(productItem);
        assertEquals(productItem,result);
    }

    @Test
    public void Should_ThrowException_When_ParameterIsNull(){

        assertThrows(IllegalArgumentException.class,()->{
            productService.addProduct(null);
        });

        verify(productRepository,times(0)).save(any());

    }

    @Test
    public void Should_Success_When_UpdateProduct() throws Exception {

        //arrange
        ProductItem productItem = new ProductItem("iPhone","13",1000D);
        productItem.setId("test-id");
        ProductItem updatedProductItem = (ProductItem) productItem.clone();//updating case
        updatedProductItem.setModel("13 Pro");//updating case
        when(productRepository.save(any())).thenReturn(productItem);
        when(productRepository.findById("test-id")).thenReturn(Optional.of(productItem));

        //act
        ProductItem result = productService.updateProduct("test-id",updatedProductItem);

        //assert
        verify(productRepository,times(1)).save(productItem);
        assertEquals(result.getModel(),"13 Pro");
    }

    @Test
    public void Should_ThrowException_When_UpdatedProductIdIsNull() throws CloneNotSupportedException {

        ProductItem productItem = new ProductItem("iPhone","13",1000D);
        productItem.setId("test-id");
        ProductItem updatedProductItem = (ProductItem) productItem.clone();//updating case
        updatedProductItem.setModel("13 Pro");//updating case

        assertThrows(IllegalArgumentException.class,()->{
            productService.updateProduct(null,productItem);
        });

        verify(productRepository,times(0)).save(any());

    }

    @Test
    public void Should_ThrowException_When_NewProductItemIsNull() throws CloneNotSupportedException {

        ProductItem productItem = new ProductItem("iPhone","13",1000D);
        productItem.setId("test-id");

        assertThrows(IllegalArgumentException.class,()->{
            productService.updateProduct("test-id",null);
        });

        verify(productRepository,times(0)).save(any());

    }

    @Test
    public void Should_Success_When_DeleteProduct() throws Exception {

        //arrange
        doNothing().when(productRepository).deleteById("test-id");

        //act
        productService.deleteProduct("test-id");

        //assert
        verify(productRepository,times(1)).deleteById("test-id");
    }

    @Test
    public void Should_ThrowException_When_ProductIdIsNull() {

        assertThrows(IllegalArgumentException.class,()->{
            productService.deleteProduct(null);
        });

        verify(productRepository,times(0)).deleteById("test-id");

    }

    @Test
    public void Should_Success_When_GetAllProduct(){

        //arrange
        List<ProductItem> productItemList = new ArrayList<>();
        ProductItem productItem1 = new ProductItem("iPhone","13",1000D);
        ProductItem productItem2 = new ProductItem("iPhone","12",900D);
        productItemList.add(productItem1);
        productItemList.add(productItem2);
        when(productRepository.findAll()).thenReturn(productItemList);

        //act
        List<ProductItem> result = productService.getProducts();

        //assert
        verify(productRepository,times(1)).findAll();
        assertNotNull(result);
        assertEquals(result.size(),2);

    }




}
