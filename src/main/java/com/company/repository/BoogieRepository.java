package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Boogie;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

public class BoogieRepository {
    private static BoogieRepository instance;

    private BoogieRepository(){}

    public static BoogieRepository getInstance(){
        if(instance == null){
            instance = new BoogieRepository();
        }
        return instance;
    }

    private Boogie maptoObject(ResultSet resultSet){
        try {
            if (resultSet.next()) {
                // int id, String name, double speed, int numberOWaggons, double fuelCost, ArrayList<String> materials, ArrayList<Double> quantity
                String str = resultSet.getString(6);
                int nr = 0;
                for(int i = 0;i < str.length(); ++i){
                    if(str.charAt(i) == '#')
                        ++nr;
                }
                String[] materials = str.split("#", nr);
                ArrayList<String> mats = new ArrayList<>(Arrays.asList(materials));

                String str1 = resultSet.getString(6);
                int nr1 = 0;
                for(int i = 0;i < str1.length(); ++i){
                    if(str1.charAt(i) == '#')
                        ++nr1;
                }
                String[] quantities = str1.split("#", nr1);
                ArrayList<Double> quants = new ArrayList<Double>();
                for(int i = 0; i < mats.size(); ++i){
                    quants.add(Double.parseDouble(quantities[i]));
                }

                return new Boogie(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
                        resultSet.getInt(4),  resultSet.getDouble(5) , mats, quants) ;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    private List<Boogie> mapToList(ResultSet resultSet){
        List<Boogie> list = new ArrayList<>();
        try {
            while(true){
                Boogie boogie = maptoObject(resultSet);
                if (boogie == null)
                    return list;
                else list.add(boogie);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Boogie> getAll(){
        String selectSql = "SELECT * FROM boogie";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSql);
            return mapToList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boogie getMaxId(){
        String selectSql = "SELECT * FROM boogie ORDER BY id DESC LIMIT 0, 1";

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
        // int id, String name, double speed, int numberOWaggons, double fuelCost, ArrayList<String> materials, ArrayList<Double> quantity
        String createSql = "CREATE TABLE IF NOT EXISTS boogie " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(50), " +
                "speed double, " +
                "numberOWaggons int, " +
                "fuelCost double," +
                "materials varchar(250)," +
                "quantity varchar(250)" +
                ")";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBoogie(Boogie boogie){
        String insertSql = "INSERT INTO boogie(name, speed, numberOWaggons, fuelCost, materials, quantity)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, boogie.getName());
            preparedStatement.setDouble(2, boogie.getSpeed());
            preparedStatement.setInt(3, boogie.getNumberOWaggons());
            preparedStatement.setDouble(4, boogie.getFuelCost());

            String mats = "";
            if(boogie.getMaterials().size() > 0){
                List<String> material = boogie.getMaterials();
                for(int i = 0; i < material.size() - 1; ++i){
                    mats += material.get(i) + "#";
                }
                mats += material.get(material.size() - 1);
            }

            String quants = "";
            if(boogie.getQuantity().size() > 0){
                List<Double> qua = boogie.getQuantity();
                for(int i = 0; i < qua.size() - 1; ++i){
                    quants += qua.get(i) + "#";
                }
                quants += qua.get(qua.size() - 1);
            }

            preparedStatement.setString(5, mats);
            preparedStatement.setString(6, quants);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boogie getBoogieById(int id) {
        String selectSql = "SELECT * FROM boogie WHERE id=?";

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

    public Boogie getBoogieByName(String name) {
        String selectSql = "SELECT * FROM boogie WHERE name=?";

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

    public void updateBoogieName(String name, int id){
        String updateSql = "UPDATE boogie SET name=? WHERE id=?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBoogie(int id){
        String deleteSql = "DELETE FROM boogie WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayBoogies(){
        String selectSql = "SELECT * FROM boogie";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                // boogie(name, speed, numberOWaggons, fuelCost, materials, quantity)
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Name:" + resultSet.getString(2));
                System.out.println("Speed:" + resultSet.getDouble(3));
                System.out.println("Number Of Waggons:" + resultSet.getInt(4));
                System.out.println("Fuel Cost:" + resultSet.getDouble(5));
                System.out.println("------------------------------------------------------------- ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayIdBoogies(){
        String selectSql = "SELECT id FROM boogie";

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
