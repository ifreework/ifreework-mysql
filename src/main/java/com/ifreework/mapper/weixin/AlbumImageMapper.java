package com.ifreework.mapper.weixin;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.AlbumImage;


public interface AlbumImageMapper  {
	public List<AlbumImage> queryList(PageData pd);
	public AlbumImage getAlbumImageById(String albumId);
	public void add(AlbumImage album);
	public void update(AlbumImage album);
	public void delete(String albumId);
}
