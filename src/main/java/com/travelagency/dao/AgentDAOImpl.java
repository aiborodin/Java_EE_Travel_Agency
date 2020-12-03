package com.travelagency.dao;

import com.travelagency.dao.interfaces.AgentDao;
import com.travelagency.entity.Agent;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentDAOImpl implements AgentDao {

    @Resource(name = "travel_agency_db")
    private DataSource dataSource;

    public static final String readAllQuery = "select * from `agent`;";

    public static final String deleteQuery = "delete from `agent` where id = ?;";

    public static final String addQuery = "insert into `agent` (f_name, l_name, active, login, pwd) " +
                                            "values (?, ?, ?, ?, ?);";

    public final String getIdQuery = "select max(id) from `agent`;";

    public static final String updateDataQuery = "update `agent` " +
                                                "set f_name = ?, l_name = ?, active = ?, login = ?, pwd = ? " +
                                                "where id = ?;";

    @Override
    public List<Agent> readAll() {
        ArrayList<Agent> agents = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(readAllQuery)) {
                try (ResultSet agentData = statement.executeQuery()) {
                    while (agentData.next()) {
                        agents.add(
                                new Agent(
                                        agentData.getString("login"),
                                        agentData.getString("pwd"),
                                        agentData.getInt("id"),
                                        agentData.getString("f_name"),
                                        agentData.getString("l_name"),
                                        agentData.getBoolean("active")
                                )
                        );
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            agents = null;
        }
        return agents;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public int add(Agent agent) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(addQuery)) {
                    statement.setString(1, agent.getFirstName());
                    statement.setString(2, agent.getLastName());
                    statement.setBoolean(3, agent.isActive());
                    statement.setString(4, agent.getLogin());
                    statement.setString(5, agent.getPassword());
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
    public void update(Agent agent) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(updateDataQuery)) {
                statement.setString(1, agent.getFirstName());
                statement.setString(2, agent.getLastName());
                statement.setBoolean(3, agent.isActive());
                statement.setString(4, agent.getLogin());
                statement.setString(5, agent.getPassword());
                statement.setInt(6, agent.getId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
