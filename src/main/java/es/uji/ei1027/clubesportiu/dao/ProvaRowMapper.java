package es.uji.ei1027.clubesportiu.dao;

import es.uji.ei1027.clubesportiu.model.Prova;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvaRowMapper implements RowMapper<Prova> {

    @Override
    public Prova mapRow(ResultSet rs, int rowNum) throws SQLException {
        Prova prova = new Prova();
        prova.setNom(rs.getString("nom"));
        prova.setData(rs.getDate("data").toLocalDate());
        prova.setDescripcio(rs.getString("descripcio"));
        prova.setTipus(rs.getString("tipus"));
        return prova;
    }
}
