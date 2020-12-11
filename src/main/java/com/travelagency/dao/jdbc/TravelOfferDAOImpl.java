package com.travelagency.dao.jdbc;

import com.travelagency.dao.interfaces.TravelOfferDao;
import com.travelagency.entity.OfferType;
import com.travelagency.entity.TravelOffer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelOfferDAOImpl implements TravelOfferDao {

    @Resource(name = "travel_agency_db")
    private DataSource dataSource;

    public static final String readAllQuery = "select * from `travel_offer`;";

    public static final String deleteQuery = "delete from `travel_offer` where id = ?;";

    public static final String addQuery = "insert into `travel_offer` (name, day_price, offer_type) " +
                                            "values (?, ?, ?);";

    public final String getIdQuery = "select max(id) from `travel_offer`;";

    public static final String updateDataQuery = "update `travel_offer` " +
                                "set name = ?, day_price = ?, offer_type = ? " +
                                "where id = ?;";


    @Override
    public List<TravelOffer> findAll() {
        ArrayList<TravelOffer> travelOffers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(readAllQuery)) {
                try (ResultSet travelOffersData = statement.executeQuery()) {
                    while (travelOffersData.next()) {
                        travelOffers.add(
                                new TravelOffer(
                                        travelOffersData.getInt("id"),
                                        travelOffersData.getString("name"),
                                        travelOffersData.getDouble("day_price"),
                                        OfferType.getBy(travelOffersData.getString("offer_type"))
                                )
                        );
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            travelOffers = null;
        }
        return travelOffers;
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
    public int persist(TravelOffer travelOffer) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(addQuery)) {
                statement.setString(1, travelOffer.getName());
                statement.setDouble(2, travelOffer.getDayPrice());
                statement.setString(3, travelOffer.getOfferType().getTypeName());
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
    public void update(TravelOffer travelOffer) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(updateDataQuery)) {
                statement.setString(1, travelOffer.getName());
                statement.setDouble(2, travelOffer.getDayPrice());
                statement.setString(3, travelOffer.getOfferType().getTypeName());
                statement.setInt(4, travelOffer.getId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<TravelOffer> getByType(OfferType offerType) {
        throw new UnsupportedOperationException("This dao doesn't support the operation");
    }
}
