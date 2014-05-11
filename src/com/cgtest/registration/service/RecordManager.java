package com.cgtest.registration.service;

import java.util.List;

import com.cgtest.biz.pojo.MsColumnData;
import com.cgtest.biz.pojo.PageModel;
import com.cgtest.biz.pojo.PieDataBean;
import com.cgtest.biz.pojo.WeekData;
import com.cgtest.registration.model.Record;
import com.cgtest.registration.model.Record.HankouSequence;
public interface RecordManager {
	/**
	 * 根据制造号获取记录
	 * @param reportNo
	 * @return
	 */
	public Record getRecordByReportNo(int reportNo);
	
	/**
	 * 根据记录id获取记录信息
	 * @param id
	 * @return
	 */
	public Record getRecordById(String id);
	
	public void saveRecord(Record record);
	public List<PieDataBean> getZoneClass();
	/**
	 * 获取所有记录的分页记录
	 * @param pageNo
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public PageModel getPagedRecord(int pageNo, int pageSize, String startDate,
			String endDate);
	/**
	 * 好哦去责任区的分析数据（大区域）
	 * @param startDate
	 * @param endDate
	 * @param seq
	 * @return
	 */
	public List<MsColumnData> getResponsibilityZoneData(String startDate, String endDate, HankouSequence seq);
	
	/**
	 *获取细化区域的分析数据（小区域） 
	 * @param startDate
	 * @param endDate
	 * @param seq
	 * @return
	 */
	public List<MsColumnData> getZoneAnalysticData(String startDate, String endDate, HankouSequence seq);
	
	public PageModel getPagedHangongRecord(final int pageNo, final int pageSize,
			final String startDate, final String endDate, final String hangongNo, HankouSequence seq);
	
	/**
	 * 焊工记录信息按照等轴图号等查询
	 * @param chartNo
	 * @param hankouNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel getPagedHangongChartNoQueryRecord(String chartNo, String hankouNo, int pageNo, int pageSize);
	
	/**
	 * 获取焊工的分析数据
	 * @param startDate
	 * @param endDate
	 * @param hangongNo
	 * @param hangongName
	 * @param seq
	 * @return
	 */
	public List<MsColumnData> getHangongAnalysticData(String startDate,
			String endDate, String hangongNo, String hangongName,
			HankouSequence seq);
	
	/**
	 * 删除记录
	 * @param id
	 */
	public void deleteRecordById(String id) ;
	public void deleteRecordByIds(List<String> ids);
	
	/**
	 * 获取星期的统计数据信息
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public WeekData getWeekData(String startDate, String endDate, HankouSequence seq);
	
	/**
	 * 获取累计底片、返修底片值对
	 * @return
	 */
	public long[] getDipianTotalFanxiuTotalPair(String startDate, HankouSequence seq);
}