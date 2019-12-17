package com.savek.RestJpaSpringExampleApp.repository.impl;

import com.savek.RestJpaSpringExampleApp.model.CustWithAdr;
import com.savek.RestJpaSpringExampleApp.repository.CustWithAdrRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCustWithAdrRepository implements CustWithAdrRepository {

	static Log log = LogFactory.getLog(JdbcCustWithAdrRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public CustWithAdr getByCustomerId(Long id) {

		String sql = "select cust.id, " +
				"            cust.first_name, " +
				"            cust.last_name, " +
				"            cust.middle_name, " +
				"            adr_reg.contry || ', г. ' || adr_reg.city || ', ул. ' || adr_reg.street || ', дом ' || adr_reg.house || ', кв. ' || adr_reg.flat adr_reg, " +
				"            adr_act.contry || ', г. ' || adr_act.city || ', ул. ' || adr_act.street || ', дом ' || adr_act.house || ', кв. ' || adr_act.flat adr_act " +
				"       from customer cust, address adr_act, address adr_reg " +
				"      where cust.registred_address_id = adr_reg.id " +
				"        and cust.actual_address_id = adr_act.id " +
				"        and cust.id = :id ";

		log.debug("sql = " + sql + ", :id = " + id);

		return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), (rs, rowNum) ->
				new CustWithAdr(rs.getLong("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("middle_name"),
						rs.getString("adr_reg"),
						rs.getString("adr_act"
						))
		);
	}
}
