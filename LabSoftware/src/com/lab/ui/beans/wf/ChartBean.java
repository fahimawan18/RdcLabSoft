package com.lab.ui.beans.wf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;

import com.lab.bll.wf.ChartBll;

@ManagedBean(name="chartBean")
@SessionScoped
public class ChartBean 
{
	private BarChartModel dateWiseChart;
	private LineChartModel dayWiseChart;
	private Date toDate;
	private Date fromDate;
	
	private Date toDate2;
	private Date fromDate2;
	
	
	public ChartBean() 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		fromDate = calendar.getTime();
		
		Calendar tocalendar = Calendar.getInstance();
		tocalendar.set(Calendar.HOUR_OF_DAY, 0);
		tocalendar.set(Calendar.MINUTE, 0);
		tocalendar.set(Calendar.SECOND, 0);
		tocalendar.set(Calendar.MILLISECOND, 0);
		toDate = tocalendar.getTime();
		
//		this.toDate = new Date();
////		toDate.setDate(toDate.getDate()+1);
//		this.toDate.setHours(0);
//		this.toDate.setMinutes(0);
//		this.toDate.setSeconds(0);
//		
//		this.fromDate = new Date();
//		this.fromDate.setHours(0);
//		this.fromDate.setMinutes(0);
//		this.fromDate.setSeconds(0);
		this.toDate2 = tocalendar.getTime();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-7);
		this.fromDate2 = calendar.getTime();
//		this.fromDate2.setHours(0);
//		this.fromDate2.setMinutes(0);
//		this.fromDate2.setSeconds(0);
		initDateWiseChart();
		initDayWiseChart();
	}
//	@PostConstruct
//	public void init() {
//		initDateWiseChart();
//		initDayWiseChart();
//	}
	
	 public void onDateSelect(SelectEvent event) 
	 {
		 
		 initDateWiseChart();
		 
	 }
	 
	 public void onDateSelect2(SelectEvent event) 
	 {
		 
		
		 initDayWiseChart();
	 }
	
	
	private void initDateWiseChart()
	{
		this.dateWiseChart = new BarChartModel();
		ChartBll bll = new ChartBll();
		System.out.println("Date selected are ==> from ="+fromDate+ " and to ="+toDate);
		
		System.out.println("Date selected are ==> from ="+fromDate.getTime()+ " and to ="+toDate.getTime());
		this.dateWiseChart = bll.populateDateWiseChart(dateWiseChart, fromDate, toDate);
		
		
		dateWiseChart.setTitle("Date Wise Summary");
		//dateWiseChart.setLegendPosition("ne");
		//dateWiseChart.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		dateWiseChart.setShowPointLabels(true);
		dateWiseChart.setShowDatatip(true);
         
        Axis xAxis = dateWiseChart.getAxis(AxisType.X);
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
        xAxis.setLabel("From:"+formatter.format(fromDate)+ " - To: "+formatter.format(toDate));
        
        Axis yAxis = dateWiseChart.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        yAxis.setMin(0);
//        yAxis.setMax(20);
	}
	
	
	private void initDayWiseChart()
	{
		this.dayWiseChart = new LineChartModel();
		ChartBll bll = new ChartBll();
		System.out.println("Date selected are ==> from ="+fromDate2+ " and to ="+toDate2);
		this.dayWiseChart = bll.populateDayWiseCashChart(dayWiseChart, fromDate2, toDate2);
		
		
		dayWiseChart.setTitle("Day Wise Cash Summary");
		dayWiseChart.setLegendPosition("ne");
		dayWiseChart.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		dayWiseChart.setShowPointLabels(true);
		dayWiseChart.getAxes().put(AxisType.X, new CategoryAxis("Days"));
         
        
        Axis yAxis = dayWiseChart.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
//      yAxis.setMax(20);
	}

	public BarChartModel getDateWiseChart() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^ getDateWiseChart " + dateWiseChart.getSeries().size());
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

	public LineChartModel getDayWiseChart() {
		return dayWiseChart;
	}

	public void setDayWiseChart(LineChartModel dayWiseChart) {
		this.dayWiseChart = dayWiseChart;
	}

	public Date getToDate2() {
		return toDate2;
	}

	public void setToDate2(Date toDate2) {
		this.toDate2 = toDate2;
	}

	public Date getFromDate2() {
		return fromDate2;
	}

	public void setFromDate2(Date fromDate2) {
		this.fromDate2 = fromDate2;
	}
}

