/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;


import javax.swing.* ;
import Controleur.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author elyes
 */
public class Login extends JFrame implements ActionListener {
    
    JFrame frame;
    private Connexion maconnexion ;
    private Utilisateur user;
    private JPanel panneau,elements;
    private JLabel accueil,email,pass;
    private JTextField mail;
    private JPasswordField mdp;
    private JButton b;
    int access = 0;
    ArrayList<String> info_user = new ArrayList();
    
    public  Login()
    {
        // creation par heritage de la fenetre 
        super("Connexion à l'emploi du temps "); 
        
         //mise en page (layout) de la fenetre visible
        frame = new JFrame();
        frame.setSize(1200, 700);
        
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creation des éléments
        panneau = new JPanel();
        panneau = add_elements();
        
        frame.add(panneau);
        frame.setVisible(true);
    }
  
    public JPanel add_elements()
        {
            
        //Ajout des éléments dans la fenetre 
        elements = new JPanel();
        elements.setLayout(null);
        accueil = new JLabel("BIENVENUE - CONNECTEZ VOUS POUR ACCEDEZ A VOTRE EMPLOI DU TEMPS");
        email = new JLabel("adresse mail : ");
        pass = new JLabel("Mot de passe : ");
        mail = new JTextField("");
        mdp = new JPasswordField("");
        b = new JButton("Connexion");
        
        //Positionnement et taille des éléments
        email.setBounds(new Rectangle(500,300,140,40));
        mail.setBounds(new Rectangle(590,300,140,40));
        
        pass.setBounds(new Rectangle(740,300,140,40));
        mdp.setBounds(new Rectangle(830,300,140,40));
        
        b.setBounds(new Rectangle(980,300,100,40));
        accueil.setBounds(new Rectangle(500,50,600,20));
        
        // CREATION DES CHAMPS 
        elements.add( accueil);
        elements.add(email);
        elements.add(mail);
        elements.add(pass);
        elements.add(mdp);
        elements.add(b);
        //Ajout des LISTENERS
        b.addActionListener(this);
        
        // Coneexion à la bdd        
            try {
                try {
                   // tentative de connexion si les 4 attributs sont remplis
                   maconnexion = new Connexion("projet_java", "root", "root");
                   System.out.println("Connexion à la bdd réussi");
                   
                } 
                catch (ClassNotFoundException cnfe) {
                    System.out.println("Connexion echouee : probleme de classe");
                    cnfe.printStackTrace();
                }
            } 
            
            catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }
        return elements;
    }
    
    /**
     *
     * Pour gerer les actions sur les boutons on utilise la fonction
     * actionPerformed
     *
     * @param evt
     */@Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent evt){
         info_user.clear();
         
         Object bouton = evt.getSource();  // on recupere la saisie du bouton
         if(bouton == b)
         { // si c'est le bouton sur lequel on a cliquer 
            String mailSelectionnee = mail.getText();
            String mdpSelectionnee = mdp.getText(); // on recupère les saisies
            
            try
            {
                try
                {
                    user = new Utilisateur();
                    access = user.Acces_co(mailSelectionnee, mdpSelectionnee);
                    if(access == 0)
                    {
                        System.out.println("Connexion echouee : probleme d'identifiant ou mdp");
                    }
                    else if(access == 1)
                    {
                        System.out.println("Connexion réussi");
                        info_user.add(String.valueOf(user.id)); //info_user [0]
                        info_user.add(user.getDroit()); //info_user [1]
                        info_user.add(String.valueOf(user.getGroupe())); //info_user [2]
                        
                        frame.dispose();
                    }
                    else
                    {
                        System.out.println("Connexion echouee : mot de passe incorrect");
                        System.out.println("access = "+ access);
                    }
                        
                }
                catch (ClassNotFoundException cnfe) {
                    System.out.println("Connexion echouee : probleme de classe");
                    cnfe.printStackTrace();
                }
            }
            
            catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }
        } 
        else 
        {
             System.out.println("erreur");
        }
        edt e = new edt(info_user);
    }
    
    public int getConnexion()
    {
        return access;
    }

}
