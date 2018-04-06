package com.petfun.dynamicdetail;

/**
 * Created by Lenovo on 2017/10/29.
 */

public class DynamicDetails {
    private String userId;//用户id
    private String userName;//用户名
    private String qNumber;//头像链接
    private String content;//评论内容
    private String commentTime;//评论时间
    private String commentId;//评论id

    public String getCommentUserId() {
        return userId;
    }

    public void setCommentUserId(String commentUserId) {
        this.userId = commentUserId;
    }

    public String getCommentUserName() {
        return userName;
    }

    public void setCommentUserName(String commentUserName) {
        this.userName = commentUserName;
    }

    public String getCommentHeadLink() {
        return qNumber;
    }

    public void setCommentHeadLink(String commentHeadLink) {
        this.qNumber = commentHeadLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
