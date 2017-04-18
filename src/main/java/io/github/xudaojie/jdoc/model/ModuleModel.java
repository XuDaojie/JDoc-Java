package io.github.xudaojie.jdoc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * ModuleDO
 *
 * User: duxing //change at Setting Tab
 * Date: 2017-04-18 09:47:38
 * Generate by autoDO
 * Powered by duxing@Taobao
 */

public class ModuleModel implements Serializable{
    private static final long serialVersionUID = -1L;

    /**
     * id
     */
    private Long id;
    /**
     * handler
     */
    private Long handler;
    /**
     * creator
     */
    private Long creator;
    /**
     * is_delete
     */
    private Integer isDelete;
    /**
     * update_date
     */
    private Date updateDate;
    /**
     * create_date
     */
    private Date createDate;
    /**
     * description
     */
    private String description;
    /**
     * name
     */
    private String name;
    /**
     * project_id
     */
    private Long projectId;

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
     * handler getter & setter
     */
    public Long getHandler() {
        return handler;
    }
    public void setHandler(Long handler){
        this.handler = handler;
    }

    /**
     * creator getter & setter
     */
    public Long getCreator() {
        return creator;
    }
    public void setCreator(Long creator){
        this.creator = creator;
    }

    /**
     * isDelete getter & setter
     */
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
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
     * description getter & setter
     */
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * name getter & setter
     */
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    /**
     * projectId getter & setter
     */
    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId){
        this.projectId = projectId;
    }

}