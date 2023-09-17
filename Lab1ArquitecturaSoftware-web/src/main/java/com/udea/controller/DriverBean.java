/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.controller;

import com.udea.ejb.DriversFacadeLocal;
import javax.ejb.EJB;

/**
 *
 * @author jose_waldo
 */
public class DriverBean {
    
    @EJB
    private com.udea.ejb.DriversFacadeLocal driversFacade;
    
    private String name;
    private String lastName;
    private String nIdentification;
    private String email;
    private String phone;
    
    public DriverBean() {   
    }

    public DriversFacadeLocal getDriversFacade() {
        return driversFacade;
    }

    public void setDriversFacade(DriversFacadeLocal driversFacade) {
        this.driversFacade = driversFacade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getnIdentification() {
        return nIdentification;
    }

    public void setnIdentification(String nIdentification) {
        this.nIdentification = nIdentification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
}
