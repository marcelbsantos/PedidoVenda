package com.algaworks.pedidovenda.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.Pedidos;
import com.algaworks.pedidovenda.security.UsuarioLogado;
import com.algaworks.pedidovenda.security.UsuarioSistema;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean {
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private LineChartModel model;
	
	public void preRender(){
		this.model = new LineChartModel();
		
		createLineModels();
		
//		adicionarSerie("Todos os pedidos", null);
//		adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
	}
	
	private void createLineModels() {
		
        adicionarSerie("Todos os pedidos", null);
        adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());


        Axis yAxis = model.getAxis(AxisType.Y);
        
        model.setTitle("Pedidos Criados");
        model.setLegendPosition("e");
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Data"));
        yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Total de Vendas");
        yAxis.setMin(0);
//        yAxis.setMax(200);
    }
     
	
	
	private void adicionarSerie(String rotulo, Usuario criadoPor) {
		
		Map<Date, BigDecimal> valoresPorData = this.pedidos.valoresTotaisPorData(30, criadoPor);
		
		LineChartSeries series = new LineChartSeries();
		series.setLabel(rotulo);
		
		for (Date data : valoresPorData.keySet()) {
			series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
		}		
			
		this.model.addSeries(series);
				
	}

	public LineChartModel getModel() {
		return model;
	}

}
