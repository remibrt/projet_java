/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author remibreton
 */
public class edt extends JFrame {
    
    JFrame frame;
    JPanel panelSemaine, panelGrille, panelRecherche;
    JMenuBar Menu;
    JButton onglets,rechercher;
    JTextField ask;
    int semaine,selec = 1;
    ArrayList<String> info = new ArrayList();
    
    public edt(ArrayList<String> info_user)
    {   
        frame = new JFrame();
        frame.setSize(1200, 700);
        
        info.addAll(info_user);
        
        //termine le programme quand on quitte la fenetre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        Menu = new JMenuBar();
        
        panelGrille = new JPanel();
        panelSemaine = new JPanel();
        Menu = new JMenuBar();
        panelRecherche = new JPanel();
        
        semaine = 1;
        panelGrille = add_edt(semaine);
        panelSemaine = add_onglet_semaines(semaine);
        Menu = add_menu(semaine);
        
        frame.setJMenuBar(Menu);
        
        //Ajout de la grille de l'emploi du temps
        frame.add(panelGrille);
        
        //Ajout des différents onglets (boutons) pour les semaines
        frame.add(panelSemaine, BorderLayout.PAGE_START);
        frame.setVisible(true);
    }
    public JPanel recherche ()
    {
        JPanel search = new JPanel();
        String texte = "";
        ask = new JTextField("");
        rechercher = new JButton("RECHERCHER");
        ask.setPreferredSize(new Dimension(120,30));
        
        search.add(ask);
        search.add(rechercher);
        
        texte = ask.getText();
        
        
        return search;
    }
    public JMenuBar add_menu(int semaine)
    {
        JMenuBar me = new JMenuBar();
        JMenuItem item1 = new JMenuItem("EMPLOI DU TEMPS");
        JMenuItem item2 = new JMenuItem("RECHERCHER");
        item1.setIcon(new ImageIcon("images/logo_edt.png"));
        item2.setIcon(new ImageIcon("images/logo_recherche.jpg"));
        me.add(item1);
        me.add(item2);
        
        item2.addActionListener(new ActionListener()
        {
            @Override
                public void actionPerformed(ActionEvent e)
                {
                    frame.remove(panelGrille);   
                    frame.remove(panelSemaine);
                    
                    panelRecherche = recherche();
                    
                    panelRecherche.revalidate();
                    panelRecherche.repaint();
                    
                    frame.add(panelRecherche);
                    frame.setVisible(true);
                }
        });
        item1.addActionListener(new ActionListener()
        {
            @Override
                public void actionPerformed(ActionEvent e)
                {
                    frame.remove(panelRecherche);
                    panelGrille.revalidate();
                    panelSemaine.revalidate();
                    
                    panelGrille.repaint();
                    panelSemaine.repaint();
                    
                    System.out.println("clic");
                    frame.remove(panelRecherche);
                    
                    frame.add(panelGrille);
                    frame.add(panelSemaine, BorderLayout.PAGE_START);
                    
                    frame.setVisible(true);
                }
        });
        
        
        return me;
    }
    public JPanel add_edt(int semaine)
    {
        JPanel edt = new JPanel();
        GridLayout grille = new GridLayout(6, 8);
        grille.setHgap(2);
        grille.setVgap(30);
        
        edt.setLayout(grille);
        
        edt.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        
        JLabel jours = new JLabel("JOURS", SwingConstants.CENTER);
        jours.setVerticalAlignment(SwingConstants.BOTTOM);
        //Label des horaires de cours
        JLabel H1 = new JLabel("8h30 - 10h00", SwingConstants.CENTER);
        H1.setVerticalAlignment(SwingConstants.BOTTOM);
        JLabel H2 = new JLabel("10h15 - 11h45", SwingConstants.CENTER);
        H2.setVerticalAlignment(SwingConstants.BOTTOM);
        JLabel H3 = new JLabel("12h00 - 13h30", SwingConstants.CENTER);
        H3.setVerticalAlignment(SwingConstants.BOTTOM);
        JLabel H4 = new JLabel("13h45 - 15h15", SwingConstants.CENTER);
        H4.setVerticalAlignment(SwingConstants.BOTTOM);
        JLabel H5 = new JLabel("15h30 - 17h00", SwingConstants.CENTER);
        H5.setVerticalAlignment(SwingConstants.BOTTOM);
        JLabel H6 = new JLabel("17h15 - 18h45", SwingConstants.CENTER);
        H6.setVerticalAlignment(SwingConstants.BOTTOM);
        JLabel H7 = new JLabel("19h00 - 20h30", SwingConstants.CENTER);
        H7.setVerticalAlignment(SwingConstants.BOTTOM);
        
        //Ajout des labels sur le panel
        edt.add(jours);
        edt.add(H1);
        edt.add(H2);
        edt.add(H3);
        edt.add(H4);
        edt.add(H5);
        edt.add(H6);
        edt.add(H7);
        
        int j = 1;
        
        ArrayList <String> info_cours = new ArrayList();
        String message = "";
        
        for (int i = 1; i < 41; i++) {
            
            info_cours.clear();
            JButton boutonEDT;
            try
            {
                try
                {
                    if(info.get(1).equals("etudiant"))
                    {
                        Seance_Groupe sg = new Seance_Groupe(info); info_cours.addAll(sg.lire_seance_groupe(j,semaine));
                    }
                    
                    else if(info.get(1).equals("enseignant"))
                    {
                        Seance_enseignant se = new Seance_enseignant(info); info_cours.addAll(se.lire_seance_enseignant(j,semaine));
                    }
                    
                }
                catch (ClassNotFoundException cnfe)
                {
                    cnfe.printStackTrace();
                }
            }
            catch (SQLException e){e.printStackTrace();}
            
            int b = 0;
            switch(i)
            {
                case 1 : boutonEDT = new JButton("LUNDI");
                         break;
                case 9 : boutonEDT = new JButton("MARDI");
                         break;
                case 17 : boutonEDT = new JButton("MERCREDI");
                         break;
                case 25 : boutonEDT = new JButton("JEUDI");
                         break;
                case 33 : boutonEDT = new JButton("VENDREDI");
                         break;
                         
                default : //if(info_cours.get(3).equals("0") == false){message = "TD" + info_cours.get(3);}
                          boutonEDT = new JButton("<html>" + info_cours.get(0) +"<br>" + info_cours.get(1) + /*"<br>" + message + */"</html>");
                          boutonEDT.setBorder(BorderFactory.createLineBorder(new Color(255, 192, 192)));
                          b = 1;
                          j = j+1;
                          message = "";
                          break;
            }
            

            boutonEDT.setOpaque(true);
            if(b == 0)
            {
                boutonEDT.setBackground(new Color(132, 166, 218));
                boutonEDT.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
            }
            else
            {
                if(info_cours.get(0).equals("") == false)
                {
                    boutonEDT.setBackground(new Color(159, 56, 34));
                }
                boutonEDT.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,new Color(159, 56, 34)));
                
            }
            boutonEDT.setPreferredSize(new Dimension(200, 100));
            edt.add(boutonEDT);
        }
        
        //System.out.println("semaine methode edt2 : " + semaine);
        return(edt);
        
    }
    public JPanel add_onglet_semaines(int semaine)
    {
        JPanel semaines = new JPanel();
        semaines.setBackground(new Color(255, 224, 255));
        GridLayout layoutWeek = new GridLayout(1, 30);
        layoutWeek.setVgap(1);

        semaines.setLayout(layoutWeek);
        
        //Ajouts des boutons semaines
        for(int i = 1 ; i < 31 ; i++) {

            String sem = String.valueOf(i);
            this.onglets = new JButton("" +i);
            onglets.setActionCommand("" +sem);
            onglets.setPreferredSize(new Dimension(40, 30));
            onglets.setOpaque(true);
            
            if(semaine == i)
            {
                onglets.setBackground(Color.blue);
            }
            
            semaines.add(onglets);
            
            onglets.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {   
                    //On recupere le numero de la semaine que l'on vient de cliquer en string
                    String source = e.getActionCommand();
        
                    //On convertie ce numero en int pour pouvoir tout reconstruire
                    int semInt = Integer.parseInt(source);
                    
                    System.out.println("Clic sur semaine : " + semInt);
                    //On enleve le precedent edt
                    frame.remove(panelGrille);   
                    frame.remove(panelSemaine);
                    
                    //On affiche l'edt de la semaine cliqué
                    panelGrille = new JPanel();
                    panelSemaine = new JPanel();
                    
                    panelGrille = add_edt(semInt);
                    panelSemaine = add_onglet_semaines(semInt);
                    
                    panelGrille.revalidate();
                    panelSemaine.revalidate();
                    
                    panelGrille.repaint();
                    panelSemaine.repaint();
                    
                    frame.add(panelGrille);
                    frame.add(panelSemaine, BorderLayout.PAGE_START);
                    
                    frame.setVisible(true);
                }
            });
        
        }
        return semaines; 
    }
}