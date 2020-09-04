package com.lawencon.booting.utility;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public String totalTicketAgent(List<ReportTotalTicketAgent> data, String nama, String nip) {
		try {
			File file = ResourceUtils.getFile("classpath:total_ticket_agent.jrxml");
			String path = "C://Users//Dell//Documents//Lawencon//Final Project//";
			JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("Nama Agent", nama);
			parameters.put("nip", nip);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, ds);
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "//totalTicketByAgentFixed.pdf");

		} catch (Exception e) {
			e.printStackTrace();
			return "Gagal Export File";
		}
		return "Berhasil Export File";
	}
}
