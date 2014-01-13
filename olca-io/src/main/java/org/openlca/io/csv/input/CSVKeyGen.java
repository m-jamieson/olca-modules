package org.openlca.io.csv.input;

import org.openlca.io.KeyGen;
import org.openlca.simapro.csv.model.SPElementaryFlow;
import org.openlca.simapro.csv.model.SPLiteratureReference;
import org.openlca.simapro.csv.model.SPProduct;
import org.openlca.simapro.csv.model.SPProductFlow;
import org.openlca.simapro.csv.model.SPWasteSpecification;
import org.openlca.simapro.csv.model.types.ProcessCategory;
import org.openlca.simapro.csv.model.types.SubCompartment;

public class CSVKeyGen {

	public static String forProcess(String name) {
		return KeyGen.get(name);
	}

	public static String forElementaryFlow(SPElementaryFlow elementaryFlow) {
		if (elementaryFlow == null)
			KeyGen.get("");

		String[] vals = new String[5];
		vals[0] = elementaryFlow.getType().getSubstance();
		if (elementaryFlow.getSubCompartment() != null)
			vals[1] = elementaryFlow.getSubCompartment().getValue();
		else
			vals[1] = SubCompartment.UNSPECIFIED.getValue();
		vals[2] = elementaryFlow.getName();
		vals[3] = elementaryFlow.getUnit();
		return KeyGen.get(vals);
	}

	public static String forProductFlow(SPProductFlow productFlow) {
		if (productFlow == null || !productFlow.hasReferenceData())
			return KeyGen.get("");

		String[] vals = new String[3];
		vals[0] = productFlow.getName();
		vals[1] = productFlow.getReferenceCategory();
		vals[2] = productFlow.getProcessCategory().getValue();
		return KeyGen.get(vals);
	}

	public static String forProduct(SPProduct product, ProcessCategory category) {
		if (product == null)
			return KeyGen.get("");

		String[] vals = new String[3];
		vals[0] = product.getName();
		vals[1] = product.getCategory();
		vals[2] = category.getValue();
		return KeyGen.get(vals);
	}

	public static String forWasteSpecification(
			SPWasteSpecification wasteSpecification, ProcessCategory category) {
		if (wasteSpecification == null)
			return KeyGen.get("");

		String[] vals = new String[3];
		vals[0] = wasteSpecification.getName();
		vals[1] = wasteSpecification.getCategory();
		vals[2] = category.getValue();
		return KeyGen.get(vals);
	}

	public static String forSource(SPLiteratureReference literatureReference) {
		if (literatureReference == null)
			return KeyGen.get("");
		String[] vals = new String[2];
		vals[0] = literatureReference.getName();
		vals[1] = literatureReference.getCategory();
		return KeyGen.get(vals);
	}

}