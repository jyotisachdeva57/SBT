package calc;

import java.util.Scanner;

public class Calculator {
    public static void main(String args[]) {
        Calop c = new Calop();
        DBop d = new DBop();
        int ch;
        char x;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("YOU HAVE FOLLOWING OPERATIONS : ");
            System.out.println("1. CALCULATOR ");
            System.out.println("2. DATABASE ");
            System.out.println("ENTER YOUR CHOICE : ");
            ch = s.nextInt();
            switch (ch) {
                case 1:
                    c.performcal();
                    break;
                case 2:
                    d.performdb();
                    break;
                default:
                    System.out.println("please enter valid choice");
                    break;
            }
            System.out.print("do you want to exit?(y/n)");
            Scanner sc = new Scanner(System.in);
            x = sc.next().charAt(0);
        } while (x!='y');
    }
}
