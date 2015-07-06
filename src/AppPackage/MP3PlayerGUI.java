package AppPackage;

//All code is added to create the widget. Follow the "Create a Widget" tutorial if you want to know how I did it.

import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import AppPackage.AnimationClass.*;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//========================/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JFrame;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MP3PlayerGUI extends javax.swing.JDialog 
{
    static MP3PlayerGUI dialog = null;
    static firlist m = new firlist();
    
    String sql;
    String url;
    Connection con;
    Statement st;
    ResultSet results;
    
    String s;
    Scanner in = new Scanner(System.in);    
    //slides r = new slides();
    bass b = new bass();
    bass2 u = new bass2();
    
    String name = "rmi://localhost/MusicPlayer";
    String hsong,dsong;
    String song=null;
    music_interface music;
    
    MainClass MC = new MainClass();
    public static int count;
    int xMouse;
    int yMouse;
    
    int width = (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 185;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height - 180;
    int flag=0;
    public MP3PlayerGUI(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException, InterruptedException 
    {
        super(parent, modal);
        initComponents();
        
        this.setLocation(width, height);
        System.out.println("Enter Client name to enter DB: ");
        s = in.nextLine();
        //System.out.println("You entered string "+s);
        Class.forName("com.mysql.jdbc.Driver" );
        url= "jdbc:mysql://localhost/music_clients";
        con = DriverManager.getConnection(url,"root","Seven");
        st = con.createStatement();
        results = st.executeQuery("SELECT name FROM client_details");
        while (results.next()) 
        {
             String s1 = results.getString(1);
             if(s1.equals(s))
             {
                 System.out.println("Searching if you there in our Database...");
                 Thread.sleep(3000);
                 System.out.println("You are there in our Database :)");
                 Thread.sleep(1000);
                 System.out.println("Welcome Back to music_engine "+s+" :)");
                 Thread.sleep(3000);
                 flag=1;
                 
             }
        }      
        if(flag==0)
        {
             System.out.println("Welcome, you are a new Client to the music_engine server :)");
             Thread.sleep(1000);
             System.out.println("Creating a new database for you...");
             Thread.sleep(3000);
             System.out.println("Database created");
             Thread.sleep(1000);
             System.out.println("Creating a new table for you in your database...");
             Thread.sleep(3000);
             System.out.println("Table created");
             Thread.sleep(1000);
             System.out.println("You are now good to use our Music_Engine ;) Have fun :)");
             Thread.sleep(3000);
             sql = "insert into client_details values('"+s+"')";
             st.executeUpdate(sql);
             sql = "CREATE DATABASE "+s;
             //System.out.println(sql);
             st.executeUpdate(sql);
             url= "jdbc:mysql://localhost/"+s;
             con = DriverManager.getConnection(url,"root","Seven");
             st = con.createStatement();
             sql = "CREATE TABLE songs_detail " +
                   "(name varchar(50) primary key, " +
                   " genre VARCHAR(50), " + 
                   " artist VARCHAR(50), " + 
                   " album varchar(50), " + 
                   " composer varchar(50),"+
                   " loc varchar(100));"; 
             st.executeUpdate(sql);
        }
        flag=0;
        try {
            // TODO add your handling code here:
            song_drop.removeAllItems();
            album_drop.removeAllItems();
            genre_drop.removeAllItems();
            artist_drop.removeAllItems();
            composer_drop.removeAllItems();
            //song_drop.addItem("Suriya");
            url = "jdbc:mysql://localhost/"+s;
            con = DriverManager.getConnection(url,"root","Seven");
            st = con.createStatement();
            results = st.executeQuery("SELECT * FROM songs_detail");
            while (results.next())
            {
                String s1=results.getString(1);//song
                String s2=results.getString(2);//genre
                String s3=results.getString(3);//artist
                String s4=results.getString(4);//album
                String s5=results.getString(5);//composer
                System.out.println("Adding ...");
                song_drop.addItem(s1);
                genre_drop.addItem(s2);
                artist_drop.addItem(s3);
                album_drop.addItem(s4);
                composer_drop.addItem(s5);
            }
            song_drop.repaint();
            genre_drop.repaint();
            artist_drop.repaint();
            album_drop.repaint();
            composer_drop.repaint();
            
        } catch (SQLException ex) {
            Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        Loopcounter = new javax.swing.JLabel();
        Display = new javax.swing.JLabel();
        Play = new javax.swing.JLabel();
        SelectFile = new javax.swing.JLabel();
        Pause = new javax.swing.JLabel();
        Stop = new javax.swing.JLabel();
        Loop = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        song_drop = new javax.swing.JComboBox();
        album_drop = new javax.swing.JComboBox();
        artist_drop = new javax.swing.JComboBox();
        genre_drop = new javax.swing.JComboBox();
        composer_drop = new javax.swing.JComboBox();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jList3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MP3 Player");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Loopcounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 50, 10));

        Display.setBackground(new java.awt.Color(255, 204, 51));
        Display.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        Display.setOpaque(true);
        getContentPane().add(Display, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 330, 20));

        Play.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PlayMouseReleased(evt);
            }
        });
        getContentPane().add(Play, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 90, 83));

        SelectFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SelectFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SelectFileMouseReleased(evt);
            }
        });
        getContentPane().add(SelectFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 60, 40, 40));

        Pause.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Pause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PauseMouseReleased(evt);
            }
        });
        getContentPane().add(Pause, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 50, 60, 60));

        Stop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                StopMouseReleased(evt);
            }
        });
        getContentPane().add(Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 70, 60));

        Loop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Loop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoopMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LoopMouseReleased(evt);
            }
        });
        getContentPane().add(Loop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 40, 40));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/Background.png"))); // NOI18N
        Background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BackgroundMousePressed(evt);
            }
        });
        Background.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BackgroundMouseDragged(evt);
            }
        });
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 130));

        song_drop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Songs" }));
        song_drop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                song_dropMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                song_dropMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                song_dropMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                song_dropMouseReleased(evt);
            }
        });
        song_drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                song_dropActionPerformed(evt);
            }
        });
        getContentPane().add(song_drop, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 100, 25));

        album_drop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Albums" }));
        album_drop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                album_dropMouseReleased(evt);
            }
        });
        album_drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                album_dropActionPerformed(evt);
            }
        });
        getContentPane().add(album_drop, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 100, 25));

        artist_drop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Artists" }));
        artist_drop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artist_dropMouseReleased(evt);
            }
        });
        artist_drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                artist_dropActionPerformed(evt);
            }
        });
        getContentPane().add(artist_drop, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 100, 25));

        genre_drop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Genres" }));
        genre_drop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                genre_dropMouseReleased(evt);
            }
        });
        genre_drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genre_dropActionPerformed(evt);
            }
        });
        getContentPane().add(genre_drop, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 100, 25));

        composer_drop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Composer" }));
        composer_drop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                composer_dropMouseReleased(evt);
            }
        });
        composer_drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                composer_dropActionPerformed(evt);
            }
        });
        getContentPane().add(composer_drop, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 95, 100, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackgroundMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackgroundMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_BackgroundMouseDragged

    private void BackgroundMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackgroundMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_BackgroundMousePressed

    private void StopMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StopMouseReleased

            MC.Stop();  
    }//GEN-LAST:event_StopMouseReleased

    private void PlayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayMouseReleased

            MC.Resume();

    }//GEN-LAST:event_PlayMouseReleased

    private void PauseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PauseMouseReleased

            MC.Pause();

    }//GEN-LAST:event_PauseMouseReleased

    public String hasher(String sng)
    {
        sng=sng.replace('\\', '#');
        return sng; 
    }
    public String dehasher(String sng)
    {
        sng=sng.replace('#', '\\');
        return sng; 
    }
    private void SelectFileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectFileMouseReleased
        // TODO add your handling code here:8
                        //list.setVisible(true);
                        int len;
                        
                        try {
                         music = (music_interface)Naming.lookup(name);
                         song=music.chooser();
                         hsong=hasher(song);
                         System.out.println("Hsong:"+hsong);
                         System.out.println("song: "+song);
                         MC.Stop();
                        } 
                        
                        catch (NotBoundException | MalformedURLException | RemoteException e) 
                        {
                            System.out.println("HelloClient exception: " + e);
                        }
                                              
                        try
                        {
                            InputStream input = new FileInputStream(new File(song));
                            ContentHandler handler = new DefaultHandler();
                            Metadata metadata = new Metadata();
                            Parser parser = new Mp3Parser();
                            ParseContext parseCtx = new ParseContext();
                            parser.parse(input, handler, metadata, parseCtx);
                            input.close();
                            // Retrieve the necessary info from metadata
                            // Names - title, xmpDM:artist etc. - mentioned below may differ based
                            System.out.println("----------------------------------------------");
                            System.out.println("Metadata of the current song:");
                            String name1=metadata.get("title");
                            Display.setText(name1);
                            String artist=metadata.get("xmpDM:artist");
                            String composer=metadata.get("xmpDM:composer");
                            String genre=metadata.get("xmpDM:genre");
                            String album=metadata.get("xmpDM:album");
                            System.out.println("Name: " + name1);
                            System.out.println("Artist: " + artist);
                            System.out.println("Composer: "+composer);
                            System.out.println("Genre : "+genre);
                            System.out.println("Album : "+album);

                            Class.forName("com.mysql.jdbc.Driver" );
                            url = "jdbc:mysql://localhost/"+s;
                            con = DriverManager.getConnection(url,"root","Seven");
                            st = con.createStatement();
                            results = st.executeQuery("SELECT name FROM songs_detail");
                            System.out.println("----------------------------------------------");
                            System.out.println("Songs in Database:");
                            flag=0;
                            while (results.next()) 
                            {
                                        String s1 = results.getString(1);
                                        
                                        if(s1.equals(name1))
                                        {
                                            System.out.println("Found the song in your database ;)");
                                            Thread.sleep(3000);
                                            System.out.println("Playing song from Databse :)");
                                            Thread.sleep(1000);
                                            flag=1;
                                        }
                                        System.out.println(s1);
                            }
                            if(flag==0)
                            {   
                                String sql = "INSERT INTO songs_detail " +
                                            "VALUES ('"+ name1 +"','"+ genre +"','"+ artist +"','"+ album +"','"+ composer +"','"+hsong+"')";
                                //System.out.println(sql);
                                st.executeUpdate(sql);
                                System.out.println("This is a new song...");
                                Thread.sleep(3000);
                                System.out.println("Adding song to your Databse :)");
                                Thread.sleep(1000);
                                System.out.println(name1);
                                song_drop.addItem(name1);
                                genre_drop.addItem(genre);
                                artist_drop.addItem(artist);
                                album_drop.addItem(album);
                                composer_drop.addItem(composer);
                            }
                            song_drop.repaint();
                            genre_drop.repaint();
                            artist_drop.repaint();
                            album_drop.repaint();
                            composer_drop.repaint();
                            
                            flag=0;
                            MC.Play(song);
                            /*if("E:\\Music\\Kaththi\\Aathi-StarMusiQ.Com.mp3".equals(song))
                            {
                                r.setVisible(true);
                                r.SlideShow();
                            }*/

                            b.setVisible(true);
                            u.setVisible(true);
                            st.close();
                            con.close();
                        } //try-ends
                        catch (FileNotFoundException e1) {
                        } 
                        catch (IOException e1) {
                        }  
                        catch (SAXException | TikaException e1) {
                        }
                        catch (ClassNotFoundException ex) {
                            Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                        catch (SQLException ex) {
                            Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
            Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SelectFileMouseReleased

    private void LoopMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoopMouseReleased
        // TODO add your handling code here:
        switch(count)
        {
            case 0:
                count = 1;
                Loopcounter.setText("Loop On");
                break;
                
            case 1:
                count = 0; 
                Loopcounter.setText("Loop Off");
                break;
        }
    }//GEN-LAST:event_LoopMouseReleased

    private void LoopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoopMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_LoopMouseEntered

    private void album_dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_album_dropActionPerformed
        // TODO add your handling code here:
            Object sname = album_drop.getSelectedItem();
             System.out.println(sname);
    }//GEN-LAST:event_album_dropActionPerformed

    private void album_dropMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_album_dropMouseReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_album_dropMouseReleased

    private void artist_dropMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artist_dropMouseReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_artist_dropMouseReleased

    private void genre_dropMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genre_dropMouseReleased
        // TODO add your handling code here:
      
    }//GEN-LAST:event_genre_dropMouseReleased

    private void composer_dropMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_composer_dropMouseReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_composer_dropMouseReleased

    private void song_dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_song_dropActionPerformed
        
        
        /*try {
                Object sname = song_drop.getSelectedItem();
                System.out.println(sname);
                url = "jdbc:mysql://localhost/"+s;
                con = DriverManager.getConnection(url,"root","Seven");
                st = con.createStatement();
                results = st.executeQuery("SELECT loc FROM songs_detail where name='"+sname+"'");
                while (results.next())
                {
                    String s1 = results.getString(1);
                    dsong=dehasher(s1);
                    MC.Stop();
                    MC.Play(dsong);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }*/
    }//GEN-LAST:event_song_dropActionPerformed

    private void song_dropMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_song_dropMouseReleased
        /*Object sname = song_drop.getSelectedItem();
        System.out.println(sname);*/
        try {
                Object sname = song_drop.getSelectedItem();
                String pp=sname+"";
                System.out.println(sname);
                url = "jdbc:mysql://localhost/"+s;
                con = DriverManager.getConnection(url,"root","Seven");
                st = con.createStatement();
                results = st.executeQuery("SELECT loc FROM songs_detail where name='"+sname+"'");
                while (results.next())
                {
                    String s1 = results.getString(1);
                    dsong=dehasher(s1);
                    MC.Stop();
                    MC.Play(dsong);
                    Display.setText(pp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_song_dropMouseReleased

    private void song_dropMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_song_dropMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_song_dropMousePressed

    private void song_dropMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_song_dropMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_song_dropMouseEntered

    private void song_dropMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_song_dropMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_song_dropMouseClicked

    private void artist_dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artist_dropActionPerformed
        // TODO add your handling code here:
        Object sname = artist_drop.getSelectedItem();
        System.out.println(sname);
    }//GEN-LAST:event_artist_dropActionPerformed

    private void genre_dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genre_dropActionPerformed
        // TODO add your handling code here:
        Object sname = genre_drop.getSelectedItem();
        System.out.println(sname);
    }//GEN-LAST:event_genre_dropActionPerformed

    private void composer_dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_composer_dropActionPerformed
        // TODO add your handling code here:
        Object sname = composer_drop.getSelectedItem();
        System.out.println(sname);
    }//GEN-LAST:event_composer_dropActionPerformed

    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                //m.setVisible(true);
                //j1 = new javax.swing.JFrame();
                
                try {
                    dialog = new MP3PlayerGUI(new javax.swing.JFrame(), true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() 
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) 
                    {
                        System.exit(0);
                    }
                    
                });
                dialog.setVisible(true);    
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    public static javax.swing.JLabel Display;
    private javax.swing.JLabel Loop;
    private javax.swing.JLabel Loopcounter;
    private javax.swing.JLabel Pause;
    private javax.swing.JLabel Play;
    private javax.swing.JLabel SelectFile;
    private javax.swing.JLabel Stop;
    private javax.swing.JComboBox album_drop;
    private javax.swing.JComboBox artist_drop;
    private javax.swing.JComboBox composer_drop;
    private javax.swing.JComboBox genre_drop;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox song_drop;
    // End of variables declaration//GEN-END:variables
}
