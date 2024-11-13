package com.proyecto.jirabeta.connection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_BASE_URL = "jdbc:h2:tcp://localhost//{DIR}";
	private static final String DB_NAME = "/proyecto";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";
	private static final Logger logger = LoggerFactory.getLogger(DBManager.class);

	public static Connection connect() {
		Connection c = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.error("Driver de base de datos no encontrado: " + DB_DRIVER, e);
			System.exit(0);
		}
		try {
			String url = "jdbc:h2:tcp://localhost//{DIR}/jira-beta";
			url = url.replace("{DIR}", getDirectorioBase());
			System.out.println(url);
			c = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
			c.setAutoCommit(false);
		} catch (SQLException e) {
			logger.error("Error al conectar con la base de datos en URL: " +  e);
			System.exit(0);
		}

		return c;
	}

	private static String getDirectorioBase() {
		File currDir = new File("h2/base_de_datos/");
		return currDir.getAbsolutePath();
	}

	public static String obtenerUbicacionBase() {
		String url = "jdbc:h2:tcp://localhost//{DIR}/ejemplo";
		url = url.replace("{DIR}", getDirectorioBase());
		return url;
	}

}
