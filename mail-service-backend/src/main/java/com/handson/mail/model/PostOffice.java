package com.handson.mail.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.handson.mail.util.Dates;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "post_office")
public class PostOffice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postOfficeId;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Date createdAt = Dates.nowUTC();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdAt")
    public LocalDateTime calcCreatedAt() {
        return Dates.atLocalTime(createdAt);
    }

    @NotEmpty
    @Length(max = 95)
    private String address;

    @NotEmpty
    @Length(min = 2, max = 11)
    private String zipCode;

    @NotEmpty
    @Length(min = 3, max = 100)
    private String name;

    public Long getPostOfficeId() {
        return postOfficeId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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


    public static final class PostOfficeBuilder {
        private Long postOfficeId;
        private Date createdAt;
        private String address;
        private String zipCode;
        private String name;

        private PostOfficeBuilder() {
        }

        public static PostOfficeBuilder aPostOffice() {
            return new PostOfficeBuilder();
        }

        public PostOfficeBuilder postOfficeId(Long postOfficeId) {
            this.postOfficeId = postOfficeId;
            return this;
        }

        public PostOfficeBuilder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PostOfficeBuilder address(String address) {
            this.address = address;
            return this;
        }

        public PostOfficeBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public PostOfficeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PostOffice build() {
            PostOffice postOffice = new PostOffice();
            postOffice.setCreatedAt(createdAt);
            postOffice.setAddress(address);
            postOffice.setZipCode(zipCode);
            postOffice.setName(name);
            postOffice.postOfficeId = this.postOfficeId;
            return postOffice;
        }
    }
}
