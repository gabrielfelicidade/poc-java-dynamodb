package br.com.gabrielfelicidade.pocdynamodb.controller;

import br.com.gabrielfelicidade.pocdynamodb.model.Product;
import br.com.gabrielfelicidade.pocdynamodb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok((List<Product>) productRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product productInfo) {
        return ResponseEntity.ok(productRepository.save(productInfo));
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product productInfo) {
        if(!productRepository.findById(productInfo.getId()).isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productRepository.save(productInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id){
        Optional<Product> productInfo = productRepository.findById(id);
        if(!productInfo.isPresent()) return ResponseEntity.notFound().build();
        productRepository.delete(productInfo.get());
        return ResponseEntity.ok().build();
    }

}
