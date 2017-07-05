package com.ifreework.entity.weixin;


import java.io.Serializable;



public class AlbumImage implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = 6509955104275118820L;
	//columns START
	private java.lang.String imageId;
	private java.lang.String albumId;
	private java.lang.String content;
	private java.lang.String remark;
	//columns END

	public void setImageId(java.lang.String value) {
		this.imageId = value;
	}
	
	public java.lang.String getImageId() {
		return this.imageId;
	}
	
	
	
	public void setAlbumId(java.lang.String value) {
		this.albumId = value;
	}
	public java.lang.String getAlbumId() {
		return this.albumId;
	}	
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	public java.lang.String getContent() {
		return this.content;
	}	
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}	
}


