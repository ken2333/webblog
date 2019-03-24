package com.sun.webblog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * MessageInfo
 * @author 
 */
public class Messageinfo implements Serializable {
    private Integer id;

    /**
     * 消息类型：1贴子提醒 2：系统提醒  3 
     */
    private String type;

    private String articleuuid;

    private String other;

    /**
     * 已读true,未读false
     */
    private Boolean isend;

    private String fromuser;

    private String touser;

    /**
     * 已读的时间
     */
    private Date endtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArticleuuid() {
        return articleuuid;
    }

    public void setArticleuuid(String articleuuid) {
        this.articleuuid = articleuuid;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Boolean getIsend() {
        return isend;
    }

    public void setIsend(Boolean isend) {
        this.isend = isend;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Messageinfo() {
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Messageinfo other = (Messageinfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getArticleuuid() == null ? other.getArticleuuid() == null : this.getArticleuuid().equals(other.getArticleuuid()))
            && (this.getOther() == null ? other.getOther() == null : this.getOther().equals(other.getOther()))
            && (this.getIsend() == null ? other.getIsend() == null : this.getIsend().equals(other.getIsend()))
            && (this.getFromuser() == null ? other.getFromuser() == null : this.getFromuser().equals(other.getFromuser()))
            && (this.getTouser() == null ? other.getTouser() == null : this.getTouser().equals(other.getTouser()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getArticleuuid() == null) ? 0 : getArticleuuid().hashCode());
        result = prime * result + ((getOther() == null) ? 0 : getOther().hashCode());
        result = prime * result + ((getIsend() == null) ? 0 : getIsend().hashCode());
        result = prime * result + ((getFromuser() == null) ? 0 : getFromuser().hashCode());
        result = prime * result + ((getTouser() == null) ? 0 : getTouser().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", articleuuid=").append(articleuuid);
        sb.append(", other=").append(other);
        sb.append(", isend=").append(isend);
        sb.append(", fromuser=").append(fromuser);
        sb.append(", touser=").append(touser);
        sb.append(", endtime=").append(endtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}