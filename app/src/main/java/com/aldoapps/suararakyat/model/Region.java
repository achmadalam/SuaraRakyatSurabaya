package com.aldoapps.suararakyat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Region {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("province")
    @Expose
    private Province province;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("kind")
    @Expose
    private String kind;

    /**
     * No args constructor for use in serialization
     *
     */
    public Region() {
    }

    /**
     *
     * @param id
     * @param name
     * @param province
     * @param kind
     */
    public Region(int id, Province province, String name, String kind) {
        this.id = id;
        this.province = province;
        this.name = name;
        this.kind = kind;
    }

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The province
     */
    public Province getProvince() {
        return province;
    }

    /**
     *
     * @param province
     * The province
     */
    public void setProvince(Province province) {
        this.province = province;
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

}
