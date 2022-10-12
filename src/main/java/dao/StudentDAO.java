package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/TestModule3";
    private static String jdbcStudentname = "root";
    private static String jdbcPassword = "handc1";

    private static final String INSERT_Student_SQL = "INSERT INTO Student(name, DateofBirth, Address,PhoneNumber,Email) VALUES (?, ?, ?,?,?);";
    private static final String SELECT_Student_BY_ID = "select id,name,DateofBirth,Address,PhoneNumber,Email from Student where id =?";
    private static final String SELECT_ALL_StudentS = "select * from Student";
    private static final String DELETE_StudentS_SQL = "delete from Student where id = ?;";
    private static final String UPDATE_StudentS_SQL = "update Student set name = ?,DateofBirth= ? , Address =? , PhoneNumber =? , Email where id = ?;";

    public StudentDAO() {
    }

    protected  Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcStudentname, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    private  void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    @Override
    public  void insertStudent(Student student) throws SQLException {
        System.out.println(INSERT_Student_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Student_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getDateofBirth());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getPhoneNumber());
            preparedStatement.setString(5, student.getEmail());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public  Student selectStudent(int id) {
        Student student = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Student_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String dateofBirth = rs.getString("dateofBirth");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                student = new Student(id, name, dateofBirth, address,phoneNumber,email);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }

    @Override
    public  List<Student> selectAllStudents() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Student> students = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_StudentS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dateofBirth = rs.getString("dateofBirth");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                students.add(new Student(id, name, dateofBirth, address,phoneNumber,email));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    @Override
    public  boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_StudentS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public  boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_StudentS_SQL);) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getDateofBirth());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getPhoneNumber());
            statement.setString(5, student.getEmail());
            statement.setInt(6, student.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public  Student getStudentById(int id) {
        Student student = null;

        String query = "{CALL get_Student_by_id(?)}";

        // Step 1: Establishing a Connection

        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object

             CallableStatement callableStatement = connection.prepareCall(query);) {

            callableStatement.setInt(1, id);

            // Step 3: Execute the query or update query

            ResultSet rs = callableStatement.executeQuery();

            // Step 4: Process the ResultSet object.

            while (rs.next()) {

                String name = rs.getString("name");
                String dateofBirth = rs.getString("dateofBirth");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");

                student = new Student(id, name, dateofBirth, address,phoneNumber,email);

            }

        } catch (SQLException e) {

            printSQLException(e);

        }

        return student;
    }

  


    @Override
    public  List<Student> selectStudentsByName(String name) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from Student where name like ?")) {
            preparedStatement.setString(1, "%"+name + "%");
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name1 = rs.getString("name");
                String dateofBirth = rs.getString("dateofBirth");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                students.add(new Student(id, name1, dateofBirth, address,phoneNumber,email));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    @Override
    public  List<Student> sortByName() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from Students order by name")) {

            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name1 = rs.getString("name");
                String dateofBirth = rs.getString("dateofBirth");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                students.add(new Student(id, name1, dateofBirth, address,phoneNumber,email));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }
}
