package com.ifreework.service.weixin;




import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.entity.weixin.Album;
import com.ifreework.mapper.weixin.AlbumMapper;
import com.ifreework.util.StringUtil;


@Service("albumService")
public class AlbumServiceImpl  implements AlbumService {
	@Autowired
	private AlbumMapper albumMapper;
	
	@Override
	public List<Album> queryList(PageData pd) {
		List<Album> list = albumMapper.queryList(pd);
		return list;
	}

	@Override
	public Album getAlbumById(String albumId) {
		return albumMapper.getAlbumById(albumId);
	}

	@Override
	public PageData add(Album album) {
		String albumId = StringUtil.uuid();
		album.setAlbumId(albumId);
		String openId = ServletRequestManager.getCookieValue("openId");
		album.setTitle("新建相册");
		album.setCreater(openId);
		album.setCreateTime(new Date());
		albumMapper.add(album);
		PageData pd = new PageData();
		pd.put("album", album);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(Album album) {
		albumMapper.update(album);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String albumId) {
		albumMapper.delete(albumId);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
}