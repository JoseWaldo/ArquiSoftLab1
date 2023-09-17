/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udea.ejb;

import com.udea.models.Vehicles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jose_waldo
 */
@Local
public interface VehiclesFacadeLocal {

    void create(Vehicles vehicles);

    void edit(Vehicles vehicles);

    void remove(Vehicles vehicles);

    Vehicles find(Object id);

    List<Vehicles> findAll();

    List<Vehicles> findRange(int[] range);
    
    List<Vehicles> getAllVehicles();
    
    int count();
    
}
