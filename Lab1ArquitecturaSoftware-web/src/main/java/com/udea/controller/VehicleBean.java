/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.controller;

import com.udea.ejb.VehiclesFacadeLocal;
import com.udea.models.Drivers;
import com.udea.models.Vehicles;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
public class VehicleBean {

    @EJB
    private com.udea.ejb.VehiclesFacadeLocal vehiclesFacade;

    public UIComponent myButton;

    private String placa;
    private String name;
    private String model;
    private String color;
    private String driverId;
    private List<Vehicles> vehiclesList;

    /* --------------------- INTERNACIONALIZACIÓN --------------------- */
    private final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {
        return this.locale;
    }

    public String getLanguaje() {
        return this.locale.getLanguage();
    }

    public void changeLanguaje(String languaje) {
        System.out.println("Changing language to: " + languaje);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(languaje));
    }

    /* --------------------------------------------------------------- */
    public VehicleBean() {
        this.vehiclesList = new ArrayList<>();
    }

    public List<Vehicles> getVehicles() {
        if (this.vehiclesList == null || this.vehiclesList.isEmpty()) {
            refresh();
        }
        return this.vehiclesList;
    }

    public void refresh() {
        this.vehiclesList = vehiclesFacade.getAllVehicles();
    }

    public VehiclesFacadeLocal getVehiclesFacade() {
        return vehiclesFacade;
    }

    public void setVehiclesFacade(VehiclesFacadeLocal vehiclesFacade) {
        this.vehiclesFacade = vehiclesFacade;
    }

    public UIComponent getMyButton() {
        return myButton;
    }

    public void setMyButton(UIComponent myButton) {
        this.myButton = myButton;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDriver_id() {
        return driverId;
    }

    public void setDriver_id(String driver_id) {
        this.driverId = driver_id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public List<Vehicles> getVehiclesList() {
        if (this.vehiclesList == null || this.vehiclesList.isEmpty()) {
            this.vehiclesList = this.vehiclesFacade.findAll();
        }
        return vehiclesList;
    }

    public void setVehiclesList(List<Vehicles> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }

    public String save() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (this.existePlaca()) {
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe un auto con esta placa", ""));
            return null;
        }
        
        if(!this.esValidoIdDriver()) {
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación del conductor es invalida", ""));
            return null;
        }
        
        if(!this.esValidaPlaca()) {
            context.addMessage("globalMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La placa no es valida.", ""));
            return null;
        }
        
        Vehicles vehiclesPojo = new Vehicles();
        vehiclesPojo.setPlaca(placa);
        vehiclesPojo.setName(name);
        vehiclesPojo.setModel(model);
        vehiclesPojo.setColor(color);
        vehiclesPojo.setDriverId(driverId);
        
        this.vehiclesFacade.create(vehiclesPojo);
        this.vehiclesList.add(vehiclesPojo);
        return "VehicleCreat";
    }

    private boolean existePlaca() {
        if (!this.vehiclesList.isEmpty()) {
            for (Vehicles vehiculo : this.vehiclesList) {
                if (vehiculo.getPlaca().equals(this.placa)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean esValidaPlaca() {
        String regex = "^[A-Z]{3}\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.placa);
        return matcher.matches();
    }

    private boolean esValidoIdDriver() {
        if (this.driverId.length() > 15) {
            return false;
        }
        String regex = "^[^a-zA-Z]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.driverId);
        return matcher.matches();
    }
    
    /*
    public boolean verficarPlaca() {
        for (Vehicles vehiculo : this.getVehicles()) {
            if (vehiculo.getPlaca().equals(this.placa)) {
                return true;
            }
        }
        return false;
    }
    */
}
