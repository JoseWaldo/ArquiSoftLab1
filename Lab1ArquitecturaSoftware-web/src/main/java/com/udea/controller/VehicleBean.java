/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.controller;

import com.udea.ejb.VehiclesFacadeLocal;
import com.udea.models.Drivers;
import com.udea.models.Vehicles;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
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
    private Drivers driverId;
    private boolean disabledButton = true;
    
    private List<Vehicles> vehicles;
    
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    
    public VehicleBean() {
    }
    
    public List<Vehicles> getVehicles() {
        if(this.vehicles == null || this.vehicles.isEmpty()) {
            refresh();
        }
        return this.vehicles;
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public String getLanguaje() {
        return this.locale.getLanguage();
    }
    
    public void changeLanguaje(String languaje) {
        locale = new Locale(languaje);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
    
    public void refresh() {
        this.vehicles = vehiclesFacade.getAllVehicles();
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

    public Drivers getDriver_id() {
        return driverId;
    }

    public void setDriver_id(Drivers driver_id) {
        this.driverId = driver_id;
    }

    public boolean isDisabledButton() {
        return disabledButton;
    }

    public void setDisabledButton(boolean disabledButton) {
        this.disabledButton = disabledButton;
    }    
    
    
    public String save() {
        
        Vehicles vehiclesPojo = new Vehicles();
        vehiclesPojo.setPlaca(this.placa);
        vehiclesPojo.setName(this.name);
        vehiclesPojo.setModel(this.model);
        vehiclesPojo.setColor(this.color);
        vehiclesPojo.setDriverId(this.driverId);
        
        this.vehiclesFacade.create(vehiclesPojo);
        return "VehicleCread";
    }
}
