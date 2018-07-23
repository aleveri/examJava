package com.callcenter.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "supervisors")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Supervisor extends Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String commissionedArea;

    @NotBlank
    private String groupBelong;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public String getCommissionedArea() {
        return commissionedArea;
    }

    public void setCommissionedArea(String commissionedArea) {
        this.commissionedArea = commissionedArea;
    }

    public String getGroup() {
        return groupBelong;
    }

    public void setGroup(String group) {
        this.groupBelong = group;
    }
}
