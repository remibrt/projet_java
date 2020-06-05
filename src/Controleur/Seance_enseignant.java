/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

/**
 *
 * @author remibreton
 */
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class Seance_enseignant extends Utilisateur{
    
    ArrayList<String> info_u = new ArrayList();
    ArrayList<String> test = new ArrayList();
    ArrayList<String> info_seance_enseignant = new ArrayList();
    
    public Seance_enseignant(ArrayList<String> info_user) throws SQLException, ClassNotFoundException
    {
       super();
       info_seance_enseignant.addAll(info_user);
    }
     public ArrayList lire_seance_enseignant(int creneau, int week)
    {
        ResultSet result;
        info_seance_enseignant.clear();
        test.clear();
        
        String nom_seance = "";
        String id_seance = "0";
        String nom_prof = "";
        
        try
        {
            try
            {
                Seance s1 = new Seance(); 
                
                test.addAll(s1.lire_seances(creneau, week));
                
                result = stmt.executeQuery("SELECT id,nom_cours FROM seance WHERE id_cours = " + test.get(3));
                while(result.next())
                {
                    id_seance = String.valueOf(result.getInt(1));
                    nom_seance = result.getString(2);
                    nom_prof = test.get(1);
                }
            }
            
            catch (ClassNotFoundException cnfe) 
            {
                cnfe.printStackTrace();
            }
        }
        
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
        info_seance_enseignant.add(nom_seance);
        info_seance_enseignant.add(nom_prof);
        info_seance_enseignant.add(id_seance);
        
        return info_seance_enseignant;
    
    }
    
}
