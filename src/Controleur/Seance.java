/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;
import java.sql.*;
import java.util.*;
/**
 *
 * @author remibreton
 */
public class Seance extends Utilisateur{
    
    public Seance() throws SQLException, ClassNotFoundException{
       super();
   }
    ArrayList<String> info_seance = new ArrayList();
    
    public ArrayList lire_seances(int creneau, int week) throws SQLException
    {
        //On supprime les data précedente pour pouvoir stocker les nouvelles
        info_seance.clear();
        
        ResultSet result1,result2;
        
        //Variable qui stockent temporairement les données de la séance
        String nom_seance = "";
        int id_cours = 0;
        String nom_prof = "";
        
        //on recupère le nom, et l'id du cours de la seance
        result1 = stmt.executeQuery("SELECT nom_cours,id_cours FROM seance WHERE creneau = " + creneau + "AND semaine = " + week);
        while(result1.next())
        {
            nom_seance = result1.getString(1);
            id_cours = result1.getInt(2);
        }
        
        //Grace à l'id cours recup au dessus, on recupere le nom de l'enseignant assigné à ce cours
        result2 = stmt.executeQuery("SELECT nom FROM enseignant WHERE id_cours = " + id_cours);
        while(result2.next())
        {
            nom_prof = result2.getString(1);
        }
        
        //On convertie la valeur int de semaine recup dans la bdd en string pour pouvoir la stocker dans la liste de String
        
        //Ajout des data à la liste d'info pour la séance
        info_seance.add(nom_seance);
        info_seance.add(nom_prof);
        
        return info_seance;
    }
}
