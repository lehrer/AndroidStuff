package be.gershon_lehrer.gettingcarassistance.model;

import java.util.UUID;

/**
 * Created by gershonlehrer on 25/04/2017.
 */

public class Garage {
    private UUID mId;
    private String mName;
    private String mStreetAddress;
    private String mPostCode;
    private String mCity;
    private String mCountry;

    public Garage(String name, String streetAddress, String postCode, String city, String country){
        this.mId=UUID.randomUUID();
        this.mName=name;
        this.mStreetAddress=streetAddress;
        this.mPostCode=postCode;
        this.mCity=city;
        this.mCountry=country;
    }


    public UUID getId() {
        return mId;
    }



    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getStreetAddress() {
        return mStreetAddress;
    }

    public void setStreetAddress(String mStreetAddress) {
        this.mStreetAddress = mStreetAddress;
    }

    public String getPostCode() {
        return mPostCode;
    }

    public void setPostCode(String mPostCode) {
        this.mPostCode = mPostCode;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    @Override
    public String toString() {
        return "id"+this.mId.toString() +"Name: "+ this.mName;
    }
}
