package br.com.gabrielfelicidade.pocdynamodb.repository;

import br.com.gabrielfelicidade.pocdynamodb.config.DynamoDBTestConfig;
import br.com.gabrielfelicidade.pocdynamodb.model.Product;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = { DynamoDBTestConfig.class })
@ActiveProfiles("test")
public class DynamoDBRepositoryTest {

    private DynamoDBProxyServer server;

    @Value("${amazon.dynamodb.port}")
    private String port;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @BeforeEach
    public void setup() throws Exception {
        server = ServerRunner.createServerFromCommandLineArgs(
                new String[]{"-inMemory", "-port", port}
        );
        server.start();

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput().withReadCapacityUnits(5L).withWriteCapacityUnits(5L);

        amazonDynamoDB.createTable(dynamoDBMapper.generateCreateTableRequest(Product.class).withProvisionedThroughput(provisionedThroughput));
    }

    @AfterEach
    public void tearDown() throws Exception {
        server.stop();
    }

}
