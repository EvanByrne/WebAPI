/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Evan Byrne
 */
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{id}/accounts")
public class AccountService {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank");
    private final EntityManager em = emf.createEntityManager();
    private final EntityTransaction tx = em.getTransaction();

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> getTransactions() {
        return allEntries();
    }

    public List<Account> allEntries() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> rootEntry = cq.from(Account.class);
        CriteriaQuery<Account> all = cq.select(rootEntry);
        TypedQuery<Account> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{accontId}")
    public Account getAccount(@PathParam("accountId") int accountId) {
        Account test = em.find(Account.class, accountId);
        em.close();
        return test;
    }

    @POST
    @Path("/save")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Account save(Account a) {
        Account test = em.find(Account.class, a.getAccountId());
        if (test == null) {
            Transactions t1 = new Transactions();
            Transactions t2 = new Transactions();
            Transactions t3 = new Transactions();
            Transactions t4 = new Transactions();
            Transactions t5 = new Transactions();
            Transactions t6 = new Transactions();
            Transactions t7 = new Transactions();
            Transactions t8 = new Transactions();

            if (a.getAccountname().equalsIgnoreCase("Current Account")) {
                t1.setTransactionName("Food");
                t2.setTransactionName("Online");
                t3.setPostBalance(2299);
                t4.setPostBalance(1229);

                a.getTransaction().add(t5);
                a.getTransaction().add(t6);

            }

            if (a.getAccountname().equalsIgnoreCase("Savings Account")) {
                t5.setTransactionName("Pay");
                t6.setTransactionName("Winnings");
                t7.setPostBalance(3229);
                t8.setPostBalance(3529);
            }

            a.getTransaction().add(t5);
            a.getTransaction().add(t6);

            tx.begin();
            em.persist(a);
            em.persist(t1);
            em.persist(t2);
            em.persist(t3);
            em.persist(t4);
            em.persist(t5);
            em.persist(t6);
            em.persist(t7);
            em.persist(t8);
            tx.commit();
            em.close();
        }
        return a;
    }

    @DELETE
    @Path("/{accountId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void deleteItem(@PathParam("accountId") int accountId) {
        Account test = em.find(Account.class, accountId);
        if (test != null) {
            tx.begin();
            em.remove(test);
            tx.commit();
            em.close();
        }
    }
    
    @POST
    @Path("/{acc_id}/{amount}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String lodgement(@PathParam("acc_id")int accId, @PathParam("amount")  double amount){
        
        Account acc = em.find(Account.class, accId);
        if(acc != null){
            //create a transaction
            tx.begin();
            Transactions trans = new Transactions();
            //add the amount new post balance presist the trans
            Date date = new Date();
            trans.setTdate(date.toString());
            double postBal = acc.getCurrentbalance() + amount;
            trans.setPostBalance(postBal);
         
            
            acc.setCurrentbalance(postBal);
            acc.addTransaction(trans);
            em.persist(trans);
            em.persist(acc);
            tx.commit();
            em.refresh(acc);
            em.close();
            emf.close();
            
            
            //from the account add the amount to the balance and presist the account 
        }
        
        return "";
    }
    
}
