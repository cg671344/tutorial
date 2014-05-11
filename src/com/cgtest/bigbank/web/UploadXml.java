package com.cgtest.bigbank.web;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cgtest.registration.model.Hangong;
import com.cgtest.registration.model.Record;
import com.cgtest.registration.model.Record.HankouSequence;
import com.cgtest.registration.service.HangongManager;
import com.cgtest.registration.service.RecordManager;

@Controller
@RequestMapping("/file")
public class UploadXml {

	private static Logger logger = LoggerFactory.getLogger(UploadXml.class);
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String Excel2003Suffix = ".xls";
	
	@Autowired
	RecordManager recordManager;
	
	@Autowired
	HangongManager hangongManager;

    @RequestMapping("/uploadingFile.do")
    public String handleRequest(ModelMap modelMap) throws Exception {
        return "/uploadingExcel/uploadingFile";
    }
	
	@ResponseBody
	@RequestMapping(value = "/xlsUpload.do", method = RequestMethod.POST)
	public String xlsUpload(HttpServletRequest request) {
		String status = SUCCESS;
		String errorMsg = "";
		String filePath = null;
		String suffix = null;
		try {
			// ת��ΪMultipartHttpRequest��  
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// ����ļ���  
			MultipartFile orginalFile = multipartRequest.getFile("uploadfile");
			// ����ļ�����  
			String filename = orginalFile.getOriginalFilename();
			int suffixIndex = StringUtils.indexOf(filename, '.');
			suffix = StringUtils.substring(filename, suffixIndex, filename.length());
			String dataPath = request.getSession().getServletContext().getRealPath("/data");
			//ʹ���ϴ�ʱ����Ϊ���ļ�������
			Date date = new Date();
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateString = dateFormat.format(date);
			filePath = dataPath + "\\" + dateString + suffix;
			logger.info("Xls file pload, filename:" + filePath);
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					filePath));// ����ļ��ľ���·��
			InputStream is = null;// ����������
			try {
				is = orginalFile.getInputStream();
				byte[] b = new byte[is.available()];
				is.read(b);
				out.write(b);
			} catch (IOException exception) {
				exception.printStackTrace();
			} finally {
				if (is != null) {
					is.close();
				}
				if (out != null) {
					out.close();
				}
			}
		} catch (IOException e) {
			status = FAILED;
			errorMsg = "�ļ��ϴ�ʧ��";
			logger.error("�ϴ��ļ�ʧ��", e);
		}
		try{
			readXls(filePath, suffix);	
		}catch(Exception e){
			status = FAILED;
			logger.error("����XML�ļ�ʧ��", e);
			errorMsg = e.getMessage();
		}
		
        Map<String,String> map = new HashMap<String,String>();  
        map.put("status", status); // ֪ͨҳ�������ύ�ɹ�
        try{
        	map.put("message", URLEncoder.encode(errorMsg,"UTF-8"));	
        }catch(Exception e){
        	logger.error("Encode exception",e);
        }
        JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonObject.toString();
	}

    @RequestMapping("/uploadingHangongFile.do")
    public String uploadingHangongFile(ModelMap modelMap) throws Exception {
        return "/uploadingExcel/uploadingHangongFile";
    }
	
	@ResponseBody
	@RequestMapping(value = "/hangongXlsUpload.do", method = RequestMethod.POST)
	public String hangongXlsUpload(HttpServletRequest request) {
		String status = SUCCESS;
		String errorMsg = "";
		String filePath = null;
		String suffix = null;
		try {
			// ת��ΪMultipartHttpRequest��  
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// ����ļ���  
			MultipartFile orginalFile = multipartRequest.getFile("uploadfile");
			// ����ļ�����  
			String filename = orginalFile.getOriginalFilename();
			int suffixIndex = StringUtils.indexOf(filename, '.');
			filename = URLDecoder.decode(filename,"utf-8");
			suffix = StringUtils.substring(filename, suffixIndex, filename.length());
			String dataPath = request.getSession().getServletContext().getRealPath("/data");
			//ʹ���ϴ�ʱ����Ϊ���ļ�������
			Date date = new Date();
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateString = dateFormat.format(date);
			filePath = dataPath + "\\" + "hangong_" + dateString + suffix;
			logger.info("Xls file pload, filename:" + filePath);
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					filePath));// ����ļ��ľ���·��
			InputStream is = null;// ����������
			try {
				is = orginalFile.getInputStream();
				byte[] b = new byte[is.available()];
				is.read(b);
				out.write(b);
			} catch (IOException exception) {
				exception.printStackTrace();
			} finally {
				if (is != null) {
					is.close();
				}
				if (out != null) {
					out.close();
				}
			}
		} catch (IOException e) {
			status = FAILED;
			errorMsg = "�ļ��ϴ�ʧ��";
			logger.error("�ϴ��ļ�ʧ��", e);
		}
		try{
			readHangongXls(filePath, suffix);	
		}catch(Exception e){
			status = FAILED;
			logger.error("����XML�ļ�ʧ��", e);
			errorMsg = e.getMessage();
		}
		
        Map<String,String> map = new HashMap<String,String>();  
        map.put("status", status); // ֪ͨҳ�������ύ�ɹ�
        try{
        	map.put("message", URLEncoder.encode(errorMsg,"UTF-8"));	
        }catch(Exception e){
        	logger.error("Encode exception",e);
        }
        JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonObject.toString();
	}
	
	
	private void readXls(String filePath,String suffix) throws IOException {
		Workbook workbook = null;
		if(Excel2003Suffix.equals(suffix)){
			InputStream is = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(is);//�Ȱ�Excel2003����
		}else{
        	workbook = new XSSFWorkbook(filePath);//����Excel2007���ϰ汾����			
		}	
		Date processTime = new Date();
		// ѭ��������Sheet  
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			if (numSheet != 0)
				continue;
			Sheet hssfSheet = workbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// ѭ����Row   
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				try{
					if (rowNum == 0)
						continue;
					Row hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow == null) {
						continue;
					}
					//У��������Ƿ��ظ�,����ظ�,�Թ���������
					Cell reportNoCell = hssfRow.getCell(4);//����λ�ڵ�һ��
					if (reportNoCell == null) {
						continue;
					}
					String reportNoString = getValue(reportNoCell);
					int reportNo = (int)Math.round(Double.valueOf(reportNoString));
					Record exsit = recordManager.getRecordByReportNo(reportNo);
					if(exsit != null){
						continue;
					}
					Record record = new Record();
					record.setCreateTime(processTime);
					record.setReportNo(reportNo);
					// ѭ����Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}
						String value = getValue(hssfCell);
						switch (cellNum) {
							case 0:
								int serialNo = (int)Math.round(Double.valueOf(value));
								record.setSerialNo(serialNo);
								break;
							case 1:
								int zhizaoNo = (int)Math.round(Double.valueOf(value));
								record.setZhizaoNo(zhizaoNo);
								break;
							case 2:
								if(value.endsWith(Record.SecondHanKouSuffix.R1.toString()) ||
										value.endsWith(Record.SecondHanKouSuffix.R2.toString())
										||value.endsWith(Record.SecondHanKouSuffix.X.toString())){
									record.setHankouSequence(HankouSequence.SECOND);
								}else{
									record.setHankouSequence(HankouSequence.FIRST);
								}
								record.setHankouNo(value);
								break;	
							case 3:
								record.setChartNo(value);
								String threeToFive = StringUtils.substring(value, 3, 6);// ��3����5λ����0��ʼ
								String two = StringUtils.substring(value, 3, 4);//��3λ����0��ʼ
								//���������⣬��һ��Ĵ���ԭ��
								//1��xLHQ->L xLHP -> L xVVP->R xTES->N xASG->W
								//2��xS->N xP->L xM->L xD->L xE->L
								//3������ͼ�ŵ�2��3λ�����򣬵�3λ���������
								if (threeToFive.equals("LHQ")
									|| threeToFive.equals("LHP")){
									String twoToFive = StringUtils.substring(value, 2, 6);
									record.setZone(twoToFive);
									record.setZoneClass("L");
								}else if(threeToFive.equals("VVP")){
									String twoToFive = StringUtils.substring(value, 2, 6);
									record.setZone(twoToFive);
									record.setZoneClass("R");
								}else if(threeToFive.equals("TES")){
									String twoToFive = StringUtils.substring(value, 2, 6);
									record.setZone(twoToFive);
									record.setZoneClass("N");
								}else if(threeToFive.equals("ASG")) {
									String twoToFive = StringUtils.substring(value, 2, 6);
									record.setZone(twoToFive);
									record.setZoneClass("W");
								}else if(two.equals("S")){
									record.setZone(StringUtils.substring(value, 2, 4));
									record.setZoneClass("N");
								} else if (two.equals("P")
									|| two.equals("M")
									|| two.equals("D")
									|| two.equals("E")) {
									record.setZone(StringUtils.substring(value, 2, 4));
									record.setZoneClass("L");
								}else {
									record.setZone(StringUtils.substring(value, 2, 4));
									record.setZoneClass(StringUtils.substring(value, 3, 4));
								}
								break;
							case 4:
								//reportNo��ΪΨһ��־���Ѿ��洢
								break;
							case 5:
								int pianziNumber = (int)Math.round(Double.valueOf(value));
								record.setPianziNumber(pianziNumber);
								break;
							case 6:
								int fanxiuNumber = (int)Math.round(Double.valueOf(value));
								record.setFanxiuNumber(fanxiuNumber);
								break;
							case 7:
								record.setJianyanDate(StringUtils.remove(value, '-'));
								break;
							case 8:
								record.setJianyanResult(value);
								break;	
							case 9:
								record.setGuige1(value);
								break;
							case 10:
								record.setGuige2(value);
								break;
							case 11:
								record.setRccmLevel(value);
								break;
							case 12:
								record.setHanfengType(value);
								break;
							case 13:
								record.setWeituoUnit(value);
								break;
							case 14:
								record.setHangongNo(value);
								break;
							default:
						}
					}
					recordManager.saveRecord(record);
				}catch(Exception e){
					throw new RuntimeException("�����" + (rowNum + 1) +"�����ݳ�����������!");
				}
			}
		}
	}

	private void readHangongXls(String filePath,String suffix) throws IOException {
		Workbook workbook = null;
		if(Excel2003Suffix.equals(suffix)){
			InputStream is = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(is);//�Ȱ�Excel2003����
		}else{
        	workbook = new XSSFWorkbook(filePath);//����Excel2007���ϰ汾����			
		}	
		// ѭ��������Sheet  
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			if (numSheet != 0)
				continue;
			Sheet hssfSheet = workbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// ѭ����Row   
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				try{
					if (rowNum == 0)
						continue;
					Row hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow == null) {
						continue;
					}
					Hangong hangong = new Hangong();
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}
						String value = getValue(hssfCell);
						switch (cellNum) {
							case 0:
								hangong.setHangongNo(value);
								break;
							case 1:
								hangong.setHangongName(value);
								break;	
						}
					}
					//�ж��ظ�
					Hangong hangongInDB = hangongManager.getHangongbyNo(hangong.getHangongNo());
					if(hangongInDB == null){
						hangongManager.save(hangong);
					}else{
						hangongManager.update(hangong);
					}
					
				}catch(Exception e){
					throw new RuntimeException("�����" + (rowNum + 1) +"�����ݳ�����������!");
				}
			}
		}
	}
	
	
	@SuppressWarnings("static-access")
	private String getValue(Cell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}
