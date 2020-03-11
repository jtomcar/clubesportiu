/**
 *  @author Jose Torro Belda
 */

package es.uji.ei1027.clubesportiu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.clubesportiu.model.Nadador;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Repository // En Spring els DAOs van anotats amb @Repository
public class NadadorDao {

   private JdbcTemplate jdbcTemplate;

   // Obté el jdbcTemplate a partir del Data Source
   @Autowired
   public void setDataSource(DataSource dataSource) {
       jdbcTemplate = new JdbcTemplate(dataSource);
   }

   /* Afegeix el nadador a la base de dades */
   public void addNadador(Nadador nadador) {
       jdbcTemplate.update("INSERT INTO Nadador VALUES(?,?,?,?,?)",nadador.getNom(),
    		   nadador.getNumFederat(),nadador.getPais(),nadador.getEdat(),nadador.getGenere());
   }

   /* Esborra el nadador de la base de dades */
   public void deleteNadador(String nadador) {
       jdbcTemplate.update("DELETE FROM Nadador WHERE nom=?",nadador);
   }

   /* Actualitza els atributs del nadador
      (excepte el nom, que és la clau primària) */
   public void updateNadador(Nadador nadador) {
       jdbcTemplate.update("UPDATE nadador SET edat=?,pais=?,num_federat=?,genere=? WHERE nom=?"
    		   ,nadador.getEdat(),nadador.getPais(),nadador.getNumFederat(),nadador.getGenere(),nadador.getNom());
   }

   /* Obté el nadador amb el nom donat. Torna null si no existeix. */
   public Nadador getNadador(String nomNadador) {
       try {
           return jdbcTemplate.queryForObject("SELECT * FROM nadador WHERE nom=?",new NadadorRowMapper(),nomNadador);
       }
       catch(EmptyResultDataAccessException e) {
           return null;
       }
   }

   /* Obté tots els nadadors. Torna una llista buida si no n'hi ha cap. */
   public List<Nadador> getNadadors() {
       try {
           return jdbcTemplate.query("SELECT * FROM nadador;",new NadadorRowMapper());
       }
       catch(EmptyResultDataAccessException e) {
           return new ArrayList<Nadador>();
       }
   }
   
// Demana a Spring que ens proporcione una instància de NadadorDAO
// mitjanjant injecció de dependencies

   
}