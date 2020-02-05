package com.aaif_seriex.flo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private String title;
    private String description;
    private String img,pimg;
    private String video_url;
    private String like;
    private String parent;
    private String id,price,previewurl,haspreview,filesize_high,filesize_low,duration,videomd5,no_watched,approved,when_uploaded,uploader,uploader_userid,actor_name,bio,actor_imageurl;
    private Integer views;

    public Post(Parcel in) {
        title = in.readString();
        description = in.readString();
        img = in.readString();
        video_url = in.readString();
        like = in.readString();
        id = in.readString();
        price = in.readString();
        previewurl = in.readString();
        haspreview = in.readString();
        filesize_high = in.readString();
        filesize_low = in.readString();
        duration = in.readString();
        parent = in.readString();
        videomd5 = in.readString();
        no_watched = in.readString();
        approved   = in.readString();
        pimg = in.readString();
        when_uploaded = in.readString();
        uploader = in.readString();
        uploader_userid = in.readString();
        actor_name = in.readString();
        bio         = in.readString();
        actor_imageurl = in.readString();

    }

    public Post(){

    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getFilesize_high() {
        return filesize_high;
    }

    public void setFilesize_high(String filesize_high) {
        this.filesize_high = filesize_high;
    }

    public String getFilesize_low() {
        return filesize_low;
    }

    public void setFilesize_low(String filesize_low) {
        this.filesize_low = filesize_low;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public static Creator<Post> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getVideo_url() {
        return video_url;
    }

    public String getLike() {
        return like;
    }

    public String getPrice() {
        return price;
    }

    public String getParent() {
        return parent;
    }

    public String getPreviewurl() {
        return previewurl;
    }

    public String getHaspreview() {
        return haspreview;
    }

    public String getVideomd5() {
        return videomd5;
    }

    public String getNo_watched() {
        return no_watched;
    }

    public String getApproved() {
        return approved;
    }

    public String getWhen_uploaded() {
        return when_uploaded;
    }
    public String getUploader_userid() {
        return uploader_userid;
    }

    public String getPimg() {
        return pimg;
    }

    public String getUploader() {
        return uploader;
    }

    public String getActor_name() {
        return actor_name;
    }

    public String getActor_imageurl() {
        return actor_imageurl;
    }

    public String getBio() {
        return bio;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setPreviewurl(String previewurl) {
        this.previewurl = previewurl;
    }

    public void setHaspreview(String haspreview) {
        this.haspreview = haspreview;
    }

    public void setNo_watched(String no_watched) {
        this.no_watched = no_watched;
    }

    public void setVideomd5(String videomd5) {
        this.videomd5 = videomd5;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }
    public void setWhen_uploaded(String when_uploaded) {
        this.when_uploaded = when_uploaded;
    }
    public void setUploader_userid(String uploader_userid) {

        this.uploader_userid = uploader_userid;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setActor_imageurl(String actor_imageurl) {
        this.actor_imageurl = actor_imageurl;
    }

    public void setActor_name(String actor_name) {
        this.actor_name = actor_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(img);
        dest.writeString(video_url);
        dest.writeString(like);
        dest.writeString(id);
        dest.writeString(price);
        dest.writeString(previewurl);
        dest.writeString(haspreview);
        dest.writeString(filesize_high);
        dest.writeString(filesize_low);
        dest.writeString(duration);

        dest.writeString(parent);

        dest.writeString(videomd5);
        dest.writeString(no_watched);
        dest.writeString(approved);
        dest.writeString(pimg);
        dest.writeString(uploader);
        dest.writeString(uploader_userid);
        dest.writeString(when_uploaded);
        dest.writeString(bio);
        dest.writeString(actor_name);
        dest.writeString(actor_imageurl);

    }
}
