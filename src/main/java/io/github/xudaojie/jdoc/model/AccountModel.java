package io.github.xudaojie.jdoc.model;

import java.io.Serializable;
import java.util.Date;
/**
 * AccountDO
 *
 * User: duxing //change at Setting Tab
 * Date: 2017-04-18 01:01:05
 * Generate by autoDO
 * Powered by duxing@Taobao
 */

public class AccountModel implements Serializable{
    private static final long serialVersionUID = -1L;

    /**
     * id
     */
    private Long id;
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * states
     */
    private Long states;
    /**
     * nickname
     */
    private String nickname;
    /**
     * create_date
     */
    private Date createDate;
    /**
     * update_date
     */
    private Date updateDate;

    /**
     * id getter & setter
     */
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    /**
     * username getter & setter
     */
    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * password getter & setter
     */
    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * states getter & setter
     */
    public Long getStates() {
        return states;
    }
    public void setStates(Long states){
        this.states = states;
    }

    /**
     * nickname getter & setter
     */
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    /**
     * createDate getter & setter
     */
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    /**
     * updateDate getter & setter
     */
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate){
        this.updateDate = updateDate;
    }

}