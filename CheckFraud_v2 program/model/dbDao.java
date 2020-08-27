package kz.ch.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class dbDao {

    //pemennaya dlya conn
    private static Connection conn;
    private static Connection conn2;

    //pemennaya dlya zaprosa
    private int status_count;
    private String number;
    private String name;
    private String subs;
    private String clnt_id;
    private String ct_name;
    private String account;
    private String status;
    private String trpl_name;
    private Double balance_$;
    private String activation_date;
    private String first_call;
    private Vector<Vector<String>> data;
    private Vector<String> columns2;
    private Vector<Vector<String>> data3;
    private Vector<String> columns3;
    private Vector<Vector<String>> data4;
    private Vector<String> columns4;
    private Vector<Vector<String>> data5;
    private Vector<String> columns5;
    private Vector<Vector<String>> data6;
    private Vector<String> columns6;
    private Vector<Vector<String>> data10;
    private Vector<String> columns10;
    public Vector<Vector<String>> data345;
    private Vector<String> columns345;
    private String call;
    private String call_d;
    public String[] data_from_buffer;


    //tip zaprosa

    private static final String SELECT_QUERY_CALL="SELECT 'CALL_'||TO_CHAR(TRUNC(SYSDATE),'MM_YYYY')AS CALL_SYSDAT FROM DUAL";

    private static final String SELECT_QUERY_CALL_D="SELECT 'CALL_D_'||TO_CHAR(TRUNC(SYSDATE),'MM_YYYY')AS CALL_SYSDAT FROM DUAL";

    private static final String SELECT_QUERY = "SELECT DISTINCT K.NAME, O.ACCOUNT, T.SUBS_ID, T.CLNT_ID, L.CT_NAME, Y.STATUS, TRIM(U.TRPL_NAME) AS TRPL_NAME, I.BALANCE_$, TO_CHAR(P.ACTIVATION_DATE,'DD.MM.YYYY HH24:MI:SS') AS ACTIVATION_DATE, TO_CHAR(P.FIRST_CALL,'DD.MM.YYYY HH24:MI:SS') AS FIRST_CALL FROM SUBS_HISTORY T, STATUS Y, " +
            "TARIFF_PLAN U, CLIENT_BALANCE I, CLIENT O, CLIENT_TYPE L, CONTRACT K, SUBSCRIBER P WHERE T.PHONE_ID IN (SELECT PHONE_ID FROM PHONE WHERE MSISDN =?) AND T.STAT_ID = Y.STAT_ID " +
            "AND T.TRPL_ID = U.TRPL_ID AND T.CLNT_ID = I.CLNT_ID AND I.BALANCE_ID IN (0, 164) AND T.CLNT_ID = O.CLNT_ID AND T.STIME <= SYSDATE AND T.ETIME > SYSDATE AND O.CT_ID = L.CT_ID " +
            "AND T.CLNT_ID = K.CLNT_ID AND K.STIME <= SYSDATE AND K.ETIME > SYSDATE AND T.SUBS_ID = P.SUBS_ID";


    private static final String SELECT_QUERY3 = "SELECT H.SERV_NAME, J.CHARGE_TYPE, TO_CHAR(F.CHARGE_DATE, 'dd.mm.yyyy hh24:mi:ss') AS CHARGE_DATE, F.SUMM_$ FROM CHARGE F, SUBS_HISTORY T, " +
            "SERVICE H, CHARGE_TYPE J WHERE T.PHONE_ID IN " +
            "(SELECT PHONE_ID FROM PHONE WHERE MSISDN =?) AND F.SUBS_ID = T.SUBS_ID AND F.SERV_ID = H.SERV_ID AND F.CHTYPE_ID = J.CHTYPE_ID AND T.STIME <= SYSDATE " +
            "AND T.ETIME > SYSDATE ORDER BY F.CHARGE_DATE DESC";

    private static final String SELECT_QUERY4 = "SELECT PT.PT_DEF, TO_CHAR(P.PAY_DATE, 'dd.mm.yyyy hh24:mi:ss') AS PAY_DATE, P.SUMM_$ FROM PAYMENT P, PAY_TYPE PT WHERE P.PT_ID = PT.PT_ID " +
            "AND PT.DEL_DATE IS NULL AND P.CLNT_ID IN" +
            "(SELECT U.CLNT_ID FROM SUBS_HISTORY U WHERE U.PHONE_ID IN(SELECT Y.PHONE_ID FROM PHONE Y WHERE Y.MSISDN =?)" +
            "AND U.STIME <= SYSDATE AND U.ETIME > SYSDATE) ORDER BY P.PAY_DATE DESC";

    private static final String SELECT_QUERY5 = "SELECT TT.COMT_NAME, TO_CHAR(T.CRE_DATE,'dd.mm.yyyy hh24:mi:ss') AS CRE_DATE, T.SUBJECT, T.TEXT FROM SUBS_COMMENT T, COMMENT_TYPE TT, SUBS_HISTORY H WHERE H.PHONE_ID IN" +
            "(SELECT PHONE_ID FROM PHONE WHERE MSISDN =?) AND T.SUBS_ID = H.SUBS_ID AND T.COMT_ID = TT.COMT_ID AND H.STIME <= SYSDATE AND H.ETIME > SYSDATE ORDER BY T.CRE_DATE DESC";

    private static final String SELECT_QUERY6 = "DELETE FROM MB_NUMBERS";

    private static final String SELECT_QUERY7 = "INSERT /*+APPEND*/ INTO MB_NUMBERS VALUES (?)";

    private static final String SELECT_QUERY8 = "SELECT COUNT(*) FROM MB_NUMBERS";

    static {
        String url = null;
        String username = null;
        String password = null;

        String url2 = null;
        String username2 = null;
        String password2 = null;

        //load db properties
        try (InputStream in = dbDao.class
                .getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            assert in != null;
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            url2 = properties.getProperty("url2");
            username2 = properties.getProperty("username2");
            password2 = properties.getProperty("password2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //acquire db connection
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url , username, password);
            conn2 = DriverManager.getConnection(url2 , username2, password2);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ClearNumber() throws SQLException{
        PreparedStatement pstmt77 = conn2.prepareStatement(SELECT_QUERY6);
        try{pstmt77.executeUpdate();}
        catch (Exception e) {
            e.printStackTrace();
        }
        pstmt77.close();
    }

    //obrabotka txt faila i zapis v buffer & clear table & load table
    public void addNumbers() throws SQLException {

        PreparedStatement pstmt66 = conn2.prepareStatement(SELECT_QUERY7);
        for (String s : data_from_buffer) {
            pstmt66.setString(1, s);
            pstmt66.executeUpdate();
        }
        pstmt66.isClosed();
    }

    public void GetCount() throws SQLException {

        PreparedStatement stmt99 = conn.prepareStatement(SELECT_QUERY8);
        ResultSet rs99 = stmt99.executeQuery(SELECT_QUERY8);
        while (rs99.next()) {
            status_count = rs99.getInt(1);
        }
        stmt99.close();
        rs99.close();
    }

    public String getStatus_count() {
        return String.valueOf(status_count);
    }

    // vivod agg dannih
    public void Agg() throws SQLException {
        Statement stmt345=conn.createStatement();
        ResultSet rs345 = stmt345.executeQuery ("SELECT SUBSTR(CALLING, -10) AS MSISDN, CASE WHEN SERV_ID = 1 AND CALT_ID = 110 " +
                "AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('705', '777', '771', '776') THEN 'Voice на BEELINE' WHEN SERV_ID = 1 " +
                "AND CALT_ID = 110 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('701', '702', '775', '778') THEN 'Voice на KCELL' " +
                "WHEN SERV_ID = 1 AND CALT_ID = 121 THEN 'Voice на ALTEL' WHEN SERV_ID = 1 AND CALT_ID = 123 THEN 'Voice на TELE2' " +
                "WHEN SERV_ID = 1 AND CALT_ID = 2 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('707', '747') THEN 'Voice на TELE2' " +
                "WHEN SERV_ID = 1 AND CALT_ID = 2 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('700', '708') THEN 'Voice на ALTEL' " +
                "WHEN SERV_ID IN (2, 1008) AND CALT_ID = 110 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('705', '777', '771', '776') THEN 'SMS на BEELINE' " +
                "WHEN SERV_ID IN (2, 1008) AND CALT_ID = 110 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('701', '702', '775', '778') THEN 'SMS на KCELL' " +
                "WHEN SERV_ID IN (2, 1008) AND CALT_ID = 121 THEN 'SMS на ALTEL' WHEN SERV_ID IN (2, 1008) AND CALT_ID = 123 THEN 'SMS на TELE2' " +
                "WHEN SERV_ID IN (2, 1008) AND CALT_ID = 2 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('707', '747') THEN 'SMS на TELE2' " +
                "WHEN SERV_ID IN (2, 1008) AND CALT_ID = 2 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('700', '708') THEN 'SMS на ALTEL' ELSE CALT_NAME END AS direction, " +
                "CASE WHEN CALT_NAME NOT IN ('GPRS') THEN COUNT(CALLED) ELSE 0 END AS count_all_calls, " +
                "CASE WHEN CALT_NAME NOT IN ('GPRS') THEN COUNT(DISTINCT SUBSTR(CALLED, 2, 10)) ELSE 0 END AS count_uniq_calls, " +
                "SUM(CASE WHEN CALT_NAME IN ('GPRS') THEN TRAFF_MB ELSE 0 END) AS TRAFF_MB FROM (SELECT Y.CALT_ID, T.PHONE AS CALLING, T.DIALED AS CALLED, " +
                "CASE WHEN Y.CALT_NAME IN ('GPRS Internet','GPRS Internet(free)') THEN 'GPRS' ELSE Y.CALT_NAME END AS CALT_NAME, T.SERV_ID, " +
                "ROUND(CASE WHEN Y.CALT_NAME IN ('GPRS Internet','GPRS Internet(free)') THEN T.PACKET_IN END,0) AS TRAFF_MB FROM CALL_02_2020 T, CALL_TYPE Y " +
                "WHERE T.CALT_ID = Y.CALT_ID AND T.SUBS_ID IN (SELECT /*+PARALLEL(8)*/ U.SUBS_ID FROM SUBS_HISTORY U WHERE U.PHONE_ID IN (SELECT Y.PHONE_ID FROM PHONE Y " +
                "WHERE Y.MSISDN IN (SELECT DISTINCT MSISDN FROM MB_NUMBERS)) AND U.STIME <= SYSDATE AND U.ETIME > SYSDATE)) GROUP BY SUBSTR(CALLING, -10), CASE WHEN SERV_ID = 1 " +
                "AND CALT_ID = 110 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('705', '777', '771', '776') THEN 'Voice на BEELINE' WHEN SERV_ID = 1 AND CALT_ID = 110 " +
                "AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('701', '702', '775', '778') THEN 'Voice на KCELL' WHEN SERV_ID = 1 AND CALT_ID = 121 THEN 'Voice на ALTEL' " +
                "WHEN SERV_ID = 1 AND CALT_ID = 123 THEN 'Voice на TELE2' WHEN SERV_ID = 1 AND CALT_ID = 2 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('707', '747') THEN 'Voice на TELE2' " +
                "WHEN SERV_ID = 1 AND CALT_ID = 2 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('700', '708') THEN 'Voice на ALTEL' WHEN SERV_ID IN (2, 1008) AND CALT_ID = 110 " +
                "AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('705', '777', '771', '776') THEN 'SMS на BEELINE' WHEN SERV_ID IN (2, 1008) AND CALT_ID = 110 " +
                "AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('701', '702', '775', '778') THEN 'SMS на KCELL' WHEN SERV_ID IN (2, 1008) AND CALT_ID = 121 THEN 'SMS на ALTEL' " +
                "WHEN SERV_ID IN (2, 1008) AND CALT_ID = 123 THEN 'SMS на TELE2' WHEN SERV_ID IN (2, 1008) AND CALT_ID = 2 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('707', '747') THEN 'SMS на TELE2' " +
                "WHEN SERV_ID IN (2, 1008) AND CALT_ID = 2 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('700', '708') THEN 'SMS на ALTEL' ELSE CALT_NAME END, CALT_NAME ORDER BY SUBSTR(CALLING, -10)");
        ResultSetMetaData md345 = rs345.getMetaData();
        int columnCount345 = md345.getColumnCount();
        columns345 = new Vector<>(columnCount345);
        for (int i = 1; i <= columnCount345; i++)
            columns345.add(md345.getColumnName(i));
        data345 = new Vector<>();
        Vector<String> row345;
        while (rs345.next()) {
            row345 = new Vector<>(columnCount345);
            for (int i = 1; i <= columnCount345; i++) {
                row345.add(rs345.getString(i));
            }
            data345.add(row345);
        }
        rs345.close();
        stmt345.close();
    }


    //metod obrabotki zaprosa
    public void SetNumber(String number) throws SQLException {
        this.number=number;
        PreparedStatement stmt = conn.prepareStatement(SELECT_QUERY);
        stmt.setString(1, number);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            name = rs.getString(1);
            account = rs.getString(2);
            subs = rs.getString(3);
            clnt_id = rs.getString(4);
            ct_name = rs.getString(5);
            status = rs.getString(6);
            trpl_name = rs.getString(7);
            balance_$ = rs.getDouble(8);
            activation_date = rs.getString(9);
            first_call = rs.getString(10);
        }
        rs.close();
        stmt.close();
    }

    public void Payment() throws SQLException {

        PreparedStatement stmt4 = conn.prepareStatement(SELECT_QUERY4);
        stmt4.setString(1, number);
        ResultSet rs4 = stmt4.executeQuery();
        ResultSetMetaData md4 = rs4.getMetaData();
        int columnCount4 = md4.getColumnCount();
        columns4 = new Vector<String>(columnCount4);
        for (int i = 1; i <= columnCount4; i++)
            columns4.add(md4.getColumnName(i));
        data4 = new Vector<>();
        Vector<String> row4;
        while (rs4.next()) {
            row4 = new Vector<>(columnCount4);
            for (int i = 1; i <= columnCount4; i++) {
                row4.add(rs4.getString(i));
            }
            data4.add(row4);
        }
        rs4.close();
        stmt4.close();
    }



    public void Charges() throws SQLException {

        PreparedStatement stmt3 = conn.prepareStatement(SELECT_QUERY3);
        stmt3.setString(1, number);
        ResultSet rs3 = stmt3.executeQuery();
        ResultSetMetaData md3 = rs3.getMetaData();
        int columnCount3 = md3.getColumnCount();
        columns3 = new Vector<String>(columnCount3);
        for (int i = 1; i <= columnCount3; i++)
            columns3.add(md3.getColumnName(i));
        data3 = new Vector<>();
        Vector<String> row3;
        while (rs3.next()) {
            row3 = new Vector<>(columnCount3);
            for (int i = 1; i <= columnCount3; i++) {
                row3.add(rs3.getString(i));
            }
            data3.add(row3);
        }
        rs3.close();
        stmt3.close();
    }



    public void Detail() throws SQLException {

        PreparedStatement stmt2 = conn.prepareStatement("SELECT /*+PARALLEL(16)*/ SERVICE,CALT_NAME,CALLED,DATE_ID,DURATION,TRAFFIC,PRICE,LAC_A,CELL_A FROM (SELECT R.SERV_NAME AS SERVICE,T.PHONE AS CALLING," +
                "T.DIALED AS CALLED,Y.CALT_NAME,to_char(T.START_TIME, 'dd.mm.yyyy hh24:mi:ss') AS DATE_ID,T.DURATION , REGEXP_REPLACE(REGEXP_REPLACE(T.PACKET_IN+T.PACKET_OUT, '\\D', '.'),'^\\D', '0.') AS TRAFFIC, " +
                "REGEXP_REPLACE(REGEXP_REPLACE(PRICE_$, '\\D','.'),'^\\D','0.') AS PRICE,CASE WHEN T.SERV_ID=1 THEN TT.LAC_A END AS LAC_A," +
                "CASE WHEN T.SERV_ID=1 THEN TT.CELL_A END AS CELL_A FROM "+call+" T, "+call_d+" TT, SERVICE R,CALL_TYPE Y WHERE T.CALL_ID=TT.CALL_ID " +
                "AND T.SERV_ID=R.SERV_ID AND T.CALT_ID=Y.CALT_ID AND T.SUBS_ID IN(SELECT U.SUBS_ID FROM SUBS_HISTORY U WHERE U.PHONE_ID IN(SELECT Y.PHONE_ID FROM PHONE Y WHERE Y.MSISDN =?)" +
                "AND U.STIME <= SYSDATE AND U.ETIME > SYSDATE)) ORDER BY DATE_ID DESC");
        stmt2.setString(1, number);
        ResultSet rs2 = stmt2.executeQuery();
        ResultSetMetaData md2 = rs2.getMetaData();
        int columnCount2 = md2.getColumnCount();
        columns2 = new Vector<String>(columnCount2);
        for(int i=1; i<=columnCount2; i++)
            columns2.add(md2.getColumnName(i));
        data = new Vector<>();
        Vector<String> row;
        while (rs2.next()) {row = new Vector<>(columnCount2);
            for(int i=1; i<=columnCount2; i++)
            {
                row.add(rs2.getString(i));
            }
            data.add(row);
        }
        rs2.close();
        stmt2.close();
    }

    public void Detail_Lac_Cell() throws SQLException {

        PreparedStatement stmt10 = conn.prepareStatement("SELECT LAC_A,CELL_A,COUNT(DISTINCT CALLED) AS COUNT FROM (SELECT R.SERV_NAME AS SERVICE,T.PHONE AS CALLING,T.DIALED AS CALLED,Y.CALT_NAME," +
                "to_char(T.START_TIME, 'dd.mm.yyyy hh24:mi:ss') AS DATE_ID,T.DURATION,REGEXP_REPLACE(REGEXP_REPLACE(T.PACKET_IN + T.PACKET_OUT,'\\D','.'),'^\\D','0.') AS TRAFFIC," +
                "REGEXP_REPLACE(REGEXP_REPLACE(PRICE_$, '\\D', '.'),'^\\D','0.') AS PRICE,CASE WHEN T.SERV_ID = 1 THEN TT.LAC_A END AS LAC_A," +
                "CASE WHEN T.SERV_ID = 1 THEN TT.CELL_A END AS CELL_A FROM "+call+" T, "+call_d+" TT, SERVICE R, CALL_TYPE Y " +
                "WHERE T.CALL_ID = TT.CALL_ID AND T.SERV_ID = R.SERV_ID AND T.CALT_ID = Y.CALT_ID AND T.SUBS_ID IN (SELECT U.SUBS_ID FROM SUBS_HISTORY U WHERE U.PHONE_ID IN " +
                "(SELECT Y.PHONE_ID FROM PHONE Y WHERE Y.MSISDN =?) AND U.STIME <= SYSDATE AND U.ETIME > SYSDATE)) WHERE (CELL_A IS NOT NULL OR LAC_A IS NOT NULL) GROUP BY LAC_A, CELL_A");
        stmt10.setString(1, number);
        ResultSet rs10 = stmt10.executeQuery();
        ResultSetMetaData md10 = rs10.getMetaData();
        int columnCount10 = md10.getColumnCount();
        columns10 = new Vector<String>(columnCount10);
        for(int i=1; i<=columnCount10; i++)
            columns10.add(md10.getColumnName(i));
        data10 = new Vector<>();
        Vector<String> row;
        while (rs10.next()) {row = new Vector<>(columnCount10);
            for(int i=1; i<=columnCount10; i++)
            {
                row.add(rs10.getString(i));
            }
            data10.add(row);
        }
        rs10.close();
        stmt10.close();
    }

    public void Aggr() throws SQLException {

        PreparedStatement stmt8 = conn.prepareStatement("SELECT CASE  WHEN SERV_ID = 1 AND CALT_ID = 110 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN  ('705', '777', '771', '776') THEN 'Voice на BEELINE' WHEN SERV_ID = 1 AND CALT_ID = 110 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN" +
                "                 ('701', '702', '775', '778') THEN  'Voice на KCELL'  WHEN SERV_ID = 1 AND CALT_ID = 121 THEN 'Voice на ALTEL'  WHEN SERV_ID = 1 AND CALT_ID = 123 THEN 'Voice на TELE2'" +
                "             WHEN SERV_ID = 1 AND CALT_ID = 2 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('707', '747') THEN 'Voice на TELE2'  WHEN SERV_ID = 1 AND CALT_ID = 2 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN" +
                "                 ('700', '708') THEN 'Voice на ALTEL'   WHEN SERV_ID  IN (2,1008) AND CALT_ID = 110 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('705', '777', '771', '776') THEN 'SMS на BEELINE'" +
                "             WHEN SERV_ID IN (2,1008) AND CALT_ID = 110 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN  ('701', '702', '775', '778') THEN  'SMS на KCELL'  WHEN SERV_ID IN (2,1008) AND CALT_ID = 121 THEN 'SMS на ALTEL'" +
                "             WHEN SERV_ID IN (2,1008) AND CALT_ID = 123 THEN 'SMS на TELE2'  WHEN SERV_ID IN (2,1008) AND CALT_ID = 2 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN  ('707', '747') THEN 'SMS на TELE2'" +
                "             WHEN SERV_ID IN (2,1008) AND CALT_ID = 2 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('700', '708') THEN 'SMS на ALTEL'  ELSE CALT_NAME END AS \"Direction\", COUNT(CALLED) AS \"Кол-во зв.\", COUNT(DISTINCT SUBSTR(CALLED, 2, 10))  AS \"Кол-во уник. зв.\" FROM (SELECT Y.CALT_ID,T.PHONE AS CALLING," +
                "            T.DIALED AS CALLED,Y.CALT_NAME,T.SERV_ID FROM "+call+" T,CALL_TYPE Y WHERE T.SERV_ID IN (1,2,1008)   AND T.CALT_ID=Y.CALT_ID AND T.SUBS_ID IN(SELECT U.SUBS_ID FROM SUBS_HISTORY U WHERE U.PHONE_ID IN(SELECT Y.PHONE_ID FROM PHONE Y WHERE Y.MSISDN =?)" +
                "            AND U.STIME <= SYSDATE AND U.ETIME > SYSDATE)) GROUP BY  CASE  WHEN SERV_ID = 1 AND CALT_ID = 110 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN  ('705', '777', '771', '776') THEN 'Voice на BEELINE'" +
                "             WHEN SERV_ID = 1 AND CALT_ID = 110 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN ('701', '702', '775', '778') THEN  'Voice на KCELL'  WHEN SERV_ID = 1 AND CALT_ID = 121 THEN 'Voice на ALTEL'" +
                "             WHEN SERV_ID = 1 AND CALT_ID = 123 THEN 'Voice на TELE2' WHEN SERV_ID = 1 AND CALT_ID = 2 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN  ('707', '747') THEN 'Voice на TELE2'" +
                "             WHEN SERV_ID = 1 AND CALT_ID = 2 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN  ('700', '708') THEN 'Voice на ALTEL'   WHEN SERV_ID  IN (2,1008) AND CALT_ID = 110 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN" +
                "                 ('705', '777', '771', '776') THEN 'SMS на BEELINE' WHEN SERV_ID IN (2,1008) AND CALT_ID = 110 AND SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN    ('701', '702', '775', '778') THEN  'SMS на KCELL'" +
                "             WHEN SERV_ID IN (2,1008) AND CALT_ID = 121 THEN 'SMS на ALTEL'  WHEN SERV_ID IN (2,1008) AND CALT_ID = 123 THEN 'SMS на TELE2'   WHEN SERV_ID IN (2,1008) AND CALT_ID = 2 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN" +
                "                 ('707', '747') THEN 'SMS на TELE2'   WHEN SERV_ID IN (2,1008) AND CALT_ID = 2 AND  SUBSTR(SUBSTR(CALLED, -10), 1, 3) IN   ('700', '708') THEN 'SMS на ALTEL'  ELSE CALT_NAME END");
        stmt8.setString(1, number);
        ResultSet rs8 = stmt8.executeQuery();
        ResultSetMetaData md8 = rs8.getMetaData();
        int columnCount8 = md8.getColumnCount();
        columns5 = new Vector<String>(columnCount8);
        for(int i=1; i<=columnCount8; i++)
            columns5.add(md8.getColumnName(i));
        data5 = new Vector<>();
        Vector<String> row8;
        while (rs8.next()) {row8 = new Vector<>(columnCount8);
            for(int i=1; i<=columnCount8; i++)
            {
                row8.add(rs8.getString(i));
            }
            data5.add(row8);
        }
        rs8.close();
        stmt8.close();
    }

    public void Block() throws SQLException {
        PreparedStatement stmt9 = conn.prepareStatement(SELECT_QUERY5);
        stmt9.setString(1, number);
        ResultSet rs9 = stmt9.executeQuery();
        ResultSetMetaData md9 = rs9.getMetaData();
        int columnCount9 = md9.getColumnCount();
        columns6 = new Vector<String>(columnCount9);
        for (int i = 1; i <= columnCount9; i++)
            columns6.add(md9.getColumnName(i));
        data6 = new Vector<>();
        Vector<String> row9;
        while (rs9.next()) {
            row9 = new Vector<>(columnCount9);
            for (int i = 1; i <= columnCount9; i++) {
                row9.add(rs9.getString(i));
            }
            data6.add(row9);
        }
        stmt9.close();
        rs9.close();
    }

    public void Call(String s4) throws SQLException {

//        PreparedStatement stmt6 = conn.prepareStatement(SELECT_QUERY_CALL);
//        ResultSet rs6 = stmt6.executeQuery();
//        while (rs6.next()) {
//            call = rs6.getString(1);
//        }
//        stmt6.close();
//        rs6.close();
        this.call="call_"+s4;
    }

    public void CallD(String s4) throws SQLException {

//        PreparedStatement stmt7 = conn.prepareStatement(SELECT_QUERY_CALL_D);
//        ResultSet rs7 = stmt7.executeQuery();
//        while (rs7.next()) {
//            call_d = rs7.getString(1);
//        }
//        stmt7.close();
//        rs7.close();
        this.call_d="call_d_"+s4;
    }


    public String getName() {
        return name;
    }

    //vozvrashaemie zna4eniya getSubs
    public String GetSubs() {
        return subs;
    }

    public String getAccount() {
        return account;
    }

    public String getStatus() {
        return status;
    }

    public String getTrpl_name() {
        return trpl_name;
    }

    public Double getBalance_$() {
        return balance_$;
    }

    public String getActivation_date() { return activation_date; }

    public String getFirst_call() { return first_call; }

    public String getClnt_id() {
        return clnt_id;
    }

    public String getCt_name() {
        return ct_name;
    }

    public Vector<Vector<String>> getData() {
        return  data;
    }

    public Vector<Vector<String>> getData3() {
        return  data3;
    }

    public Vector<Vector<String>> getData4() {
        return  data4;
    }

    public Vector<Vector<String>> getData5() {
        return  data5;
    }

    public Vector<Vector<String>> getData6() {
        return  data6;
    }

    public Vector<Vector<String>> getData10() {return data10; }

    public Vector<Vector<String>> getData345() {return data345; }

}

