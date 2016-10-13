package com.mastek.dna.api.db;

import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.apache.commons.lang3.tuple.Pair;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseChecker
{
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public abstract String getXmlTemplate();

	public abstract String getTableName();

	public String getTableIdentifier()
	{
		return "id";
	}

	public final void assertTable(final int id, final Collection<Pair<String, Object>> replacements) throws DataSetException, SQLException, DatabaseUnitException
	{
		final ITable expectedTable = loadTable(replacements);

		final ITable actualTable = DefaultColumnFilter.excludedColumnsTable(new DatabaseDataSourceConnection(dataSource)
				.createQueryTable(getTableName(), String.format("SELECT * FROM %s where %s = %d", getTableName(), getTableIdentifier(), id)),
				new String[] { "last_update_timestamp" });

		Assertion.assertEquals(expectedTable, actualTable);
	}

	public final void assertNoRow(final int id)
	{
		Assert.assertThat("Unexpected row count",
				jdbcTemplate.queryForObject(String.format("SELECT COUNT(*) FROM %s WHERE %s = ?", getTableName(), getTableIdentifier()), Integer.class, id),
				Is.is(0));
	}

	private final ITable loadTable(final Collection<Pair<String, Object>> replacements) throws DataSetException
	{
		final DataFileLoader dataFileLoader = new FlatXmlDataFileLoader();
		final ITable expectedTable = dataFileLoader.load(getXmlTemplate()).getTable(getTableName());

		final ReplacementTable replacementTable = new ReplacementTable(expectedTable);

		replacements.forEach(p -> replacementTable.addReplacementObject(p.getLeft(), p.getRight()));

		return replacementTable;
	}

}
