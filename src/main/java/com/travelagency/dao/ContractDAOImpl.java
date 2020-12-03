package com.travelagency.dao;

import com.travelagency.dao.interfaces.ContractDAO;
import com.travelagency.entity.Client;
import com.travelagency.entity.Contract;
import com.travelagency.service.interfaces.AgentService;
import com.travelagency.service.interfaces.ClientService;
import com.travelagency.service.interfaces.TravelOfferService;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDAOImpl implements ContractDAO {

    public static final String readAllQuery = "select * from `contract`;";
    public static final String deleteQuery = "delete from `contract` where id = ?;";
    public static final String addQuery = "insert into `contract` (client_id, agent_id, travel_offer_id, travel_days, total_transp_costs, total_visa_costs) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    public static final String updateDataQuery = "update `contract` " +
            "set agent_id = ?, client_id = ?, travel_offer_id = ?, travel_days = ?, total_transp_costs = ?, total_visa_costs = ? " +
            "where id = ?;";
    public final String getIdQuery = "select max(id) from `contract`;";
    @Inject
    ClientService clientService;
    @Inject
    AgentService agentService;
    @Inject
    TravelOfferService travelOfferService;
    @Resource(name = "travel_agency_db")
    private DataSource dataSource;

    @Override
    public List<Contract> readAll() {
        ArrayList<Contract> contracts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(readAllQuery)) {
                try (ResultSet contractsData = statement.executeQuery()) {
                    while (contractsData.next()) {
                        contracts.add(
                                new Contract(
                                        contractsData.getInt("id"),
                                        clientService.find(contractsData.getInt("client_id")).orElse(null),
                                        agentService.find(contractsData.getInt("agent_id")).orElse(null),
                                        travelOfferService.find(contractsData.getInt("travel_offer_id")).orElse(null),
                                        contractsData.getInt("travel_days"),
                                        contractsData.getDouble("total_transp_costs"),
                                        contractsData.getDouble("total_visa_costs")
                                )
                        );
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            contracts = null;
        }
        return contracts;
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
    public int add(Contract contract) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(addQuery)) {
                statement.setInt(1, contract.getClient().getId());
                statement.setInt(2, contract.getAgent().getId());
                statement.setInt(3, contract.getTravelOffer().getId());
                statement.setInt(4, contract.getTravelDays());
                statement.setDouble(5, contract.getTransportationCosts());
                statement.setDouble(6, contract.getTotalVisaCosts());
                statement.executeUpdate();
            }
            try (Statement st = connection.createStatement()) {
                try (ResultSet maxId = st.executeQuery(getIdQuery)) {
                    maxId.next();
                    return maxId.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void update(Contract contract) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(updateDataQuery)) {
                statement.setInt(1, contract.getClient().getId());
                statement.setInt(2, contract.getAgent().getId());
                statement.setInt(3, contract.getTravelOffer().getId());
                statement.setInt(4, contract.getTravelDays());
                statement.setDouble(5, contract.getTransportationCosts());
                statement.setDouble(6, contract.getTotalVisaCosts());
                statement.setInt(7, contract.getId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
