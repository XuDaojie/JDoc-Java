package io.github.xudaojie.jdoc.model;

import java.io.Serializable;
import java.util.Date;
/**
 * MarkdownDO
 *
 * User: duxing //change at Setting Tab
 * Date: 2017-04-18 09:51:38
 * Generate by autoDO
 * Powered by duxing@Taobao
 */

public class MarkdownModel implements Serializable{
    private static final long serialVersionUID = -1L;

    /**
     * id
     */
    private Long id;
    /**
     * content
     */
    private String content;
    /**
     * creator
     */
    private Long creator;
    /**
     * create_date
     */
    private Date createDate;
    /**
     * update_date
     */
    private Date updateDate;
    /**
     * is_delete
     */
    private Integer isDelete;
    /**
     * project_id
     */
    private Long projectId;
    /**
     * handler
     */
    private Long handler;
    /**
     * module_id
     */
    private Long moduleId;

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
     * content getter & setter
     */
    public String getContent() {
        return content;
    }
    public void setContent(String content){
        this.content = content;
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
     * projectId getter & setter
     */
    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId){
        this.projectId = projectId;
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
     * moduleId getter & setter
     */
    public Long getModuleId() {
        return moduleId;
    }
    public void setModuleId(Long moduleId){
        this.moduleId = moduleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}