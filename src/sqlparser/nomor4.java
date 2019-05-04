/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlparser;

import java.io.IOException;
import java.nio.file.Files;
import static java.sql.JDBCType.NULL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class nomor4 {
    private int intLagu[] = new int[3];
    private int intPengguna[] =  new int[3];
    private int intMemutar[] = new int[3];
    private int intNomor4[] = new int[2];
    nomor1 no1;
    nomor2 no2;
    private String[] tempLagu, tempPengguna, tempMemutar;
    private List col = new ArrayList();
    String[] tempTabel;
    int fromPointer, joinPointer;

    public nomor4() throws IOException {
        no1 = new nomor1();
        no1.getNumber();
        intLagu = no1.getIntLagu();
        intPengguna = no1.getIntPengguna();
        intMemutar = no1.getIntMemutar();
        intNomor4 = no1.getIntNomor1();
        no2 = new nomor2();
        tempMemutar = no1.getTempMemutar();
        tempLagu = no1.getTempLagu();
        tempPengguna = no1.getTempPengguna();
    }
    
    public List getColoumn(String statement){
        String[] tempCol = statement.split(" ");
//        System.out.println(tempCol);
        int i = 0;
        
        if (tempCol[1].equals("*")){
            if (tempCol[3].toUpperCase().equals("LAGU")){
                while (i < 5){
                    col.add(tempLagu[i]);
                    i++;
                }
            }
            else if (tempCol[3].toUpperCase().equals("PENGGUNA")){
                while (i < 3){
                    col.add(tempPengguna[i]);
                    i++;
                }   
            }
            else if (tempCol[3].toUpperCase().equals("MEMUTAR")){
                while (i < 2){
                    col.add(tempMemutar[i]);
                    i++;
                }                
            }
            fromPointer = 2;
        }
        else{
            i = 1;
            while ( !tempCol[i].toUpperCase().equals("FROM")){
                i++;
            }
            
            fromPointer = i;
//            System.out.println(fromPointer);
            for (int j = 1; j < fromPointer; j++) {
                col.add(tempCol[j].replace(",", "").toUpperCase());
//                System.out.println(tempCol[j] + j + "[---------");
            }
        }
        return col;
    }
    
    public String printJoin(String statement){
        String tabel[] = statement.split(" ");
        int i = 0;
        while(!tabel[i].toUpperCase().equals("JOIN")){
            i++;
        }
        return tabel[i+1].replace(";", "");
    }
    
    public void printColoumn(List coloumn){
        int i = 0;
        while (i < coloumn.size()){
            System.out.print(col.get(i) + " ");
            i++;
        }
        System.out.println("");
    } 
    
    public String printTable(String statement){
        String tabel[] = statement.split(" ");
        return (tabel[fromPointer + 1].replace(";", ""));
    } 
    
    public void printWhere(String statement){
        String tabel[] = statement.split(" ");
        int i = 0;
        
        while(!tabel[i].toUpperCase().equals("WHERE")){
            i++;
        }
        i++;
        while(i < tabel.length){
            System.out.print(tabel[i].replace(";", "") + " ");
            i++;
        }
        System.out.println();
    }
    
    public void printQEP(boolean pk, String query, boolean join){
        
        System.out.println("MENU 4 : QEP and Cost");
        
        col = getColoumn(query);
        boolean q1 = false;
        int cost1 = 0, cost2 = 0;
        int blockMemutar = no2.blockData(no1.BFR(intNomor4[1],intMemutar[0]), intMemutar[1]);
        int blockLagu = no2.blockData(no1.BFR(intNomor4[1],intLagu[0]), intLagu[1]);
        int blockPengguna = no2.blockData(no1.BFR(intNomor4[1],intPengguna[0]), intPengguna[1]);
        
        if (pk){
            
            if (printTable(query).toUpperCase().equals("MEMUTAR")){
                cost1 = blockMemutar / 2;
                cost2 = 1 + (int) (Math.log(no2.blockData(no1.BFR(intNomor4[1],intMemutar[0]), intMemutar[1])) / Math.log(no1.fanout(intNomor4[1], intMemutar[2], intNomor4[0])));
            }
            else if (printTable(query).toUpperCase().equals("LAGU")){
                cost1 = blockLagu / 2;
                cost2 = 1 + (int) (Math.log(no2.blockData(no1.BFR(intNomor4[1],intLagu[0]), intLagu[1])) / Math.log(no1.fanout(intNomor4[1], intLagu[2], intNomor4[0])));
            }
            else if (printTable(query).toUpperCase().equals("PENGGUNA")){
                cost1 = blockPengguna / 2;
                cost2 = 1 + (int) (Math.log(no2.blockData(no1.BFR(intNomor4[1],intPengguna[0]), intPengguna[1])) / Math.log(no1.fanout(intNomor4[1], intPengguna[2], intNomor4[0])));
            }
            else{
                System.out.println("Undefined Table Name");
            }
            
            System.out.println("KEY");
            System.out.println("");
            
//            Print A1
            System.out.println("A1");
            System.out.print("PROJECTION ");
            printColoumn(col);
            System.out.println("SELECTION ");
            printWhere(query);
            System.out.println(printTable(query).toUpperCase());
            System.out.println("COST QEP1 : " + cost1);
            
            System.out.println("");
            
//            Print A2
            System.out.println("A2");
            System.out.print("PROJECTION ");
            printColoumn(col);
            System.out.println("SELECTION ");
            printWhere(query);
            System.out.println(printTable(query).toUpperCase());
            System.out.println("COST QEP2 : " + cost2);
            
//            Get the optimum QEP
            System.out.println("");
            if (cost1 <= cost2) System.out.println("QEP Optimal : QEP1");
            else System.out.println("QEP Optimal : QEP2");
        }
        else{
            System.out.println("Non - Key");
            System.out.print("PROJECTION ");
            printColoumn(col);
            
//            Check for join is exist
            if (join){
                System.out.println("JOIN : " + printTable(query).toUpperCase() + " & " + printJoin(query).toUpperCase() + " --- Block Nested Loop 1");
                System.out.println("       " + printJoin(query).toUpperCase() + " & " + printTable(query).toUpperCase() + " --- Block Nested Loop 2");
                
                if (printTable(query).toUpperCase().equals("MEMUTAR")){
                    if (printJoin(query).toUpperCase().equals("LAGU")){
                        cost1 = blockMemutar * blockLagu + blockMemutar;
                        cost2 = blockLagu * blockMemutar + blockLagu;
                    }
                    else if (printJoin(query).toUpperCase().equals("PENGGUNA")){
                        cost1 = blockMemutar * blockPengguna + blockMemutar;
                        cost2 = blockPengguna * blockMemutar + blockPengguna;
                    }
                    else{
                        System.out.println("Undefined Table Join");
                    }
                }
                else if (printTable(query).toUpperCase().equals("LAGU")){
                    if (printJoin(query).toUpperCase().equals("MEMUTAR")){
                        cost2 = blockMemutar * blockLagu + blockMemutar;
                        cost1 = blockLagu * blockMemutar + blockLagu;
                    }
                    else if (printJoin(query).toUpperCase().equals("PENGGUNA")){
                        cost2 = blockPengguna * blockLagu + blockPengguna;
                        cost1 = blockLagu * blockPengguna + blockLagu;
                    }
                    else{
                        System.out.println("Undefined Table Join");
                    }
                }
                else if (printTable(query).toUpperCase().equals("PENGGUNA")){
                    if (printJoin(query).toUpperCase().equals("LAGU")){
                        cost1 = blockPengguna * blockLagu + blockPengguna;
                        cost2 = blockLagu * blockPengguna + blockLagu;
                    }
                    else if (printJoin(query).toUpperCase().equals("MEMUTAR")){
                        cost2 = blockMemutar * blockPengguna + blockMemutar;
                        cost1 = blockPengguna * blockMemutar + blockPengguna;
                    }
                    else{
                        System.out.println("Undefined Table Join");
                    }
                }
                else{
                    System.out.println("Undefined Table Name");
                }
                
                System.out.println("");
                System.out.println("Cost QEP1 : " + cost1);
                System.out.println("Cost QEP2 : " + cost2);
                System.out.println("");
                
//                get the optimum QEP
                if (cost1 >= cost2) System.out.println("QEP Optimal : QEP2");
                else System.out.println("QEP Optimal : QEP1");
            }
            else{
           
                System.out.println("SELECTION ");
                printWhere(query);
                System.out.println(printTable(query).toUpperCase());
                
                int cost = 0;
                if (printTable(query).toUpperCase().equals("MEMUTAR")){
                    cost = blockMemutar;
                }
                else if (printTable(query).toUpperCase().equals("LAGU")){
                    cost = blockLagu;
                }
                else if (printTable(query).toUpperCase().equals("PENGGUNA")){
                    cost = blockPengguna;
                }
                else{
                    System.out.println("Undefined Table Name");
                }
                
                System.out.println("COST : " + cost); 
                System.out.println("");
                System.out.println("QEP Optimal : QEP A1 Non - Key");
            }  
        }
    }
    
    
}
