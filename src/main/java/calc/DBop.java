package calc;

import java.sql.*;
import java.util.Scanner;

public class DBop {
    public void displaydb(int ch, String op) {
        String query;
        ResultSet rs;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/j", "root", "root");
            if (ch == 1) {
                stmt = con.createStatement();
                rs = stmt.executeQuery("select * from calculator");
            } else {
                PreparedStatement statement = con.prepareStatement("select * from calculator where operator = ?");
                statement.setString(1, op);
                rs = statement.executeQuery();
            }
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getTimestamp(2) + "  " + rs.getDouble(3) + " " + rs.getDouble(4) + " " + rs.getString(5) + " " + rs.getDouble(6));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void performdb() {
        Scanner s = new Scanner(System.in);
        int ch;
        String query = null;
        char x;
        do {
            System.out.println("1. SELECT ALL");
            System.out.println("2. SELECT SPECIFIC:");
            System.out.println("ENTER YOUR CHOICE :");
            ch = s.nextInt();
            if (ch != 1 || ch != 2) {
                System.out.println("Invalid choice:");
                System.out.print("do you want to exit?(y/n)");
                Scanner sc = new Scanner(System.in);
                x = sc.next().charAt(0);
                if (x == 'y') {
                    System.exit(0);
                }
            }
        }
        while ((ch != 1 && ch != 2));
        String opertr;

        if (ch == 1) {
            query = "select * from calculator";
            displaydb(1, null);
        } else if (ch == 2) {
            do {
                System.out.println("YOU HAVE FOLLOWING CHOICES : ");
                System.out.println("1. ADDITION");
                System.out.println("2. SUBTRACTION ");
                System.out.println("3. MULTIPLICATION ");
                System.out.println("4. DIVISION");
                System.out.println("5. POWER");
                System.out.println("6. ABSOLUTE");
                System.out.println("7. MODULUS");
                System.out.println("8. MAXIMUM");
                System.out.println("9. MINIMUM");
                System.out.println("ENTER YOUR CHOICE : ");
                ch = s.nextInt();
                switch (ch) {
                    case 1:
                        opertr = "add";
                        displaydb(2, opertr);
                        break;
                    case 2:
                        opertr = "subtract";
                        displaydb(2, opertr);
                        break;
                    case 3:
                        opertr = "multiply";
                        displaydb(2, opertr);
                        break;
                    case 4:
                        opertr = "divide";
                        displaydb(2, opertr);
                        break;
                    case 5:
                        opertr = "power";
                        displaydb(2, opertr);
                        break;
                    case 6:
                        opertr = "abs";
                        displaydb(2, opertr);
                        break;
                    case 7:
                        opertr = "modulus";
                        displaydb(2, opertr);
                        break;
                    case 8:
                        opertr = "max";
                        displaydb(2, opertr);
                        break;
                    case 9:
                        opertr = "min";
                        displaydb(2, opertr);
                        break;
                    default:
                        System.out.print("enter valid choice");
                        break;
                }

                System.out.print("do you want to exit?(y/n)");
                Scanner sc = new Scanner(System.in);
                x = sc.next().charAt(0);
            } while (x != 'y');
        }
    }
}
