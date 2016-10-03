package com.lab.bll.wf;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

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
		
		populateSeries(fromDate,toDate);
		
		model.addSeries(regnSeries);
		model.addSeries(cashSeries);
		model.addSeries(gpeSeries);
		model.addSeries(sampleSeries);
		
		return model;
		
	}
	
	private void populateSeries(Date fromDate, Date toDate)
	{
		Session session = null;
		int count = 0;
		try
		{
			String x = "Date Range" ;
			
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

}
