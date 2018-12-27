package org.commons;

import java.io.FileInputStream;
import java.util.Properties;

import org.flywaydb.core.Flyway;

public class FlywayInitializer {
	public static void initialize() {
		try {
			Properties properties = new Properties();
			String path = FlywayInitializer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			System.out.println(path);
			System.out.println(path + "META-INF/flywayConfig.properties");
			properties.load(new FileInputStream(path + "META-INF/flywayConfig.properties"));
			String jdbcUrl = properties.getProperty("flyway.url");
			String user = properties.getProperty("flyway.user");
			String password = properties.getProperty("flyway.password");
			String locations = properties.getProperty("flyway.locations");
			String prefix = properties.getProperty("flyway.sqlMigrationPrefix");
			String separator = properties.getProperty("flyway.sqlMigrationSeparator");
			String suffix = properties.getProperty("flyway.sqlMigrationSuffix");

			Flyway flyway = new Flyway();
			flyway.setDataSource(jdbcUrl, user, password);
			flyway.setLocations(locations);
			flyway.setSqlMigrationSuffix(suffix);
			flyway.setSqlMigrationPrefix(prefix);
			flyway.setSqlMigrationSeparator(separator);
			flyway.clean();
			flyway.migrate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
