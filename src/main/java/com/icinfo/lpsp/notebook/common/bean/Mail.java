package com.icinfo.lpsp.notebook.common.bean;


import java.io.Serializable;
import java.util.Map;

public class Mail implements Serializable {

    private static final long serialVersionUID = -6390720891150157552L;
    public static final String ENCODEING = "UTF-8";

    // 服务器地址
    private String host;
    // 发件人的邮箱
    private String sender;
    // 发件人昵称
    private String name;
    // 账号
    private String username;
    // 密码
    private String password;
    // 收件人的邮箱
    private String receiver;
    // 收件人的名称
    private String receiverName;
    // 收件人的邮箱(key)和名称(value)
    private Map<String, String> to;
    // 抄送人的邮箱(key)和名称(value)
    private Map<String, String> cc;
    // 秘密抄送人的邮箱(key)和名称(value)
    private Map<String, String> bcc;
    // 主题
    private String subject;
    // 信息(支持HTML)
    private String message;

    public Mail() {

    }

    /**
     * 自定义构造函数
     *
     * @param host     服务器地址
     * @param sender   发件人的邮箱
     * @param name     发件人昵称
     * @param username 账号
     * @param password 密码
     */
    public Mail(String host, String sender, String name, String username, String password) {
        this.host = host;
        this.sender = sender;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public boolean setMessage(String message) {
        this.message = message;
        return false;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Map<String, String> getTo() {
        return to;
    }

    public void setTo(Map<String, String> to) {
        this.to = to;
    }

    public Map<String, String> getCc() {
        return cc;
    }

    public void setCc(Map<String, String> cc) {
        this.cc = cc;
    }

    public Map<String, String> getBcc() {
        return bcc;
    }

    public void setBcc(Map<String, String> bcc) {
        this.bcc = bcc;
    }
}