package com.petfun.dynamicdetail;

/**
 * Created by yjc19 on 2017/10/29.
 */

public class CommentItem {

    String commenter_image,commenter_name,comment_content;

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public void setCommenter_image(String commenter_image) {
        this.commenter_image = commenter_image;
    }

    public void setCommenter_name(String commenter_name) {
        this.commenter_name = commenter_name;
    }

    public String getComment_content() {
        return comment_content;
    }

    public String getCommenter_image() {
        return commenter_image;
    }

    public String getCommenter_name() {
        return commenter_name;
    }
}
