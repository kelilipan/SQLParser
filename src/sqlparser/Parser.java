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
 * @author Anvaqta Tangguh
 */
public class Parser {

    //LAGU
    public List<String> tableLagu = new ArrayList<String>();
    //PENGGUNA
    public List<String> tablePengguna = new ArrayList();
    //MEMUTAR
    public List<String> tableMemutar = new ArrayList();
    public String[] tempLagu, tempPengguna, tempMemutar;
    public List<String> col = new ArrayList();
    public boolean primaryKey = false, colExist = false;

    public Parser() throws FileNotFoundException, IOException {
        BufferedReader BR = new BufferedReader(new FileReader("lagu.txt"));
        String line;
        BufferedReader BR2 = new BufferedReader(new FileReader("pengguna.txt"));
        String line2;
        BufferedReader BR3 = new BufferedReader(new FileReader("memutar.txt"));
        String line3;
        //load data
        while (((line = BR.readLine()) != null) && ((line2 = BR2.readLine()) != null) && ((line3 = BR3.readLine()) != null)) {
            tableLagu.add(line);
            tablePengguna.add(line2);
            tableMemutar.add(line3);
            }
    }

    public String[] findTable(String table) {
        table = table.toUpperCase();
        if (table.equals("LAGU")) {
            return tempLagu;
        } else if (table.equals("PENGGUNA")) {
            return tempPengguna;
        } else if (table.equals("MEMUTAR")) {
            return tempMemutar;
        } else {
            return null;
        }
    }

    public void printAll(String[] table, String tableName) {
        System.out.println("TABLE : " + tableName.toUpperCase());
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i].toUpperCase() + " ");
        }
        System.out.println("");
    }

    public boolean checkColumn(String col) {
        boolean found = false;
        for (int i = 0; i < tempLagu.length; i++) {
            if (tempLagu[i].toUpperCase().equals(col)) {
                return true;
            }
//            System.out.println("pusing "+tempLagu[i]);
        }
        for (int i = 0; i < tempMemutar.length; i++) {
            if (tempMemutar[i].toUpperCase().equals(col)) {
                return true;
            }
        }
        for (int i = 0; i < tempPengguna.length; i++) {
            if (tempPengguna[i].toUpperCase().equals(col)) {
                return true;
            }
        }
        return found;
    }
    
    public boolean checkPrimary(String col){
        boolean found = false;
        if (tempPengguna[0].toUpperCase().equals(col)){
            found = true;
        }
        if (tempLagu[0].toUpperCase().equals(col)){
            found = true;
        }
        if (tempMemutar[0].toUpperCase().equals(col)){
            found = true;
        }
        return found;
    }
    
    public boolean checkColumn(String col, String[] table){
        boolean found=false;
        for (int i = 0; i < table.length; i++) {
//            System.out.println(table[i]);
            if (table[i].toUpperCase().equals(col.toUpperCase())) {
                return true;
            }
        }
        return found;
    }

    public boolean parse(String query) {
        String[] statement = query.split(" ");
        String[] selectedTable, selectedTableJoin;
        String lastStat = statement[statement.length - 1];
        
        for (int i = 0; i < statement.length; i++) {
            statement[i] = statement[i].toUpperCase();
        }
        if (statement[0].equals("SELECT")) {
            tempLagu = tableLagu.get(0).split(";");
            tempPengguna = tablePengguna.get(0).split(";");
            tempMemutar = tableMemutar.get(0).split(";");
            
//            Checking ;
            if (!lastStat.endsWith(";")) {
                System.out.println("ERROR :  Missing ';' in the end of the statement.");
                return false;
            } else {
                statement[statement.length - 1] = lastStat.replace(";", "");
                int i = 1;
                int fromPointer = 1;
                boolean fromExist = false, joinExist = false, usingExist = false, whereExist = false;
//              Dapetin semua pointer join, from, using, where
                while (!(statement[fromPointer].equals("FROM") && (fromPointer < statement.length - 1))) {
                    if (statement[fromPointer].endsWith(",")) {
                        statement[fromPointer] = statement[fromPointer].replace(",", "");
                    }
                    col.add(statement[fromPointer]);
                    fromPointer++;
                }
                if ((statement[fromPointer].toUpperCase().equals("FROM"))) {
                    fromExist = true;
                }
                
                int joinPointer = 0;
                while (!(statement[joinPointer].toUpperCase().equals("JOIN")) && (joinPointer < statement.length - 1)) {
                    joinPointer++;
                }
                if ((statement[joinPointer].toUpperCase().equals("JOIN"))) {
                    joinExist = true;
//                    System.out.println("JOIN FOUND");
                }
                
                int usingPointer = 0;
                while (!(statement[usingPointer].toUpperCase().equals("USING")) && (usingPointer < statement.length - 1)) {
                    usingPointer++;
                }
                if ((statement[usingPointer].toUpperCase().equals("USING"))) {
                    usingExist = true;
//                    System.out.println("USING FOUND");
                }
                
                // Cek apakah JOIN / select Ada di query atau tidak
                if (!fromExist) {
                    System.out.println("ERROR: Missing statement after FROM");
                    return false;
                } else {
                    //if(fromExist){// jika tidak ada JOIN
                    //lakukan Pengecekan tabel yang ditunjuk from
                    selectedTable = findTable(statement[fromPointer + 1]);
                    if (selectedTable == null) {
                        System.out.println("ERROR : Table " + statement[fromPointer + 1] + " doesn't exist");
                        return false;
                    } else {
                        //jika tabel ada maka print sesuai argumen yang diberikan
                        if (statement[1].equals("*")) {//select all
                            printAll(selectedTable, statement[fromPointer + 1]);
                        } else {// sesuai
                            System.out.println("TABLE : " + statement[fromPointer + 1].toUpperCase());
                            for (String data : col) {
                                if (!checkColumn(data)) {
                                    System.out.println("ERROR : Column " + data + " Not FOUND");
                                    return false;
                                } else {
                                    System.out.print(data + " ");
                                }
                            }
                            System.out.println("");
                        }
                    }
                    int wherePointer = 0;
                    while (!(statement[wherePointer].toUpperCase().equals("WHERE")) && (wherePointer < statement.length - 1)){
                        wherePointer++;
                    }
                    if ((statement[wherePointer].toUpperCase().equals("WHERE"))){
                        whereExist = true;
                    }
                    if (whereExist){
                        if(wherePointer == statement.length - 1){
                            System.out.println("ERROR : Missing statement after WHERE");
                            return false;
                        }
                        else{
//                            Check column after where
                            if(!checkColumn(statement[wherePointer+1])){
                                System.out.println(statement[wherePointer+1] + "ERROR : Argument after WHERE");
                                return false;
                            }
                            else{
//                                Check for PK
                                primaryKey = checkPrimary(statement[wherePointer+1]);
                                if (primaryKey == true){
                                    System.out.println("PRIMARY KEY : "+statement[wherePointer+1]);
                                }
                                
//                                System.out.println("WHERE Success");
                                if (statement[wherePointer + 2].equals("=") || statement[wherePointer + 2].equals("LIKE") || statement[wherePointer + 2].equals("<") || statement[wherePointer + 2].equals(">") || statement[wherePointer + 2].equals("!=") || statement[wherePointer + 2].equals("<=") || statement[wherePointer + 2].equals(">=")){
//                                    System.out.println("Masuk ?");
                                    if (statement[wherePointer + 3] == ""){
                                        System.out.println("ERROR : Missing statement after '='");
                                        return false;
                                    }
                                    else{
//                                        System.out.println("Check column after where success");
                                    }
                                }
                            }
                        }
                    }
                    
                    if (joinExist) {//JIKA ADA JOIN 
                        if (joinExist && joinPointer == statement.length-1) {
                            System.out.println("ERROR: Missing statement after JOIN");
                            return false;
                        } else {
                            selectedTableJoin = findTable(statement[joinPointer + 1]);
                            if (selectedTableJoin == null) {
                                System.out.println("ERROR : Table " + statement[joinPointer + 1] + " doesn't exist");
                                return false;
                            } else {
                                System.out.println(statement[joinPointer + 1]);
                                if(statement[1].equals("*")){
                                    printAll(selectedTableJoin, statement[joinPointer + 1]);
                                }
                                else if(!usingExist){
                                    System.out.println("ERROR: Invalid Arguments JOIN");
                                    return false;
                                }else{
                                    if(usingPointer == statement.length-1){
                                        System.out.println("ERROR: Missing statement after USING");
                                        return false;
                                    }else{
                                        int idUsing=usingPointer+1;
                                        String argUsing = statement[idUsing];
                                        if (!statement[idUsing].endsWith(")")){
                                            System.out.println("ERROR: Missing ')'");
                                            return false;
                                        }
                                        else{
                                            argUsing = argUsing.replace("(","").replace(")", "");
                                            if (!(checkColumn(argUsing, selectedTable) && checkColumn(argUsing, selectedTableJoin))){
                                                System.out.println(checkColumn(argUsing, selectedTable));
                                                System.out.println(checkColumn(argUsing, selectedTableJoin));
                                                System.out.println(argUsing);
                                                System.out.println("ERROR: Invalid Arguments");
                                                return false;
                                            }else{
                                                for(String data : col){
                                                    System.out.print(data+" ");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("ERROR : Invalid Arguments");
            return false;
        }
        return true;
    }
}
