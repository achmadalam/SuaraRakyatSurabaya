package com.aldoapps.suararakyat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Participant {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("pob")
    @Expose
    private String pob;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("work")
    @Expose
    private String work;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public Participant() {
    }

    /**
     *
     * @param work
     * @param status
     * @param address
     * @param dob
     * @param name
     * @param pob
     * @param gender
     * @param kind
     */
    public Participant(String kind, String name, String gender, String pob, String dob, String address, String work, String status) {
        this.kind = kind;
        this.name = name;
        this.gender = gender;
        this.pob = pob;
        this.dob = dob;
        this.address = address;
        this.work = work;
        this.status = status;
    }

    /**
     *
     * @return
     * The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     * The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The pob
     */
    public String getPob() {
        return pob;
    }

    /**
     *
     * @param pob
     * The pob
     */
    public void setPob(String pob) {
        this.pob = pob;
    }

    /**
     *
     * @return
     * The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     *
     * @param dob
     * The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The work
     */
    public String getWork() {
        return work;
    }

    /**
     *
     * @param work
     * The work
     */
    public void setWork(String work) {
        this.work = work;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
