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
 * @author elyes
 */
public class Enseignant extends Utilisateur {
    
     ArrayList<Integer> liste_cours = new ArrayList<>();
   public Enseignant() throws SQLException, ClassNotFoundException{
       super();
   }
   
    public void VoirTousLesCours() throws SQLException{
        
        ResultSet result = stmt.executeQuery("SELECT * FROM cours, enseignant WHERE id = id_cours AND id_utilisateur = '"+ id +"' ; ");
        while(result.next()){
            System.out.println(result.getString(2)); // on affiche le nom du cours
            this.liste_cours.add(result.getInt(1)); // on crée une liste de tous les cours suivis par ce prof pr les garder en mémoire
            
        }
    }
    
    public void AjoutCours(String nom) throws SQLException{
                stmt.executeUpdate("INSERT INTO `cours`( `nom`) VALUES ('" + nom +"') ; ");

    }
    
    public void AjouterSeance(int id_cours, int date, int heure_debut, int heure_fin, int etat, int semaine) throws SQLException{
      //  ResultSet result = stmt.executeQuery("INSERT INTO seance (date,heure_debut, heure_fin, etat) VALUES (50,4,5,7);");
        stmt.executeUpdate("INSERT INTO `seance`( `semaine`, `date`, `heure_debut`, `heure_fin`, `etat`, `#id_cours`, `#id_type`) VALUES ("+ semaine +" ,"+ date +","+ heure_debut +","+ heure_fin +","+ etat +",0,9)  ");
    }
}
