import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jdbc {
    Connection conn = null;
    public void insert() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jsonfile?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "4423125");

            String fileName = "2024subData.txt";
            String jsonString = readJsonFromFile(fileName);

            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                saveToDatabase(conn, jsonObject);
            }

            System.out.println("Data inserted successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String readJsonFromFile(String fileName) {
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonContent.toString();
    }

    private void saveToDatabase(Connection connection, JSONObject jsonObject) throws SQLException {
        String query = "INSERT INTO test (SBJ_NO, SBJ_NM, LECT_TIME_ROOM, CMP_DIV_RCD, THEO_TIME, ATTC_FILE_NO, DIVCLS, TLSN_RMK, CDT, SES_RCD, CMP_DIV_NM, CYBER_YN, CYBER_B_YN, SCH_YEAR, PRAC_TIME, CYBER_S_YN, FILE_PBY_YN, KIND_RCD, SBJ_DIVCLS, STAFF_NM, DEPT_CD, RMK, CYBER_E_YN, REP_STAFF_NO, EST_DEPT_INFO, SMT_RCD, CRS_SHYR, KIND_NM, BEF_CTNT_02, BEF_CTNT_01)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, jsonObject.getString("SBJ_NO"));
            preparedStatement.setString(2, jsonObject.getString("SBJ_NM"));
            preparedStatement.setString(3, jsonObject.optString("LECT_TIME_ROOM", null));
            preparedStatement.setString(4, jsonObject.optString("CMP_DIV_RCD", null));
            preparedStatement.setInt(5, jsonObject.optInt("THEO_TIME", 0));
            preparedStatement.setObject(6, jsonObject.isNull("ATTC_FILE_NO") ? null : jsonObject.getInt("ATTC_FILE_NO"));
            preparedStatement.setInt(7, jsonObject.optInt("DIVCLS", 0));
            preparedStatement.setObject(8, jsonObject.isNull("TLSN_RMK") ? null : jsonObject.getString("TLSN_RMK"));
            preparedStatement.setInt(9, jsonObject.optInt("CDT", 0));
            preparedStatement.setString(10, jsonObject.optString("SES_RCD", null));
            preparedStatement.setString(11, jsonObject.optString("CMP_DIV_NM", null));
            preparedStatement.setString(12, jsonObject.optString("CYBER_YN", null));
            preparedStatement.setString(13, jsonObject.optString("CYBER_B_YN", null));
            preparedStatement.setString(14, jsonObject.optString("SCH_YEAR", null));
            preparedStatement.setInt(15, jsonObject.optInt("PRAC_TIME", 0));
            preparedStatement.setString(16, jsonObject.optString("CYBER_S_YN", null));
            preparedStatement.setString(17, jsonObject.optString("FILE_PBY_YN", null));
            preparedStatement.setString(18, jsonObject.optString("KIND_RCD", null));
            preparedStatement.setString(19, jsonObject.optString("SBJ_DIVCLS", null));
            preparedStatement.setString(20, jsonObject.optString("STAFF_NM", null));
            preparedStatement.setString(21, jsonObject.optString("DEPT_CD", null));
            preparedStatement.setObject(22, jsonObject.isNull("RMK") ? null : jsonObject.getString("RMK"));
            preparedStatement.setString(23, jsonObject.optString("CYBER_E_YN", null));
            preparedStatement.setString(24, jsonObject.optString("REP_STAFF_NO", null));
            preparedStatement.setString(25, jsonObject.optString("EST_DEPT_INFO", null));
            preparedStatement.setString(26, jsonObject.optString("SMT_RCD", null));
            preparedStatement.setInt(27, jsonObject.optInt("CRS_SHYR", 0));
            preparedStatement.setString(28, jsonObject.optString("KIND_NM", null));
            preparedStatement.setObject(29, jsonObject.isNull("BEF_CTNT_02") ? null : jsonObject.getString("BEF_CTNT_02"));
            preparedStatement.setObject(30, jsonObject.isNull("BEF_CTNT_01") ? null : jsonObject.getString("BEF_CTNT_01"));

            preparedStatement.executeUpdate();
        }
    }
    public void createTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jsonfile?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "4423125");

            String query = "CREATE TABLE IF NOT EXISTS test ( " +
                    "SBJ_NO VARCHAR(50), " +
                    "SBJ_NM VARCHAR(255), " +
                    "LECT_TIME_ROOM VARCHAR(255), " +
                    "CMP_DIV_RCD VARCHAR(50), " +
                    "THEO_TIME INT, " +
                    "ATTC_FILE_NO VARCHAR(50), " +
                    "DIVCLS INT, " +
                    "TLSN_RMK VARCHAR(255), " +
                    "CDT INT, " +
                    "SES_RCD VARCHAR(50), " +
                    "CMP_DIV_NM VARCHAR(255), " +
                    "CYBER_YN VARCHAR(1), " +
                    "CYBER_B_YN VARCHAR(1), " +
                    "SCH_YEAR VARCHAR(4), " +
                    "PRAC_TIME INT, " +
                    "CYBER_S_YN VARCHAR(1), " +
                    "FILE_PBY_YN VARCHAR(1), " +
                    "KIND_RCD VARCHAR(50), " +
                    "SBJ_DIVCLS VARCHAR(50) PRIMARY KEY, " +
                    "STAFF_NM VARCHAR(255), " +
                    "DEPT_CD VARCHAR(50), " +
                    "RMK VARCHAR(255), " +
                    "CYBER_E_YN VARCHAR(1), " +
                    "REP_STAFF_NO VARCHAR(50), " +
                    "EST_DEPT_INFO VARCHAR(255), " +
                    "SMT_RCD VARCHAR(50), " +
                    "CRS_SHYR INT, " +
                    "KIND_NM VARCHAR(255), " +
                    "BEF_CTNT_02 VARCHAR(255), " +
                    "BEF_CTNT_01 VARCHAR(255) " +
                    ")";

            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.executeUpdate();
                System.out.println("Table created successfully.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void dropTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jsonfile?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "4423125");

            String query = "DROP TABLE IF EXISTS test";

            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.executeUpdate();
                System.out.println("Table dropped successfully.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
