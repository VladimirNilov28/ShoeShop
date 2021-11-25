/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ArTIK
 */
@Entity
public class Shoe implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shoeFirm;    
    private String shoeName;
    private int cost;
    private int quntity;
    private int count;

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }


    public String getShoeFirm() {
        return shoeFirm;
    }

    public void setShoeFirm(String shoeFirm) {
        this.shoeFirm = shoeFirm;
    }
    
    @Override
    public String toString() {
        return "Ботинки: " + shoeFirm +" "+ shoeName + ", цена: " + cost + " евро, " + count + " шт." + ' ';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
