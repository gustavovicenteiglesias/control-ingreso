package com.unsada.integradora.service.impl;

import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.EntidadAula;
import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.service.interfaces.ActividadServiceApi;
import com.unsada.integradora.service.interfaces.AsignarAulaInterface;
import com.unsada.integradora.service.interfaces.EntidadAulaServiceApi;
import com.unsada.integradora.service.interfaces.SesionPresencialServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AsignarAulaService implements AsignarAulaInterface {
    @Autowired
    EntidadAulaServiceApi aulaServiceApi;
    @Autowired
    ActividadServiceApi actividadServiceApi;
    @Autowired
    SesionPresencialServiceApi sesionPresencialServiceApi;
    @Override
    public ResponseEntity<String> asignarAulaEnFecha(int idAula, int idActividad, Date fecha) {

        Optional<EntidadAula> aula = aulaServiceApi.findById(idAula);
        Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
        List<SesionPresencial> sesiones = actividad.get().getCohortes().stream()
                .flatMap( cohorte -> cohorte.getCohorteHorarios().stream())
                .flatMap( cohorteHorario -> cohorteHorario.getSesionPresencials().stream())
                .filter(sesionPresencial -> !sesionPresencial.getFecha().equals(fecha)).collect(Collectors.toList());

		try{
		   /* if(sesiones.size() > 1){
		        En este caso encontraria dos sesiones para el mismo dia, dejo comentado porque hay algunos casos en los que se repite
		        en estos casos va a seleccionar la primera sesion que corresponda
		        return  new ResponseEntity<String>("Existe mas de una sesion para la misma actividad en la misma fecha", HttpStatus.BAD_REQUEST);
            }*/
		    SesionPresencial sesion = sesiones.get(0);
		    sesion.setEntidadAula(aula.get());
		    sesionPresencialServiceApi.save(sesion);
		    return  new ResponseEntity<String>("Success", HttpStatus.OK);
        }catch (Exception e){
		    return new ResponseEntity<String>("Error asignando aula", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> asignarAulaEnTodasLasSesiones(int idAula, int idActividad) {
        Optional<EntidadAula> aula = aulaServiceApi.findById(idAula);
        Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
        List<SesionPresencial> sesiones = actividad.get().getCohortes().stream()
                .flatMap( cohorte -> cohorte.getCohorteHorarios().stream())
                .flatMap( cohorteHorario -> cohorteHorario.getSesionPresencials().stream())
                .collect(Collectors.toList());

        try{
            EntidadAula au = aula.get();
            sesiones.forEach( sesion -> {
                sesion.setEntidadAula(au);
                sesionPresencialServiceApi.save(sesion);
            });
            return  new ResponseEntity<String>("Success", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Error asignando aula", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
