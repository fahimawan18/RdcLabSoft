package com.lab.ui.beans.wf;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;

import com.lab.bll.wf.ChartBll;

@ManagedBean(name="chartBean")
@SessionScoped
public class ChartBean 
{
	private BarChartModel dateWiseChart;
	private Date toDate;
	private Date fromDate;
	
	public ChartBean() 
	{
		this.toDate = new Date();
		this.fromDate = new Date();
		this.fromDate.setHours(0);
		this.fromDate.setMinutes(0);
		this.fromDate.setSeconds(0);
		initDateWiseChart();
	}
	
	 public void onDateSelect(SelectEvent event) 
	 {
		 
		 initDateWiseChart();
	 }
	
	
	private void initDateWiseChart()
	{
		this.dateWiseChart = new BarChartModel();
		ChartBll bll = new ChartBll();
		System.out.println("Date selected are ==> from ="+fromDate+ " and to ="+toDate);
		this.dateWiseChart = bll.populateDateWiseChart(dateWiseChart, fromDate, toDate);
		
		
		dateWiseChart.setTitle("Date Wise Summary");
		dateWiseChart.setLegendPosition("ne");
		dateWiseChart.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		
         
        Axis xAxis = dateWiseChart.getAxis(AxisType.X);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
        xAxis.setLabel(formatter.format(fromDate)+ " - "+formatter.format(toDate));
        
        Axis yAxis = dateWiseChart.getAxis(AxisType.Y);
        yAxis.setLabel("Numbers");
        yAxis.setMin(0);
//        yAxis.setMax(20);
	}

	public BarChartModel getDateWiseChart() {
		return dateWiseChart;
	}

	public void setDateWiseChart(BarChartModel dateWiseChart) {
		this.dateWiseChart = dateWiseChart;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
}
