package com.example.projetjavafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDB {
    Connection cnx;

    public ServiceDB() {
        cnx = ConnectionDB.getInstance().getCnx();
    }

    public String login(String email, String motDePasse){
        try {
            String query = "SELECT * FROM `user` WHERE email= '" + email + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(!rs.next()){
                return "false";
            }else{
                if(rs.getString("password").equals(motDePasse)){
                    if(rs.getString("role").equals("ADMIN")){
                        return "admin";
                    }else{
                        return "user";
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "false";
    }
    public void addUser(User user){
        try {
        String query = "INSERT INTO `user`(`email`,`name`,`age`,`password`,`role`) VALUES ('"+user.getEmail() + "','" + user.getName() + "','"+user.getAge() + "','" + user.getPassword() + "','USER')";
        Statement st = cnx.createStatement();
        st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createDoctor(Doctor doctor) {
        try {
            String query = "INSERT INTO doctors (name, location, phone, price) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getLocation());
            statement.setInt(3, doctor.getPhone());
            statement.setInt(4, doctor.getPrice());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating doctor: " + e.getMessage());
        }
    }

    public Doctor getDoctorById(int id) {
        try {
            String query = "SELECT * FROM doctors WHERE id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setLocation(rs.getString("location"));
                doctor.setPhone(rs.getInt("phone"));
                doctor.setPrice(rs.getInt("price"));
                return doctor;
            } else {
                return null; // No doctor found with the given ID
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting doctor by ID: " + e.getMessage());
        }
    }

    public boolean updateDoctor(Doctor doctor) {
        try {
            String query = "UPDATE doctors SET name = ?, location = ?, phone = ?, price = ? WHERE id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getLocation());
            statement.setInt(3, doctor.getPhone());
            statement.setInt(4, doctor.getPrice());
            statement.setInt(5, doctor.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating doctor: " + e.getMessage());
        }
    }

    public boolean deleteDoctor(int id) {
        try {
            String query = "DELETE FROM doctors WHERE id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting doctor: " + e.getMessage());
        }
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            String query = "SELECT * FROM doctors";
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setLocation(rs.getString("location"));
                doctor.setPhone(rs.getInt("phone"));
                doctor.setPrice(rs.getInt("price"));
                doctors.add(doctor);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all doctors: " + e.getMessage());
        }
        return doctors;
    }

}
