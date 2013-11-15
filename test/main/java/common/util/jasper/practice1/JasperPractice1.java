package common.util.jasper.practice1;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperPractice1 {
	public static void main(String[] args) {
		exportToPdf();
	}

	public static void exportToPdf() {
		System.out.println("Start with Report Design ...");
		try {
			// Compile it generates .jasper
			String sourceFileName = JasperPractice1.class.getClassLoader().getResource("DataSourceReport.jrxml").getFile();
			JasperCompileManager.compileReportToFile(sourceFileName);

			String sourceJasperFileName = JasperPractice1.class.getClassLoader().getResource("DataSourceReport.jasper").getFile();
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ReportTitle", "Address Report");
			parameters.put("DataFile", "CustomDataSource.java");

			// fillreport it generates .jrprint
			JRBeanCollectionDataSource collectionDS = new JRBeanCollectionDataSource(CustomBeanFactory.getBeanCollection());
			JasperFillManager.fillReportToFile(sourceJasperFileName, parameters, collectionDS);

			// export
			String jrprintName = JasperPractice1.class.getClassLoader().getResource("DataSourceReport.jrprint").getFile();
			JasperExportManager.exportReportToPdfFile(jrprintName, "reports/datasource.pdf");

		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done reporting!!!");
	}
}
