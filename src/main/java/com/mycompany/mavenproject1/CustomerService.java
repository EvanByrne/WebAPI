package com.mycompany.mavenproject1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerService {
    
        private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank");
        private final EntityManager em = emf.createEntityManager();
        private final EntityTransaction tx = em.getTransaction(); 


    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> getCustomers() {
       return allEntries();
    }
    
     public List<Customer> allEntries() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
     
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("{id}")
    public Customer getCustomer(@PathParam("id") int id) {
        Customer test = em.find(Customer.class, id);
        em.close();
        return test;
    }
    
    @POST
    @Path("/save")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Customer save(Customer c) {
        Customer test = em.find(Customer.class, c.getId());
        if (test == null) {

            
            Account a1 = new Account();
            a1.setAccountname("Current Account");
            
            Transactions t1 = new Transactions();
            t1.setTransactionName("Chicken");
            t1.setPostBalance(5550);
            Transactions t2 = new Transactions();
            t2.setTransactionName("Payment");
            t2.setPostBalance(6550);
      
            
            a1.getTransaction().add(t1);
            a1.getTransaction().add(t2);



            Account a2 = new Account();
            a2.setAccountname("Savings Account");

            Transactions p3 = new Transactions();
            p3.setTransactionName("Savings");            
            p3.setPostBalance(300);
            Transactions p4 = new Transactions();
            p4.setTransactionName("Holiday");           
            p4.setPostBalance(2300);



            a2.getTransaction().add(p3);
            a2.getTransaction().add(p4);  

            
            c.getAccount().add(a1);
            c.getAccount().add(a2);

            
            tx.begin();
            em.persist(c);
            em.persist(a1);
            em.persist(a2);
            em.persist(t1);
            em.persist(t2);
            em.persist(p3);
            em.persist(p4);
            tx.commit();
            em.close();
        }
        return c;   
    }
    
        
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void deleteItem(@PathParam("id") int id) {
        Customer test = em.find(Customer.class, id);
        if (test !=null) {
            tx.begin();
            em.remove(test);
            tx.commit();
            em.close();
            emf.close();
        }
    }
    @PUT
    @Path("/{cust_id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public String updateCustomer(@PathParam("cust_id") int custId, Customer c){
        Customer customer = em.find(Customer.class, custId);
        if(customer != null){
          tx.begin();
          customer.setName(c.getName());
          customer.setEmail(c.getEmail());
          customer.setAddress(c.getAddress());
          em.persist(customer);
          tx.commit();
          em.close();
          emf.close();
          return "Updated";
        }
        return "Customer not found";
    }
}