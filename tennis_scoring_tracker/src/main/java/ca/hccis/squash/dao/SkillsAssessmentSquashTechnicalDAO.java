package ca.hccis.squash.dao;

import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * DAO class to access db.
 *
 * @author bjmaclean
 * @since 20251009
 */
public class SkillsAssessmentSquashTechnicalDAO {

    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(SkillsAssessmentSquashTechnicalDAO.class);

    public SkillsAssessmentSquashTechnicalDAO() {

        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error(e.toString());
        }

    }

    /**
     * Select all
     *
     * @since 20210924
     * @author BJM
     */
    public ArrayList<SkillsAssessmentSquashTechnical> selectAll() {
        ArrayList<SkillsAssessmentSquashTechnical> assessments = null;
        Statement stmt = null;

        //******************************************************************
        //Use the DriverManager to get a connection to our MySql database.  Note
        //that in the dependencies, we added the Java connector to MySql which 
        //will allow us to connect to a MySql database.
        //******************************************************************
        //******************************************************************
        //Create a statement object using our connection to the database.  This 
        //statement object will allow us to run sql commands against the database.
        //******************************************************************
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from SkillsAssessmentSquashTechnical;");

            //******************************************************************
            //Loop through the result set using the next method.  
            //******************************************************************
            assessments = new ArrayList();

            while (rs.next()) {

                SkillsAssessmentSquashTechnical squash = new SkillsAssessmentSquashTechnical();
                squash.setId(rs.getInt("id"));
                squash.setAssessmentDate(rs.getString("assessmentDate"));
                squash.setCreatedDateTime(rs.getString("createdDateTime"));
                squash.setAthleteName(rs.getString("athleteName"));
                squash.setAssessorName(rs.getString("assessorName"));
                squash.setForehandDrives(rs.getInt("forehandDrives"));
                squash.setBackhandDrives(rs.getInt("backhandDrives"));
                squash.setForehandVolleyMax(rs.getInt("forehandVolleyMax"));
                squash.setForehandVolleySum(rs.getInt("forehandVolleySum"));
                squash.setBackhandVolleyMax(rs.getInt("backhandVolleyMax"));
                squash.setBackhandVolleySum(rs.getInt("backhandVolleySum"));

                assessments.add(squash);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return assessments;
    }

    /**
     * Select all by athlete/assessor name
     *
     * @since 20251009
     * @author BJM
     */
    public ArrayList<SkillsAssessmentSquashTechnical> selectAllByAthleteAssessorName(String athleteName) {
        ArrayList<SkillsAssessmentSquashTechnical> assessments = null;
        Statement stmt = null;

        //******************************************************************
        //Use the DriverManager to get a connection to our MySql database.  Note
        //that in the dependencies, we added the Java connector to MySql which
        //will allow us to connect to a MySql database.
        //******************************************************************
        //******************************************************************
        //Create a statement object using our connection to the database.  This
        //statement object will allow us to run sql commands against the database.
        //******************************************************************
        try {

            stmt = conn.createStatement();
            String sqlStatement = "select * from SkillsAssessmentSquashTechnical " +
                    "where athleteName = '"+athleteName+"' or assessorName = '"+athleteName+"';";
            rs = stmt.executeQuery(sqlStatement);

            //******************************************************************
            //Loop through the result set using the next method.
            //******************************************************************
            assessments = loadList(rs);

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return assessments;
    }

    /**
     * Select all by min/max
     *
     * @since 20251009
     * @author BJM
     */
    public ArrayList<SkillsAssessmentSquashTechnical> selectAllByScoreMinMax(int min, int max) {
        ArrayList<SkillsAssessmentSquashTechnical> assessments = null;
        Statement stmt = null;

        //******************************************************************
        //Use the DriverManager to get a connection to our MySql database.  Note
        //that in the dependencies, we added the Java connector to MySql which
        //will allow us to connect to a MySql database.
        //******************************************************************
        //******************************************************************
        //Create a statement object using our connection to the database.  This
        //statement object will allow us to run sql commands against the database.
        //******************************************************************
        try {

            stmt = conn.createStatement();
            String sqlStatement = "select * from SkillsAssessmentSquashTechnical " +
                    "where technicalScore >= "+min+" and technicalScore <= "+max;
            rs = stmt.executeQuery(sqlStatement);

            //******************************************************************
            //Loop through the result set using the next method.
            //******************************************************************
            assessments = loadList(rs);

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return assessments;
    }

    public ArrayList<SkillsAssessmentSquashTechnical> loadList(ResultSet rs) throws SQLException {
        ArrayList<SkillsAssessmentSquashTechnical> assessments = new  ArrayList();

        while (rs.next()) {

            SkillsAssessmentSquashTechnical squash = new SkillsAssessmentSquashTechnical();
            squash.setId(rs.getInt("id"));
            squash.setAssessmentDate(rs.getString("assessmentDate"));
            squash.setCreatedDateTime(rs.getString("createdDateTime"));
            squash.setAthleteName(rs.getString("athleteName"));
            squash.setAssessorName(rs.getString("assessorName"));
            squash.setForehandDrives(rs.getInt("forehandDrives"));
            squash.setBackhandDrives(rs.getInt("backhandDrives"));
            squash.setForehandVolleyMax(rs.getInt("forehandVolleyMax"));
            squash.setForehandVolleySum(rs.getInt("forehandVolleySum"));
            squash.setBackhandVolleyMax(rs.getInt("backhandVolleyMax"));
            squash.setBackhandVolleySum(rs.getInt("backhandVolleySum"));
            squash.setTechnicalScore(rs.getInt("technicalScore"));

            assessments.add(squash);
        }
        return assessments;
    }

//
//    /**
//     * Select all
//     *
//     * @since 20251118
//     * @author BJM
//     */
//    public ArrayList<SkillsAssessmentSquashTechnical> selectAll() {
//        ArrayList<SkillsAssessmentSquashTechnical> assessments = null;
//        Statement stmt = null;
//
//        //******************************************************************
//        //Use the DriverManager to get a connection to our MySql database.  Note
//        //that in the dependencies, we added the Java connector to MySql which
//        //will allow us to connect to a MySql database.
//        //******************************************************************
//        //******************************************************************
//        //Create a statement object using our connection to the database.  This
//        //statement object will allow us to run sql commands against the database.
//        //******************************************************************
//        try {
//
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("select * from SkillsAssessmentSquashTechnical;");
//
//            //******************************************************************
//            //Loop through the result set using the next method.
//            //******************************************************************
//            assessments = new ArrayList();
//
//            while (rs.next()) {
//
//                SkillsAssessmentSquashTechnical squash = new SkillsAssessmentSquashTechnical();
//                squash.setId(rs.getInt("id"));
//                squash.setAssessmentDate(rs.getString("assessmentDate"));
//                squash.setCreatedDateTime(rs.getString("createdDateTime"));
//                squash.setAthleteName(rs.getString("athleteName"));
//                squash.setAssessorName(rs.getString("assessorName"));
//                squash.setForehandDrives(rs.getInt("forehandDrives"));
//                squash.setBackhandDrives(rs.getInt("backhandDrives"));
//                squash.setForehandVolleyMax(rs.getInt("forehandVolleyMax"));
//                squash.setForehandVolleySum(rs.getInt("forehandVolleySum"));
//                squash.setBackhandVolleyMax(rs.getInt("backhandVolleyMax"));
//                squash.setBackhandVolleySum(rs.getInt("backhandVolleySum"));
//                squash.setTechnicalScore(rs.getInt("technicalScore"));
//
//                assessments.add(squash);
//            }
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//
//        } finally {
//
//            try {
//                stmt.close();
//            } catch (SQLException ex) {
//                System.out.println("There was an error closing");
//            }
//        }
//        return assessments;
//    }


}
