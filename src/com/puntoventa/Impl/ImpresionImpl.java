package com.puntoventa.Impl;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.puntoventa.dao.CentroVentasDaoImpl;
import com.puntoventa.hibernate.CatVentas;
import com.puntoventa.hibernate.CentroVenta;
import com.puntoventa.interfase.Impresion;
import com.puntoventa.utils.Ticket;
import com.puntoventa.utils.TicketUsb;

@Component("impresionImpl")
public class ImpresionImpl implements Impresion, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier("centroVentasDaoImpl")
	private CentroVentasDaoImpl cveDao;
	
	@SuppressWarnings("static-access")
	@Override
	public void impresionTicket(List<CatVentas> venta) {
		// TODO Auto-generated method stub

		try {
			
			
			CentroVenta cve = cveDao.findById(new CentroVenta(1), new Integer(1));
			Date date = new Date();
			SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss aa");
			
			
			TicketUsb ticketUsb = new TicketUsb();
			String itemFinal = "";
			
			for(CatVentas ventaCat : venta){
				
				String item = ventaCat.getCantidad() + ventaCat.getCatProductos().getCatUnidadMedida().getDesUnidadMedida() +" "+ ventaCat.getCatProductos().getDescProducto() + " "+ventaCat.getPUnitario() ;
				
				itemFinal = itemFinal + ticketUsb.items(item,ventaCat.getSubTotal().toString());
				
			}
			
			ticketUsb.ticket(cve.getNombre(), "", "", venta.get(0).getVentas().getFolio().toString(), venta.get(0).getVentas().getUsuario().getNombre(),  hora.format(date), itemFinal, venta.get(0).getVentas().getSubTotal().toString(), venta.get(0).getVentas().getIva().toString(), venta.get(0).getVentas().getTotal().toString());
			ticketUsb.print();
			
//			Ticket ticket = new Ticket();
//			ticket.AddCabecera("          "+cve.getNombre());
//			ticket.AddCabecera(ticket.DarEspacio());
//			ticket.AddCabecera("r.f.c.: "+cve.getRfc());
//			ticket.AddCabecera(ticket.DarEspacio());
//			ticket.AddCabecera("direccion: "+cve.getDomicilio());			
//			ticket.AddCabecera(ticket.DarEspacio());
//			ticket.AddSubCabecera(ticket.DibujarLinea(40));
//			ticket.AddSubCabecera(ticket.DarEspacio());
//			ticket.AddSubCabecera("        Ticket No: " + venta.get(0).getVentas().getFolio());
//			ticket.AddSubCabecera(ticket.DarEspacio());
//			ticket.AddSubCabecera("        " + fecha.format(date) + " " + hora.format(date));
//			ticket.AddSubCabecera(ticket.DarEspacio());
//			ticket.AddSubCabecera("        Atendio: "+venta.get(0).getVentas().getUsuario().getNombre());
//			ticket.AddSubCabecera(ticket.DarEspacio());
//			ticket.AddSubCabecera(ticket.DibujarLinea(40));
//			ticket.AddSubCabecera(ticket.DarEspacio());
//			ticket.AddSubCabecera("cant      producto         p.u     total");
//			ticket.AddSubCabecera(ticket.DarEspacio());
//			ticket.AddSubCabecera(ticket.DibujarLinea(40));
//			ticket.AddSubCabecera(ticket.DarEspacio());
//
//			for (CatVentas catVentas : venta) {
//
//				// cantidad de decimales
//				NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
//				DecimalFormat form = (DecimalFormat) nf;
//				form.applyPattern("#,###.00");
//				// cantidad
//				String cantidad = String.valueOf(catVentas.getCantidad());
//				if (cantidad.length() < 4) {
//					int cant = 4 - cantidad.length();
//					String can = "";
//					for (int f = 0; f < cant; f++) {
//						can += " ";
//					}
//					cantidad += can;
//				}
//
//				// items
//				String item = catVentas.getCatProductos().getDescProducto();
//				if (item.length() > 17) {
//					item = item.substring(0, 16) + ".";
//				} else {
//					int c = 17 - item.length();
//					String comple = "";
//					for (int y1 = 0; y1 < c; y1++) {
//						comple += " ";
//					}
//					item += comple;
//				}
//
//				// precio
//				String precio = String.valueOf(catVentas.getPUnitario().toString());
//				double pre1 = Double.parseDouble(precio);
//				precio = form.format(pre1);
//				if (precio.length() < 8) {
//					int p = 8 - precio.length();
//					String pre = "";
//					for (int y1 = 0; y1 < p; y1++) {
//						pre += " ";
//					}
//					precio = pre + precio;
//				}
//
//				// total
//				String total = String.valueOf(catVentas.getSubTotal().toString());
//				total = form.format(Double.parseDouble(total));
//				if (total.length() < 8) {
//					int t = 8 - total.length();
//					String tota = "";
//					for (int y1 = 0; y1 < t; y1++) {
//						tota += " ";
//					}
//					total = tota + total;
//				}
//				// agrego los items al detalle
//				ticket.AddItem(cantidad, item, precio, total);
//				ticket.AddItem("", "", "", ticket.DarEspacio());
//
//			}
//
//			NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
//			DecimalFormat form = (DecimalFormat) nf;
//			form.applyPattern("#,###.00");
//
//			ticket.AddItem(ticket.DibujarLinea(40), "", "", "");
//			ticket.AddTotal("", ticket.DarEspacio());
//			ticket.AddTotal("total                   ", form.format(venta.get(0).getVentas().getSubTotal()));
//			ticket.AddTotal("", ticket.DarEspacio());
//			ticket.AddTotal("IVA                     ", form.format(venta.get(0).getVentas().getIva()));
//			ticket.AddTotal("", ticket.DarEspacio());
//			ticket.AddTotal("total venta             ", form.format(venta.get(0).getVentas().getTotal()));
//			ticket.AddTotal("", ticket.DarEspacio());
//			// ticket.AddTotal("paga con ", "200.00");
//			// ticket.AddTotal("", ticket.DarEspacio());
//			// ticket.AddTotal("vuelto ", "84.00");
//			// ticket.AddPieLinea(ticket.DarEspacio());
//			ticket.AddPieLinea("Gracias por su Preferencia");
//			ticket.ImprimirDocumento("C:\\Users\\USUARIO\\Documents\\XPSPort" + venta.get(0).getVentas().getFolio() + ".txt", false,true,20);
		} catch (Exception e) {
			System.out.print("\nerror " + e.toString());		}

	}

}
