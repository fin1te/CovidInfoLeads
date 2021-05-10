package com.finite.covidinfoleads;

public class blogmodel {
    String blogtitle,blogbody,blogauthor;

    blogmodel() {

    }

    public blogmodel(String blogtitle, String blogbody, String blogauthor) {
        this.blogtitle = blogtitle;
        this.blogbody = blogbody;
        this.blogauthor = blogauthor;
    }

    public String getBlogtitle() {
        return blogtitle;
    }

    public void setBlogtitle(String blogtitle) {
        this.blogtitle = blogtitle;
    }

    public String getBlogbody() {
        return blogbody;
    }

    public void setBlogbody(String blogbody) {
        this.blogbody = blogbody;
    }

    public String getBlogauthor() {
        return blogauthor;
    }

    public void setBlogauthor(String blogauthor) {
        this.blogauthor = blogauthor;
    }
}
