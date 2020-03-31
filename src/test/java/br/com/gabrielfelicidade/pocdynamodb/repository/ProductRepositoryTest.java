package br.com.gabrielfelicidade.pocdynamodb.repository;

import br.com.gabrielfelicidade.pocdynamodb.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductRepositoryTest extends DynamoDBRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldQueryProductsSuccessfully(){
        Product product = Product.builder()
                .description("teste")
                .value(123.4)
                .available(10)
                .build();

        product = productRepository.save(product);

        Assertions.assertNotNull(product);

        boolean productFound = productRepository.findById(product.getId()).isPresent();

        Assertions.assertTrue(productFound);
    }

}
