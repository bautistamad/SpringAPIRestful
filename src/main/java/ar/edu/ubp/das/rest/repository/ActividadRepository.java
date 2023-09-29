package ar.edu.ubp.das.rest.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import ar.edu.ubp.das.rest.classes.Actividad;

@Repository
public class ActividadRepository {
	
    @Autowired
    private JdbcTemplate jdbcTpl;
    
	@SuppressWarnings("unchecked")
	public List<Actividad> getActividades() {
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
    	   .withProcedureName("get_actividades")
           .withSchemaName("dbo")
       	   .returningResultSet("actividades", BeanPropertyRowMapper.newInstance(Actividad.class));
       	
       	Map<String, Object> out = jdbcCall.execute();
       	return (List<Actividad>)out.get("actividades");
    }


}
