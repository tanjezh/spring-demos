package com.tan.mybatis.plus.generator.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author tan
 * @since 2024-09-01
 */
@TableName("story_t0")
@ApiModel(value = "StoryT0对象", description = "")
public class StoryT0 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("作者的userID")
    private Integer userId;

    @ApiModelProperty("作者名")
    private String name;

    @ApiModelProperty("密码")
    private String title;

    @ApiModelProperty("故事内容")
    private String story;

    private Boolean isDeleted;

    private String createAt;

    private String updateAt;

    private Integer tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "StoryT0{" +
            "id = " + id +
            ", userId = " + userId +
            ", name = " + name +
            ", title = " + title +
            ", story = " + story +
            ", isDeleted = " + isDeleted +
            ", createAt = " + createAt +
            ", updateAt = " + updateAt +
            ", tag = " + tag +
        "}";
    }
}
