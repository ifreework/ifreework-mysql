package com.ifreework.service.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
@Service("workFlowService")
public class WorkFlowServiceImpl implements WorkFlowService {

	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 
	 * 描述：部署新的流程
	 * @param files
	 * @return
	 * @throws IOException 
	 */
	@Override
	public PageData saveDeploye(MultipartFile[] files) throws IOException {
		PageData pd = new PageData();
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			fileName = fileName.substring(0, fileName.lastIndexOf("."));

			ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());

			repositoryService.createDeployment()// 创建部署对象
					.name(fileName)// 添加部署名称
					.addZipInputStream(zipInputStream)//
					.deploy();// 完成部署
		}
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	/**
	 * 描述：查询部署对象信息，对应表（act_re_deployment）
	 * @return
	 */
	@Override
	public List<Deployment> queryDeploymentList() {
		List<Deployment> list = repositoryService.createDeploymentQuery()//创建部署对象查询
							.orderByDeploymenTime().asc()//
							.list();
		return list;
	}
	
	/**
	 * 
	 * 描述：查询流程定义的信息，对应表（act_re_procdef）
	 * @return
	 */
	@Override
	public List<ProcessDefinition> queryProcessDefinitionList() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()//创建流程定义查询
							.orderByProcessDefinitionVersion().asc()//
							.list();
		return list;
	}
	
	/**
	 * 描述：使用部署对象ID和资源图片名称，获取图片的输入流
	 * @param deploymentId
	 * @param imageName
	 * @return 
	 * @return
	 */
	@Override
	public InputStream findImageInputStream(String deploymentId,
			String imageName) {
		return repositoryService.getResourceAsStream(deploymentId, imageName);
	}
}
