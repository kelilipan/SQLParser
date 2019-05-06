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
public class nomor2 {
    private int intLagu[] = new int[3];
    private int intPengguna[] =  new int[3];
    private int intMemutar[] = new int[3];
    private int intNomor2[] = new int[2];
    nomor1 no1;
    
    public nomor2() throws IOException {
        
        no1 = new nomor1();
        no1.getNumber();
        intLagu = no1.getIntLagu();
        intPengguna = no1.getIntPengguna();
        intMemutar = no1.getIntMemutar();
        intNomor2 = no1.getIntNomor1();
    }
    
    public int blockData(int BFR, int n){
        return (n/BFR);
    }
    
    public int indeksData(int fanout, int n){
        return (n/fanout);
    }
    
    public void menu2(){
        System.out.println("MENU 2 : Jumlah Block");
        System.out.println("");
        System.out.println("    Tabel Data Lagu : " + blockData(no1.BFR(intNomor2[1],intLagu[0]), intLagu[1]));
        System.out.println("    Indeks Data Lagu : " + indeksData(no1.fanout(intNomor2[1], intLagu[2], intNomor2[0]), intLagu[1]));
        System.out.println("    Tabel Data Pengguna : " + blockData(no1.BFR(intNomor2[1],intPengguna[0]), intPengguna[1]));
        System.out.println("    Indeks Data Pengguna : " + indeksData(no1.fanout(intNomor2[1], intPengguna[2], intNomor2[0]), intPengguna[1]));
        System.out.println("    Tabel Data Memutar : " + blockData(no1.BFR(intNomor2[1],intMemutar[0]), intMemutar[1]));
        System.out.println("    Indeks Data Memutar : " + indeksData(no1.fanout(intNomor2[1], intMemutar[2], intNomor2[0]), intMemutar[1]));
    }
}
