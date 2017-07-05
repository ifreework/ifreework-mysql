package com.ifreework.entity.weixin;

import java.io.Serializable;

public class Album implements Serializable {

	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */

	private static final long serialVersionUID = -3555537469983258133L;
	// columns START
	private java.lang.String albumId;
	private java.lang.String title;
	private java.lang.String remark;
	private java.lang.String creater;
	private java.util.Date createTime;
	
	private AlbumImage albumImage;
	// columns END

	public void setAlbumId(java.lang.String value) {
		this.albumId = value;
	}

	public java.lang.String getAlbumId() {
		return this.albumId;
	}

	public void setTitle(java.lang.String value) {
		this.title = value;
	}

	public java.lang.String getTitle() {
		return this.title;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setCreater(java.lang.String value) {
		this.creater = value;
	}

	public java.lang.String getCreater() {
		return this.creater;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public AlbumImage getAlbumImage() {
		return albumImage;
	}

	public void setAlbumImage(AlbumImage albumImage) {
		this.albumImage = albumImage;
	}
}
