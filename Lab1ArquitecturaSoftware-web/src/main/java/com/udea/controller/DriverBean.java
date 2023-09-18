/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.controller;

import com.udea.ejb.DriversFacadeLocal;
import com.udea.models.Drivers;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author jose_waldo
 */
public class DriverBean {
    
    @EJB
    private com.udea.ejb.DriversFacadeLocal driversFacade;
    
    private UIComponent myButton;
    
    private String name;
    private String lastName;
    private String identification;
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
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
    
    public List<Drivers> driversList;

    public UIComponent getMyButton() {
        return myButton;
    }

    public void setMyButton(UIComponent myButton) {
        this.myButton = myButton;
    }

    public List<Drivers> getDriversList() {
        if (this.driversList == null || this.driversList.isEmpty()) {
            this.driversList = this.driversFacade.findAll();
        }

        return driversList;
    }

    public void setDriversList(List<Drivers> driversList) {
        this.driversList = driversList;
    }
    
    public String saveDriver() {
     System.out.println("Entro en saveDriver  a a a a a a  a a a a a a a aa a a a a a a a a a a a a a a a a a a a a  aa  aa a a a  aa a  a aa ");
        if(this.verificarCedula()){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cédula ya existe"));
            return null;

        }
        Drivers conductor = new Drivers();
        conductor.setEmail(email);
        conductor.setName(name);
        conductor.setLastName(lastName);
        conductor.setNIdentification(identification);
        conductor.setPhone(phone);
        
        System.out.println(conductor.toString());
        Drivers conductorAuxiliar = this.driversFacade.find("14");
        System.out.println(conductorAuxiliar.toString());
        this.driversFacade.create(conductor);
        this.driversList.add(conductor);
        return "successfully";
    }
    
    public boolean verificarCedula(){
        System.out.println("entro a verificarcedula b b b b b b b b b b b  bb b b b b b b b b b b b b b b b b b b b b b b b b b b b b b b b b b  ");
        for(Drivers conductor: this.getDriversList()){
            System.out.println("Entro en el for de verificarcedula cc c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c c   aa a  a aa ");
            if (conductor.getNIdentification() == this.identification){
                
                return true;
            } 
        }
        return false;
    }
    
}
