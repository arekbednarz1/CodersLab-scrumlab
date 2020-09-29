package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admins;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminsDao {
    // Zapytania
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, email, password) VALUES (?,?,?,?)";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins where id=?";
    private static final String READ_ALL_ADMINS_QUERY = "SELECT * FROM admins";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name=?, last_name=?, email=?, password=? WHERE id=?";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id=?";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /*
    * Create new admin
     */

    public Admins createAdmin(Admins admins) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(CREATE_ADMIN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, admins.getFirstName());
            statement.setString(2, admins.getLastName());
            statement.setString(3, admins.getEmail());
            statement.setString(4, hashPassword(admins.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admins.setId(resultSet.getInt(1));
            }
            return admins;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
/*
* Read admin by id
 */
public Admins readAdmin(int adminId){
    Admins admins = new Admins();
    try(Connection conn = DbUtil.getConnection();
    PreparedStatement statement = conn.prepareStatement(READ_ADMIN_QUERY)){
        statement.setInt(1, adminId);
        try(ResultSet resultSet = statement.executeQuery()){
        while (resultSet.next()){
            admins.setId(resultSet.getInt("id"));
            admins.setFirstName(resultSet.getString("first_name"));
            admins.setLastName(resultSet.getString("last_name"));
            admins.setEmail(resultSet.getString("email"));
            admins.setPassword(resultSet.getString("password"));
        }
        return admins;
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}

public List<Admins> readAllAdmins(){
    List<Admins> adminsList = new ArrayList<>();
    try( Connection conn = DbUtil.getConnection();
    PreparedStatement statement = conn.prepareStatement(READ_ALL_ADMINS_QUERY);
    ResultSet resultSet = statement.executeQuery()){
        while (resultSet.next()){
            Admins admins = new Admins();
            admins.setId(resultSet.getInt("id"));
            admins.setFirstName(resultSet.getString("first_name"));
            admins.setLastName(resultSet.getString("last_name"));
            admins.setEmail(resultSet.getString("email"));
            admins.setPassword(resultSet.getString("password"));
            adminsList.add(admins);
        }

    }catch (Exception e){
        e.printStackTrace();
    }
    return adminsList;
}

/*
* Update admin data
 */
    public void updateAdminData(Admins admins){
        try(Connection conn = DbUtil.getConnection();
        PreparedStatement statement = conn.prepareStatement(UPDATE_ADMIN_QUERY)){
            statement.setInt(5,admins.getId());
            statement.setString(1, admins.getFirstName());
            statement.setString(2, admins.getLastName());
            statement.setString(3, admins.getEmail());
            statement.setString(4, this.hashPassword(admins.getPassword()));
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
/*
* Delete admin
 */
    public void deleteAdmin(int id){
try(Connection conn = DbUtil.getConnection();
PreparedStatement statement = conn.prepareStatement(DELETE_ADMIN_QUERY)){
statement.setInt(1, id);
statement.executeUpdate();

boolean deleted = statement.execute();
if(!deleted){
    throw new NotFoundException("Admin not found");
}
}catch (Exception e){
    e.printStackTrace();
}
    }

}
