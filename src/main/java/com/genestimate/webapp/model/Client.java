package com.genestimate.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
@Table(name = "CLIENT", schema = "PUBLIC")
public class Client {
    private int id;
    private String name;
    private String streetAndHouseNr;
    private String city;
    private int postalCode;
    private String country;
    private String phone;

    @JsonIgnore
    private List<Properties> orders;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "STREET_HOUSE_NR")
    public String getStreetAndHouseNr() {
        return streetAndHouseNr;
    }

    public void setStreetAndHouseNr(String streetAndHouseNr) {
        this.streetAndHouseNr = streetAndHouseNr;
    }

    @Basic
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "POSTAL_CODE")
    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (postalCode != client.postalCode) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (streetAndHouseNr != null ? !streetAndHouseNr.equals(client.streetAndHouseNr) : client.streetAndHouseNr != null)
            return false;
        if (city != null ? !city.equals(client.city) : client.city != null) return false;
        if (country != null ? !country.equals(client.country) : client.country != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        return orders != null ? orders.equals(client.orders) : client.orders == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (streetAndHouseNr != null ? streetAndHouseNr.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + postalCode;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    public List<Properties> getOrders() {
        return orders;
    }

    public void setOrders(List<Properties> orders) {
        this.orders = orders;
    }
}
