/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SGBD_Request_Package;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Guillaume
 */
public class Mariadb_Request {
    
    static String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static String DB_URL = "jdbc:mariadb://10.1.129.140:3306/bdd_projet_java";
    
    static String USER = "utilisateur";
    static String PWD = "motdepasse";
    
    // Chargement du driver JDBC.
    public static void LoadDriver(){
        try {
            Class.forName(JDBC_DRIVER);  
        }catch (ClassNotFoundException e){
            Error_Message("Erreur lors du chargement du driver MariaDb");
        }    
    }
    
    // Méthode permettant d'afficher un message en cas d'erreur.
    private static void Error_Message(String message) {
        System.err.println(message);
        System.exit(99);
    }
    
    // Méthode permettant l'ajout de données dans la base.
    public static void Add_Data(String p_nom, String p_prenom, String p_email, 
                                String p_date, String p_service){
        Connection conn = null;
        PreparedStatement addData = null;
                    
        try {
            
            // Connexion à la base de données
            try {
                DriverManager.setLoginTimeout(5); // Temps limite de connexion
                conn = DriverManager.getConnection(DB_URL, USER, PWD);
            }           
            catch (Exception e) {
                // Si pas de connexion, renvoi d'une liste vide.
                System.err.println("Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur.");
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur. L'application s'éxecute en mode \"hors-ligne\"." , 
                                                "Erreur de connexion à la base", JOptionPane.INFORMATION_MESSAGE);
            }
            
           // Préparation de la requête sql. 
            String sql = "INSERT INTO Employee (Nom, Prenom, Email, Date_de_Naissance, Service) "
                       + "VALUES (?,?,?,?,?)";
                      
            addData = conn.prepareStatement(sql);
            
            addData.setString(1, p_nom);
            addData.setString(2, p_prenom);
            addData.setString(3, p_email);
            addData.setString(4, p_date);
            addData.setString(5, p_service);
            
            // Execution de la requête.
            addData.execute();
            
        } catch (SQLException e) {
            System.out.println("SQLException");
            do {
              System.out.println("SQLState : " + e.getSQLState());
              System.out.println("Description :  " + e.getMessage());
              System.out.println("code erreur :   " + e.getErrorCode());
              System.out.println("");
              e = e.getNextException();
            } while (e != null);
            System.exit(99);
            
        } catch (Exception e){
             e.printStackTrace();
        } finally {
            
            try {
                
                if (addData != null) {
                    conn.close();
                }
                
            } catch (SQLException e){
                
                e.printStackTrace();
            }
        }      
    }  
    
    // Méthode permettant d'obtenir les données de tous les salariés.
    public static List<String[]> Get_Data() {
        List<String[]> dataList = new ArrayList<String[]>();   
        Connection conn = null;
        Statement getData = null;
        
        try {
                  
            String Nom;
            String Prenom;
            String Email;
            String DateDeNaissance;
            String Service;
            
            // Connexion à la base de données
            try {
                DriverManager.setLoginTimeout(5);
                conn = DriverManager.getConnection(DB_URL, USER, PWD); 
                getData = conn.createStatement();
            } catch (Exception e) {
                // Si pas de connexion, renvoi d'une liste vide.
                System.err.println("Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur.");
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur. L'application s'éxecute en mode \"hors-ligne\"." , 
                                                "Erreur de connexion à la base", JOptionPane.INFORMATION_MESSAGE);
                dataList.clear();
                return dataList;
            }
           
            // Execution de la requête.
            ResultSet resultats = getData.executeQuery("SELECT Nom, Prenom, Email, Date_de_Naissance, Service FROM Employee");
                        
            // Mise en forme du résultat pour utilisation par l'interface utilisateur.
            while(resultats.next()){
                Nom = resultats.getString("Nom");
                Prenom = resultats.getString("Prenom");
                Email = resultats.getString("Email");
                DateDeNaissance = resultats.getString("Date_de_Naissance");
                Service = resultats.getString("Service");
                      
                String dataArray[] = {Nom,Prenom,Email,DateDeNaissance,Service};
                
                dataList.add(dataArray);
            }
                     
            resultats.close();
            
        } catch (SQLException e) {
            System.out.println("SQLException");
            do {
              System.out.println("SQLState : " + e.getSQLState());
              System.out.println("Description :  " + e.getMessage());
              System.out.println("code erreur :   " + e.getErrorCode());
              System.out.println("");
              e = e.getNextException();
            } while (e != null);
            System.exit(99);           
            
        } catch (Exception e) {
            
            e.printStackTrace();
            System.exit(99);
            
        } finally {
            
            try {
                
                if (getData != null) {
                    conn.close();
                }
                
            } catch (SQLException e){
                
                e.printStackTrace();
            }
        } 
        
        return dataList;
    }
    
    // Méthode permettant d'obtenir les noms de tous les salariés.
    public static List<String[]> Get_Name(){
        List<String[]> dataList = new ArrayList<String[]>(); 
        Connection conn = null;
        Statement getName = null;       
        
        try {
                  
            String Nom;
            String Prenom;
            
            // Connexion à la base de données
            try{
                DriverManager.setLoginTimeout(5);
                conn = DriverManager.getConnection(DB_URL, USER, PWD); 
                getName = conn.createStatement();
            }
            catch (Exception e){
                System.err.println("Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur.");
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur. L'application s'éxecute en mode \"hors-ligne\"." , 
                                                "Erreur de connexion à la base", JOptionPane.INFORMATION_MESSAGE);
                dataList.clear();
                return dataList;
            }
           
            // Execution de la requête.
            ResultSet resultats = getName.executeQuery("SELECT Nom, Prenom FROM Employee");
                                   
            // Mise en forme du résultat pour utilisation par l'interface utilisateur.
            while(resultats.next()){
                Nom = resultats.getString("Nom");
                Prenom = resultats.getString("Prenom");  
                
                String dataArray[] = {Nom,Prenom};
                
                dataList.add(dataArray);
            }
                  
            resultats.close();
            
        } catch (SQLException e) {
            System.out.println("SQLException");
            do {
              System.out.println("SQLState : " + e.getSQLState());
              System.out.println("Description :  " + e.getMessage());
              System.out.println("code erreur :   " + e.getErrorCode());
              System.out.println("");
              e = e.getNextException();
            } while (e != null);
            System.exit(99);
            
            dataList = null;
            
        } catch (Exception e) {

            e.printStackTrace();
            System.exit(99);
            
        } finally {
            
            try {
                
                if (getName != null) {
                    conn.close();
                }
                
            } catch (SQLException e){
                
                e.printStackTrace();
            }
        } 
        
        return dataList;
    }
    
    // Méthode permettant d'obtenir les infos d'un salarié.
    public static String[] Get_User(String User){
        String[] userData = new String[5];
        Connection conn = null;
        PreparedStatement getAllDataFrUser = null;
        
        try {

            String separateur = " ";      
            String[] splitUser = User.split(separateur);

            // Connexion à la base de données
            try {
                DriverManager.setLoginTimeout(5);
                conn = DriverManager.getConnection(DB_URL, USER, PWD);
            }           
            catch (Exception e) {
                // Si pas de connexion, renvoi d'une liste vide.
                userData = null;
                System.err.println("Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur.");
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur. L'application s'éxecute en mode \"hors-ligne\"." , 
                                                "Erreur de connexion à la base", JOptionPane.INFORMATION_MESSAGE);
                return userData;
            }
           
            // Préparation de la requête sql. 
            String sql = "SELECT Nom, Prenom, Email, Date_de_Naissance, Service FROM Employee WHERE Nom = ? AND Prenom = ?";
            getAllDataFrUser = conn.prepareStatement(sql);       
            getAllDataFrUser.setString(1, splitUser[0]);
            getAllDataFrUser.setString(2, splitUser[1]);
            
            // Execution de la requête.
            ResultSet resultats = getAllDataFrUser.executeQuery();
                                   
            // Mise en forme du résultat pour utilisation par l'interface utilisateur.
            while(resultats.next()){
                userData[0] = resultats.getString("Nom");
                userData[1] = resultats.getString("Prenom");
                userData[2] = resultats.getString("Email");
                userData[3] = resultats.getString("Date_de_Naissance");
                userData[4] = resultats.getString("Service");
            }
                     
            resultats.close();
            
        } catch (SQLException e) {
            System.out.println("SQLException");
            do {
              System.out.println("SQLState : " + e.getSQLState());
              System.out.println("Description :  " + e.getMessage());
              System.out.println("code erreur :   " + e.getErrorCode());
              System.out.println("");
              e = e.getNextException();
            } while (e != null);
            System.exit(99);
            
        } catch (Exception e) {
            
            e.printStackTrace();
            System.exit(99);
            
        } finally {
            
            try {
                
                if (getAllDataFrUser != null) {
                    conn.close();
                }
                
            } catch (SQLException e){
                
                e.printStackTrace();
            }
        } 
        
        return userData;
    }
    
    // Méthode permettant la suppression de données dans la base. 
    public static void Delete_Data(String employeeName){
     
        String separateur = " ";      
        String[] splitName = employeeName.split(separateur);
        
        Connection conn = null;
        PreparedStatement delData = null;
        
        try {
           
            // Connexion à la base de données
            try {
                DriverManager.setLoginTimeout(5);
                conn = DriverManager.getConnection(DB_URL, USER, PWD);
            }           
            catch (Exception e) {
                // Si pas de connexion, renvoi d'une liste vide.
                System.err.println("Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur.");
                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données, assurez vous d'être connecté sur le réseau du serveur. L'application s'éxecute en mode \"hors-ligne\"." , 
                                                "Erreur de connexion à la base", JOptionPane.INFORMATION_MESSAGE);
            }
            
            // Préparation de la requête sql. 
            String sql = "DELETE FROM Employee WHERE Nom = ? AND Prenom = ? ";         
            delData = conn.prepareStatement(sql);           
            delData.setString(1, splitName[0]);
            delData.setString(2, splitName[1]);     
            
            // Execution de la requête.
            delData.execute();
            
        } catch (SQLException e) {
            System.out.println("SQLException");
            do {
              System.out.println("SQLState : " + e.getSQLState());
              System.out.println("Description :  " + e.getMessage());
              System.out.println("code erreur :   " + e.getErrorCode());
              System.out.println("");
              e = e.getNextException();
            } while (e != null);
            System.exit(99);
            
        } catch (Exception e) {
            
            e.printStackTrace();
            System.exit(99);
            
        } finally {
            
            try {
                
                if (delData != null) {
                    conn.close();
                }
                
            } catch (SQLException e){
                
                e.printStackTrace();
            }
        }           
    }
}