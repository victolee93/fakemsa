package com.victolee.fakemsa.repository;

import com.victolee.fakemsa.entity.MailLogEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MailLogRepository extends CrudRepository<MailLogEntity, String> {
    List<MailLogEntity> findAllByDateContaining(String date);
}
