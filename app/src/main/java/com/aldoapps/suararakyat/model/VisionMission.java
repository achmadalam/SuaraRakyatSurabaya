package com.aldoapps.suararakyat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisionMission {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("candidate_id")
    @Expose
    private int candidateId;
    @SerializedName("vision")
    @Expose
    private String vision;
    @SerializedName("mision")
    @Expose
    private String mision;

    /**
     * No args constructor for use in serialization
     *
     */
    public VisionMission() {
    }

    /**
     *
     * @param id
     * @param candidateId
     * @param vision
     * @param mision
     */
    public VisionMission(int id, int candidateId, String vision, String mision) {
        this.id = id;
        this.candidateId = candidateId;
        this.vision = vision;
        this.mision = mision;
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
     * The candidateId
     */
    public int getCandidateId() {
        return candidateId;
    }

    /**
     *
     * @param candidateId
     * The candidate_id
     */
    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    /**
     *
     * @return
     * The vision
     */
    public String getVision() {
        return vision;
    }

    /**
     *
     * @param vision
     * The vision
     */
    public void setVision(String vision) {
        this.vision = vision;
    }

    /**
     *
     * @return
     * The mision
     */
    public String getMision() {
        return mision;
    }

    /**
     *
     * @param mision
     * The mision
     */
    public void setMision(String mision) {
        this.mision = mision;
    }

}