package com.lawencon.booting.utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
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

	public String allListClient(List<ReportAllListClient> data) {
		try {
			File file = ResourceUtils.getFile("classpath:list_client_all.jrxml");
			String path = "C://Users//Dell//Documents//Lawencon//Final Project//";
			JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("List Client", "List Client");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, ds);
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "//listClient.pdf");

		} catch (Exception e) {
			e.printStackTrace();
			return "Gagal Export File";
		}
		return "Berhasil Export File";
	}
	
	public String totalTicketAgent(List<ReportTotalTicketAgent> data, String nama, String nip, HttpServletResponse res) {
		try {
			File file = ResourceUtils.getFile("classpath:total_ticket_agent.jrxml");
			String path = "classpath";
			JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("Nama Agent", nama);
			parameters.put("nip", nip);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, ds);
			res.setContentType("application/pdf");
			res.addHeader("Content-Disposition", "inline; filename=jasperReport.pdf;");
			JasperExportManager.exportReportToPdfStream(jasperPrint, res.getOutputStream());
//			JasperExportManager.exportReportToPdfFile(jasperPrint );
			} catch (Exception e) {
			e.printStackTrace();
			return "Gagal Export File";
		}
		return "Berhasil Export File";
	}
}
