package com.ifreework.service.weixin;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.AlbumImage;
import com.ifreework.mapper.weixin.AlbumImageMapper;
import com.ifreework.util.StringUtil;


@Service("albumImageService")
public class AlbumImageServiceImpl  implements AlbumImageService {
	@Autowired
	private AlbumImageMapper albumImageMapper;
	
	@Override
	public List<AlbumImage> queryList(PageData pd) {
		List<AlbumImage> list = albumImageMapper.queryList(pd);
		return list;
	}

	@Override
	public AlbumImage getAlbumImageById(String albumImageId) {
		return albumImageMapper.getAlbumImageById(albumImageId);
	}

	@Override
	public PageData add(AlbumImage albumImage) {
		String imageId = StringUtil.uuid();
		albumImage.setImageId(imageId);
		albumImageMapper.add(albumImage);
		PageData pd = new PageData();
		pd.put("imageId", imageId);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(AlbumImage albumImage) {
		albumImageMapper.update(albumImage);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String albumImageId) {
		albumImageMapper.delete(albumImageId);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
}