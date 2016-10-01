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
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(WfClient.class);
			cr.add(Restrictions.ge("insertDate", fromDate));
			cr.add(Restrictions.le("insertDate", toDate));
			cr.setProjection(Projections.rowCount());
			count = (Integer)cr.uniqueResult();
			System.out.println("Value of regnSeriesCount = "+ count);
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		String x = "Date Range" ; //fromDate.toLocaleString()+ " - "+toDate.toLocaleString();
		regnSeries.set(x, count);
		cashSeries.set(x, 4);
		gpeSeries.set(x, 2);
		sampleSeries.set(x, 6);
	}

}
