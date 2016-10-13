package com.mastek.dna.api.db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseResetter
{
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void reset(final String dataset) throws SQLException, DatabaseUnitException
	{
		loadData();
		resetSequences();
	}

	private void loadData() throws SQLException, DatabaseUnitException
	{
		final IDatabaseConnection connection = new DatabaseDataSourceConnection(dataSource);
		final DataFileLoader dataFileLoader = new FlatXmlDataFileLoader();
		final IDataSet dataSet = dataFileLoader.load("/data/dataset.xml");

		try
		{
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		}
		finally
		{
			connection.close();
		}
	}

	private void resetSequences()
	{
		final List<String> sequences = jdbcTemplate.queryForList("SELECT c.relname FROM pg_class c WHERE c.relkind = 'S';", String.class);

		sequences.forEach(s -> jdbcTemplate.execute(String.format("ALTER SEQUENCE %s RESTART WITH %d", s, 1000)));
	}
}
