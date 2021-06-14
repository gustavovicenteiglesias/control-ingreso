package com.unsada.integradora.dao;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.SesionPresencial;
@Transactional
public interface SesionPresencialDao extends CrudRepository<SesionPresencial, Integer> {
	@Modifying
	 @Query(value="SELECT sp.* FROM sesion_presencial sp \r\n" + 
	 		"INNER JOIN solicitud s ON s.id_sesion_presencial=sp.id_sesion_presencial\r\n" + 
	 		"INNER JOIN ddjj d ON d.id_ddjj=s.id_ddjj\r\n" + 
	 		"INNER JOIN persona p ON p.id_persona=d.id_persona\r\n" + 
	 		"WHERE p.id_persona=:id_persona",nativeQuery = true )
	Iterable<SesionPresencial> session_persona (@Param("id_persona") int id_persona );
	@Query(value="SELECT sp.* FROM sesion_presencial sp\r\n" + 
			"INNER JOIN cohorte_horario ch ON ch.id_cohorte_horario=sp.id_cohorte_horario \r\n" + 
			"INNER JOIN horario h ON h.id_horario=ch.id_horario \r\n" + 
			"WHERE h.id_horario=?",nativeQuery = true)
	
	Iterable<SesionPresencial> findSesionHoratio(Integer id_Horario);
}
