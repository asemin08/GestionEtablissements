package eu.ensup.gestionetablissement.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Connect.
 */
public class Connect
{
	private static String DRIVER = null;
	private static String URL = null;
	private static String USERNAME = null;
	private static String PASSWORD = null;
	/**
	 * Open an connention with the information in the class
	 *
	 * @return an connection open
	 */
	public static Connection openConnection() throws ExceptionDao
	{
		try (InputStream input = Connect.class.getClassLoader().getResourceAsStream("db.properties")) {

			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Impossible de charger les valeurs");
			}
			prop.load(input);

			DRIVER = prop.getProperty("db.driver");
			URL = prop.getProperty("db.url");
			USERNAME = prop.getProperty("db.username");
			PASSWORD = prop.getProperty("db.password");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		System.out.println(DRIVER + "..." + USERNAME +  "... " + PASSWORD + "..." + URL);
		Connection cn = null;
		try
		{
			//Chargement du Driver
			Class.forName(DRIVER);

			//Récuperation de la connection
			if( URL != null && USERNAME != null && PASSWORD != null )
				cn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			if( cn == null && URL != null )
				cn = DriverManager.getConnection(URL);

			// TODO:  Add logger failed and successfull
		}
		catch (ClassNotFoundException | SQLException e){
			// TODO:  Add logger failed and successfull
			throw new ExceptionDao("Nous ne parvenons pas à joindre le serveur distant. Veuillez réessayer ultérieurement");
		}
		
		return cn;
	}
}
