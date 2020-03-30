package br.com.gabrielfelicidade.pocdynamodb;

import br.com.gabrielfelicidade.pocdynamodb.model.Product;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PocDynamodbApplication {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    public static void main(String[] args) {
        SpringApplication.run(PocDynamodbApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput().withReadCapacityUnits(5L).withWriteCapacityUnits(6L);

        amazonDynamoDB.createTable(dynamoDBMapper.generateCreateTableRequest(Product.class).withProvisionedThroughput(provisionedThroughput));
    }

}
