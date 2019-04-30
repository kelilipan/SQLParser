/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class nomor1 {
    //LAGU
    public List<String> tableLagu = new ArrayList<String>();
    //PENGGUNA
    public List<String> tablePengguna = new ArrayList();
    //MEMUTAR
    public List<String> tableMemutar = new ArrayList();
    //B dan P
    public List<String> tabelNomor1 = new ArrayList();
    
    public String[] tempLagu, tempPengguna, tempMemutar, tempNomor1;
    public int intLagu[] = new int[3];
    public int intPengguna[] =  new int[3];
    public int intMemutar[] = new int[3];
    public int intNomor1[] = new int[2];
    
    public nomor1()throws FileNotFoundException, IOException {
        BufferedReader BR = new BufferedReader(new FileReader("lagu.txt"));
        String line;
        BufferedReader BR2 = new BufferedReader(new FileReader("pengguna.txt"));
        String line2;
        BufferedReader BR3 = new BufferedReader(new FileReader("memutar.txt"));
        String line3;
        BufferedReader BR4 = new BufferedReader(new FileReader("PdanB.txt"));
        String number1;
        
//        Load Data
        while (((line = BR.readLine()) != null) && ((line2 = BR2.readLine()) != null) && ((line3 = BR3.readLine()) != null) && ((number1 = BR4.readLine()) != null)) {
            tableLagu.add(line);
            tablePengguna.add(line2);
            tableMemutar.add(line3);
            tabelNomor1.add(number1);
        }
        tempLagu = tableLagu.get(0).split(";");
        tempPengguna = tablePengguna.get(0).split(";");
        tempMemutar = tableMemutar.get(0).split(";");
        tempNomor1 = tabelNomor1.get(0).split(";");
    }
    
    public void getNumber(){
//        Pengguna
        int p = 3, l = 5, m = 2;
        for (int i = 0; p < tempPengguna.length; i++) {
            intPengguna[i] = Integer.parseInt(tempPengguna[p]);
//            System.out.println("PENGGUNA " + intPengguna[i]);
            p++;
        }
//        Lagu
        for (int i = 0; l < tempLagu.length; i++) {
            intLagu[i] = Integer.parseInt(tempLagu[l]);
//            System.out.println("LAGU "+intLagu[i]);
            l++;
        }
//        Memutar
        for (int i = 0; m < tempMemutar.length; i++) {
            intMemutar[i] = Integer.parseInt(tempMemutar[m]);
//            System.out.println("MEMUTAR "+intMemutar[i]);
            m++;
        }
//        PdanB
        for (int i = 0; i < tempNomor1.length; i++) {
            intNomor1[i] = Integer.parseInt(tempNomor1[i]);
//            System.out.println("Nomor1" + intNomor1[i]);
        }
    }
    
    public void menu1(){
        getNumber();
        
        System.out.println("MENU 1 : BFR dan Fanout Ratio");
        System.out.println("");
        System.out.println("  BFR Memutar : "+BFR(intNomor1[1],intMemutar[0]));
        System.out.println("  Fanout Ratio Memutar : "+fanout(intNomor1[1], intMemutar[2], intNomor1[0]));
//        System.out.println("");
        System.out.println("  BFR Pengguna : "+BFR(intNomor1[1],intPengguna[0]));
        System.out.println("  Fanout Ratio Pengguna : "+fanout(intNomor1[1], intPengguna[2], intNomor1[0]));
//        System.out.println("");
        System.out.println("  BFR Lagu : "+BFR(intNomor1[1],intLagu[0]));
        System.out.println("  Fanout Ratio Lagu : "+fanout(intNomor1[1], intLagu[2], intNomor1[0]));
    }
    
    public int BFR(int B, int R){
        return (B/R);
    }
    
    public int fanout(int B, int V, int P){
        return (B/ (V + P));
    }
  
    
//    Get number each tabel

    public int[] getIntLagu() {
        return intLagu;
    }

    public int[] getIntPengguna() {
        return intPengguna;
    }

    public int[] getIntMemutar() {
        return intMemutar;
    }

    public int[] getIntNomor1() {
        return intNomor1;
    }
    
//    List Table
    public List<String> getTableLagu() {
        return tableLagu;
    }

    public List<String> getTablePengguna() {
        return tablePengguna;
    }

    public List<String> getTableMemutar() {
        return tableMemutar;
    }

    public List<String> getTabelNomor1() {
        return tabelNomor1;
    }

//get table column 
    public String[] getTempLagu() {
        return tempLagu;
    }

    public String[] getTempPengguna() {
        return tempPengguna;
    }

    public String[] getTempMemutar() {
        return tempMemutar;
    }

    public String[] getTempNomor1() {
        return tempNomor1;
    }
    
    
}
