package pe.area51.clase06_01.database.usuarios;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UsuarioEntidad extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private int idData;
    private String name;
    private String username;
    private String email;
    private AddressEntidad address;
    private String phone;
    private String website;
    private CompanyEntidad company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressEntidad getAddress() {
        return address;
    }

    public void setAddress(AddressEntidad address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public CompanyEntidad getCompany() {
        return company;
    }

    public void setCompany(CompanyEntidad company) {
        this.company = company;
    }
}
