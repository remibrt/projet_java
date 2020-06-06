/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.sql.SQLException;
import java.sql.*;
import java.util.*;
/**
 *
 * @author remibreton
 */
public class Seance_Groupe extends Utilisateur
{
    ArrayList<String> info_u = new ArrayList();
    ArrayList<String> test = new ArrayList();
    ArrayList<String> info_groupe = new ArrayList();
    
    public Seance_Groupe(ArrayList<String> info_user) throws SQLException, ClassNotFoundException
    {
       super();
       info_u.addAll(info_user);
    }
    

    public ArrayList lire_seance_groupe(int creneau, int week) throws SQLException,ClassNotFoundException
    {
        
        stmt = conn.createStatement();
        info_groupe.clear();
        test.clear();
        System.out.println("semaine methode groupe 1 : " + week);
        String nom_seance = "";
        String id_groupe = "0";
        String id_seance = "0";
        String nom_prof = "";
        
        ResultSet result;
        Seance s1 = new Seance(); 
                
        test.addAll(s1.lire_seances(creneau, week));
                
        result = stmt.executeQuery("SELECT id_groupe FROM seance_groupes WHERE id_seance = " + test.get(2));
        while(result.next())
        {
            if(String.valueOf(result.getInt(1)).equals(info_u.get(2)) == true)
            {
                nom_seance = test.get(0);
                nom_prof = test.get(1);
                id_seance = test.get(2);
                id_groupe = info_u.get(2);
            }
        }
        result.close();
        stmt.close();
        
        info_groupe.add(nom_seance);
        info_groupe.add(nom_prof);
        info_groupe.add(id_seance);
        info_groupe.add(id_groupe);
        //System.out.println("semaine methode groupe 2 : " + week);
        //info_groupe [0] = nom seance
        //info_groupe [1] = nom prof
        //info_groupe [2] = id seance
        //info_groupe [3] = id_groupe
        
        return info_groupe;
    }
    
}
