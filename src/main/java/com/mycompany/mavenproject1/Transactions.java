/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.Serializable;
import java.util.Date;// changed to int 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Evan Byrne
 */
@Entity
@XmlRootElement
public class Transactions implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int transactionId;
    private String transactionName;
    private int postBalance;
    private int tDate;

    public int getPostBalance() {
        return postBalance;
    }

    public int getTDate() {
        return tDate;
    }

    public void setPostBalance(int postBalance) {
        this.postBalance = postBalance;
    }

    public void setTdate(int tDate) {
        this.tDate = tDate;
    }
    

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }


    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
}
