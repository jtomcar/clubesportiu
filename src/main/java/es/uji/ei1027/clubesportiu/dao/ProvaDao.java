package es.uji.ei1027.clubesportiu.dao;

import es.uji.ei1027.clubesportiu.model.Prova;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProvaDao {
    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /** Afegeix una prova nova*/
    void addProva(Prova prova){
        jdbcTemplate.update("INSERT INTO prova VALUES(?,?,?,?)",
                prova.getNom(),prova.getDescripcio(),prova.getTipus(),prova.getData());

    }
    /** Esborra una prova */
    void deleteNadador(Prova prova) {
        jdbcTemplate.update("DELETE FROM prova WHERE nom=?", prova.getNom());
    }

    void updateProva(Prova prova) {
        jdbcTemplate.update("UPDATE prova SET descripcio=?,tipus=?,data=?",
                                prova.getDescripcio(),prova.getTipus(),prova.getData());
    }

    public Prova getProva(String nomProva){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM prova WHERE nom=?",new ProvaRowMapper(),nomProva);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Prova> getProva() {
        try {
            return jdbcTemplate.query("SELECT * FROM prova;",new ProvaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Prova>();
        }
    }
}
