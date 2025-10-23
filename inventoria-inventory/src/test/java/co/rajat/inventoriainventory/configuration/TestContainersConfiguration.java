package co.rajat.inventoriainventory.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

/*
proxyBeanMethods = false.
This is a test configuration, and the two beans are not calling each other. Hence, we don't really need CGLIB
proxies for these beans. This allows for faster bean creation, faster test execution, and less memory footprint.
 */
@TestConfiguration(proxyBeanMethods = false)
public class TestContainersConfiguration {

    /*
    Creating a docker container running MongoDB as soon as the test context loads.
     */
    @Bean
    MongoDBContainer mongoDBContainer() {
        return new MongoDBContainer(DockerImageName.parse("mongo:8"));
    }

    /*
    Dynamically registering properties at runtime. Gets the MongoDB URI from the MongoDB testcontainer, and
    sets the database name too (for all tests)
     */
    @Bean
    DynamicPropertyRegistrar dynamicPropertyRegistrar(MongoDBContainer mongoDBContainer) {
        return registry -> {
            registry.add("spring.data.mongodb.uri", mongoDBContainer::getConnectionString);
            registry.add("spring.data.mongodb.database", () -> "inventoria-inventory");
        };
    }
}
