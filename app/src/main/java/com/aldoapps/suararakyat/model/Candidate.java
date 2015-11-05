package com.aldoapps.suararakyat.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Candidate {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("participants")
    @Expose
    private List<Participant> participants = new ArrayList<Participant>();
    @SerializedName("region")
    @Expose
    private Region region;
    @SerializedName("endorsement_type")
    @Expose
    private String endorsementType;
    @SerializedName("endorsement")
    @Expose
    private String endorsement;
    @SerializedName("vote_type")
    @Expose
    private String voteType;
    @SerializedName("acceptance_status")
    @Expose
    private String acceptanceStatus;
    @SerializedName("document_completeness")
    @Expose
    private String documentCompleteness;
    @SerializedName("research_result")
    @Expose
    private String researchResult;
    @SerializedName("acceptance_document_repair")
    @Expose
    private String acceptanceDocumentRepair;
    @SerializedName("amount_support")
    @Expose
    private String amountSupport;
    @SerializedName("amount_support_repair")
    @Expose
    private String amountSupportRepair;
    @SerializedName("amount_support_determination")
    @Expose
    private String amountSupportDetermination;
    @SerializedName("eligibility_support")
    @Expose
    private String eligibilitySupport;
    @SerializedName("eligibility_support_repair")
    @Expose
    private String eligibilitySupportRepair;
    @SerializedName("pertahana")
    @Expose
    private String pertahana;
    @SerializedName("dynasty")
    @Expose
    private String dynasty;
    @SerializedName("amount_women")
    @Expose
    private String amountWomen;
    @SerializedName("incumbent")
    @Expose
    private String incumbent;

    /**
     * No args constructor for use in serialization
     *
     */
    public Candidate() {
    }

    /**
     *
     * @param region
     * @param eligibilitySupport
     * @param pertahana
     * @param amountWomen
     * @param eligibilitySupportRepair
     * @param acceptanceStatus
     * @param participants
     * @param amountSupportRepair
     * @param acceptanceDocumentRepair
     * @param id
     * @param amountSupport
     * @param amountSupportDetermination
     * @param documentCompleteness
     * @param endorsementType
     * @param voteType
     * @param incumbent
     * @param endorsement
     * @param researchResult
     * @param dynasty
     */
    public Candidate(int id, List<Participant> participants, Region region, String endorsementType, String endorsement, String voteType, String acceptanceStatus, String documentCompleteness, String researchResult, String acceptanceDocumentRepair, String amountSupport, String amountSupportRepair, String amountSupportDetermination, String eligibilitySupport, String eligibilitySupportRepair, String pertahana, String dynasty, String amountWomen, String incumbent) {
        this.id = id;
        this.participants = participants;
        this.region = region;
        this.endorsementType = endorsementType;
        this.endorsement = endorsement;
        this.voteType = voteType;
        this.acceptanceStatus = acceptanceStatus;
        this.documentCompleteness = documentCompleteness;
        this.researchResult = researchResult;
        this.acceptanceDocumentRepair = acceptanceDocumentRepair;
        this.amountSupport = amountSupport;
        this.amountSupportRepair = amountSupportRepair;
        this.amountSupportDetermination = amountSupportDetermination;
        this.eligibilitySupport = eligibilitySupport;
        this.eligibilitySupportRepair = eligibilitySupportRepair;
        this.pertahana = pertahana;
        this.dynasty = dynasty;
        this.amountWomen = amountWomen;
        this.incumbent = incumbent;
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
     * The participants
     */
    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     *
     * @param participants
     * The participants
     */
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    /**
     *
     * @return
     * The region
     */
    public Region getRegion() {
        return region;
    }

    /**
     *
     * @param region
     * The region
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     *
     * @return
     * The endorsementType
     */
    public String getEndorsementType() {
        return endorsementType;
    }

    /**
     *
     * @param endorsementType
     * The endorsement_type
     */
    public void setEndorsementType(String endorsementType) {
        this.endorsementType = endorsementType;
    }

    /**
     *
     * @return
     * The endorsement
     */
    public String getEndorsement() {
        return endorsement;
    }

    /**
     *
     * @param endorsement
     * The endorsement
     */
    public void setEndorsement(String endorsement) {
        this.endorsement = endorsement;
    }

    /**
     *
     * @return
     * The voteType
     */
    public String getVoteType() {
        return voteType;
    }

    /**
     *
     * @param voteType
     * The vote_type
     */
    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    /**
     *
     * @return
     * The acceptanceStatus
     */
    public String getAcceptanceStatus() {
        return acceptanceStatus;
    }

    /**
     *
     * @param acceptanceStatus
     * The acceptance_status
     */
    public void setAcceptanceStatus(String acceptanceStatus) {
        this.acceptanceStatus = acceptanceStatus;
    }

    /**
     *
     * @return
     * The documentCompleteness
     */
    public String getDocumentCompleteness() {
        return documentCompleteness;
    }

    /**
     *
     * @param documentCompleteness
     * The document_completeness
     */
    public void setDocumentCompleteness(String documentCompleteness) {
        this.documentCompleteness = documentCompleteness;
    }

    /**
     *
     * @return
     * The researchResult
     */
    public String getResearchResult() {
        return researchResult;
    }

    /**
     *
     * @param researchResult
     * The research_result
     */
    public void setResearchResult(String researchResult) {
        this.researchResult = researchResult;
    }

    /**
     *
     * @return
     * The acceptanceDocumentRepair
     */
    public String getAcceptanceDocumentRepair() {
        return acceptanceDocumentRepair;
    }

    /**
     *
     * @param acceptanceDocumentRepair
     * The acceptance_document_repair
     */
    public void setAcceptanceDocumentRepair(String acceptanceDocumentRepair) {
        this.acceptanceDocumentRepair = acceptanceDocumentRepair;
    }

    /**
     *
     * @return
     * The amountSupport
     */
    public String getAmountSupport() {
        return amountSupport;
    }

    /**
     *
     * @param amountSupport
     * The amount_support
     */
    public void setAmountSupport(String amountSupport) {
        this.amountSupport = amountSupport;
    }

    /**
     *
     * @return
     * The amountSupportRepair
     */
    public String getAmountSupportRepair() {
        return amountSupportRepair;
    }

    /**
     *
     * @param amountSupportRepair
     * The amount_support_repair
     */
    public void setAmountSupportRepair(String amountSupportRepair) {
        this.amountSupportRepair = amountSupportRepair;
    }

    /**
     *
     * @return
     * The amountSupportDetermination
     */
    public String getAmountSupportDetermination() {
        return amountSupportDetermination;
    }

    /**
     *
     * @param amountSupportDetermination
     * The amount_support_determination
     */
    public void setAmountSupportDetermination(String amountSupportDetermination) {
        this.amountSupportDetermination = amountSupportDetermination;
    }

    /**
     *
     * @return
     * The eligibilitySupport
     */
    public String getEligibilitySupport() {
        return eligibilitySupport;
    }

    /**
     *
     * @param eligibilitySupport
     * The eligibility_support
     */
    public void setEligibilitySupport(String eligibilitySupport) {
        this.eligibilitySupport = eligibilitySupport;
    }

    /**
     *
     * @return
     * The eligibilitySupportRepair
     */
    public String getEligibilitySupportRepair() {
        return eligibilitySupportRepair;
    }

    /**
     *
     * @param eligibilitySupportRepair
     * The eligibility_support_repair
     */
    public void setEligibilitySupportRepair(String eligibilitySupportRepair) {
        this.eligibilitySupportRepair = eligibilitySupportRepair;
    }

    /**
     *
     * @return
     * The pertahana
     */
    public String getPertahana() {
        return pertahana;
    }

    /**
     *
     * @param pertahana
     * The pertahana
     */
    public void setPertahana(String pertahana) {
        this.pertahana = pertahana;
    }

    /**
     *
     * @return
     * The dynasty
     */
    public String getDynasty() {
        return dynasty;
    }

    /**
     *
     * @param dynasty
     * The dynasty
     */
    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    /**
     *
     * @return
     * The amountWomen
     */
    public String getAmountWomen() {
        return amountWomen;
    }

    /**
     *
     * @param amountWomen
     * The amount_women
     */
    public void setAmountWomen(String amountWomen) {
        this.amountWomen = amountWomen;
    }

    /**
     *
     * @return
     * The incumbent
     */
    public String getIncumbent() {
        return incumbent;
    }

    /**
     *
     * @param incumbent
     * The incumbent
     */
    public void setIncumbent(String incumbent) {
        this.incumbent = incumbent;
    }

}

