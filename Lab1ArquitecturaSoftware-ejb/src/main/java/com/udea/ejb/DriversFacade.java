/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.ejb;

import com.udea.models.Drivers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jose_waldo
 */
@Stateless
public class DriversFacade extends AbstractFacade<Drivers> implements DriversFacadeLocal {

    @PersistenceContext(unitName = "com.udea_Lab1ArquitecturaSoftware-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DriversFacade() {
        super(Drivers.class);
    }

    @Override
    public boolean existeCedula(String cedula) {
        String queryString = "SELECT COUNT(d) FROM Drivers d WHERE d.nIdentification = :cedula";
        Query query = em.createQuery(queryString);
        query.setParameter("cedula", cedula);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existeCorreo(String correo) {
        String queryString = "SELECT COUNT(d) FROM Drivers d WHERE d.email = :correo";
        Query query = em.createQuery(queryString);
        query.setParameter("correo", correo);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

    @Override
    public boolean esValidoCorreo(String correo) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    @Override
    public boolean esValidaCedula(String cedula) {
        String regex = "^[0-9]{1,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cedula);
        return matcher.matches();
    }

}
