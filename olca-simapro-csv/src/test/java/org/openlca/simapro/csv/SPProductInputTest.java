package org.openlca.simapro.csv;

import org.junit.Assert;
import org.junit.Test;
import org.openlca.simapro.csv.model.ProductInputRow;
import org.openlca.simapro.csv.model.enums.DistributionType;

public class SPProductInputTest {

	private CsvConfig config = CsvConfig.getDefault();

	@Test
	public void testFromCsv() {
		String line = "Transport, freight train {RoW}| market for | Alloc Def, U;"
				+ "tkm;0,124620979212666;Lognormal;2,281;0;0;(1,1,4,5,4,na)";
		ProductInputRow input = ProductInputRow.fromCsv(line, config);
		Assert.assertEquals(
				"Transport, freight train {RoW}| market for | Alloc Def, U",
				input.getName());
		Assert.assertEquals("tkm", input.getUnit());
		Assert.assertEquals("0.124620979212666", input.getAmount());
		Assert.assertEquals(DistributionType.LOG_NORMAL, input
				.getUncertaintyDistribution().getType());
		Assert.assertEquals("(1,1,4,5,4,na)", input.getPedigreeUncertainty());
	}

	@Test
	public void testToCsv() {
		ProductInputRow input = new ProductInputRow();
		input.setName("test name");
		input.setUnit("kg");
		Assert.assertTrue(input.toCsv(config).startsWith("test name;kg"));
	}

}
