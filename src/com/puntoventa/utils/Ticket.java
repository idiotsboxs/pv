package com.puntoventa.utils;

import java.io.FileWriter;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.swing.JOptionPane;

public class Ticket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ticket() {
	}

	static ArrayList<String> CabezaLineas = new ArrayList<String>();
	static ArrayList<String> subCabezaLineas = new ArrayList<String>();
	static ArrayList<String> items = new ArrayList<String>();
	static ArrayList<String> totales = new ArrayList<String>();
	static ArrayList<String> LineasPie = new ArrayList<String>();

	public static void AddCabecera(String line) {
		CabezaLineas.add(line);
	}

	public static void AddSubCabecera(String line) {
		subCabezaLineas.add(line);
	}

	public static void AddItem(String cantidad, String item, String price, String total) {
		OrderItem newItem = new OrderItem(' ');
		items.add(newItem.GeneraItem(cantidad, item, price, total));
	}

	public static void AddTotal(String name, String price) {
		OrderTotal newTotal = new OrderTotal(' ');
		totales.add(newTotal.GeneraTotal(name, price));
	}

	public static void AddPieLinea(String line) {
		LineasPie.add(line);
	}

	public static String DibujarLinea(int valor) {
		String raya = "";
		for (int x = 0; x < valor; x++) {
			raya += "-";
		}
		return raya;
	}

	public static String DarEspacio() {
		return "\n";
	}

	public static void SetFormato(FileWriter pw, int formato) {
		try {
			char[] ESC_CUT_PAPER = new char[] { 0x1B, '!', (char) formato };
			pw.write(ESC_CUT_PAPER);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static void ImprimirDocumento() {
		String cadena = "";
		for (int cabecera = 0; cabecera < CabezaLineas.size(); cabecera++) {
			cadena += CabezaLineas.get(cabecera);
		}
		for (int subcabecera = 0; subcabecera < subCabezaLineas.size(); subcabecera++) {
			cadena += subCabezaLineas.get(subcabecera);
		}
		for (int ITEM = 0; ITEM < items.size(); ITEM++) {
			cadena += items.get(ITEM);
		}
		for (int total = 0; total < totales.size(); total++) {
			cadena += totales.get(total);
		}
		for (int pie = 0; pie < LineasPie.size(); pie++) {
			cadena += LineasPie.get(pie);
		}

		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet printRequestAttributeSet=new HashPrintRequestAttributeSet();
	      printRequestAttributeSet.add(new JobName("JAligner",null));
	      PrintService[] printServices=PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE,null);
	      
	      PrintService service=ServiceUI.printDialog(null,50,50,printServices,PrintServiceLookup.lookupDefaultPrintService(),DocFlavor.INPUT_STREAM.AUTOSENSE,printRequestAttributeSet);
	  
		//PrintService service = PrintServiceLookup.lookupDefaultPrintService();
		System.out.println(service.getName());
		DocPrintJob pj = service.createPrintJob();
		byte[] bytes = cadena.getBytes();
		Doc doc = new SimpleDoc(bytes, flavor, null);
		try {
			pj.print(doc, null);
		} catch (Exception e) {
		}
	}

	public static void ImprimirDocumento(String impresora, boolean abrir) {
		try {
			 FileWriter imp = new FileWriter("LPT1");
			//FileWriter imp = new FileWriter(impresora);
			char[] Caracter = new char[] { 0x1B, 'R', 18 };
			imp.write(Caracter);
			for (int cabecera = 0; cabecera < CabezaLineas.size(); cabecera++) {
				imp.write(CabezaLineas.get(cabecera));
			}
			for (int subcabecera = 0; subcabecera < subCabezaLineas.size(); subcabecera++) {
				imp.write(subCabezaLineas.get(subcabecera));
			}
			for (int ITEM = 0; ITEM < items.size(); ITEM++) {
				imp.write(items.get(ITEM));
			}
			for (int total = 0; total < totales.size(); total++) {
				imp.write(totales.get(total));
			}
			for (int pie = 0; pie < LineasPie.size(); pie++) {
				imp.write(LineasPie.get(pie));
			}
			for (int u = 0; u <= 10; u++) {
				imp.write("\n");
			}
			// corta el papel
			char[] CORTAR_PAPEL = new char[] { 0x1B, 'm' };
			imp.write(CORTAR_PAPEL);
			if (abrir) {
				char ABRIR_GAVETA[] = { (char) 27, (char) 112, (char) 0, (char) 10, (char) 100 };
				imp.write(ABRIR_GAVETA);
			}
			imp.close();
			// limpio las listas que contiene los datos
			CabezaLineas.removeAll(CabezaLineas);
			subCabezaLineas.removeAll(subCabezaLineas);
			items.removeAll(items);
			totales.removeAll(totales);
			LineasPie.removeAll(LineasPie);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al Imprimir:\n" + e.getMessage());
			CabezaLineas.removeAll(CabezaLineas);
			subCabezaLineas.removeAll(subCabezaLineas);
			items.removeAll(items);
			totales.removeAll(totales);
			LineasPie.removeAll(LineasPie);
		}
	}

	public static void ImprimirDocumento(String impresora, boolean abrir, boolean formatoCabecera, int formato) {
		try {
			// FileWriter imp = new FileWriter("LPT1");
			FileWriter imp = new FileWriter(impresora);
			for (int cabecera = 0; cabecera < CabezaLineas.size(); cabecera++) {
				SetFormato(imp, 27);
				imp.write(CabezaLineas.get(cabecera));
			}
			for (int subcabecera = 0; subcabecera < subCabezaLineas.size(); subcabecera++) {
				imp.write(subCabezaLineas.get(subcabecera));
			}
			for (int ITEM = 0; ITEM < items.size(); ITEM++) {
				imp.write(items.get(ITEM));
			}
			for (int total = 0; total < totales.size(); total++) {
				imp.write(totales.get(total));
			}
			for (int pie = 0; pie < LineasPie.size(); pie++) {
				imp.write(LineasPie.get(pie));
			}
			for (int u = 0; u <= 10; u++) {
				imp.write("\n");
			}
			// corta el papel
			char[] CORTAR_PAPEL = new char[] { 0x1B, 'm' };
			imp.write(CORTAR_PAPEL);
			if (abrir) {
				char ABRIR_GAVETA[] = { (char) 27, (char) 112, (char) 0, (char) 10, (char) 100 };
				imp.write(ABRIR_GAVETA);
			}
			imp.close();
			// limpio las listas que contiene los datos
			CabezaLineas.removeAll(CabezaLineas);
			subCabezaLineas.removeAll(subCabezaLineas);
			items.removeAll(items);
			totales.removeAll(totales);
			LineasPie.removeAll(LineasPie);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al Imprimir:\n" + e.getMessage());
			CabezaLineas.removeAll(CabezaLineas);
			subCabezaLineas.removeAll(subCabezaLineas);
			items.removeAll(items);
			totales.removeAll(totales);
			LineasPie.removeAll(LineasPie);
		}
	}

	public static void main(String[] args) {

		try {
			Date date = new Date();
			SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss aa");
			Ticket ticket = new Ticket();
			ticket.AddCabecera("             SANDALS RESTAURANT");
			ticket.AddCabecera(ticket.DarEspacio());
			ticket.AddCabecera("     tlf: 222222  r.u.c: 22222222222");
			ticket.AddCabecera(ticket.DarEspacio());
			ticket.AddSubCabecera(ticket.DibujarLinea(40));
			ticket.AddSubCabecera(ticket.DarEspacio());
			ticket.AddSubCabecera("     Ticket Factura No:'003-000011'");
			ticket.AddSubCabecera(ticket.DarEspacio());
			ticket.AddSubCabecera("        " + fecha.format(date) + " " + hora.format(date));
			ticket.AddSubCabecera(ticket.DarEspacio());
			ticket.AddSubCabecera("         Mesa 1 Mozo Christian Pers 5");
			ticket.AddSubCabecera(ticket.DarEspacio());
			ticket.AddSubCabecera(ticket.DibujarLinea(40));
			ticket.AddSubCabecera(ticket.DarEspacio());
			ticket.AddSubCabecera("cant      producto         p.u     total");
			ticket.AddSubCabecera(ticket.DarEspacio());
			ticket.AddSubCabecera(ticket.DibujarLinea(40));
			ticket.AddSubCabecera(ticket.DarEspacio());
			for (int y = 0; y < 3; y++) {
				// cantidad de decimales
				NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
				DecimalFormat form = (DecimalFormat) nf;
				form.applyPattern("#,###.00");
				// cantidad
				String cantidad = String.valueOf(1+y);
				if (cantidad.length() < 4) {
					int cant = 4 - cantidad.length();
					String can = "";
					for (int f = 0; f < cant; f++) {
						can += " ";
					}
					cantidad += can;
				}
				// items
				String item = "Item "+y;
				if (item.length() > 17) {
					item = item.substring(0, 16) + ".";
				} else {
					int c = 17 - item.length();
					String comple = "";
					for (int y1 = 0; y1 < c; y1++) {
						comple += " ";
					}
					item += comple;
				}
				// precio
				String precio = String.valueOf(y);
				double pre1 = Double.parseDouble(precio);
				precio = form.format(pre1);
				if (precio.length() < 8) {
					int p = 8 - precio.length();
					String pre = "";
					for (int y1 = 0; y1 < p; y1++) {
						pre += " ";
					}
					precio = pre + precio;
				}
				// total
				String total = String.valueOf(y);
				total = form.format(Double.parseDouble(total));
				if (total.length() < 8) {
					int t = 8 - total.length();
					String tota = "";
					for (int y1 = 0; y1 < t; y1++) {
						tota += " ";
					}
					total = tota + total;
				}
				// agrego los items al detalle
				ticket.AddItem(cantidad, item, precio, total);
				ticket.AddItem("","","",ticket.DarEspacio());
			}
			ticket.AddItem(ticket.DibujarLinea(40), "", "", "");
			ticket.AddTotal("", ticket.DarEspacio());
			ticket.AddTotal("total                   ", "100.00");
			ticket.AddTotal("", ticket.DarEspacio());
			ticket.AddTotal("Igv                     ", "16");
			ticket.AddTotal("", ticket.DarEspacio());
			ticket.AddTotal("total venta             ", "116.00");
			ticket.AddTotal("", ticket.DarEspacio());
			ticket.AddTotal("paga con                ", "200.00");
			ticket.AddTotal("", ticket.DarEspacio());
			ticket.AddTotal("vuelto                  ", "84.00");
			ticket.AddPieLinea(ticket.DarEspacio());
			ticket.AddPieLinea("Gracias por su Preferencia");
			ticket.ImprimirDocumento("C:\\Users\\USUARIO\\Documents\\XPSPort.txt",false);
		} catch (Exception e) {
			System.out.print("\nerror " + e.toString());
		}

	}
}