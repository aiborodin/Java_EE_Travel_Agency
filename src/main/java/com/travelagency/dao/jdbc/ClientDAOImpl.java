package com.travelagency.dao.jdbc;

import com.travelagency.dao.interfaces.ClientDao;
import com.travelagency.entity.Client;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDao {

    @Resource(name = "travel_agency_db")
    private DataSource dataSource;

    public static final String readAllQuery = "select * from `client`;";

    public static final String deleteQuery = "delete from `client` where id = ?;";

    public static final String addQuery = "insert into `client` (first_name, last_name, phone, email, age, cust_from, login, pwd) " +
                                            "values (?, ?, ?, ?, ?, ?, ?, ?);";

    public final String getIdQuery = "select max(id) from `client`;";

    public static final String updateDataQuery = "update `client` " +
                                                "set first_name = ?, last_name = ?, phone = ?, email = ?, age = ?, cust_from = ?, login = ?, pwd = ? " +
                                                "where id = ?;";

    @Override
    public List<Client> findAll() {
        ArrayList<Client> clients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(readAllQuery)) {
                try (ResultSet clientsData = statement.executeQuery()) {
                    while (clientsData.next()) {
                        clients.add(
                                new Client(
                                        clientsData.getString("login"),
                                        clientsData.getString("pwd"),
                                        clientsData.getInt("id"),
                                        clientsData.getString("first_name"),
                                        clientsData.getString("last_name"),
                                        clientsData.getInt("age"),
                                        clientsData.getString("phone"),
                                        clientsData.getString("email"),
                                        clientsData.getDate("cust_from").toLocalDate()
                                )
                        );
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            clients = null;
        }
        return clients;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public int persist(Client client) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(addQuery)) {
                    statement.setString(1, client.getFirstName());
                    statement.setString(2, client.getLastName());
                    statement.setString(3, client.getPhone());
                    statement.setString(4, client.getEmail());
                    statement.setInt(5, client.getAge());
                    statement.setDate(6, Date.valueOf(client.getCustomerFrom()));
                    statement.setString(7, client.getLogin());
                    statement.setString(8, client.getPassword());
                    statement.executeUpdate();
                }
                try (Statement st = connection.createStatement()) {
                    try (ResultSet maxId = st.executeQuery(getIdQuery)) {
                        maxId.next();
                        return maxId.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void update(Client client) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(updateDataQuery)) {
                statement.setString(1, client.getFirstName());
                statement.setString(2, client.getLastName());
                statement.setString(3, client.getPhone());
                statement.setString(4, client.getEmail());
                statement.setInt(5, client.getAge());
                statement.setDate(6, Date.valueOf(client.getCustomerFrom()));
                statement.setString(7, client.getLogin());
                statement.setString(8, client.getPassword());
                statement.setInt(9, client.getId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
