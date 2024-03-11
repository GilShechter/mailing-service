package com.handson.mail.model;

import com.handson.mail.util.Dates;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
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
