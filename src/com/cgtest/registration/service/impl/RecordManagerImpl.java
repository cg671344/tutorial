package com.cgtest.registration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cgtest.biz.pojo.MsColumnData;
import com.cgtest.biz.pojo.PageModel;
import com.cgtest.biz.pojo.PieDataBean;
import com.cgtest.biz.pojo.WeekData;
import com.cgtest.registration.dao.RecordDao;
import com.cgtest.registration.model.Record;
import com.cgtest.registration.model.Record.HankouSequence;
import com.cgtest.registration.service.RecordManager;

@Component
@Transactional
public class RecordManagerImpl implements RecordManager {
	
	@Autowired
	private RecordDao recordDao;
	
	public void saveRecord(Record record){
		recordDao.save(record);
	}
	
	public Record getRecordById(String id){
		return recordDao.getRecordById(id);
	}
	
	public Record getRecordByReportNo(int reportNo){
		return recordDao.getRecordByReportNo(reportNo);
	}
	
	public void deleteRecordById(String id) {
		recordDao.deleteRecordById(id);
	}
	
	public void deleteRecordByIds(List<String> ids){
		for(String id: ids){
			this.deleteRecordById(id);
		}
	}
	
	public List<PieDataBean> getZoneClass(){
		return recordDao.getZoneClass();
	}
	
	public PageModel getPagedRecord(int pageNo, int pageSize, String startDate, String endDate){
		return recordDao.findPagedRecord(pageNo, pageSize, startDate, endDate);
	}
	
	
	public List<MsColumnData> getResponsibilityZoneData(String startDate, String endDate, HankouSequence seq){
		return recordDao.getResponsibilityZoneData(startDate, endDate, seq);
	}
	
	public List<MsColumnData> getZoneAnalysticData(String startDate, String endDate, HankouSequence seq){
		return recordDao.getZoneAnalysticData(startDate, endDate, seq);
	}
	
	public List<MsColumnData> getHangongAnalysticData(String startDate,
			String endDate, String hangongNo, String hangongName,
			HankouSequence seq) {
		return recordDao.getHangongAnalysticData(startDate, endDate, hangongNo, hangongName,seq);
	}
	
	public PageModel getPagedHangongRecord(final int pageNo, final int pageSize,
			final String startDate, final String endDate, final String hangongNo,final HankouSequence seq){
		return recordDao.getPagedHangongRecord(pageNo, pageSize, startDate, endDate, hangongNo, seq);
	}
	
	public WeekData getWeekData(String startDate, String endDate, HankouSequence seq){
		return recordDao.getWeekData(startDate, endDate, seq);
	}
	
	public PageModel getPagedHangongChartNoQueryRecord(String chartNo, String hankouNo, int pageNo, int pageSize){
		return recordDao.getPagedHangongChartNoQueryRecord(chartNo, hankouNo, pageNo, pageSize);
	}
	
	public long[] getDipianTotalFanxiuTotalPair(String startdate, HankouSequence seq){
		return recordDao.getDipianTotalFanxiuTotalPair(startdate, seq);
	}
}
