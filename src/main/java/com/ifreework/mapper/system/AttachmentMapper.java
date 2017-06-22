package com.ifreework.mapper.system;

import com.ifreework.entity.system.Attachment;


public interface AttachmentMapper  {
	public Attachment getAttachmentById(String attachmentId);
	public void add(Attachment attachment);
	public void update(Attachment attachment);
	public void delete(String attachmentId);
}
