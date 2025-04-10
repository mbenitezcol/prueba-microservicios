package com.ejercicio.clientepersona.config;

import java.sql.*;

import javax.sql.*;

import org.springframework.core.io.*;
import org.springframework.jdbc.datasource.init.*;
import org.springframework.stereotype.*;

import jakarta.annotation.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class OracleTeardownExecutor {

    private final DataSource dataSource;

    @PreDestroy
    public void runOracleTeardownScript() {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("setup/teardown-oracle.sql"));
            System.out.println("Script de limpieza Oracle ejecutado correctamente.");
        } catch (Exception e) {
            System.err.println("Error ejecutando script de limpieza Oracle: " + e.getMessage());
        }
    }
}
