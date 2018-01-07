package calc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Calop {
    public void dboper(double a, double b, double res, String oper) {
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
        System.out.println("RESULT IS");
        System.out.println(res);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/j", "root", "root");
            PreparedStatement stmt = con.prepareStatement("insert into calculator(timestamp,left_operand,right_operand,operator,result) values (?,?,?,?,?);");
            stmt.setTimestamp(1, sqlTime);
            stmt.setDouble(2, a);
            stmt.setDouble(3, b);
            stmt.setString(4, oper);
            stmt.setDouble(5, res);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void performcal() {
        Scanner s = new Scanner(System.in);
        int ch;
        char x;
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
            if (!(ch >= 1 && ch <= 9)) {
                System.out.println("Invalid choice:");
                System.out.print("do you want to exit?(y/n)");
                Scanner sc = new Scanner(System.in);
                x = sc.next().charAt(0);
                if (ch == 'y')
                    System.exit(0);
            }
        } while (!(ch >= 1 && ch <= 9));
        double a;
        double b;
        double result;
        String op;
        if (ch == 6) {
            System.out.println("ENTER NUMBER ");
            a = s.nextDouble();
            result = Math.abs(a);
            op = "abs";
            dboper(a, 0, result, op);
        } else {
            System.out.println("ENTER FIRST NUMBER ");
            a = s.nextDouble();
            System.out.println("ENTER SECOND NUMBER ");
            b = s.nextDouble();
            switch (ch) {
                case 1:
                    op = "add";
                    result = a + b;
                    dboper(a, b, result, op);
                    break;
                case 2:
                    op = "subtract";
                    result = a - b;
                    dboper(a, b, result, op);
                    break;
                case 3:
                    op = "multiply";
                    result = a * b;
                    dboper(a, b, result, op);
                    break;
                case 4:
                    op = "divide";
                    if (b == 0) {
                        System.out.println("DIVISION NOT POSSIBLE");
                        break;
                    } else {
                        result = a / b;
                        dboper(a, b, result, op);
                    }
                    break;
                case 5:
                    op = "power";
                    if (b == 0)
                        result = 1;
                    else if (b < 0)
                        result = (1 / Math.pow(a, b));
                    else
                        result = Math.pow(a, b);
                    dboper(a, b, result, op);
                    break;
                case 7:
                    op = "modulus";
                    int i = (int) a;
                    int j = (int) b;
                    int k = i % j;
                    result = (double) k;
                    dboper(a, b, result, op);
                    break;
                case 8:
                    op = "max";
                    result = Math.max(a, b);
                    dboper(a, b, result, op);
                    break;
                case 9:
                    op = "min";
                    result = Math.min(a, b);
                    dboper(a, b, result, op);
                    break;
                default:
                    System.out.println("YOU HAVE ENTERED A WRONG CHOICE");
            }
        }
    }
}
