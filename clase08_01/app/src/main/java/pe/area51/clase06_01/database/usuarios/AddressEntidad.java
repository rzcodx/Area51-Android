package pe.area51.clase06_01.database.usuarios;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AddressEntidad extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoEntidad geo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public GeoEntidad getGeo() {
        return geo;
    }

    public void setGeo(GeoEntidad geo) {
        this.geo = geo;
    }
}
