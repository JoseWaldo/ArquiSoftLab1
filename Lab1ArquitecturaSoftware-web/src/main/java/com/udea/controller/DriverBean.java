/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.controller;

import com.udea.ejb.DriversFacadeLocal;
import com.udea.models.Drivers;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private List<Drivers> driversList;

    public DriverBean() {
        this.driversList = new ArrayList<>();
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
        FacesContext context = FacesContext.getCurrentInstance();
        
        /*
        if (!this.driversFacade.esValidaCedula(this.identification)) {
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cédula solo debe tener números"));
            return null;
        }

        if (this.driversFacade.existeCedula(this.identification)) {
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cédula ya existe"));
            return null;
        }

        if (!this.driversFacade.esValidoCorreo(this.email)) {
            System.out.println("");
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No es un correo válido"));
            return null;
        }

        if (this.driversFacade.existeCorreo(this.email)) {
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El correo ya existe"));
            return null;
        }
        */
        
        if (!this.esValidaCedula()) {
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cédula solo debe tener números", ""));
            return null;
        }

        if (this.existeCedula()) {
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cédula ya existe", "La cédula ya existe"));
            return null;
        }

        if (!this.esValidoCorreo()) {
            System.out.println("");
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es un correo válido", "No es un correo válido"));
            return null;
        }

        if (this.existeCorreo()) {
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo ya existe", "El correo ya existe"));
            return null;
        }
        
        Drivers conductor = new Drivers();
        conductor.setEmail(this.email);
        conductor.setName(this.name);
        conductor.setLastName(this.lastName);
        conductor.setNIdentification(this.identification);
        conductor.setPhone(this.phone);

        System.out.println(conductor.toString());
        // Drivers conductorAuxiliar = this.driversFacade.find("14");
        this.driversFacade.create(conductor);
        this.driversList.add(conductor);
        return "successfully";
    }

    private boolean existeCedula() {
        if (!this.getDriversList().isEmpty()) {
            for (Drivers conductor : this.getDriversList()) {
                if (conductor.getNIdentification().equals(this.identification)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean existeCorreo() {
        if (!this.getDriversList().isEmpty()) {
            for (Drivers conductor : this.getDriversList()) {
                if (conductor.getEmail().equals(this.email)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean esValidoCorreo() {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.email);
        return matcher.matches();
    }

    private boolean esValidaCedula() {
        String regex = "^[0-9]{1,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.identification);
        return matcher.matches();
    }

}
