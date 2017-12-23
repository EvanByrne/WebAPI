package com.mycompany.mavenproject1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
// @Table 
@XmlRootElement
public class Customer implements Serializable {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int id;
    private String name;
    private String email;
    private String address;
    
    @OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER,  mappedBy = "customer")
    private Collection<Account> account;

    public Customer() {
         account = new ArrayList<Account>();
    }
    
    
    public Collection<Account> getAccount() {
        return account;
    }

    public void setAccount(Collection<Account> account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addAccount(Account acc){
        account.add(acc);
    }
}
