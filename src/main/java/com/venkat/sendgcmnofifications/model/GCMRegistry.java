package com.venkat.sendgcmnofifications.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reg_gcm_users")
public class GCMRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="gcm_regid")
    private String gcmRegid;

    @Column(name="created_at", insertable = false,  updatable=false)
    private Date creationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGcmRegid() {
        return gcmRegid;
    }

    public void setGcmRegid(String gcmRegid) {
        this.gcmRegid = gcmRegid;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
