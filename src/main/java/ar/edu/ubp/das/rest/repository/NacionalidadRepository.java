package ar.edu.ubp.das.rest.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import ar.edu.ubp.das.rest.classes.Nacionalidad;

@Repository
public class NacionalidadRepository {
	
    @Autowired
    private JdbcTemplate jdbcTpl;
    
	@SuppressWarnings("unchecked")
	public List<Nacionalidad> getNacionalidades() {
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
    	   .withProcedureName("get_nacionalidades")
           .withSchemaName("dbo")
       	   .returningResultSet("nacionalidades", BeanPropertyRowMapper.newInstance(Nacionalidad.class));
       	
       	Map<String, Object> out = jdbcCall.execute();
       	return (List<Nacionalidad>)out.get("nacionalidades");
    }

}
