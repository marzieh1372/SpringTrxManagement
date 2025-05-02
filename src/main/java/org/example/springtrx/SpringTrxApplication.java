package org.example.springtrx;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@SpringBootApplication
public class SpringTrxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTrxApplication.class, args);
    }

    @Autowired
    private DataSource dataSource;


    @PostConstruct
    public void checkSavepointSupport() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            boolean supportsSavepoints = metaData.supportsSavepoints();
            System.out.println("Database supports savepoints: " + supportsSavepoints);
            //it means your database and JDBC driver support savepoints, and thus nested transactions with
            // Propagation.NESTED should work in your environment.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
