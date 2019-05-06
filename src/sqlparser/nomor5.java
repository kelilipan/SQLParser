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
public class nomor5 {
    private nomor4 no4;

    public nomor5(boolean pk, String query, boolean join) throws IOException {
        no4 = new nomor4();
        System.out.println(query);
        System.out.println("");
        no4.printQEP(pk, query, join);
    }
    
    
}
