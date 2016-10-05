package com.lab.bll.wf;

import java.text.SimpleDateFormat;
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
import com.lab.dal.dao.WfClientSamples;
import com.lab.ui.beans.admin.CriteriaBean;
import com.lab.utils.HibernateUtilsAnnot;
import com.lab.utils.MessageConstants;

public class ChartBll 
{
	private ChartSeries regnSeries = new ChartSeries();
	private ChartSeries cashSeries = new ChartSeries();
	private ChartSeries gpeSeries = new ChartSeries();
	private ChartSeries sampleSeries = new ChartSeries();
	private ChartSeries dayWiseCashSeries = new ChartSeries();
	
	private CriteriaBean cb = ((CriteriaBean)FacesUtils.getManagedBean("crit"));
	
	public ChartBll() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public BarChartModel populateDateWiseChart(BarChartModel model, Date fromDate, Date toDate)
	{
		
		regnSeries.setLabel(MessageConstants.Constants.ChartLabels.REGN);
		cashSeries.setLabel(MessageConstants.Constants.ChartLabels.CASH);
		gpeSeries.setLabel(MessageConstants.Constants.ChartLabels.GPE);
		sampleSeries.setLabel(MessageConstants.Constants.ChartLabels.SAMPLES);
		
		populateDateWiseSeries(fromDate, toDate);
		
		model.addSeries(regnSeries);
		model.addSeries(cashSeries);
		model.addSeries(gpeSeries);
		model.addSeries(sampleSeries);
		
		return model;
		
	}
	
	private void populateDateWiseSeries(Date fromDate, Date toDate)
	{
		Session session = null;
		int count = 0;
		try
		{
			String x = "Activity" ;
			
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(WfClient.class);
			cr.add(Restrictions.ge("insertDate", fromDate));
			cr.add(Restrictions.le("insertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			regnSeries.set(x, count);
			System.out.println("Value of regnSeriesCount = "+ count);
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClientFinance.class);
			cr.add(Restrictions.ge("cashPaidDate", fromDate));
			cr.add(Restrictions.le("cashPaidDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			cashSeries.set(x, count);
			System.out.println("Value of cashSeriesCount = "+ count);			
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClientGpe.class);
			cr.add(Restrictions.ge("insertDate", fromDate));
			cr.add(Restrictions.le("insertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			gpeSeries.set(x, count);
			System.out.println("Value of gpeSeriesCount = "+ count);
			gpeSeries.set(x, count);
			
			cr= null;
			count =0; 
			
			cr = session.createCriteria(WfClientSamples.class);
			cr.add(Restrictions.ge("insertDate", fromDate));
			cr.add(Restrictions.le("insertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			sampleSeries.set(x, count);
			System.out.println("Value of sampleSeriesCount = "+ count);
			sampleSeries.set(x, count);
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
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
//					System.out.println(o[0] +" ===== "+label );
					dayWiseCashSeries.set(label,(Integer.valueOf(o[0].toString())));
					
				}
			}
			
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		
		
	}

}

