package ar.edu.ubp.das.rest.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ubp.das.rest.classes.Actividad;
import ar.edu.ubp.das.rest.classes.Equipo;
import ar.edu.ubp.das.rest.classes.Persona;
import ar.edu.ubp.das.rest.classes.PersonaData;


@Repository
public class PersonaRepository {
	
    @Autowired
    private JdbcTemplate jdbcTpl;
    
    @SuppressWarnings("unchecked")
	public List<PersonaData> getPersona(int nroPersona) {
    	
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("nroPersona", nroPersona);
        
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
    	   .withProcedureName("get_persona")
           .withSchemaName("dbo")
       	   .returningResultSet("persona", BeanPropertyRowMapper.newInstance(PersonaData.class));
       	
       	Map<String, Object> out = jdbcCall.execute(in);
       	return (List<PersonaData>)out.get("persona");
    }
    
    @SuppressWarnings("unchecked")
	public List<Persona> getPersonas() {
    	
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("string_busqueda", null);
        
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
    	   .withProcedureName("get_personas")
           .withSchemaName("dbo")
       	   .returningResultSet("personas", BeanPropertyRowMapper.newInstance(Persona.class));
       	
       	Map<String, Object> out = jdbcCall.execute(in);
       	return (List<Persona>)out.get("personas");
    }
    
	@SuppressWarnings("unchecked")
	public List<Equipo> getEquipoPersona(int nroPersona) {
    	
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("nro_persona", nroPersona);
        
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
    	   .withProcedureName("get_equipos_persona")
           .withSchemaName("dbo")
       	   .returningResultSet("equipos", BeanPropertyRowMapper.newInstance(Equipo.class));
       	
       	Map<String, Object> out = jdbcCall.execute(in);
       	return (List<Equipo>)out.get("equipos");
    }
	
	@SuppressWarnings("unchecked")
	public List<Actividad> getActividadesPersona(int nroPersona) {
    	
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("nro_persona", nroPersona);
        
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
    	   .withProcedureName("get_actividades_persona")
           .withSchemaName("dbo")
       	   .returningResultSet("actividades", BeanPropertyRowMapper.newInstance(Actividad.class));
       	
       	Map<String, Object> out = jdbcCall.execute(in);
       	return (List<Actividad>)out.get("actividades");
    }
	
	@SuppressWarnings("unchecked")
	public List<PersonaData> deletePersona(int nroPersona) {
    	
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("nro_persona", nroPersona);
        
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
    	   .withProcedureName("delete_persona")
           .withSchemaName("dbo")
           .returningResultSet("personas", BeanPropertyRowMapper.newInstance(PersonaData.class));
       	
       	Map<String, Object> out = jdbcCall.execute(in);
       	
       	return (List<PersonaData>)out.get("personas");
    }
	
    @Transactional
    public PersonaData insPersona(PersonaData data) {
   
        SqlParameterSource in = new MapSqlParameterSource()
           .addValue("apellido", data.getApellido())
           .addValue("nombre", data.getNombre())
           .addValue("clave", data.getClave())
           .addValue("correo", data.getCorreo())
           .addValue("cod_genero",data.getCodGenero())
           .addValue("fecha_nacimiento",data.getFechaNacimiento())
           .addValue("cod_nacionalidad",data.getCodNacionalidad())
           .addValue("equipos",data.getEquipos())
           .addValue("actividades",data.getActividades());
        
           
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTpl)
           .withProcedureName("ins_persona")
           .withSchemaName("dbo");
        jdbcCall.execute(in);

        return data;
    }
	
}
