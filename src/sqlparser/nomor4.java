/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlparser;

import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class nomor4 {
    private int intLagu[] = new int[3];
    private int intPengguna[] =  new int[3];
    private int intMemutar[] = new int[3];
    private int intNomor3[] = new int[2];
    nomor1 no1;
    nomor2 no2;

    public nomor4() throws IOException {
        no1 = new nomor1();
        no1.getNumber();
        intLagu = no1.getIntLagu();
        intPengguna = no1.getIntPengguna();
        intMemutar = no1.getIntMemutar();
        intNomor3 = no1.getIntNomor1();
        no1.getNumber();
        no2 = new nomor2();
    }
    
    public void printQEP(boolean pk, String tabel){
        if (pk){
            System.out.println("=== A1");
            System.out.println("A1");
            System.out.println("PROJECTION");
            System.out.println("SELECTION");
            System.out.println();//Nama Tabel
            int cost = 0;
            if (tabel.toUpperCase().equals("MEMUTAR")){
                cost = no2.blockData(no1.BFR(intNomor3[1],intMemutar[0]), intMemutar[1]) / 2;
            }
            else if (tabel.toUpperCase().equals("LAGU")){
                cost = no2.blockData(no1.BFR(intNomor3[1],intLagu[0]), intLagu[1]) / 2;
            }else if (tabel.toUpperCase().equals("PENGGUNA")){
                cost = no2.blockData(no1.BFR(intNomor3[1],intPengguna[0]), intPengguna[1]) / 2;
            }
            else{
                System.out.println("Undefined Table Name");
            }
            System.out.println("COST : " + cost);
            System.out.println("=== A2");
            System.out.println("A1");
            System.out.println("PROJECTION");
            System.out.println("SELECTION");
            System.out.println();//Nama Tabel
            cost = 0;
            if (tabel.toUpperCase().equals("MEMUTAR")){
                cost = 1 + (int) (Math.log(no2.blockData(no1.BFR(intNomor3[1],intMemutar[0]), intMemutar[1])) / Math.log(no1.fanout(intNomor3[1], intMemutar[2], intNomor3[0])));
            }
            else if (tabel.toUpperCase().equals("LAGU")){
                cost = 1 + (int) (Math.log(no2.blockData(no1.BFR(intNomor3[1],intLagu[0]), intLagu[1])) / Math.log(no1.fanout(intNomor3[1], intLagu[2], intNomor3[0])));
            }else if (tabel.toUpperCase().equals("PENGGUNA")){
                cost = 1 + (int) (Math.log(no2.blockData(no1.BFR(intNomor3[1],intPengguna[0]), intPengguna[1])) / Math.log(no1.fanout(intNomor3[1], intPengguna[2], intNomor3[0])));
            }
            else{
                System.out.println("Undefined Table Name");
            }
            System.out.println("COST : " + cost);
        }
        else{
            System.out.println("=== A1");
            System.out.println("A1");
            System.out.println("PROJECTION");
            System.out.println("SELECTION");
            System.out.println();//Nama Tabel
            int cost = 0;
            if (tabel.toUpperCase().equals("MEMUTAR")){
                cost = no2.blockData(no1.BFR(intNomor3[1],intMemutar[0]), intMemutar[1]);
            }
            else if (tabel.toUpperCase().equals("LAGU")){
                cost = no2.blockData(no1.BFR(intNomor3[1],intLagu[0]), intLagu[1]);
            }else if (tabel.toUpperCase().equals("PENGGUNA")){
                cost = no2.blockData(no1.BFR(intNomor3[1],intPengguna[0]), intPengguna[1]);
            }
            else{
                System.out.println("Undefined Table Name");
            }
            System.out.println("COST : " + cost);          
        }
    }
    
    
}
