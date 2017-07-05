package com.ifreework.service.weixin;


import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Album;

public interface AlbumService {
	public List<Album> queryList(PageData pd);
	public Album getAlbumById(String albumId);
	public PageData add(Album album);
	public PageData update(Album album);
	public PageData delete(String albumId);
}