package com.handson.mail.model;

import com.handson.mail.util.Dates;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

enum PackageType {
    Letter,
    Package
}

@Entity
@Table(name="package")
public class Package implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trackingNumber;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Date createdAt = Dates.nowUTC();

    @NotEmpty
    @Length(max = 95)
    private String destinationAddress;

    @NotEmpty
    @Length(min = 2, max = 11)
    private String destinationZipCode;

    @NotEmpty
    @Length(min = 3, max = 100)
    private String recipientName;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private PackageType type;

    public Long getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Long trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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

    public static final class PackageBuilder {
        private Long trackingNumber;
        private Date createdAt = Dates.nowUTC();
        private String destinationAddress;
        private String destinationZipCode;
        private String recipientName;
        private PackageType type;

        private PackageBuilder() {
        }

        public static PackageBuilder aPackage() {
            return new PackageBuilder();
        }

        public PackageBuilder trackingNumber(Long trackingNumber) {
            this.trackingNumber = trackingNumber;
            return this;
        }

        public PackageBuilder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PackageBuilder destinationAddress(String destinationAddress) {
            this.destinationAddress = destinationAddress;
            return this;
        }

        public PackageBuilder destinationZipCode(String destinationZipCode) {
            this.destinationZipCode = destinationZipCode;
            return this;
        }

        public PackageBuilder recipientName(String recipientName) {
            this.recipientName = recipientName;
            return this;
        }

        public PackageBuilder type(PackageType type) {
            this.type = type;
            return this;
        }

        public Package build() {
            Package pack =new Package();
            pack.setTrackingNumber(trackingNumber);
            pack.setCreatedAt(createdAt);
            pack.setDestinationAddress(destinationAddress);
            pack.setDestinationZipCode(destinationZipCode);
            pack.setRecipientName(recipientName);
            return pack;
        }

    }






}
