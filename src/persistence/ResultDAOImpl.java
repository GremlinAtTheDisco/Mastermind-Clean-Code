package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultDAOImpl implements ResultDAO {

    private Statement stmt;
    private ResultSet rs;
    private Connection connection;

    public ResultDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/moodb", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Database not available " + ex);
        }
    }

    @Override
    public List<Integer> getAllResults(int id) {
        List<Integer> results = new ArrayList<>();
        int result = 0;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select * from results where player = " + id);

            while (rs.next()) {
                result = rs.getInt("result");
                results.add(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;
    }

    @Override
    public void setResult(int nGuesses, int id) {
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO results "
                    + "(result, player) VALUES (" + nGuesses + ", " + id + ")");

        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
