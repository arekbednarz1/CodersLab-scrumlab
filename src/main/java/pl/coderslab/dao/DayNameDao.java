package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {
    //Zapytania SQL
    private static final String READ_ALL_DAYS_QUERY = "SELECT * FROM day_name";

    public List<DayName> readAllDays(){
    List<DayName> dayNamesList = new ArrayList<>();
    try(Connection conn = DbUtil.getConnection();
        PreparedStatement statement = conn.prepareStatement(READ_ALL_DAYS_QUERY);
        ResultSet resultSet = statement.executeQuery()){
        while (resultSet.next()){
            DayName dayName = new DayName();
            dayName.setId(resultSet.getInt("id"));
            dayName.setName(resultSet.getString("name"));
            dayName.setDisplay_order(resultSet.getInt("display_order"));
            dayNamesList.add(dayName);
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
    return dayNamesList;
    }
}
