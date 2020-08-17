package com.victolee.fakemsa.repository;

import com.victolee.fakemsa.entity.MailLogEntity;
import org.springframework.data.repository.CrudRepository;

public interface MailLogRepository extends CrudRepository<MailLogEntity, String> {
}
