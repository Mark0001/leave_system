package com.tku.leave.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbctest {
 public static Connection getConn() {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    String username = "hr";
    String pwd = "hr";
    Connection conn = null;
    try {
     Class.forName(driver);
     conn = DriverManager.getConnection(url, username, pwd);
    } catch (ClassNotFoundException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    } catch (SQLException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
    return conn;
   }
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    //一种是：System.out.println(Conn.getConn());
   //另一种是：
    try {
       System.out.println(Jdbctest.getConn().isClosed());
       System.out.println("如果成功打印false 失败就输出true");
       }catch (SQLException e) {
       e.printStackTrace();
       }
    }
    }