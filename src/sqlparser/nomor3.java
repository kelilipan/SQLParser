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
 * @author ASUS
 */
public class nomor3 {

    private int intLagu[] = new int[3];
    private int intPengguna[] =  new int[3];
    private int intMemutar[] = new int[3];
    private int intNomor3[] = new int[2];
    nomor1 no1;
    private int x;
    private String tabel;
    
    Scanner inputInt = new Scanner(System.in);
    Scanner inputString = new Scanner(System.in);
    
    public nomor3() throws IOException {
        no1 = new nomor1();
        no1.getNumber();
        intLagu = no1.getIntLagu();
        intPengguna = no1.getIntPengguna();
        intMemutar = no1.getIntMemutar();
        intNomor3 = no1.getIntNomor1();
        no1.getNumber();
    }
    
    public void menu3(){
        
        System.out.println("MENU 3 : Pencarian Record");
        System.out.println("");
        System.out.print("Cari record ke - ");
        x = inputInt.nextInt();
        System.out.print("Nama Tabel : ");
        tabel = inputString.nextLine();
        if (tabel.toUpperCase().equals("MEMUTAR")){
            System.out.println(x);
            System.out.println(no1.BFR(intNomor3[1],intMemutar[0]));
            System.out.println("[INDEX] Jumlah blok yang diakses : " + idxBlockData(x,no1.fanout(intNomor3[1], intMemutar[2], intNomor3[0])));
            System.out.println("[NO INDEX] Jumlah Blok yang diakses : " + blockData(x, no1.BFR(intNomor3[1],intMemutar[0])));
        }
        else if (tabel.toUpperCase().equals("LAGU")){
            System.out.println("[INDEX] Jumlah blok yang diakses : " + idxBlockData(x,no1.fanout(intNomor3[1], intLagu[2], intNomor3[0])));
            System.out.println("[NO INDEX] Jumlah Blok yang diakses : " + blockData(x, no1.BFR(intNomor3[1],intLagu[0])));
        }
        else if (tabel.toUpperCase().equals("PENGGUNA")){
            System.out.println("[INDEX] Jumlah blok yang diakses : " + idxBlockData(x,no1.fanout(intNomor3[1], intPengguna[2], intNomor3[0])));
            System.out.println("[NO INDEX] Jumlah Blok yang diakses : " + blockData(x, no1.BFR(intNomor3[1],intPengguna[0])));
        }
        else{
            System.out.println("ERROR : Tabel not found");
        }
    }
    
    public int blockData(int input, int bfr){
        return (int) Math.ceil(input/bfr);
    }
    
    public int idxBlockData(int input, int fanout){
        return (int) Math.ceil((input / fanout)+1);
    }
    
}
