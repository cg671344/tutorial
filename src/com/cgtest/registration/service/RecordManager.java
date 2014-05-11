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
	 * ��������Ż�ȡ��¼
	 * @param reportNo
	 * @return
	 */
	public Record getRecordByReportNo(int reportNo);
	
	/**
	 * ���ݼ�¼id��ȡ��¼��Ϣ
	 * @param id
	 * @return
	 */
	public Record getRecordById(String id);
	
	public void saveRecord(Record record);
	public List<PieDataBean> getZoneClass();
	/**
	 * ��ȡ���м�¼�ķ�ҳ��¼
	 * @param pageNo
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public PageModel getPagedRecord(int pageNo, int pageSize, String startDate,
			String endDate);
	/**
	 * ��Ŷȥ�������ķ������ݣ�������
	 * @param startDate
	 * @param endDate
	 * @param seq
	 * @return
	 */
	public List<MsColumnData> getResponsibilityZoneData(String startDate, String endDate, HankouSequence seq);
	
	/**
	 *��ȡϸ������ķ������ݣ�С���� 
	 * @param startDate
	 * @param endDate
	 * @param seq
	 * @return
	 */
	public List<MsColumnData> getZoneAnalysticData(String startDate, String endDate, HankouSequence seq);
	
	public PageModel getPagedHangongRecord(final int pageNo, final int pageSize,
			final String startDate, final String endDate, final String hangongNo, HankouSequence seq);
	
	/**
	 * ������¼��Ϣ���յ���ͼ�ŵȲ�ѯ
	 * @param chartNo
	 * @param hankouNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel getPagedHangongChartNoQueryRecord(String chartNo, String hankouNo, int pageNo, int pageSize);
	
	/**
	 * ��ȡ�����ķ�������
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
	 * ɾ����¼
	 * @param id
	 */
	public void deleteRecordById(String id) ;
	public void deleteRecordByIds(List<String> ids);
	
	/**
	 * ��ȡ���ڵ�ͳ��������Ϣ
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public WeekData getWeekData(String startDate, String endDate, HankouSequence seq);
	
	/**
	 * ��ȡ�ۼƵ�Ƭ�����޵�Ƭֵ��
	 * @return
	 */
	public long[] getDipianTotalFanxiuTotalPair(String startDate, HankouSequence seq);
}