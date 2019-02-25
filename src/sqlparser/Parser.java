/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlparser;

import java.util.ArrayList;

/**
 *
 * @author Anvaqta Tangguh
 */
public class Parser {
    public String statement[];
    public ArrayList<String> args = new ArrayList();
    Tabel tabel = new Tabel();
    
    
    
    public Parser(String input) {
        statement=input.split(" ");
        for (int i = 0; i < statement.length; i++) {
            statement[i] = statement[i].toUpperCase();
        }
        
        String lastStatement = statement[statement.length - 1];
        if(!lastStatement.endsWith(";"))
        {
            System.out.println("ERROR missing ;");
        }
        statement[statement.length - 1] = lastStatement.replace(";", "");
        if(statement[0].equals("SELECT")){
            for (int i = 1;!statement.equals("FROM"); i++) {
                args.add(statement[i].replace(",", ""));
            }
        }
    }

    
    
    
}
