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

public class MarkdownModel extends FileModel implements Serializable{
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
    private Long dirId;
    /**
     * handler
     */
    private Long handler;

    private String name;
    private String description;

    // 分享时使用的链接
    private String pageUrl;
    private String projectUrl;
    private boolean readOnly;

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
     * dirId getter & setter
     */
    public Long getDirId() {
        return dirId;
    }
    public void setDirId(Long dirId){
        this.dirId = dirId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPageUrl() {
        return pageUrl;
    }
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getProjectUrl() {
        return projectUrl;
    }
    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public boolean isReadOnly() {
        return readOnly;
    }
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}