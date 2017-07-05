package com.ifreework.mapper.weixin;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Album;


public interface AlbumMapper  {
	public List<Album> queryList(PageData pd);
	public Album getAlbumById(String albumId);
	public void add(Album album);
	public void update(Album album);
	public void delete(String albumId);
}
