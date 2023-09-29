package ar.edu.ubp.das.rest.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import ar.edu.ubp.das.rest.classes.Equipo;

@Repository
public class EquipoRepository {
	

    @Autowired
    private JdbcTemplate jdbcTpl;
    
	@SuppressWarnings("unchecked")
	public List<Equipo> getEquipos() {
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
    	   .withProcedureName("get_equipos")
           .withSchemaName("dbo")
       	   .returningResultSet("equipos", BeanPropertyRowMapper.newInstance(Equipo.class));
       	
       	Map<String, Object> out = jdbcCall.execute();
       	return (List<Equipo>)out.get("equipos");
    }

}
