package com.jockey.community.model;

public class Notification {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.SENDER_NAME
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    private String senderName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.SENDER_ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    private Long senderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.RECEIVER_ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    private Long receiverId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.TYPE
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.QUESTION_TITLE
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    private String questionTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.QUESTION_ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    private Long questionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.STATUS
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    private Long gmtCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.ID
     *
     * @return the value of NOTIFICATION.ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.ID
     *
     * @param id the value for NOTIFICATION.ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.SENDER_NAME
     *
     * @return the value of NOTIFICATION.SENDER_NAME
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.SENDER_NAME
     *
     * @param senderName the value for NOTIFICATION.SENDER_NAME
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.SENDER_ID
     *
     * @return the value of NOTIFICATION.SENDER_ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public Long getSenderId() {
        return senderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.SENDER_ID
     *
     * @param senderId the value for NOTIFICATION.SENDER_ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.RECEIVER_ID
     *
     * @return the value of NOTIFICATION.RECEIVER_ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.RECEIVER_ID
     *
     * @param receiverId the value for NOTIFICATION.RECEIVER_ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.TYPE
     *
     * @return the value of NOTIFICATION.TYPE
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.TYPE
     *
     * @param type the value for NOTIFICATION.TYPE
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.QUESTION_TITLE
     *
     * @return the value of NOTIFICATION.QUESTION_TITLE
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public String getQuestionTitle() {
        return questionTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.QUESTION_TITLE
     *
     * @param questionTitle the value for NOTIFICATION.QUESTION_TITLE
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle == null ? null : questionTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.QUESTION_ID
     *
     * @return the value of NOTIFICATION.QUESTION_ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.QUESTION_ID
     *
     * @param questionId the value for NOTIFICATION.QUESTION_ID
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.STATUS
     *
     * @return the value of NOTIFICATION.STATUS
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.STATUS
     *
     * @param status the value for NOTIFICATION.STATUS
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.GMT_CREATE
     *
     * @return the value of NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.GMT_CREATE
     *
     * @param gmtCreate the value for NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Tue Nov 26 16:39:13 CST 2019
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}