import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    public void printStates() {

        try{

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalab", "root", "mysqlroot1234");
            Statement statement = conn.createStatement();


            ResultSet results = statement.executeQuery("select distinct state from airports");
            while (results.next()) {
                System.out.println(results.getString("state"));
            }
            System.out.println("");
            statement.close();

        } catch (Exception e) {
            System.out.println("State not found");
            e.printStackTrace();
        }

    }

    public void printStateCities(String state) {
        try{

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalab", "root", "mysqlroot1234");
            Statement statement = conn.createStatement();

            String query = "select distinct city from airports where state =" + "'" + state + "'" ;
            ResultSet results = statement.executeQuery(query);
            System.out.println("Cidades: ");
            while (results.next()) {
                System.out.println(results.getString("city"));
            }
            System.out.println("");
            statement.close();

        } catch (Exception e) {
            System.out.println("City not found");
            e.printStackTrace();
        }
    }

    public void printCityAirports(String city, String state) {
        try{

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalab", "root", "mysqlroot1234");
            Statement statement = conn.createStatement();

            String query = "select * from airports where city =" + "'" + city + "' and state =" + "'" + state + "'";
            ResultSet results = statement.executeQuery(query);
            System.out.println("Aerportos: ");
            while (results.next()) {
                System.out.println(results.getString("abreviation"));
            }
            System.out.println("");
            statement.close();

        } catch (Exception e) {
            System.out.println("Airports not found");
            e.printStackTrace();
        }
    }

    public Double getLongitude(String airport) {
        try{

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalab", "root", "mysqlroot1234");
            Statement statement = conn.createStatement();

            String query = "select (longitude) from airports where abreviation = " + "'" + airport + "'";
            ResultSet results = statement.executeQuery(query);
            results.next();
            double lon = results.getDouble("longitude");
            Double longitude = Double.valueOf(lon);
            statement.close();
            return longitude;

        } catch (Exception e) {
            e.printStackTrace();
            return Double.valueOf(10000);
        }
    }

    public Double getLatitude(String airport) {
        try{

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalab", "root", "mysqlroot1234");
            Statement statement = conn.createStatement();

            String query = "select * from airports where abreviation = " + "'" + airport + "'";
            ResultSet results = statement.executeQuery(query);
            results.next();
            double lat = results.getDouble("latitude");
            Double latitude = Double.valueOf(lat);
            statement.close();
            return latitude;

        } catch (Exception e) {
            e.printStackTrace();
            return Double.valueOf(10000);
        }
    }

    public List<String> getAirports() {
        try{
            List<String> airports = new ArrayList<String>();

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalab", "root", "mysqlroot1234");
            Statement statement = conn.createStatement();

            String query = "select * from airports";
            ResultSet results = statement.executeQuery(query);
            while(results.next()) {
                airports.add(results.getString("abreviation"));
            }
            statement.close();
            return airports;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void registerQuery(String origin, String destiny) {
        try{

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalab", "root", "mysqlroot1234");
            Statement statement = conn.createStatement();

            String query = "INSERT INTO queries (origin,destiny,date) VALUES ('" + origin + "','" + destiny + "', now())";
            statement.execute(query);
            System.out.println("Consulta cadastrada!");
            System.out.println("");
            statement.close();

        } catch (Exception e) {
            System.out.println("Cadastro falhou!");
            e.printStackTrace();
        }
    }
}
