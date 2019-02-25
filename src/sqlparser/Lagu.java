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
public class Lagu {
    public String idLagu;
    public String judulLagu;
    public String artist;
    public String genre;
    public int years;

    public Lagu(String idLagu, String judulLagu, String artist, String genre, int years) {
        this.idLagu = idLagu;
        this.judulLagu = judulLagu;
        this.artist = artist;
        this.genre = genre;
        this.years = years;
    }
    
    
}
