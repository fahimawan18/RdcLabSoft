package com.lab.bll.wf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import com.iac.web.util.FacesUtils;
import com.lab.dal.dao.WfClient;
import com.lab.dal.dao.WfClientFinance;
import com.lab.dal.dao.WfClientGpe;
import com.lab.dal.dao.WfClientProgress;
import com.lab.dal.dao.WfClientSamples;
import com.lab.dal.dao.WfClientXray;
import com.lab.dal.dao.WfLabResultBlood;
import com.lab.ui.beans.admin.CriteriaBean;
import com.lab.utils.HibernateUtilsAnnot;
import com.lab.utils.MessageConstants;

public class ChartBll 
{
	private ChartSeries regnSeries = new ChartSeries();
	private ChartSeries cashSeries = new ChartSeries();
	private ChartSeries gpeSeries = new ChartSeries();
	private ChartSeries sampleSeries = new ChartSeries();
	private ChartSeries xraySeries = new ChartSeries();
	private ChartSeries radiologistSeries = new ChartSeries();
	private ChartSeries labSeries = new ChartSeries();
	private ChartSeries pathologistSeries = new ChartSeries();
	private ChartSeries directorSeries = new ChartSeries();
	private ChartSeries dayWiseCashSeries = new ChartSeries();
	
//	private CriteriaBean cb = ((CriteriaBean)FacesUtils.getManagedBean("crit"));
	
	public ChartBll() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public BarChartModel populateDateWiseChart(BarChartModel model, Date fromDate, Date toDate)
	{
		
		regnSeries.setLabel(MessageConstants.Constants.ChartLabels.REGN);
//		cashSeries.setLabel(MessageConstants.Constants.ChartLabels.CASH);
//		gpeSeries.setLabel(MessageConstants.Constants.ChartLabels.GPE);
//		sampleSeries.setLabel(MessageConstants.Constants.ChartLabels.SAMPLES);
//		xraySeries.setLabel(MessageConstants.Constants.ChartLabels.XRAY);
//		radiologistSeries.setLabel(MessageConstants.Constants.ChartLabels.RADIOLOGIST);
//		labSeries.setLabel(MessageConstants.Constants.ChartLabels.LAB);
//		pathologistSeries.setLabel(MessageConstants.Constants.ChartLabels.PATHOLOGIST);
//		directorSeries.setLabel(MessageConstants.Constants.ChartLabels.DIRECTOR);
		
		populateDateWiseSeries(fromDate, toDate);
		model.setExtender("chartExtender");
		model.addSeries(regnSeries);
//		model.addSeries(cashSeries);
//		model.addSeries(gpeSeries);
//		model.addSeries(sampleSeries);
//		model.addSeries(xraySeries);
//		model.addSeries(radiologistSeries);
//		model.addSeries(labSeries);
//		model.addSeries(pathologistSeries);
//		model.addSeries(directorSeries);
		
		return model;
		
	}
	
	private void populateDateWiseSeries(Date fromDate, Date toDateNew)
	{
		Date toDate = Calendar.getInstance().getTime();
		toDate.setTime( toDateNew.getTime());
//		toDate.setDate(toDateNew.getDate());
		if(fromDate.getTime() == toDate.getTime()){
			System.out.println("Dates are same ==> from ="+toDate);
//			toDate.setDate(fromDate.getDate()+1);
			Calendar tocalendar = Calendar.getInstance();
			tocalendar.set(Calendar.HOUR_OF_DAY, 0);
			tocalendar.set(Calendar.MINUTE, 0);
			tocalendar.set(Calendar.SECOND, 0);
			tocalendar.setTime(toDate);
			tocalendar.set(Calendar.DATE, tocalendar.get(Calendar.DATE)+1);
			toDate = tocalendar.getTime();
		}
		Session session = null;
		int count = 0;
		try
		{
			//String x = "Activity" ;
			System.out.println("Date selected are in dal ==> from ="+fromDate.getTime()+ " and to ="+toDate.getTime());
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(WfClient.class);
//			cr.add(Restrictions.between("insertDate", fromDate, toDate));
			cr.add(Restrictions.ge("insertDate", fromDate));
			cr.add(Restrictions.le("insertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(MessageConstants.Constants.ChartLabels.REGN, count);
			System.out.println("Value of regnSeriesCount = "+ count);
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClientFinance.class);
			cr.add(Restrictions.ge("cashPaidDate", fromDate));
			cr.add(Restrictions.le("cashPaidDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(MessageConstants.Constants.ChartLabels.CASH, count);
			System.out.println("Value of cashSeriesCount = "+ count);			
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClientGpe.class);
			cr.add(Restrictions.ge("insertDate", fromDate));
			cr.add(Restrictions.le("insertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(MessageConstants.Constants.ChartLabels.GPE, count);
			System.out.println("Value of gpeSeriesCount = "+ count);
			//gpeSeries.set(x, count);
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClientSamples.class);
			cr.add(Restrictions.ge("insertDate", fromDate));
			cr.add(Restrictions.le("insertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(MessageConstants.Constants.ChartLabels.SAMPLES, count);
			System.out.println("Value of sampleSeriesCount = "+ count);
			//sampleSeries.set(x, count);
			
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClientXray.class);
			cr.add(Restrictions.ge("xrayInsertDate", fromDate));
			cr.add(Restrictions.le("xrayInsertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(MessageConstants.Constants.ChartLabels.XRAY, count);
			System.out.println("Value of xraySeriesCount = "+ count);
			//sampleSeries.set(x, count);
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClientXray.class);
			cr.add(Restrictions.ge("radiologyInsertDate", fromDate));
			cr.add(Restrictions.le("radiologyInsertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(MessageConstants.Constants.ChartLabels.RADIOLOGIST, count);
			System.out.println("Value of radiologySeriesCount = "+ count);
			//sampleSeries.set(x, count);
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfLabResultBlood.class);
			cr.add(Restrictions.ge("labInsertDate", fromDate));
			cr.add(Restrictions.le("labInsertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(MessageConstants.Constants.ChartLabels.LAB, count);
			System.out.println("Value of labSeriesCount = "+ count);
			//sampleSeries.set(x, count);
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClientProgress.class);
			cr.add(Restrictions.ge("pathologistInsertDate", fromDate));
			cr.add(Restrictions.le("pathologistInsertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(MessageConstants.Constants.ChartLabels.PATHOLOGIST, count);
			System.out.println("Value of pathologySeriesCount = "+ count);
			//sampleSeries.set(x, count);
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClient.class);
			cr.add(Restrictions.ge("finalDeclaredDate", fromDate));
			cr.add(Restrictions.le("finalDeclaredDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(MessageConstants.Constants.ChartLabels.DIRECTOR, count);
			System.out.println("Value of directorSeriesCount = "+ count);
			//sampleSeries.set(x, count);
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		
		
	}
	
	
	public LineChartModel populateDayWiseCashChart(LineChartModel model, Date fromDate, Date toDate)
	{	
		dayWiseCashSeries.setLabel(MessageConstants.Constants.ChartLabels.CASH);		
		populateDayWiseSeries(fromDate,toDate, model);
		model.addSeries(dayWiseCashSeries);		
		return model;		
	}
	
	private void populateDayWiseSeries(Date fromDate, Date toDate, LineChartModel model)
	{
		Session session = null;
		try
		{
			String x = "Date Range" ;
			
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(WfClientFinance.class);
			cr.add(Restrictions.ge("cashPaidDate", fromDate));
			cr.add(Restrictions.le("cashPaidDate", toDate));
			cr.add(Restrictions.isNotNull("cashPaidDate"));
			cr.setProjection(Projections.projectionList()
					.add(Projections.sum("cashAmount"))
					.add(Projections.groupProperty("cashPaidDate"))
					);
					
			
			if(cr.list().size()>0)
			{
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
				
				Iterator<Object> iter = cr.list().iterator();
				while(iter.hasNext())
				{
					Object[] o = (Object[])iter.next();
					
//					System.out.println(o[0] +" ===== "+ o[1]);
					String label = formatter.format(o[1]);
					System.out.println(o[0] +" ===== "+label );
					dayWiseCashSeries.set(label,(Integer.valueOf(o[0].toString())));					
				}				
			}
			else
			{
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
				String label = formatter.format(fromDate);
				dayWiseCashSeries.set(label,0);
				label = formatter.format(toDate);
				dayWiseCashSeries.set(label,0);
			}
	        
//			model.addSeries(dayWiseCashSeries);
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		
	}

}

