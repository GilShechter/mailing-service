package com.handson.mail.model;

import com.handson.mail.util.Dates;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

import static com.handson.mail.model.Package.PackageBuilder.aPackage;

public class PackageIn implements Serializable {

    @Length(max = 95)
    private String destinationAddress;

    @Length(min = 2, max = 11)
    private String destinationZipCode;

    @Length(min = 3, max = 100)
    private String recipientName;

    @Enumerated(EnumType.STRING)
    private PackageType type;

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDestinationZipCode() {
        return destinationZipCode;
    }

    public void setDestinationZipCode(String destinationZipCode) {
        this.destinationZipCode = destinationZipCode;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public PackageType getType() {
        return type;
    }

    public void setType(PackageType type) {
        this.type = type;
    }

    public Package toPackage() {
        return aPackage().createdAt(Dates.nowUTC()).destinationAddress(destinationAddress)
                .destinationZipCode(destinationZipCode).recipientName(recipientName).type(type)
                .build();
    }

    public void updatePackage(Package pack) {
        pack.setDestinationAddress(destinationAddress);
        pack.setDestinationZipCode(destinationZipCode);
        pack.setRecipientName(recipientName);
        pack.setType(type);
    }
}
