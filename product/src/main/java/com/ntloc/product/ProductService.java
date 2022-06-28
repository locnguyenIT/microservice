package com.ntloc.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProduct() {
        List<ProductEntity> listProduct = productRepository.findAll();
        return productMapper.toListDTO(listProduct);
    }

    public ProductDTO getProduct(Long productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() ->
                new IllegalStateException("Product not found"));
        return productMapper.toDTO(product);
    }
}
