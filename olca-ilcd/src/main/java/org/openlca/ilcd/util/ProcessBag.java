package org.openlca.ilcd.util;

import java.util.Collections;
import java.util.List;

import org.openlca.ilcd.commons.Class;
import org.openlca.ilcd.commons.ClassificationInfo;
import org.openlca.ilcd.commons.CommissionerAndGoal;
import org.openlca.ilcd.commons.DataSetReference;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.commons.Time;
import org.openlca.ilcd.processes.AdminInfo;
import org.openlca.ilcd.processes.Completeness;
import org.openlca.ilcd.processes.ComplianceDeclaration;
import org.openlca.ilcd.processes.ComplianceDeclarationList;
import org.openlca.ilcd.processes.DataEntry;
import org.openlca.ilcd.processes.DataGenerator;
import org.openlca.ilcd.processes.DataSetInfo;
import org.openlca.ilcd.processes.Exchange;
import org.openlca.ilcd.processes.Geography;
import org.openlca.ilcd.processes.LCIMethod;
import org.openlca.ilcd.processes.ModellingAndValidation;
import org.openlca.ilcd.processes.Parameter;
import org.openlca.ilcd.processes.ParameterSection;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.ProcessInfo;
import org.openlca.ilcd.processes.ProcessName;
import org.openlca.ilcd.processes.Publication;
import org.openlca.ilcd.processes.QuantitativeReference;
import org.openlca.ilcd.processes.Representativeness;
import org.openlca.ilcd.processes.Review;
import org.openlca.ilcd.processes.Technology;
import org.openlca.ilcd.processes.Validation;
import org.openlca.ilcd.productmodel.ProductModel;

public class ProcessBag implements IBag<Process> {

	private Process process;
	private String[] langs;

	public ProcessBag(Process process, String... langs) {
		this.process = process;
		this.langs = langs;
	}

	@Override
	public Process getValue() {
		return process;
	}

	@Override
	public String getId() {
		DataSetInfo info = getDataSetInformation();
		if (info != null)
			return info.uuid;
		return null;
	}

	public String getName() {
		DataSetInfo info = getDataSetInformation();
		if (info == null || info.name == null)
			return null;
		ProcessName processName = info.name;
		StringBuilder builder = new StringBuilder();
		appendNamePart(processName.baseName, builder, null);
		appendNamePart(processName.mixAndLocationTypes, builder, ", ");
		appendNamePart(processName.treatmentStandardsRoutes, builder, ", ");
		appendNamePart(processName.functionalUnitFlowProperties, builder,
				", ");
		return builder.toString();
	}

	private void appendNamePart(List<LangString> parts, StringBuilder builder,
			String prefix) {
		if (parts != null) {
			String part = LangString.getFirst(parts, langs);
			if (part != null) {
				if (prefix != null) {
					builder.append(prefix);
				}
				builder.append(part);
			}
		}
	}

	public String getSynonyms() {
		DataSetInfo info = getDataSetInformation();
		if (info != null)
			return LangString.getFirst(info.synonyms, langs);
		return null;
	}

	public List<Class> getSortedClasses() {
		DataSetInfo info = getDataSetInformation();
		if (info != null) {
			ClassificationInfo classInfo = info.classificationInformation;
			return ClassList.sortedList(classInfo);
		}
		return Collections.emptyList();
	}

	public String getComment() {
		DataSetInfo info = getDataSetInformation();
		if (info != null)
			return LangString.getFirst(info.generalComment, langs);
		return null;
	}

	public Time getTime() {
		ProcessInfo info = process.processInfo;
		if (info != null) {
			return info.time;
		}
		return null;
	}

	public Geography getGeography() {
		ProcessInfo info = process.processInfo;
		if (info != null)
			return info.geography;
		return null;
	}

	public List<Integer> getReferenceFlowIds() {
		ProcessInfo info = process.processInfo;
		if (info != null) {
			QuantitativeReference qRef = info.quantitativeReference;
			if (qRef != null)
				return qRef.referenceToReferenceFlow;
		}
		return Collections.emptyList();
	}

	public Technology getTechnology() {
		ProcessInfo info = process.processInfo;
		if (info != null)
			return info.technology;
		return null;
	}

	public List<Parameter> getParameters() {
		ProcessInfo info = process.processInfo;
		if (info != null) {
			ParameterSection list = info.parameters;
			if (list != null && list.parameters != null) {
				return list.parameters;
			}
		}
		return Collections.emptyList();
	}

	public ProcessType getProcessType() {
		ModellingAndValidation mav = process.modellingAndValidation;
		if (mav != null) {
			LCIMethod method = mav.lciMethod;
			if (method != null)
				return method.processType;
		}
		return null;
	}

	public Representativeness getRepresentativeness() {
		ModellingAndValidation mav = process.modellingAndValidation;
		if (mav != null)
			return mav.representativeness;
		return null;
	}

	public Completeness getCompleteness() {
		ModellingAndValidation mav = process.modellingAndValidation;
		if (mav != null)
			return mav.completeness;
		return null;
	}

	public List<Review> getReviews() {
		ModellingAndValidation mav = process.modellingAndValidation;
		if (mav != null) {
			Validation validation = mav.validation;
			if (validation != null && validation.review != null) {
				return validation.review;
			}
		}
		return Collections.emptyList();
	}

	public List<ComplianceDeclaration> getComplianceDeclarations() {
		ModellingAndValidation mav = process.modellingAndValidation;
		if (mav != null) {
			ComplianceDeclarationList list = mav.complianceDeclarations;
			if (list != null && list.complianceDeclatations != null) {
				return list.complianceDeclatations;
			}
		}
		return Collections.emptyList();
	}

	public CommissionerAndGoal getCommissionerAndGoal() {
		AdminInfo info = process.administrativeInformation;
		if (info != null)
			return info.commissionerAndGoal;
		return null;
	}

	public DataGenerator getDataGenerator() {
		AdminInfo info = process.administrativeInformation;
		if (info != null)
			return info.dataGenerator;
		return null;
	}

	public DataEntry getDataEntry() {
		AdminInfo info = process.administrativeInformation;
		if (info != null)
			return info.dataEntry;
		return null;
	}

	public Publication getPublication() {
		AdminInfo info = process.administrativeInformation;
		if (info != null)
			return info.publication;
		return null;
	}

	public List<Exchange> getExchanges() {
		return process.exchanges;
	}

	public LCIMethod getLciMethod() {
		ModellingAndValidation mav = process.modellingAndValidation;
		if (mav != null)
			return mav.lciMethod;
		return null;
	}

	private DataSetInfo getDataSetInformation() {
		if (process.processInfo != null)
			return process.processInfo.dataSetInformation;
		return null;
	}

	public ProductModel getProductModel() {
		if (process.processInfo == null)
			return null;
		Other other = process.processInfo.other;
		if (other == null)
			return null;
		for (Object extension : other.getAny()) {
			if (extension instanceof ProductModel)
				return (ProductModel) extension;
		}
		return null;
	}

	public List<DataSetReference> getAllSources() {
		return SourceRefCollection.getAll(process, langs);
	}

	public boolean hasProductModel() {
		// TODO: check at least one process as reference
		return getProductModel() != null;
	}

}
