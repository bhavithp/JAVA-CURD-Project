import java.nio.channels.spi.AbstractSelectionKey;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //1. Load and Register the Driver
        // name of the mysql driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.Establish the connection with the database
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket","root","707@Hnmk");
       //3. create the statement object - CAR
        Statement st = con.createStatement();
        boolean flag = true;
        while(flag) {
            System.out.println("Enter the choice: ");
            System.out.println("1. View the scoreboard");
            System.out.println("2. Insert the new record");
            System.out.println("3. Update the record");
            System.out.println("4. Delete the record");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            switch (choice){
                //View
                case 1:
                    viewTable(st);
                    break;
                    //INSERT
                case 2:
                    insertTable(st,sc);
                    break;
                    //UPDATE
                case 3:
                    updateTable(st,sc);
                    break;
                //DELETE
                case 4:
                    deleteTable(st,sc);
                    break;

                default:
                    flag= false;
                    break;

            }

        }

    }
    public static void viewTable(Statement st) throws Exception{
        String sql = "select * from scoretable;";
        ResultSet rs = st.executeQuery(sql);

        System.out.println("ID\t| Name\t| Runs\t| Balls\t|");
        while (rs.next()) {
            System.out.println(rs.getInt(1) +
                    "\t" + rs.getString(2) +
                    "\t" + rs.getInt(3) +
                    "\t" + rs.getInt(4));
        }

    }
    public static void insertTable(Statement st, Scanner sc) throws Exception {
        //Insert into score table values(1 "kohli",100,50):
        System.out.println("Enter the id: ");
        int id = sc.nextInt();
        System.out.println("Enter the name of the player ");
        String name = sc.next();
        System.out.println("Enter the runs scored: ");
        int runs= sc.nextInt();
        System.out.println("Enter in how many balls ????");
        int balls = sc.nextInt();

        String insertQuery = "INSERT into scoretable VALUE ("+id+",'"+name+"',"+runs+","+balls+");";

        int rows = st.executeUpdate(insertQuery);
        System.out.println(rows+"rows inserted");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

    }
    public static void updateTable(Statement st, Scanner sc) throws Exception {
        // Update SCORE TABLE set runs=140 , balls= 80 id=1
        System.out.println("Enter the id of the Player: ");
        int id= sc.nextInt();
        System.out.println("Enter new runs:");
        int runs = sc.nextInt();
        System.out.println("In how many balls ?");
        int balls = sc.nextInt();

        String updateQuery = "Update scoretable set runs= "+runs+", balls="+balls+" where id= "+id+";";

        int rows = st.executeUpdate(updateQuery);
        System.out.println(rows+"rows updated");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
    }
    public static void deleteTable(Statement st, Scanner sc) throws Exception {
        //Delete scoretable where id=1;
        System.out.println("Enter the id of the Player");
        int id=sc.nextInt();
        String deleteQuery = "DELETE from scoretable where id="+id+";";

        int rows = st.executeUpdate(deleteQuery);
        System.out.println(rows+"rows updated");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
    }
}
