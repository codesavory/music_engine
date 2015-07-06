package AppPackage;
//import static AppPackage.MP3PlayerGUI.Display;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author SA
 */
public class music_server extends UnicastRemoteObject implements music_interface
{
    MainClass MC = new MainClass();
    //slides r = new slides();
    bass b = new bass();
    bass2 u = new bass2();
    public music_server() throws RemoteException
    {
    }
    public String chooser() throws RemoteException
    {
        
        String song=null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files","mp3","mpeg3");
        JFileChooser chooser = new JFileChooser("E:\\Music");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
           File myFile = chooser.getSelectedFile();   
           song = myFile.getAbsolutePath();//file to string      
            //System.out.println("Abs Path:"+song);
        }
        return song;                        
    }
    
    public static void main(String[] args)
    {
      try {
          //bind the remote object's stud in the registry
          LocateRegistry.createRegistry(1099);
         Naming.rebind("rmi://localhost/MusicPlayer",
                                new music_server());
         System.out.println("Music Server is ready.");
      }
      catch (RemoteException | MalformedURLException e) 
      {
         System.out.println("Hello Server failed: " + e);
      }
   }
}
