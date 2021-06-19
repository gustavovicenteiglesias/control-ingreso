package com.unsada.integradora.service.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;

import com.unsada.integradora.dao.EntidadAulaDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unsada.integradora.model.entity.EntidadAula;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Primary
@Repository
public interface EntidadAulaServiceApi extends EntidadAulaDao{


}
