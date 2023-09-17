/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udea.ejb;

import com.udea.models.Drivers;
import com.udea.models.Vehicles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jose_waldo
 */
@Local
public interface DriversFacadeLocal {

    void create(Drivers drivers);

    void edit(Drivers drivers);

    void remove(Drivers drivers);

    Drivers find(Object id);

    List<Drivers> findAll();

    List<Drivers> findRange(int[] range);
        
    int count();
    
}
