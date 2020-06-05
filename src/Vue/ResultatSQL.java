/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Connexion;
import java.sql.SQLException;
import Controleur.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author elyes
 */
public class ResultatSQL {
    
    public Connexion maconnexion ; 
    
    public Statement stmt ;
    public ResultSet rset;
    private ResultSetMetaData rsetMeta;
    
   public ResultatSQL(){
      
}
}