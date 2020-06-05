/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.sql.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author remibreton
 */
public class Utilisateur {
    public int id ; 
    public String email ;
    private String passwd ; 
    private String nom ; 
    private String prenom ; 
    private String droit ;
    private int groupe;
    
 
  public ResultSet rset;
  public ResultSetMetaData rsetMeta;
  public final Connection conn;
    public Statement stmt;
    
    
    public  Utilisateur() throws SQLException, ClassNotFoundException{
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost:3306/" + "projet_java";
       // String urlDatabase = "jdbc:mysql://localhost:3308/jps?characterEncoding=latin1";

        //création d'une connexion JDBC à la base 
        conn = DriverManager.getConnection(urlDatabase, "root", "root");

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }


    public int Acces_co(String email, String passwd) throws SQLException {
        int access = 0;
        ArrayList<String> liste_mail;
        liste_mail = new ArrayList<>(); // creation d'une liste de tous les mail
        
        ArrayList<String> liste_passwd; // creation d'une liste de tous les passwd
        liste_passwd = new ArrayList<>();
        
         
        int i ; 
        
       
        
        stmt = conn.createStatement();
        
        ResultSet result = stmt.executeQuery("SELECT * FROM utilisateur");
        
        ResultSetMetaData resulM = result.getMetaData();
        
        
         while (result.next()) {
       
              // System.out.println(result.getString(2)); // le champs 2 correspond aux adresses mail
               liste_mail.add(result.getString(2));     // on ajoute à la liste

       //   System.out.println(result.getString(3)); // champs 3 correspond au passw
          liste_passwd.add(result.getString(3)); // on ajoute à la liste 
          
          // comparaison 
          
        }

       
       for(i=0; i<liste_mail.size(); i++){
           
              if(email.equals(liste_mail.get(i))){  // on test si on trouve d'abord l'adresse mail
                 
                  if(passwd.equals(liste_passwd.get(i)))
                  {
                      
                      // si les données sont correctes on récupere les données relatives à l'utilisateur
                      
                      ResultSet res = stmt.executeQuery("SELECT * FROM utilisateur WHERE passwd = '" +  passwd +" ' ;" );
                       
                      while(res.next()){
                        
                          id = res.getInt(1) ;
                          this.email = res.getString(2) ;
                          this.passwd = res.getString(3) ;
                          nom = res.getString(4) ;
                          prenom = res.getString(5) ;
                          droit = res.getString(6) ;
                          groupe = res.getInt(7);
                          
                      }
                      access = 1;
                      
                  }
                  else 
                  {
                      access = 2;
                  }
                  
              }
              else access = 0;
              
          }
       
        return access;
    }
    
    public String getNom()
    {
        return nom ;
    }
    public String getPrenom()
    {
        return prenom ;
    }
    public String getDroit()
    {
        return droit ;
    }
    public int getGroupe()
    {
        return groupe;
    }
    
    
}
