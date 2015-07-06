/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppPackage;
import java.rmi.*;
/**
 *
 * @author SA
 */
public interface music_interface extends Remote
{
    public String chooser() throws RemoteException;
}
