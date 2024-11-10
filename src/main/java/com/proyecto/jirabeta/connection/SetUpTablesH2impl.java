package com.proyecto.jirabeta.connection;

import com.proyecto.jirabeta.DAOs.interfaces.SetUpTablesDAO;

import java.sql.*;

public class SetUpTablesH2impl implements SetUpTablesDAO {

    public void crearTablas() {
        Connection c = DBManager.connect();

        String sqlProyectos = "CREATE TABLE proyectos (id INTEGER IDENTITY, nombre VARCHAR(256) UNIQUE)";
        String sqlEmpleados = "CREATE TABLE empleados (id INTEGER IDENTITY, nombre VARCHAR(256), apellido VARCHAR(256), dni VARCHAR(256) UNIQUE , email  VARCHAR(256), capacity FLOAT, disponible BIT, proyecto INTEGER, FOREIGN KEY (proyecto) REFERENCES proyectos(id) ON DELETE SET NULL )";
        String sqlTareas = "CREATE TABLE tareas (id INTEGER IDENTITY PRIMARY KEY, horasEstimadas FLOAT, titulo VARCHAR(256) UNIQUE , descripcion VARCHAR(256), estimacion VARCHAR(256), prioridad VARCHAR(256), responsable INTEGER NULL,proyecto INTEGER,     FOREIGN KEY (responsable) REFERENCES empleados(id), FOREIGN KEY (proyecto) REFERENCES proyectos(id) ON DELETE CASCADE)";
        try {
            Statement s = c.createStatement();
            DatabaseMetaData metaData = c.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "PROYECTOS", null);
            //si la tabla proyectos existe no creo ninguna
            if (!rs.next()) {
                s.execute(sqlProyectos);
                s.execute(sqlEmpleados);
                s.execute(sqlTareas);
            } else {
                System.out.println("Las tablas ya se encuentran creadas");
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
