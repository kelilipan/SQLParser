/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlparser;

/**
 *
 * @author Anvaqta Tangguh
 */
public class Pengguna {
    public String idUser;
    public String password;
    public String namaUser;

    public Pengguna(String idUser, String password, String namaUser) {
        this.idUser = idUser;
        this.password = password;
        this.namaUser = namaUser;
    }
    
}
