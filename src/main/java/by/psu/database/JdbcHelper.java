package by.psu.database;

import by.psu.model.Excursion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class JdbcHelper {

    public void create(Excursion excursion)
            throws SQLException {

        String sql = """
                INSERT INTO excursion(name, price)
                VALUES (?, ?)
                """;

        try (
                Connection connection =
                        ConnectionManager.open();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    excursion.getName()
            );

            statement.setDouble(
                    2,
                    excursion.getPrice().doubleValue()
            );

            statement.executeUpdate();
        }
    }

    public Excursion findById(int id)
            throws SQLException {

        String sql =
                "SELECT * FROM excursion WHERE id = ?";

        try (
                Connection connection =
                        ConnectionManager.open();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                Excursion excursion =
                        new Excursion();

                excursion.setId(
                        resultSet.getInt("id")
                );

                excursion.setName(
                        resultSet.getString("name")
                );

                excursion.setPrice(
                        BigDecimal.valueOf(
                                resultSet.getDouble("price")
                        )
                );

                return excursion;
            }
        }

        return null;
    }

    public List<Excursion> findAll()
            throws SQLException {

        String sql =
                "SELECT * FROM excursion";

        List<Excursion> excursions =
                new ArrayList<>();

        try (
                Connection connection =
                        ConnectionManager.open();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Excursion excursion =
                        new Excursion();

                excursion.setId(
                        resultSet.getInt("id")
                );

                excursion.setName(
                        resultSet.getString("name")
                );

                excursion.setPrice(
                        BigDecimal.valueOf(
                                resultSet.getDouble("price")
                        )
                );

                excursions.add(excursion);
            }
        }

        return excursions;
    }

    public void update(Excursion excursion)
            throws SQLException {

        String sql = """
                UPDATE excursion
                SET name = ?, price = ?
                WHERE id = ?
                """;

        try (
                Connection connection =
                        ConnectionManager.open();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    excursion.getName()
            );

            statement.setDouble(
                    2,
                    excursion.getPrice().doubleValue()
            );

            statement.setInt(
                    3,
                    excursion.getId()
            );

            statement.executeUpdate();
        }
    }

    public void delete(int id)
            throws SQLException {

        String sql =
                "DELETE FROM excursion WHERE id = ?";

        try (
                Connection connection =
                        ConnectionManager.open();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }
}