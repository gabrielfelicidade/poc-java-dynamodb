package br.com.gabrielfelicidade.pocdynamodb.repository;

import br.com.gabrielfelicidade.pocdynamodb.model.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductRepository extends CrudRepository<Product, String> {
}