package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Declaracion de variables importante para la conexion con Mysql
        String urlmSGBBLocal = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String bbdd = "biblioteca";
        String urlConexion = urlmSGBBLocal + bbdd;


        // Menu
        int opcion;
        do {
            imprimirMenu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    CreacionTablas(user, password, urlConexion);
                    break;
                case 2:
                    InsercionRegistroVideojuegos(user, password, urlConexion);
                    break;
                case 3:
                    InsercionRegistroUsuario(user, password, urlConexion);
                    break;
                case 4:
                    InsercionRegistroObservacion(user, password, urlConexion);
                    break;
                case 5:
                    Consultas(user, password, urlConexion);
                    System.out.println("Consulta satisfactoriamente hecha!!");
                    break;
                case 6:
                    System.out.println("Adios!!");
                    break;
            }
        } while (opcion != 5);
    }

    // Metodos a usar
    //Todo -> CreacionTablas
    /**
     * Crea las tablas `videojuegos`, `usuarios` y `observaciones` en la base de datos especificada.
     *
     * @param user        El nombre de usuario para conectarse a la base de datos.
     * @param password    La contraseña para conectarse a la base de datos.
     * @param urlConexion La URL de conexión a la base de datos.
     */
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
            System.out.println("Tablas creadas con exito....");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Todo -> InsercionRegistrosVideojuegos
    /**
     * Inserta registros en la tabla `videojuegos` en la base de datos especificada.
     *
     * @param user        El nombre de usuario para conectarse a la base de datos.
     * @param password    La contraseña para conectarse a la base de datos.
     * @param urlConexion La URL de conexión a la base de datos.
     */
    public static void InsercionRegistroVideojuegos(String user, String password, String urlConexion) {
        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            // Primer Registro
            String queryPreparadaInsercion = "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('1', 'Dark Soul', 'Soul', 'Playstation', '16', '60');";
            PreparedStatement ps = cn.prepareStatement(queryPreparadaInsercion);
            ps.execute();
            // Segundo Registro
            String queryPreparadaInsercion2 = "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('2', 'The Legend of Zelda: Breath of the Wild', 'Action-Adventure', 'Nintendo Switch', '12', '70');";
            ps = cn.prepareStatement(queryPreparadaInsercion2);
            ps.execute();
            // Tercer Registro
            String queryPreparadaInsercion3 = "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('3', 'Red Dead Redemption 2', 'Action-Adventure', 'Xbox, PlayStation, PC', '18', '60');";
            ps = cn.prepareStatement(queryPreparadaInsercion3);
            ps.execute();
            // Cuarto Registro
            String queryPreparadaInsercion4 = "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('4', 'Minecraft', 'Sandbox', 'PC, PlayStation, Xbox, Nintendo Switch', '7', '30');";
            ps = cn.prepareStatement(queryPreparadaInsercion4);
            ps.execute();
            // Quinto Registro
            String queryPreparadaInsercion5 = "INSERT INTO `biblioteca`.`videojuegos` (`idVideojuegos`, `nombre`, `genero`, `plataformas`, `PEGI`, `precio`) VALUES ('5', 'God of War Ragnarok', 'Action', 'PlayStation', '18', '70');";
            ps = cn.prepareStatement(queryPreparadaInsercion5);
            ps.execute();

            System.out.println("Valores insertado con exito...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

// Todo -> InsercionRegistroObservacion
    /**
     * Inserta registros en la tabla `observaciones` en la base de datos especificada.
     *
     * @param user        El nombre de usuario para conectarse a la base de datos.
     * @param password    La contraseña para conectarse a la base de datos.
     * @param urlConexion La URL de conexión a la base de datos.
     */
    public static void InsercionRegistroObservacion(String user, String password, String urlConexion) {
        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            // Primer Registro
            String queryPreparadaInsercion = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('1', '1', '12', '10', '2', '1');";
            PreparedStatement ps = cn.prepareStatement(queryPreparadaInsercion);
            ps.execute();
            // Segundo Registro
            String queryPreparadaInsercion2 = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('2', '2', '15', '10', '7', '1');";
            ps = cn.prepareStatement(queryPreparadaInsercion2);
            ps.execute();
            // Tercer Registro
            String queryPreparadaInsercion3 = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('3', '3', '19', '9', '4', '2');";
            ps = cn.prepareStatement(queryPreparadaInsercion3);
            ps.execute();
            // Cuarto Registro
            String queryPreparadaInsercion4 = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('4', '4', '59', '7', '9', '1');";
            ps = cn.prepareStatement(queryPreparadaInsercion4);
            ps.execute();
            // Quinto Registro
            String queryPreparadaInsercion5 = "INSERT INTO `biblioteca`.`observaciones` (`idobservaciones`, `idVideojuego`, `duracion`, `puntuacion`, `vecesJugado`, `idUsuario`) VALUES ('5', '5', '49', '6', '6', '2');";
            ps = cn.prepareStatement(queryPreparadaInsercion5);
            ps.execute();
            System.out.println("Valores insertado con exito...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//Todo -> InsercionRegistroUsuario
    /**
     * Inserta registros en la tabla `usuarios` en la base de datos especificada.
     *
     * @param user        El nombre de usuario para conectarse a la base de datos.
     * @param password    La contraseña para conectarse a la base de datos.
     * @param urlConexion La URL de conexión a la base de datos.
     */
    public static void InsercionRegistroUsuario(String user, String password, String urlConexion) {
        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            // Primer Registro
            String queryPreparadaInsercion = "INSERT INTO `biblioteca`.`usuarios` (`idUsuarios`, `nombre`, `apellidos`) VALUES ('1', 'juan', 'palma');";
            PreparedStatement ps = cn.prepareStatement(queryPreparadaInsercion);
            ps.execute();
            // Segundo Registro
            String queryPreparadaInsercion2 = "INSERT INTO `biblioteca`.`usuarios` (`idUsuarios`, `nombre`, `apellidos`) VALUES ('2', 'Sergio', 'Rojas');";
            ps = cn.prepareStatement(queryPreparadaInsercion2);
            ps.execute();
            System.out.println("Valores insertado con exito...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Todo -> Consultas
    /**
     * Realiza consultas sobre las observaciones de videojuegos de un usuario específico en la base de datos.
     *
     * @param user     El nombre de usuario para conectarse a la base de datos.
     * @param password La contraseña para conectarse a la base de datos. * @param urlConexion La URL de conexión a la base de datos.
     */
    public static void Consultas(String user, String password, String urlConexion) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido a las Consultas!! ");
        System.out.println("Introduce el id del usuario, que quieres consultar: ");
        int idUsuario = sc.nextInt();

        try (Connection cn = DriverManager.getConnection(urlConexion, user, password)) {
            String SentenciaConsulta = "SELECT \n" +
                    "    u.nombre AS Nombre_Usuario, \n" +
                    "    v.nombre AS Titulo_Juego, \n" +
                    "    o.duracion AS Duracion, \n" +
                    "    o.puntuacion AS Puntuacion, \n" +
                    "    o.vecesJugado AS Veces_Jugados\n" +
                    "FROM \n" +
                    "    biblioteca.usuarios AS u\n" +
                    "JOIN \n" +
                    "    biblioteca.observaciones AS o ON u.idUsuarios = o.idUsuario\n" +
                    "JOIN \n" +
                    "    biblioteca.videojuegos AS v ON v.idVideojuegos = o.idVideojuego\n" +
                    "WHERE \n" +
                    "    u.idUsuarios = ?;";
            PreparedStatement pst = cn.prepareStatement(SentenciaConsulta);
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();

            boolean encontradoUsuario = false;
            while (rs.next()) {
                encontradoUsuario = true;
                System.out.println("El nombre de usuario es: " + rs.getString(1));
                System.out.println("El nombre del titulo es: " + rs.getString(2));
                System.out.println("La duracion del juego es de unos: " + rs.getString(3));
                System.out.println("La puntuacion del juego es de: " + rs.getString(4));
                System.out.println("La veces jugadas en el juego es de: " + rs.getString(5));
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("\n");
            }
            if (!encontradoUsuario) {
                System.out.println("Error, IdUsuario no encontrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Imprime el menu creado
     * */
    public static void imprimirMenu() {
        System.out.println("=================================================");
        System.out.println("               MENÚ PRINCIPAL                   ");
        System.out.println("=================================================");
        System.out.println("1. Crear Tablas (Usuarios, Videojuegos, Observaciones)");
        System.out.println("2. Insertar Registro de Videojuegos");
        System.out.println("3. Insertar Registro de Usuarios");
        System.out.println("4. Insertar Registro de Observaciones");
        System.out.println("5. Consultas");
        System.out.println("6. Salir");
        System.out.println("=================================================");
        System.out.print("Selecciona una opción: ");
    }
}