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
     ArrayList<Integer> liste_cours2 = new ArrayList<>();
     ArrayList<Integer> liste_seance = new ArrayList<>();
     ArrayList<String> liste_nom_seance = new ArrayList<>();
     ArrayList<String> liste_enseignants = new ArrayList<>();
     
      ArrayList<Integer> liste_semaine_seance = new ArrayList<>();
      ArrayList<Integer> liste_etat_seance = new ArrayList<>();
      ArrayList<String> tout = new ArrayList<>();
      
      String nom_enseignant = ""; 
      
   public Enseignant() throws SQLException, ClassNotFoundException{
       super();
   }
   
    public String Nom(int id) throws SQLException{
        stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM utilisateur WHERE id = '"+ id +"' ; ");
        while(result.next()){
            
          //  System.out.println(result.getInt(1)); // on affiche l'id du cours 
            nom_enseignant = result.getString(4);  
        }
        result.close();
        stmt.close();
        return nom_enseignant ;
   }
   
   public ArrayList AfficheTousNomEnseignant() throws SQLException{
        stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM utilisateur WHERE droit = enseignant");
        
        //System.out.println("\n La liste des enseignants est la suivante ");
        while(result.next()){
           liste_enseignants.add(result.getString(4));
        }
        result.close();
        stmt.close();
        return liste_enseignants;
   }
   
   public int ID(String nom) throws SQLException{
       stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM utilisateur WHERE nom = '"+ nom +"' ; ");
        while(result.next()){
          //  System.out.println(result.getInt(1)); // on affiche l'id de l'utilisateur 
            id = result.getInt(1);
        }
        result.close();
        stmt.close();
        return id ;
   }
   
    public ArrayList VoirTousLesCours(int id) throws SQLException{
        stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM cours, enseignant WHERE id = id_cours AND id_utilisateur = '"+ id +"' ; ");
        while(result.next()){
            //System.out.println(result.getString(2)); // on affiche le nom du cours 
            liste_cours.add(result.getInt(1)); // on crée une liste de tous les cours suivis par ce prof pr les garder en mémoire
        }
        result.close();
        stmt.close();
        return liste_cours;
    }
    
    
    public ArrayList VoirSeance_enseignant(int id) throws SQLException{
        stmt = conn.createStatement();
        // ResultSet result = stmt.executeQuery("SELECT * FROM seance_enseignants WHERE id_enseignant = '"+ id +"' ; ");
        ResultSet result = stmt.executeQuery("SELECT * FROM seance_enseignants WHERE id_enseignant = '"+id+"' ; ");
        while(result.next()){
          //  System.out.println(result.getInt(1)); // on affiche l'id de la seance
            liste_seance.add(result.getInt(1));
        }
        result.close();
        stmt.close();
        return liste_seance ;
        
    }
    
    public ArrayList VoirSeance(int id) throws SQLException{
        stmt = conn.createStatement();
        this.liste_seance = VoirSeance_enseignant(id);
              
           
            ResultSet res = stmt.executeQuery("SELECT * FROM `seance_enseignants` JOIN `seance` WHERE `id_seance` =  `id_s` AND `id_enseignant` = "+ id +" ");
                       
            while(res.next()){
                  
                   liste_cours2.add(res.getInt(7));
                  
            }

            res.close();
            stmt.close();
        return liste_cours2 ; 
    }
    
    
     public ArrayList VoirSemaineSeance() throws SQLException{
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM `seance_enseignants` JOIN `seance` WHERE `id_seance` =  `id_s` AND `id_enseignant` = "+ id +" ");
                       
            while(res.next()){
                  
                   liste_semaine_seance.add(res.getInt(4));
                   System.out.println(res.getInt(4));
                  
            }
            res.close();
            stmt.close();
        return liste_semaine_seance ; 
    }
     
      public ArrayList VoirEtatSeance() throws SQLException{
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM `seance_enseignants` JOIN `seance` WHERE `id_seance` =  `id_s` AND `id_enseignant` = "+ id +" ");
                       
            while(res.next()){
                  
                   liste_etat_seance.add(res.getInt(6));
                   System.out.println(res.getInt(6));
                  
            }
            res.close();
            stmt.close();
        return liste_etat_seance ; 
    }
     
     
    public ArrayList VoirNomSeance() throws SQLException{
        stmt = conn.createStatement();
        this.liste_cours2 = VoirSeance(id);
        for(int i=0; i< liste_cours2.size() ; i++ ){
            
           
            ResultSet res = stmt.executeQuery("SELECT * FROM `cours` WHERE id ='"+liste_cours2.get(i) +"' ; ");
            while(res.next()){
                //System.out.println(res.getString(2));
                liste_nom_seance.add(res.getString(2));
            }
            res.close();
        }
        stmt.close();
        return liste_nom_seance ; 
    }
    
    
    
    
   /*public void ToutVoir() throws SQLException, ClassNotFoundException{
       // fonction pour le professeur qui est connecté qui peut voir
       // uniquement ses cours donnés
      liste_seance = VoirSeance_enseignant(id);
      liste_nom_seance = VoirNomSeance();
      liste_etat_seance = VoirEtatSeance();
      liste_semaine_seance = VoirSemaineSeance();
      nom_enseignant = Nom(id);
      
      System.out.println("Pour le professeur" + nom_enseignant);
      for(int i =0 ; i< liste_nom_seance.size() ; i++){
          
        System.out.println("\n");
        
          
          System.out.println("Le nom du cours donnée est " +liste_nom_seance.get(i));
          System.out.println("L'état de la seance est " + liste_etat_seance.get(i));
          System.out.println("La seance se déroulera la semaine :" +liste_semaine_seance.get(i));
      }
      
   }
    
    public void ToutVoir(String nom_recherche) throws SQLException{
       
      id = ID(nom_recherche);
      nom_enseignant = Nom(id);
      liste_nom_seance = VoirNomSeance();
      liste_etat_seance = VoirEtatSeance();
      liste_semaine_seance = VoirSemaineSeance();
      
      System.out.println("Pour le professeur " + nom_enseignant );
      
      for(int i =0 ; i< liste_nom_seance.size() ; i++){
          
        System.out.println("\n");
        
          
          System.out.println("Le nom du cours donnée est " +liste_nom_seance.get(i));
          System.out.println("L'état de la seance est " + liste_etat_seance.get(i));
          System.out.println("La seance se déroulera la semaine :" +liste_semaine_seance.get(i));
          
          
      }
      
   }
    */
    public void AjoutCours(String nom) throws SQLException{
        stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO `cours`( `nom`) VALUES ('" + nom +"') ; ");
                stmt.close();
    }
    
    
    
    public void AjouterSeance(int id_cours, int date, int heure_debut, int heure_fin, int etat, int semaine) throws SQLException{
        stmt = conn.createStatement();
      //  ResultSet result = stmt.executeQuery("INSERT INTO seance (date,heure_debut, heure_fin, etat) VALUES (50,4,5,7);");
        stmt.executeUpdate("INSERT INTO `seance`( `semaine`, `date`, `heure_debut`, `heure_fin`, `etat`, `#id_cours`, `#id_type`) VALUES ("+ semaine +" ,"+ date +","+ heure_debut +","+ heure_fin +","+ etat +",0,9)  ");
        stmt.close();
    }
}
