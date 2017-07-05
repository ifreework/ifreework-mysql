package com.ifreework.service.weixin;


import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.AlbumImage;

public interface AlbumImageService {
	public List<AlbumImage> queryList(PageData pd);
	public AlbumImage getAlbumImageById(String albumImageId);
	public PageData add(AlbumImage albumImage);
	public PageData update(AlbumImage albumImage);
	public PageData delete(String albumImageId);
}