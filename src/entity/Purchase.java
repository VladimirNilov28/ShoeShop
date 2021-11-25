/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ArTIK
 */
@Entity
public class Purchase implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Shoe shoe;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseShoe;
    private int generalMoney;

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPurchaseShoe() {
        return purchaseShoe;
    }

    public void setPurchaseShoe(Date purchaseShoe) {
        this.purchaseShoe = purchaseShoe;
    }

    public double getGeneralMoney() {
        return generalMoney;
    }

    public void setGeneralMoney(int generalMoney) {
        this.generalMoney = generalMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
