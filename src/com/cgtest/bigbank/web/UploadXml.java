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

import com.cgtest.exception.MyException;
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
			// 转型为MultipartHttpRequest：  
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获得文件：  
			MultipartFile orginalFile = multipartRequest.getFile("uploadfile");
			// 获得文件名：  
			String filename = orginalFile.getOriginalFilename();
			int suffixIndex = StringUtils.indexOf(filename, '.');
			suffix = StringUtils.substring(filename, suffixIndex, filename.length());
			String dataPath = request.getSession().getServletContext().getRealPath("/data");
			//使用上传时间作为新文件的名字
			Date date = new Date();
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateString = dateFormat.format(date);
			filePath = dataPath + "\\" + dateString + suffix;
			logger.info("Xls file pload, filename:" + filePath);
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					filePath));// 存放文件的绝对路径
			InputStream is = null;// 附件输入流
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
			errorMsg = "文件上传失败";
			logger.error("上传文件失败", e);
		}
		try{
			readXls(filePath, suffix);	
		}catch(Exception e){
			status = FAILED;
			logger.error("解析XML文件失败", e);
			errorMsg = e.getMessage();
		}
		
        Map<String,String> map = new HashMap<String,String>();  
        map.put("status", status); // 通知页面数据提交成功
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
			// 转型为MultipartHttpRequest：  
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获得文件：  
			MultipartFile orginalFile = multipartRequest.getFile("uploadfile");
			// 获得文件名：  
			String filename = orginalFile.getOriginalFilename();
			int suffixIndex = StringUtils.indexOf(filename, '.');
			filename = URLDecoder.decode(filename,"utf-8");
			suffix = StringUtils.substring(filename, suffixIndex, filename.length());
			String dataPath = request.getSession().getServletContext().getRealPath("/data");
			//使用上传时间作为新文件的名字
			Date date = new Date();
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateString = dateFormat.format(date);
			filePath = dataPath + "\\" + "hangong_" + dateString + suffix;
			logger.info("Xls file pload, filename:" + filePath);
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					filePath));// 存放文件的绝对路径
			InputStream is = null;// 附件输入流
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
			errorMsg = "文件上传失败";
			logger.error("上传文件失败", e);
		}
		try{
			readHangongXls(filePath, suffix);	
		}catch(Exception e){
			status = FAILED;
			logger.error("解析XML文件失败", e);
			errorMsg = e.getMessage();
		}
		
        Map<String,String> map = new HashMap<String,String>();  
        map.put("status", status); // 通知页面数据提交成功
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
			workbook = new HSSFWorkbook(is);//先按Excel2003解析
		}else{
        	workbook = new XSSFWorkbook(filePath);//尝试Excel2007以上版本解析			
		}	
		Date processTime = new Date();
		// 循环工作表Sheet  
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			if (numSheet != 0)
				continue;
			Sheet hssfSheet = workbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row   
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				try{
					if (rowNum == 0)
						continue;
					Row hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow == null) {
						continue;
					}
					//校验制造号是否重复,如果重复,略过此行数据
					Cell reportNoCell = hssfRow.getCell(4);//制造位于第一列
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
					
					//标准模板有15列数据
					if(hssfRow.getLastCellNum() < 15){
						throw new MyException("Excel模板错误，数据小于15列！");
					}
					// 循环列Cell
					for (int cellNum = 0; cellNum < 15; cellNum++) {
						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}
						String value = getValue(hssfCell);
						if("".equals(value)){
							throw new MyException("有数据项为空");
						}
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
								String zeroToFive = StringUtils.substring(value, 0, 5);// 第0—4位，从0开始
								String threeToFive = StringUtils.substring(value, 3, 6);// 第3——5位，从0开始
								String two = StringUtils.substring(value, 3, 4);//第3位，从0开始
								//采用先特殊，后一般的处理原则
								//0、小区域“EUF8N”->8N 大区域N
								//1、xLHQ->L xLHP -> L xVVP->R xTES->N xASG->W
								//2、xS->N xP->L xM->L xD->L xE->L
								//3、等轴图号第2～3位是区域，第3位是区域大类
								if(zeroToFive.equals("EUF8N")){
									record.setZone("8N");
									record.setZoneClass("N");
								}else if (threeToFive.equals("LHQ")
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
									//判断是不是ES,如果是ES，则与前面两个字符一起组成细化区域
									String twoToFourEs = StringUtils.substring(value, 2, 4);
									if(twoToFourEs.equals("ES")){
										record.setZone(StringUtils.substring(value, 0, 4));
									}else{
										record.setZone(twoToFourEs);
									}
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
								//大区域必须是K、L、R、W、N中的其中一个，责任区错误
								if(!record.getZoneClass().equals("K") &&
										!record.getZoneClass().equals("L") &&
										!record.getZoneClass().equals("R") &&
										!record.getZoneClass().equals("W") &&
										!record.getZoneClass().equals("N")){
									throw new MyException("责任区错误,必须是K、L、R、W、N其中一个");
								}
								break;
							case 4:
								//reportNo作为唯一标志，已经存储
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
								String dateString = StringUtils.remove(value, '-');
								record.setJianyanDate(dateString);
								if(dateString == null || dateString.length() != 8){
									throw new MyException("日期必须是2013-01-01或20130101格式的文本");
								}
								break;
							case 8:
								if("NI".equalsIgnoreCase(value)){
									record.setJianyanResult("Y");
								}else{
									record.setJianyanResult(value);	
								}
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
				}catch(MyException e){
					throw new RuntimeException("处理第" + (rowNum + 1) +"行数据出错"+"("+ e.getMessage()+")");
				}catch(Exception e){
					throw new RuntimeException("处理第" + (rowNum + 1) +"行数据出错，请检查数据!");
				}
			}
		}
	}

	private void readHangongXls(String filePath,String suffix) throws IOException {
		Workbook workbook = null;
		if(Excel2003Suffix.equals(suffix)){
			InputStream is = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(is);//先按Excel2003解析
		}else{
        	workbook = new XSSFWorkbook(filePath);//尝试Excel2007以上版本解析			
		}	
		// 循环工作表Sheet  
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			if (numSheet != 0)
				continue;
			Sheet hssfSheet = workbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row   
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
					//判断重复
					Hangong hangongInDB = hangongManager.getHangongbyNo(hangong.getHangongNo());
					if(hangongInDB == null){
						hangongManager.save(hangong);
					}else{
						hangongManager.update(hangong);
					}
					
				}catch(Exception e){
					throw new RuntimeException("处理第" + (rowNum + 1) +"行数据出错，请检查数据!");
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
