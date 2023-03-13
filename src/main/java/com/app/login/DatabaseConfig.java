package com.app.login;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
<<<<<<< HEAD
import io.helidon.config.Config;
=======

import io.helidon.config.Config;

>>>>>>> dev
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@ApplicationScoped
public class DatabaseConfig {
    @ApplicationScoped
    private Config config = Config.create();

    @Produces
    @ApplicationScoped
    public DataSource createDataSource() {
        DataSource dataSource = null;

        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(config.get("datasource.url").asString().get());
            hikariConfig.setUsername(config.get("datasource.username").asString().get());
            hikariConfig.setPassword(config.get("datasource.password").asString().get());
            dataSource = new HikariDataSource(hikariConfig);
        } catch (Exception e) {
			System.out.println("Error in Database Connection");
			e.printStackTrace();
        }
		System.out.println("DataSource is:"+dataSource);
        return dataSource;
    }
}
