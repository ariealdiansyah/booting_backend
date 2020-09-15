package com.lawencon.booting.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.booting.model.ReportAllListClient;
import com.lawencon.booting.model.ReportTotalTicketAgent;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperReportService {

	public byte[] allListClient(List<ReportAllListClient> data, HttpServletResponse res) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			File file = ResourceUtils.getFile("classpath:list_client.jrxml");
			JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("List Client", "List Client");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, ds);
			res.setContentType("application/pdf");
			res.addHeader("Content-Disposition", "inline; filename=ListClient.pdf;");
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return baos.toByteArray();
	}
	
	public byte[] totalTicketAgents(List<ReportTotalTicketAgent> data, String nama, String nip, HttpServletResponse res) {
		ByteArrayOutputStream baot = new ByteArrayOutputStream();
		try {
			File file = ResourceUtils.getFile("classpath:total_ticket_agent.jrxml");
			JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("Nama Agent", nama);
			parameters.put("nip", nip);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, ds);
			res.setContentType("application/pdf");
			res.addHeader("Content-Disposition", "inline; filename=TicketReportSummary.pdf;");
			JasperExportManager.exportReportToPdfStream(jasperPrint, baot);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return baot.toByteArray();
	}
}
