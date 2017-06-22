package com.ifreework.controller.system;



import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.service.system.AttachmentService;



/**    
 *     
 * 类名称：AttachmentController    
 * 类描述：    
 * 创建人：王宜华    
 * 创建时间：2014-11-26 下午4:46:18    
 * 修改人：王宜华    
 * 修改时间：2014-11-26 下午4:46:18    
 * 修改备注：    
 * @version     
 *     
 */
@Controller
@RequestMapping("/system/attachment")
public class AttachmentController extends BaseControllerSupport {
	private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

	@Autowired
	private AttachmentService attachmentService ;

	@RequestMapping()
	public String gotoView(){
		return "/system/attachment/edit";
	}
	
	
	/**
	 * 
	 * 描述：文件上传
	 * @Title: fileUpload
	 * @param 
	 * @return   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/fileUpload")
	public PageData fileUpload(@RequestParam(required=false) MultipartFile[] file){
		try {
			return attachmentService.fileUpload(file);
		} catch (IOException e) {
			logger.error("File upload error is {}",e);
			PageData pd = new PageData();
			pd.setResult(Constant.FAILED);
			pd.setMsg("系统异常，文件上传失败！");
			return pd;
		}
	}
	
	
	/**
	 * 
	 * 描述：删除附件
	 * @Title: fileUpload
	 * @param 
	 * @return   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public PageData delete(){
		PageData pd = this.getPageData();
		String attachmentId = pd.getString("attachmentId");
		attachmentService.delete(attachmentId);
		return pd;
	}
	
	
	/**
	 * 
	 * 描述：删除附件
	 * @Title: fileUpload
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping("/download")
	@ResponseBody
	public void download(){
		PageData pd = this.getPageData();
		String attachmentId = pd.getString("attachmentId");
		attachmentService.download(attachmentId);
	}
}
