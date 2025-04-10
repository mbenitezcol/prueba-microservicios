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
public class OracleSetupExecutor {

    private final DataSource dataSource;

    @PostConstruct
    public void runOracleSetupScript() {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("setup/setup-oracle.sql"));
            System.out.println("Script de configuracion Oracle ejecutado correctamente.");
        } catch (Exception e) {
            System.err.println("Error ejecutando script Oracle: " + e.getMessage());
        }
    }
}
