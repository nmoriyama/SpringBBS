package jp.co.bbs.dto;

import java.util.Date;

public class CommentDto {
    
	private Integer postingId;
	private String body;
	private Date date;
	private Integer userId;
	public Integer getPostingId() {
		return postingId;
	}
	public void setPostingId(Integer postingId) {
		this.postingId = postingId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
