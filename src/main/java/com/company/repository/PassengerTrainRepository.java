package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.PassengerTrain;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PassengerTrainRepository {
    // int id, String name, double speed, int numberOWaggons, double fuelCost, int numberOfSeats, int numberOfClasses

    private static PassengerTrainRepository instance;

    private PassengerTrainRepository(){}

    public static PassengerTrainRepository getInstance(){
        if(instance == null){
            instance = new PassengerTrainRepository();
        }
        return instance;
    }

    private PassengerTrain maptoObject(ResultSet resultSet){
        try {
            if (resultSet.next()) {
                // int id, String name, double speed, int numberOWaggons, double fuelCost, int numberOfSeats, int numberOfClasses

                return new PassengerTrain(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
                        resultSet.getInt(4),  resultSet.getDouble(5) ,
                        resultSet.getInt(6), resultSet.getInt(7)) ;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    private List<PassengerTrain> mapToList(ResultSet resultSet){
        List<PassengerTrain> list = new ArrayList<>();
        try {
            while(true){
                PassengerTrain PassengerTrain = maptoObject(resultSet);
                if (PassengerTrain == null)
                    return list;
                else list.add(PassengerTrain);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<PassengerTrain> getAll(){
        String selectSql = "SELECT * FROM passengerTrain";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSql);
            return mapToList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PassengerTrain getMaxId(){
        String selectSql = "SELECT * FROM passengerTrain ORDER BY id DESC LIMIT 0, 1";

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
        // int id, String name, double speed, int numberOWaggons, double fuelCost, int numberOfSeats, int numberOfClasses
        String createSql = "CREATE TABLE IF NOT EXISTS passengerTrain " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(50), " +
                "speed double, " +
                "numberOWaggons int, " +
                "fuelCost double," +
                "numberOfSeats int," +
                "numberOfClasses int" +
                ")";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPassengerTrain(PassengerTrain passengerTrain){
        String insertSql = "INSERT INTO passengerTrain(name, speed, numberOWaggons, fuelCost, numberOfSeats, numberOfClasses)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, passengerTrain.getName());
            preparedStatement.setDouble(2, passengerTrain.getSpeed());
            preparedStatement.setInt(3, passengerTrain.getNumberOWaggons());
            preparedStatement.setDouble(4, passengerTrain.getFuelCost());
            preparedStatement.setInt(5, passengerTrain.getNumberOfSeats());
            preparedStatement.setInt(6, passengerTrain.getNumberOfClasses());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PassengerTrain getPassengerTrainById(int id) {
        String selectSql = "SELECT * FROM passengerTrain WHERE id=?";

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

    public PassengerTrain getPassengerTrainByName(String name) {
        String selectSql = "SELECT * FROM passengerTrain WHERE name=?";

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

    public void updatePassengerTrainName(String name, int id){
        String updateSql = "UPDATE PassengerTrain SET name=? WHERE id=?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePassengerTrain(int id){
        String deleteSql = "DELETE FROM passengerTrain WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayPassengerTrains(){
        String selectSql = "SELECT * FROM passengerTrain";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                // PassengerTrain(name, speed, numberOWaggons, fuelCost, materials, quantity)
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Name:" + resultSet.getString(2));
                System.out.println("Speed:" + resultSet.getDouble(3));
                System.out.println("Number Of Waggons:" + resultSet.getInt(4));
                System.out.println("Fuel Cost:" + resultSet.getDouble(5));
                System.out.println("Number of Seats:" + resultSet.getInt(6));
                System.out.println("Number of Classes:" + resultSet.getInt(7));
                System.out.println("------------------------------------------------------------- ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayIdPassengerTrains(){
        String selectSql = "SELECT id FROM passengerTrain";

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
