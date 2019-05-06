/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlparser;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Anvaqta Tangguh
 */
public class SQLParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Parser data = new Parser();
        String query = "SELECT * FROM Lagu JOIN Memutar using (idLagu);";
        Scanner input = new Scanner(System.in);
        Scanner x = new Scanner(System.in);
        Scanner y = new Scanner(System.in);
        
//        System.out.print("SQLParser>");
//        String query = input.nextLine();
        if(data.parse(query)){
            System.out.println("\nSuccess exit code 0");
        }else{
            System.out.println("SQL ERROR.");
        }
        
//        Runtime.getRuntime().exec("cls");
        int jawab = 0;
        int menu;
        do{
            System.out.println("1. MENU 1");
            System.out.println("2. MENU 2");
            System.out.println("3. MENU 3");
            System.out.println("4. MENU 4");
            System.out.println("5. MENU 5");
            System.out.println("0. EXIT");
            System.out.print("Pilih Menu : ");
            menu = x.nextInt();
            
            switch (menu) {
            case 1:
                System.out.println("========= Nomor 1 =========");
                nomor1 no1 = new nomor1();
                no1.menu1();
                System.out.println("");
                break;
            case 2:
                System.out.println("========= Nomor 2 =========");
                nomor2 no2 = new nomor2();
                no2.menu2();
                System.out.println("");
                break;
            case 3:
                System.out.println("========= Nomor 3 =========");
                nomor3 no3 = new nomor3();
                no3.menu3();
                System.out.println("");
                break;
            case 4:
                System.out.println("========= Nomor 4 =========");
                System.out.println("MENU 4 : QEP and Cost");
                System.out.println("");
                nomor4 no4 = new nomor4();
                no4.printQEP(data.primaryKey, query, data.joinExist);
                System.out.println("");
                break;
            case 5:
                System.out.println("========= Nomor 5 =========");
                nomor5 no5 = new nomor5(data.primaryKey, query, data.joinExist);
                break;
            }
        }while (menu != 0);
    }
    
}
