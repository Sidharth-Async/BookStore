package com.example.bookstore.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Disabled
@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testDatabaseConnection(){
        Assertions.assertDoesNotThrow(() -> {
            try (Connection connection = dataSource.getConnection()) {
                Assertions.assertFalse(connection.isClosed());
                System.out.println("Database connection successful in test!");


                // Print DB metadata
                String dbName = connection.getMetaData().getDatabaseProductName();
                String dbVersion = connection.getMetaData().getDatabaseProductVersion();
                System.out.println("Connected to: " + dbName + " (version: " + dbVersion + ")");

                // Run a simple query
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT 1")) {

                    Assertions.assertTrue(rs.next(), "Query should return a result");
                    int result = rs.getInt(1);
                    Assertions.assertEquals(1, result, "SELECT 1 should return 1");
                    System.out.println("✅ Test query executed successfully: SELECT 1 = " + result);
                }
            }
        }, "Database connection failed in test!");
    }
}
