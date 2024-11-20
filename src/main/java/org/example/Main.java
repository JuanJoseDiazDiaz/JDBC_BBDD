package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String urlmSGBBLocal = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String bbdd = "tiendavidejuegos";
        String urlConexion = urlmSGBBLocal + bbdd;
        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            Statement st = cn.createStatement();
            String sentenciaCreacionTabla = "CREATE TABLE `tiendavidejuegos`.`videojuegos` (\n" +
                    "  `idvideojuegos` INT(7) NOT NULL,\n" +
                    "  `nombre` VARCHAR(45) NULL,\n" +
                    "  `genero` VARCHAR(45) NULL,\n" +
                    "  `plataforma` VARCHAR(45) NULL,\n" +
                    "  `PEGI` VARCHAR(45) NULL,\n" +
                    "  `precio` FLOAT NULL,\n" +
                    "  PRIMARY KEY (`idvideojuegos`));";
            st.executeUpdate(sentenciaCreacionTabla);

            String sentenciaCreacionTablaUsuarios = "CREATE TABLE `tiendavidejuegos`.`usuarios` (\n" +
                    "  `idUsuario` INT NOT NULL,\n" +
                    "  `nombre` VARCHAR(45) NULL,\n" +
                    "  `apellidos` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`idUsuario`));\n";
            st.executeUpdate(sentenciaCreacionTablaUsuarios);


            String sentenciaCreacionTablaObservaciones = "CREATE TABLE `tiendavidejuegos`.`observaciones` (\n" +
                    "  `idObservaciones` INT NOT NULL,\n" +
                    "  `idvideojuegos` INT NULL,\n" +
                    "  `duracion` FLOAT NULL,\n" +
                    "  `puntuacion` FLOAT NULL,\n" +
                    "  `vecesJugado` INT NULL,\n" +
                    "  `idUsuario` INT NULL,\n" +
                    "  PRIMARY KEY (`idObservaciones`),\n" +
                    "  INDEX `idUsuario_idx` (`idUsuario` ASC) VISIBLE,\n" +
                    "  INDEX `idvideojuegos_idx` (`idvideojuegos` ASC) VISIBLE,\n" +
                    "  CONSTRAINT `idUsuario`\n" +
                    "    FOREIGN KEY (`idUsuario`)\n" +
                    "    REFERENCES `tiendavidejuegos`.`usuarios` (`idUsuario`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `idvideojuegos`\n" +
                    "    FOREIGN KEY (`idvideojuegos`)\n" +
                    "    REFERENCES `tiendavidejuegos`.`videojuegos` (`idvideojuegos`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION);";
            st.executeUpdate(sentenciaCreacionTablaObservaciones);

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}