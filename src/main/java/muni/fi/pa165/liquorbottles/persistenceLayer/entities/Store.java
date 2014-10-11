package muni.fi.pa165.liquorbottles.persistenceLayer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
@Entity
@PrimaryKeyJoinColumn(name = "store_id", referencedColumnName = "user_id")
public class Store extends User {

    @Column
    private String name;

    @Column
    private String address;
    
    public Store(){
        
    }

    public Store(String name, String address, String username, String password) {
        this.username = username;
        this.password = password;
        
        this.name = name;
        this.address = address;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
