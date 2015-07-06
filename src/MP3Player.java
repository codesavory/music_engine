
import AppPackage.MP3PlayerGUI;
import AppPackage.music_client;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SA
 */
public class MP3Player 
{
    MP3PlayerGUI g1;

    public MP3Player() throws ClassNotFoundException, SQLException, InterruptedException {
        this.g1 = new MP3PlayerGUI(new javax.swing.JFrame(), true);
    }
    public static void main(String[] args)
    {
      
    }
}
