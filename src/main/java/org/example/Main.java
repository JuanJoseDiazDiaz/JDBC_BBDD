package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String urlmSGBBLocal = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String bbdd = "biblioteca";
        String urlConexion = urlmSGBBLocal + bbdd;


        // Metodos BBDD
        CreacionTablas(user, password, urlConexion);
        InsercionRegistroVideojuegos(user, password, urlConexion);
        InsercionRegistroUsuario(user, password, urlConexion);
        InsercionRegistroObservacion(user, password, urlConexion);
    }

    public static void CreacionTablas(String user, String password, String urlConexion) {
        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            Statement st = cn.createStatement();
            String sentenciaCreacionVideojuegos = "CREATE TABLE `biblioteca`.`videojuegos` (\n" +
                    "  `idVideojuegos` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `nombre` VARCHAR(45) NULL,\n" +
                    "  `genero` VARCHAR(45) NULL,\n" +
                    "  `plataformas` VARCHAR(45) NULL,\n" +
                    "  `PEGI` VARCHAR(45) NULL,\n" +
                    "  `precio` FLOAT NULL,\n" +
                    "  PRIMARY KEY (`idVideojuegos`));";
            st.executeUpdate(sentenciaCreacionVideojuegos);

            String sentenciaCreacionTablaUsuarios = "CREATE TABLE `biblioteca`.`usuarios` (\n" +
                    "  `idUsuarios` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `nombre` VARCHAR(45) NULL,\n" +
                    "  `apellidos` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`idUsuarios`));";
            st.executeUpdate(sentenciaCreacionTablaUsuarios);


            String sentenciaCreacionTablaObservaciones = "CREATE TABLE `biblioteca`.`observaciones` (\n" +
                    "  `idobservaciones` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `idVideojuego` INT NULL,\n" +
                    "  `duracion` FLOAT NULL,\n" +
                    "  `puntuacion` FLOAT NULL,\n" +
                    "  `vecesJugado` INT NULL,\n" +
                    "  `idUsuario` INT NULL,\n" +
                    "  PRIMARY KEY (`idobservaciones`),\n" +
                    "  INDEX `idVideojuego_idx` (`idVideojuego` ASC) VISIBLE,\n" +
                    "  INDEX `idUsuario_idx` (`idUsuario` ASC) VISIBLE,\n" +
                    "  CONSTRAINT `idVideojuego`\n" +
                    "    FOREIGN KEY (`idVideojuego`)\n" +
                    "    REFERENCES `biblioteca`.`videojuegos` (`idVideojuegos`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `idUsuario`\n" +
                    "    FOREIGN KEY (`idUsuario`)\n" +
                    "    REFERENCES `biblioteca`.`usuarios` (`idUsuarios`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION);";
            st.executeUpdate(sentenciaCreacionTablaObservaciones);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void InsercionRegistroVideojuegos(String user, String password, String urlConexion) {
        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            // Primer Registro
            String queryPreparadaInsercion =  "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('1', 'Dark Soul', 'Soul', 'Playstation', '16', '60');";
            PreparedStatement ps = cn.prepareStatement(queryPreparadaInsercion);
            ps.execute();
            // Segundo Registro
            String queryPreparadaInsercion2 =  "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('2', 'The Legend of Zelda: Breath of the Wild', 'Action-Adventure', 'Nintendo Switch', '12', '70');";
            ps = cn.prepareStatement(queryPreparadaInsercion2);
            ps.execute();
            // Tercer Registro
            String queryPreparadaInsercion3 =  "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('3', 'Red Dead Redemption 2', 'Action-Adventure', 'Xbox, PlayStation, PC', '18', '60');";
            ps = cn.prepareStatement(queryPreparadaInsercion3);
            ps.execute();
            // Cuarto Registro
            String queryPreparadaInsercion4 =  "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('4', 'Minecraft', 'Sandbox', 'PC, PlayStation, Xbox, Nintendo Switch', '7', '30');";
            ps = cn.prepareStatement(queryPreparadaInsercion4);
            ps.execute();
            // Quinto Registro
            String queryPreparadaInsercion5 =  "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('5', 'God of War Ragnarok', 'Action', 'PlayStation', '18', '70');";
            ps = cn.prepareStatement(queryPreparadaInsercion5);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void InsercionRegistroObservacion(String user, String password, String urlConexion) {
        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            // Primer Registro
            String queryPreparadaInsercion = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('1', '1', '12', '10', '2', '1');";
            PreparedStatement ps = cn.prepareStatement(queryPreparadaInsercion);
            ps.execute();
            // Primer Registro
            String queryPreparadaInsercion2 = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('2', '2', '15', '10', '7', '1');";
            ps = cn.prepareStatement(queryPreparadaInsercion2);
            ps.execute();
            // Primer Registro
            String queryPreparadaInsercion3 = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('3', '3', '19', '9', '4', '2');";
            ps = cn.prepareStatement(queryPreparadaInsercion3);
            ps.execute();
            // Primer Registro
            String queryPreparadaInsercion4 = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('4', '4', '59', '7', '9', '1');";
            ps = cn.prepareStatement(queryPreparadaInsercion4);
            ps.execute();
            String queryPreparadaInsercion5 = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('5', '5', '49', '6', '6', '2');";
            ps = cn.prepareStatement(queryPreparadaInsercion5);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void InsercionRegistroUsuario(String user, String password, String urlConexion) {
        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            // Primer Registro
            String queryPreparadaInsercion =  "INSERT INTO `biblioteca`.`usuarios` (`idUsuarios`, `nombre`, `apellidos`) VALUES ('1', 'juan', 'palma');";
            PreparedStatement ps = cn.prepareStatement(queryPreparadaInsercion);
            ps.execute();
            String queryPreparadaInsercion2 =  "INSERT INTO `biblioteca`.`usuarios` (`idUsuarios`, `nombre`, `apellidos`) VALUES ('2', 'Sergio', 'Rojas');";
            ps = cn.prepareStatement(queryPreparadaInsercion2);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void Consultas(String user, String password, String urlConexion) {
        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            // TERMINAR EL METODO
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}