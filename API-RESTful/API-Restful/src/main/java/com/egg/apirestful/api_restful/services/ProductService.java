package com.egg.apirestful.api_restful.services;

import com.egg.apirestful.api_restful.dto.ProductDTO;
import com.egg.apirestful.api_restful.entities.Product;
import com.egg.apirestful.api_restful.exceptions.MyException;
import com.egg.apirestful.api_restful.mappers.ProductMapper;
import com.egg.apirestful.api_restful.repositories.ProductRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    ProductService(ProductMapper productMapper, ProductRepository productRepository){
        this.productMapper= productMapper;
        this.productRepository= productRepository;
    }

    public List<ProductDTO> getAllProducts(){
        List<Product> products = this.productRepository.findAll();
        return this.productMapper.convertToDtoList(products);
    }


    public ProductDTO getProductById(String id){
        Product product = this.productRepository.getById(id);
        return this.productMapper.convertToDto(product);

    }
    private void validate(ProductDTO productDTO) throws MyException{
        if (productDTO.getId().isEmpty()){
            throw new MyException("ID cannot be blank");
        }

        if (productDTO.getNombre().isEmpty()){
            throw new MyException("Nombre cannot be blank");
        }

        if (productDTO.getPrecio()==null){
            throw new MyException("Precio cannot be blank");
        }
    }
    @Transactional
    public ProductDTO addProduct(ProductDTO productDTO) throws MyException {
        validate(productDTO);
        Product product = this.productMapper.convertToEntity(productDTO);
        this.productRepository.save(product);
        return productDTO;
    }
    public void updateProduct (String id, ProductDTO productDTO) throws MyException {
        Product toUpProd= this.productRepository.getById(id);
        if (! (toUpProd.getId().isEmpty())){
            toUpProd.setPrecio(productDTO.getPrecio());
            toUpProd.setNombre(productDTO.getNombre());
            productRepository.save(toUpProd);
        }else throw new MyException("there is no product with id: "+ id);

    }

    public ProductDTO deactivateProduct(String id, ProductDTO productDTO) throws MyException {

        Product product = productRepository.getById(id);

        if (!(product.getId().isEmpty())){
            product.setActive(false);
            this.productRepository.save(product);
            return productMapper.convertToDto(product);
        }else throw new MyException("there is no product with id: "+ id);
    }

}
