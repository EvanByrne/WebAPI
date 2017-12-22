/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Evan Byrne
 */
@Entity
@XmlRootElement
public class Account implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int accountId;
    private String accountname;
    private int accountNumber;
    private int currentbalance;
    private int sortcode;    
    @OneToMany
    private Collection<Transactions> transaction = new ArrayList<Transactions>();
    
    public Collection<Transactions> getTransaction() {
        return transaction;
    }

    public void setTransaction(Collection<Transactions> transaction) {
        this.transaction = transaction;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCurrentbalance(int currentbalance) {
        this.currentbalance = currentbalance;
    }

    public void setSortcode(int sortcode) {
        this.sortcode = sortcode;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getCurrentbalance() {
        return currentbalance;
    }

    public int getSortcode() {
        return sortcode;
    }
      
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    Object getAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    
}
