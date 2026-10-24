package com.den.repository;

import com.den.model.ext.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Denis on 30.09.2014.
 */
public interface AttachmentPersistence extends JpaRepository<Attachment, Long> {
}
