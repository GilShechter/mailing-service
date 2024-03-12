package com.handson.mail.model;

import com.handson.mail.util.Dates;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

import static com.handson.mail.model.PostOffice.PostOfficeBuilder.aPostOffice;


public class PostOfficeIn implements Serializable {

    @Length(max = 95)
    private String address;

    @Length(min = 2, max = 11)
    private String zipCode;

    @Length(min = 3, max = 100)
    private String name;

    public PostOffice toPostOffice() {
        return aPostOffice().createdAt(Dates.nowUTC()).address(address).zipCode(zipCode).name(name)
                .build();
    }

    public void updatePostOffice(PostOffice postOffice) {
        postOffice.setAddress(address);
        postOffice.setName(name);
        postOffice.setZipCode(zipCode);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
