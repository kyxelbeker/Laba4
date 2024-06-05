package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainService {

    private final DBService dbService;

    public MainService() {
        this.dbService = new DBService();
    }

    public void addUser(String name, String password, String email) {
        String sql = "INSERT INTO accounts(name, password, email, birth_at) VALUES (?, ?, ?, CURRENT_DATE)";
        dbService.insert(sql,name, password, email);
    }

    public void updateUser(int id, String name, String password) {
        String sql = "UPDATE accounts SET name = ?, password = ? WHERE id = ?";
        dbService.update(sql, name, password, id);
    }


    public void deleteUser(int Id) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        dbService.delete(sql, Id);
    }

    public List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> users = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        ResultSet rs = dbService.select(sql);

        try {
            while (rs != null && rs.next()) {
                Map<String, Object> user = new HashMap<>();
                user.put("id", rs.getInt("id"));
                user.put("name", rs.getString("name"));
                user.put("password", rs.getString("password"));
                user.put("email", rs.getString("email"));
                user.put("birth_at", rs.getString("birth_at"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
