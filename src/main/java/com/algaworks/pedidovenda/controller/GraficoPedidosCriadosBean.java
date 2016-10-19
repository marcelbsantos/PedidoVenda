package com.algaworks.pedidovenda.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean {
	
	private LineChartModel model;
	
	public void preRender(){
		this.model = new LineChartModel();
		
		adicionarSerie("Todos os pedidos");
		adicionarSerie("Meus pedidos");
	}
	
	private void adicionarSerie(String rotulo) {
		LineChartSeries series = new LineChartSeries();
		series.setLabel(rotulo);
		
		if(rotulo.equalsIgnoreCase("Todos os pedidos")) {
			series.set(1, 2);
			series.set(2, 1);
			series.set(3, 4);
			series.set(4, 6);
			series.set(5, 8);			
		} else {
			series.set(1, 3);
			series.set(2, 2);
			series.set(3, 3);
			series.set(4, 5);
			series.set(5, 7);
		}
		
		this.model.addSeries(series);
		
		model.setTitle("Pedidos Criados");
		model.setLegendPosition("e");
		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);
		
	}

	public LineChartModel getModel() {
		return model;
	}

}
