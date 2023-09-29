package ar.edu.ubp.das.rest.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import ar.edu.ubp.das.rest.classes.Genero;

@Repository
public class GeneroRepository {
	
    @Autowired
    private JdbcTemplate jdbcTpl;
    
	@SuppressWarnings("unchecked")
	public List<Genero> getGeneros() {
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
    	   .withProcedureName("get_generos")
           .withSchemaName("dbo")
       	   .returningResultSet("generos", BeanPropertyRowMapper.newInstance(Genero.class));
       	
       	Map<String, Object> out = jdbcCall.execute();
       	return (List<Genero>)out.get("generos");
    }

}
