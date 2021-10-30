/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

/**
 *
 * @author alumne
 */
public class User {
    private String id_usuario;
    private String password; 

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   
      
    public User(String id_usuario, String password) {
        this.id_usuario = id_usuario;
        this.password = password;
    }
    
    public User(){
    }
}
