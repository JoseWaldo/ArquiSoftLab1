/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udea.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "drivers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drivers.findAll", query = "SELECT d FROM Drivers d"),
    @NamedQuery(name = "Drivers.findByName", query = "SELECT d FROM Drivers d WHERE d.name = :name"),
    @NamedQuery(name = "Drivers.findByLastName", query = "SELECT d FROM Drivers d WHERE d.lastName = :lastName"),
    @NamedQuery(name = "Drivers.findByNIdentification", query = "SELECT d FROM Drivers d WHERE d.nIdentification = :nIdentification"),
    @NamedQuery(name = "Drivers.findByEmail", query = "SELECT d FROM Drivers d WHERE d.email = :email"),
    @NamedQuery(name = "Drivers.findByPhone", query = "SELECT d FROM Drivers d WHERE d.phone = :phone")})
public class Drivers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "last_name")
    private String lastName;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "n_identification")
    private String nIdentification;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "phone")
    private String phone;

    public Drivers() {
    }

    public Drivers(String nIdentification) {
        this.nIdentification = nIdentification;
    }

    public Drivers(String nIdentification, String name, String lastName, String email, String phone) {
        this.nIdentification = nIdentification;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
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

    public String getNIdentification() {
        return nIdentification;
    }

    public void setNIdentification(String nIdentification) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdentification != null ? nIdentification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drivers)) {
            return false;
        }
        Drivers other = (Drivers) object;
        if ((this.nIdentification == null && other.nIdentification != null) || (this.nIdentification != null && !this.nIdentification.equals(other.nIdentification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udea.models.Drivers[ nIdentification=" + nIdentification + " ]";
    }
    
}
