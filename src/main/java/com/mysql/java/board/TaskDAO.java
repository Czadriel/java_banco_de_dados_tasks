package com.mysql.java.board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/board_tasks?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Santos022.";

    private Connection connection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insert( Task task){
        String sql = "INSERT INTO tasks(title, description, status) VALUES (?, ?, ?)";

        try (Connection conn = connection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Task> getAllTalks(){
        String sql = "SELECT *  FROM tasks";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = connection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()){
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("created_at")
                );
                tasks.add(task);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return tasks;
    }
    public void updateStatus(int id, String status){
        String sql = "UPDATE tasks SET status = ? WHERE id = ?";

        try(Connection conn = connection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(int id){
        String sql ="DELETE FROM tasks WHERE id = ?";

        try(Connection conn = connection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
