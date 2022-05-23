package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Station;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StationRepository {
    private static StationRepository instance;

    private StationRepository(){}

    public static StationRepository getInstance(){
        if(instance == null){
            instance = new StationRepository();
        }
        return instance;
    }

    private Station maptoObject(ResultSet resultSet){
        try {
            if (resultSet.next()) {
                Date date = null;
                try{
                    date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(resultSet.getString(3));
                } catch (ParseException e){
                    e.printStackTrace();
                }
                return new Station(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), date);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    private List<Station> mapToList(ResultSet resultSet){
        List<Station> list = new ArrayList<>();
        try {
            while(true){
                Station station = maptoObject(resultSet);
                if (station == null)
                    return list;
                else list.add(station);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Station> getAll(){
        String selectSql = "SELECT * FROM station";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSql);
            return mapToList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Station getMaxId(){
        String selectSql = "SELECT * FROM station ORDER BY id DESC LIMIT 0, 1";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSql);
            if (resultSet.next())
                return maptoObject(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createTable() {
        // int id, String name, String city, String country, Date establishmentDate)
        String createSql = "CREATE TABLE IF NOT EXISTS station " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(50), " +
                "city varchar(50), " +
                "country varchar(50), " +
                "establishmentDate varchar(50) " +
                ")";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStation(Station station){
        String insertSql = "INSERT INTO station(name, city, country, establishmentDate) " + "VALUES (?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, station.getName());
            preparedStatement.setString(2, station.getCity());
            preparedStatement.setString(3, station.getCountry());
            Date date = (Date) station.getEstablishmentDate();
            String dateString = new SimpleDateFormat("dd/MM/yyyy").format(date);
            preparedStatement.setString(4, dateString);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Station getStationById(int id) {
        String selectSql = "SELECT * FROM station WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return maptoObject(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Station getStationByName(String name) {
        String selectSql = "SELECT * FROM station WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return maptoObject(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStationName(String name, int id){
        String updateSql = "UPDATE station SET name=? WHERE id=?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStation(int id){
        String deleteSql = "DELETE FROM station WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayStations(){
        String selectSql = "SELECT * FROM station";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Name:" + resultSet.getString(2));
                System.out.println("City:" + resultSet.getString(3));
                System.out.println("Country:" + resultSet.getString(4));
                System.out.println("Establishment Date:" + resultSet.getString(5));
                System.out.println("------------------------------------------------------------- ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayIdStations(){
        String selectSql = "SELECT id FROM station";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
