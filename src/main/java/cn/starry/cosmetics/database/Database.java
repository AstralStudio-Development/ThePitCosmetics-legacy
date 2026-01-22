package cn.starry.cosmetics.database;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.database.PlayerData;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Database {
    private final String host;
    private final String database;
    private final String user;
    private final String pass;
    private final int port;
    private final int poolSize;
    private final int maxLifetime;
    private final boolean ssl;
    private final boolean certificateVerification;
    private final String OWNED_COSMETICS = "cosmetics_owned";
    private final String SELECTED_COSMETICS = "cosmetics_selected";
    private final String UUID = "uuid";
    private final String COSMETIC_TYPE_ID = "type_id";
    private final String COSMETIC_ID = "id";
    private HikariDataSource dataSource;

    public Database() {
        FileConfiguration yml = Cosmetics.getInstance().getConfig();
        this.host = yml.getString("database.host");
        this.database = yml.getString("database.database");
        this.user = yml.getString("database.username");
        this.pass = yml.getString("database.password");
        this.port = yml.getInt("database.port");
        this.ssl = yml.getBoolean("database.ssl");
        this.certificateVerification = yml.getBoolean("database.verify-certificate", true);
        this.poolSize = yml.getInt("database.pool-size", 10);
        this.maxLifetime = yml.getInt("database.max-lifetime", 1800);
    }

    public boolean connect() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName("PitCosmeticsPool");
        hikariConfig.setMaximumPoolSize(this.poolSize);
        hikariConfig.setMaxLifetime((long) this.maxLifetime * 1000L);
        hikariConfig.setJdbcUrl("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database);
        hikariConfig.setUsername(this.user);
        hikariConfig.setPassword(this.pass);
        hikariConfig.addDataSourceProperty("useSSL", String.valueOf(this.ssl));
        if (!this.certificateVerification) {
            hikariConfig.addDataSourceProperty("verifyServerCertificate", String.valueOf(false));
        }
        hikariConfig.addDataSourceProperty("characterEncoding", "utf8");
        hikariConfig.addDataSourceProperty("encoding", "UTF-8");
        hikariConfig.addDataSourceProperty("useUnicode", "true");
        hikariConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
        hikariConfig.addDataSourceProperty("jdbcCompliantTruncation", "false");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "275");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("socketTimeout", String.valueOf(TimeUnit.SECONDS.toMillis(30L)));
        this.dataSource = new HikariDataSource(hikariConfig);
        try {
            this.dataSource.getConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void init0() {
        try (Connection connection = this.dataSource.getConnection()) {
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s VARCHAR(200), %s INT(20), %s INT(20));", "cosmetics_owned", "uuid", "type_id", "id");
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
            sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s VARCHAR(200), %s INT(20), %s INT(20));", "cosmetics_selected", "uuid", "type_id", "id");
            try (Statement statement = connection.createStatement()) {
                try {
                    statement.executeUpdate(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s VARCHAR(200), %s INT(20));", "cosmetics_sorter", "uuid", "sort_id");
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlayerData fetchData(UUID uuid) {
        PlayerData playerData = new PlayerData(uuid);
        try (Connection connection = this.dataSource.getConnection()) {
            ResultSet rs;
            String sql = String.format("SELECT %s,%s FROM %s WHERE %s=?;", "type_id", "id", "cosmetics_selected", "uuid");
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, uuid.toString());
                rs = ps.executeQuery();
                try {
                    while (rs.next()) {
                        playerData.setSelectedCosmetic(rs.getInt("type_id"), rs.getInt("id"));
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                }
            }
            sql = String.format("SELECT %s,%s FROM %s WHERE %s=?;", "type_id", "id", "cosmetics_owned", "uuid");
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, uuid.toString());
                rs = ps.executeQuery();
                try {
                    while (rs.next()) {
                        playerData.addOwnedCosmetic(rs.getInt("type_id"), rs.getInt("id"));
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerData;
    }



    public void saveData(PlayerData playerData) {
        try (Connection connection = this.dataSource.getConnection()) {
            String sql1 = String.format("SELECT %s FROM %s WHERE %s=? AND %s=?", "id", "cosmetics_selected", "uuid", "type_id");
            playerData.getSelectedCosmeticsMap().forEach((k, v) -> {
                block27:
                {
                    try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                        ps.setString(1, playerData.getUuid().toString());
                        ps.setInt(2, k);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                String sql2 = String.format("UPDATE %s SET %s=? WHERE %s=? AND %s=?", "cosmetics_selected", "id", "uuid", "type_id");
                                try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                                    ps2.setInt(1, v);
                                    ps2.setString(2, playerData.getUuid().toString());
                                    ps2.setInt(3, k);
                                    ps2.executeUpdate();
                                    break block27;
                                }
                            }
                            String sql3 = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)", "cosmetics_selected", "uuid", "type_id", "id");
                            try (PreparedStatement ps2 = connection.prepareStatement(sql3)) {
                                ps2.setString(1, playerData.getUuid().toString());
                                ps2.setInt(2, k);
                                ps2.setInt(3, v);
                                ps2.executeUpdate();
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            String sql2 = String.format("SELECT * FROM %s WHERE %s=? AND %s=? AND %s=?", "cosmetics_owned", "uuid", "type_id", "id");
            playerData.getOwnedCosmetics().keySet().forEach(k -> playerData.getOwnedCosmetics().get(k).forEach(v -> {
                block20:
                {
                    try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                        ps.setString(1, playerData.getUuid().toString());
                        ps.setInt(2, k);
                        ps.setInt(3, v);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) break block20;
                            String sql3 = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)", "cosmetics_owned", "uuid", "type_id", "id");
                            try (PreparedStatement ps2 = connection.prepareStatement(sql3)) {
                                ps2.setString(1, playerData.getUuid().toString());
                                ps2.setInt(2, k);
                                ps2.setInt(3, v);
                                ps2.executeUpdate();
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

