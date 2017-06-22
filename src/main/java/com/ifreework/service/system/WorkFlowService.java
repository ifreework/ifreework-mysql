package com.ifreework.service.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.web.multipart.MultipartFile;

import com.ifreework.common.entity.PageData;

public interface WorkFlowService {

	/**
	 * 
	 * 描述：部署新的流程
	 * @param files
	 * @return
	 * @throws IOException 
	 */
	PageData saveDeploye(MultipartFile[] files) throws IOException;

	/**
	 * 描述：查询部署对象信息，对应表（act_re_deployment）
	 * @return
	 */
	List<Deployment> queryDeploymentList();

	/**
	 * 
	 * 描述：查询流程定义的信息，对应表（act_re_procdef）
	 * @return
	 */
	List<ProcessDefinition> queryProcessDefinitionList();

	/**
	 * 描述：使用部署对象ID和资源图片名称，获取图片的输入流
	 * @param deploymentId
	 * @param imageName
	 * @return 
	 * @return
	 */
	InputStream findImageInputStream(String deploymentId, String imageName);

}