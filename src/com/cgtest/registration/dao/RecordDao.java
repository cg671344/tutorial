package com.cgtest.registration.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cgtest.biz.pojo.MsColumnData;
import com.cgtest.biz.pojo.PageModel;
import com.cgtest.biz.pojo.PieDataBean;
import com.cgtest.biz.pojo.WeekData;
import com.cgtest.registration.model.Record;
import com.cgtest.registration.model.Record.HankouSequence;

@Repository
public class RecordDao{
	private static Logger logger = LoggerFactory.getLogger(RecordDao.class);
	
	@Resource
	private HibernateTemplate hibernateTemplate; 
	
	@SuppressWarnings("unchecked")
	public Record getRecordByReportNo(int reportNo){
		String hql = "from Record where reportNo = :reportNo";
		Query query = getSession().createQuery(hql);
		query.setParameter("reportNo", reportNo);
		List<Record> list =  query.list();
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public Record getRecordById(String id){
		String hql = "from Record where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		List<Record> list =  query.list();
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public void save(Record record) {
		hibernateTemplate.saveOrUpdate(record);
	}
	
	public void deleteRecordById(String id) {
		Query query = getSession().createQuery("delete from Record where id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	public Session getSession(){
		return this.hibernateTemplate.getSessionFactory().getCurrentSession();	
	}
	
	@SuppressWarnings("unchecked")
	public List<PieDataBean> getZoneClass(){
		String hqlString = "select r.zoneClass, sum(pianziNumber) from Record r group by r.zoneClass";
		Query query = getSession().createQuery(hqlString);
		List<Object[]> list = query.list();
		List<PieDataBean> dataList = new ArrayList<PieDataBean>();
		for(Object[] o : list){
			PieDataBean bean = new PieDataBean();
			bean.setLabel(String.valueOf(o[0]));
			bean.setValue(String.valueOf(o[1]));
			dataList.add(bean);
		}
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel findPagedRecord(final int pageNo, final int pageSize,
			final String startDate, final String endDate) {
		String hql = null;
		Query query = null;
		if (startDate == null || endDate == null) {
			hql = "from Record";
			query = getSession().createQuery(hql).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
		} else {
			hql = "from Record r where r.jianyanDate >= :startDate and r.jianyanDate <= :endDate";
			query = getSession().createQuery(hql).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		List<Record> itemList = (List<Record>) query.list();
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		pageModel.setList(itemList);
		pageModel.setTotalRecords(getTotalRecords(startDate, endDate));
		return pageModel;
	}

	private int getTotalRecords(final String startDate, final String endDate) {
		String hql = null;
		Query query = null;
		Long totalRecords = 0L;
		if (startDate == null || endDate == null) {
			hql = "select count(*) from Record";
			query = this.getSession().createQuery(hql);
		} else {
			hql = "select count(*) from Record r where r.jianyanDate >= :startDate and r.jianyanDate <= :endDate";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		totalRecords = (Long) query.uniqueResult();
		return totalRecords.intValue();
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	public List<MsColumnData> getResponsibilityZoneData(String startDate, String endDate, HankouSequence seq){
		String hqlString = null;
		Query query = null;
		if (startDate == null || endDate == null) {
			hqlString = "select r.zoneClass, sum(pianziNumber), sum(fanxiuNumber) from Record r where r.hankouSequence = :hankouSequence group by r.zoneClass order by zoneClass";
			query = getSession().createQuery(hqlString);
			query.setParameter("hankouSequence", seq);
		} else {
			hqlString = "select r.zoneClass, sum(pianziNumber), sum(fanxiuNumber) from Record r where r.hankouSequence = :hankouSequence and r.jianyanDate >= :startDate and r.jianyanDate <= :endDate group by r.zoneClass order by zoneClass";
			query = getSession().createQuery(hqlString);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("hankouSequence", seq);
		}
		List<Object[]> list = query.list();
		List<MsColumnData> dataList = new ArrayList<MsColumnData>();
		long  fanxiuSumCount = 0L;
		for(Object[] o : list){
			MsColumnData bean = new MsColumnData();
			bean.setDataType(String.valueOf(o[0]));
			bean.setDipianNumber((Long) o[1]);
			bean.setUnqualifiedPianziNum((Long)o[2]);
			fanxiuSumCount = fanxiuSumCount + bean.getUnqualifiedPianziNum();
			dataList.add(bean);
		}
		for(MsColumnData data : dataList){
			if(fanxiuSumCount !=0){
				data.setUnqualifiedDistributeRate(data.getUnqualifiedPianziNum()/(double)fanxiuSumCount);
			}else{
				data.setUnqualifiedDistributeRate(0);
			}
			if(data.getDipianNumber()!=0){
				data.setZoneQualifiedRate((data.getDipianNumber() - data
						.getUnqualifiedPianziNum())
						/ (double) data.getDipianNumber());
			}else{
				data.setZoneQualifiedRate(0);
			}
		}
		return dataList;
	}
	
	@SuppressWarnings({"unchecked" })
	public List<MsColumnData> getZoneAnalysticData(String startDate, String endDate, HankouSequence seq){
		String hqlString = null;
		Query query = null;
		if(startDate ==null || endDate == null || startDate.equals("null") || endDate.equals("null")){
			hqlString = "select r.zone, sum(pianziNumber), sum(fanxiuNumber) from Record r where r.hankouSequence = :hankouSequence group by r.zone order by zone";
			query = getSession().createQuery(hqlString);
			query.setParameter("hankouSequence",seq);
		}else{
			hqlString = "select r.zone, sum(pianziNumber), sum(fanxiuNumber) from Record r where r.hankouSequence = :hankouSequence and r.jianyanDate >= :startDate and r.jianyanDate <= :endDate group by r.zone order by zone";
			query = getSession().createQuery(hqlString);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("hankouSequence",seq);
		}
		List<Object[]> list = query.list();
		List<MsColumnData> dataList = new ArrayList<MsColumnData>();
		long  fanxiuSumCount = 0L;
		for(Object[] o : list){
			MsColumnData bean = new MsColumnData();
			bean.setDataType(String.valueOf(o[0]));
			bean.setDipianNumber((Long) o[1]);
			bean.setUnqualifiedPianziNum((Long)o[2]);
			fanxiuSumCount = fanxiuSumCount + bean.getUnqualifiedPianziNum();
			dataList.add(bean);
		}
		for(MsColumnData data : dataList){
			if(fanxiuSumCount!=0){
				data.setUnqualifiedDistributeRate(data.getUnqualifiedPianziNum()/(double)fanxiuSumCount);
			}else{
				data.setUnqualifiedDistributeRate(0);
			}
			if(data.getDipianNumber() != 0){
				data.setZoneQualifiedRate((data.getDipianNumber() - data
						.getUnqualifiedPianziNum())
						/ (double) data.getDipianNumber());
			}else{
				data.setZoneQualifiedRate(0);
			}
		}
		return dataList;
	}
	
	@SuppressWarnings({"unchecked" })
	public List<MsColumnData> getHangongAnalysticData(String startDate, String endDate, String hangongNo, String hangongName, HankouSequence seq){
		StringBuffer hqlString = new StringBuffer();
		Query query = null;
		if(startDate!=null && endDate!=null){
			if(hangongNo == null){
				if(hangongName == null){
					hqlString.append("select r.hangongNo, count(*), sum(pianziNumber), sum(fanxiuNumber),sum(case when r.jianyanResult='N' then 1 else 0 end) from Record r")
						.append(" where r.jianyanDate >= :startDate and r.jianyanDate <= :endDate ")
						.append(" and r.hankouSequence = :hankouSequence")
						.append(" group by r.hangongNo order by hangongNo");
					query = getSession().createQuery(hqlString.toString());
					query.setParameter("startDate", startDate);
					query.setParameter("endDate", endDate);
					query.setParameter("hankouSequence", seq);
				}else{
					hqlString.append("select r.hangongNo, count(*), sum(pianziNumber), sum(fanxiuNumber),sum(case when r.jianyanResult='N' then 1 else 0 end) from Record r")
						.append(" where r.jianyanDate >= :startDate and r.jianyanDate <= :endDate ")
						.append(" and r.hankouSequence = :hankouSequence")
						.append(" and r.hangongNo in ( select h.hangongNo from Hangong h where h.hangongName = :hangongName)")
						.append(" group by r.hangongNo order by r.hangongNo");
					query = getSession().createQuery(hqlString.toString());
					query.setParameter("startDate", startDate);
					query.setParameter("endDate", endDate);
					query.setParameter("hankouSequence", seq);
					query.setParameter("hangongName", hangongName);
					logger.info(startDate + ""+ endDate  + ""+ hangongName	+	"" + query.toString());
				}
			}else{
				hqlString.append("select r.hangongNo, count(*), sum(pianziNumber), sum(fanxiuNumber),sum(case when r.jianyanResult='N' then 1 else 0 end) from Record r ")
					.append(" where r.jianyanDate >= :startDate and r.jianyanDate <= :endDate ")
					.append(" and r.hankouSequence = :hankouSequence")
					.append(" and r.hangongNo = :hangongNo")
					.append(" group by r.hangongNo order by hangongNo");
				query = getSession().createQuery(hqlString.toString());
				query.setParameter("startDate", startDate);
				query.setParameter("endDate", endDate);
				query.setParameter("hangongNo", hangongNo);
				query.setParameter("hankouSequence", seq);
			}
		}else{
			if(hangongNo == null){
				if(hangongName == null){
					hqlString.append("select r.hangongNo,  count(*), sum(pianziNumber), sum(fanxiuNumber),sum(case when r.jianyanResult='N' then 1 else 0 end) from Record r")
						.append(" where r.hankouSequence = :hankouSequence")
						.append(" group by r.hangongNo order by r.hangongNo");
				query = getSession().createQuery(hqlString.toString());
				query.setParameter("hankouSequence", seq);
				}else{
					hqlString.append("select r.hangongNo, count(*), sum(pianziNumber), sum(fanxiuNumber),sum(case when r.jianyanResult='N' then 1 else 0 end) from Record r")
						.append(" where r.hankouSequence = :hankouSequence")
						.append(" and r.hangongNo in ( select h.hangongNo from Hangong h where h.hangongName = :hangongName)")
						.append(" group by r.hangongNo order by r.hangongNo");
					query = getSession().createQuery(hqlString.toString());
					query.setParameter("hankouSequence", seq);
					query.setParameter("hangongName", hangongName);
				}
			}else{
				hqlString.append("select r.hangongNo, count(*), sum(pianziNumber), sum(fanxiuNumber),sum(case when r.jianyanResult='N' then 1 else 0 end) from Record r ")
					.append(" where r.hankouSequence = :hankouSequence")
					.append(" and r.hangongNo = :hangongNo")
					.append(" group by r.hangongNo order by hangongNo");
				query = getSession().createQuery(hqlString.toString());
				query.setParameter("hangongNo", hangongNo);
				query.setParameter("hankouSequence", seq);
			}
		}
		List<Object[]> list = query.list();
		List<MsColumnData> dataList = new ArrayList<MsColumnData>();
		long  fanxiuSumCount = 0L;
		for(Object[] o : list){
			MsColumnData bean = new MsColumnData();
			bean.setDataType(String.valueOf(o[0]));
			bean.setHangkouNumber((Long) o[1]);
			bean.setDipianNumber((Long) o[2]);
			bean.setUnqualifiedPianziNum((Long)o[3]);
			bean.setUnqualifiedhangkouNumber((Long)o[4]);
			
			String hangongNameQuery = "select hangongName from Hangong where hangongNo=:hangongNo";
			Query nameQuery = getSession().createQuery(hangongNameQuery.toString());
			nameQuery.setParameter("hangongNo", bean.getDataType());
			String hangongQueryName = (String) nameQuery.uniqueResult();
			bean.setHangongName(hangongQueryName);
			
			fanxiuSumCount = fanxiuSumCount + bean.getUnqualifiedPianziNum();
			dataList.add(bean);
		}
		for(MsColumnData data : dataList){
			if(fanxiuSumCount!=0){
				data.setUnqualifiedDistributeRate(data.getUnqualifiedPianziNum()/(double)fanxiuSumCount);
			}else{
				data.setUnqualifiedDistributeRate(0);
			}
			if(data.getDipianNumber() != 0){
				data.setZoneQualifiedRate((data.getDipianNumber() - data
						.getUnqualifiedPianziNum())
						/ (double) data.getDipianNumber());	
			}else{
				data.setZoneQualifiedRate(0);
			}
		}
		return dataList;
	}
	
	public PageModel getPagedHangongRecord(final int pageNo,
			final int pageSize, final String startDate, final String endDate,
			final String hangongNo, final HankouSequence seq) {
		String hql = null;
		Query query = null;
		if (startDate == null || endDate == null) {
			hql = "from Record r where r.hangongNo = :hangongNo and r.hankouSequence = :hankouSequence";
			query = getSession().createQuery(hql).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
			query.setParameter("hangongNo", hangongNo);
			query.setParameter("hankouSequence", seq);
		} else {
			hql = "from Record r where r.jianyanDate >= :startDate and r.jianyanDate <= :endDate and r.hangongNo = :hangongNo and r.hankouSequence = :hankouSequence";
			query = getSession().createQuery(hql).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("hangongNo", hangongNo);
			query.setParameter("hankouSequence", seq);
		}
		List<Record> itemList = (List<Record>) query.list();
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		pageModel.setList(itemList);
		pageModel.setTotalRecords(getPagedHangongRecordTotals(startDate,
				endDate, hangongNo, seq));
		return pageModel;
	}

	private int getPagedHangongRecordTotals(final String startDate,
			final String endDate, final String hangongNo,
			final HankouSequence seq) {
		String hql = null;
		Query query = null;
		Long totalRecords = 0L;
		if (startDate == null || endDate == null) {
			hql = "select count(*) from Record r where r.hangongNo = :hangongNo and r.hankouSequence = :hankouSequence";
			query = this.getSession().createQuery(hql);
			query.setParameter("hangongNo", hangongNo);
			query.setParameter("hankouSequence", seq);
		} else {
			hql = "select count(*) from Record r where r.jianyanDate >= :startDate and r.jianyanDate <= :endDate and r.hangongNo = :hangongNo and r.hankouSequence = :hankouSequence";
			query = getSession().createQuery(hql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("hangongNo", hangongNo);
			query.setParameter("hankouSequence", seq);
		}
		totalRecords = (Long) query.uniqueResult();
		return totalRecords.intValue();
	}
	
	/**
	 * 统计一次焊口制定时间段的合格率信息
	 */
	public WeekData getWeekData(String startDate, String endDate, HankouSequence seq){
		String hqlString = null;
		hqlString = "select sum(pianziNumber), sum(fanxiuNumber),count(*) from Record r where r.hankouSequence=:hankouSequence and r.jianyanDate >= :startDate and r.jianyanDate <= :endDate";
		Query query	= getSession().createQuery(hqlString);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("hankouSequence", seq);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		WeekData data = new WeekData();
		data.setStartDate(startDate);
		data.setEndDate(endDate);
		for(Object[] o : list){
			Long dipianNumber = o[0] != null ? (Long)o[0]:0;
			Long fanxiuNumber = o[0] != null ? (Long)o[1]:0;
			Long hankouNumber = o[0] != null ? (Long)o[2]:0;
			data.setDipianNumber(dipianNumber);
			data.setFanxiuNumber(fanxiuNumber);
			data.setHangkouNumber(hankouNumber);
			if(data.getDipianNumber()!=0){
				data.setQulifiedRate((dipianNumber - fanxiuNumber)/(double)dipianNumber);
			}else{
				data.setQulifiedRate(1);
			}
		}
		return data;
	}
	
	/**
	 * 根据等轴图号和焊口号获取记录
	 * @param chartNo
	 * @param hankouNo
	 * @return
	 */
	public PageModel getPagedHangongChartNoQueryRecord(String chartNo, String hankouNo, int pageNo, int pageSize){
		String hql = null;
		Query query = null;
		if(chartNo!=null && hankouNo!=null){
			hql = "from Record r where r.chartNo like :chartNo and r.hankouNo =:hankouNo";
			query = getSession().createQuery(hql).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
			query.setParameter("chartNo", "%" + chartNo + "%");
			query.setParameter("hankouNo", hankouNo);
		}else if(chartNo != null && hankouNo == null){
			hql = "from Record r where r.chartNo like :chartNo";
			query = getSession().createQuery(hql).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
			query.setParameter("chartNo", "%" + chartNo + "%").setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
		}else if(chartNo==null && hankouNo!=null){
			hql = "from Record r where r.hankouNo = :hankouNo";
			query = getSession().createQuery(hql).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
			query.setParameter("hankouNo", hankouNo).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
		}else{
			hql = "from Record";
			query = getSession().createQuery(hql).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
			query = getSession().createQuery(hql).setFirstResult(
					(pageNo - 1) * pageSize).setMaxResults(pageSize);
		}
		List<Record> itemList = (List<Record>) query.list();
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		pageModel.setList(itemList);
		pageModel.setTotalRecords(getPagedHangongChartNoQueryTotal(chartNo, hankouNo));
		return pageModel;
	}
	
	private int getPagedHangongChartNoQueryTotal(String chartNo, String hankouNo) {
		String hql = null;
		Query query = null;
		Long totalRecords = 0L;
		if(chartNo!=null && hankouNo!=null){
			hql = "select count(*) from Record r where r.chartNo like :chartNo and r.hankouNo =:hankouNo";
			query = getSession().createQuery(hql);
			query.setParameter("chartNo", "%" + chartNo + "%");
			query.setParameter("hankouNo", hankouNo);
		}else if(chartNo != null && hankouNo == null){
			hql = "select count(*) from Record r where r.chartNo like :chartNo";
			query = getSession().createQuery(hql);
			query.setParameter("chartNo", "%" + chartNo + "%");
		}else if(chartNo==null && hankouNo!=null){
			hql = "select count(*) from Record r where r.hankouNo = :hankouNo";
			query = getSession().createQuery(hql);
			query.setParameter("hankouNo", hankouNo);
		}else{
			hql = "select count(*) from Record";
			query = getSession().createQuery(hql);
		}
		totalRecords = (Long) query.uniqueResult();
		return totalRecords.intValue();
	}
	
	/**
	 * 获取片子总数
	 */
	public long[] getDipianTotalFanxiuTotalPair(String startdate, HankouSequence seq){
		String hql = "select sum(pianziNumber), sum(fanxiuNumber) from Record r where r.jianyanDate >= :startDate and r.hankouSequence=:hankouSequence";
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", startdate);
		query.setParameter("hankouSequence", seq);
		List<Object[]> list = query.list();
		long [] dataPair = new long[2];
		for(Object[] o : list){
			Long dipianNumber = o[0] != null ? (Long)o[0]:0;
			Long fanxiuNumber = o[0] != null ? (Long)o[1]:0;
			dataPair[0] = dipianNumber;
			dataPair[1] = fanxiuNumber;
		}
		return dataPair;
	}
}
