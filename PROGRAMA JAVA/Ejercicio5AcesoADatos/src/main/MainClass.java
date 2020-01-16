package main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.beans.Conducir;
import model.beans.Revisar;
import model.beans.Taller;
import model.beans.Taxi;
import model.beans.Taxista;
import model.beans.others.BdCompleta;
import model.connectionFactory.ConnectionFactory;
import model.dao.ConducirDAO;
import model.dao.RevisarDAO;
import model.dao.TallerDAO;
import model.dao.TaxiDAO;
import model.dao.TaxistaDAO;
import model.utils.reader.XMLReaderListaConducir;
import model.utils.reader.XMLReaderListaRevisar;
import model.utils.reader.XMLReaderTaller;
import model.utils.reader.XMLReaderTaxi;
import model.utils.reader.XMLReaderTaxista;
import model.utils.writer.XMLWriterConducir;
import model.utils.writer.XMLWriterDB;
import model.utils.writer.XMLWriterRevisar;
import model.utils.writer.XMLWriterTaller;
import model.utils.writer.XMLWriterTaxi;
import model.utils.writer.XMLWriterTaxista;

public class MainClass {

	private static Scanner leer = new Scanner(System.in);
	private static File file;
	private static final String WINDOWS_PATH = "F:\\GSINFORMATICA\\2SI\\ACCESO A DATOS\\1ª EVALUACIÓN\\TEMA 2 - XML\\EjerciciosTema2\\EJ5\\XML\\";
	private static final String PATH = WINDOWS_PATH;
	private static String userFileName = "";
	private static String fileName;

	private static TaxiDAO taxiDAO;
	private static TaxistaDAO taxistaDAO;
	private static TallerDAO tallerDAO;
	private static ConducirDAO conducirDAO;
	private static RevisarDAO revisarDAO;

	public static void main(String[] args) {
		menuPrincipal();
	}

	private static void menuPrincipal() {

		boolean salir = false;
		do {
			String texto = "¿Que desea hacer?\n1 - Importar base de datos.\n2 - Exportar base de datos.\n3 - Salir";
			System.out.println(texto);

			String valor = leer.nextLine();

			if (valor != null && !valor.equals("")) {
				switch (Integer.parseInt(valor)) {
				case 1:
					ConnectionFactory.setDb(1);
					menuImportExport("Importar");
					break;

				case 2:
					ConnectionFactory.setDb(0);
					menuImportExport("Exportar");
					break;

				case 3:
					salir = true;
					break;

				default:
					System.out.println("Introduce un valor correcto");
					break;
				}
			}
		} while (!salir);
		System.out.println("Has salido del programa");

	}

	private static void menuImportExport(String value) {

		taxiDAO = new TaxiDAO();
		taxistaDAO = new TaxistaDAO();
		tallerDAO = new TallerDAO();
		conducirDAO = new ConducirDAO();
		revisarDAO = new RevisarDAO();

		boolean salir = false;
		do {
			String texto = "¿Que desea hacer?\n1 - " + value + " la base de datos completa.\n2 - " + value
					+ " tabla TAXI.\n3 - " + value + " tabla TAXISTA.\n4 - " + value + " tabla TALLER.\n5 - " + value
					+ " tabla CONDUCIR.\n6 - " + value + " tabla REVISAR.\n7 - Salir al menu anterior.";
			System.out.println(texto);

			int valor = Integer.parseInt(leer.nextLine());
			if (valor != 7) {

				if (value.equals("Importar")) {
					System.out.println("Introduce el nombre del archivo a abrir");
					userFileName = leer.nextLine();

				} else if (value.equals("Exportar")) {
					System.out.println(
							"Introduce el nombre del archivo a guardar (dejalo en blanco para tener el nombre por defecto)");
					userFileName = leer.nextLine();
				}

				if (userFileName.equals("")) {

					switch (valor) {
					case 1:
						fileName = PATH + "BDCompleta.xml";
						break;
					case 2:
						fileName = PATH + "Taxi.xml";
						break;
					case 3:
						fileName = PATH + "Taxista.xml";
						break;
					case 4:
						fileName = PATH + "Taller.xml";
						break;
					case 5:
						fileName = PATH + "Conducir.xml";
						break;
					case 6:
						fileName = PATH + "Revisar.xml";
						break;

					default:
						break;
					}
				} else {
					if (userFileName.endsWith(".xml")) {
						fileName = PATH + userFileName;
					} else {
						fileName = PATH + userFileName + ".xml";
					}
				}
				file = new File(fileName);
			}

			switch (valor) {
			case 1:
				// Toda la BD
				if (value.equals("Importar")) {
					if (file.exists()) {
						try {
							JAXBContext jaxbContext = JAXBContext.newInstance(BdCompleta.class);
							Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
							BdCompleta bdCompleta = (BdCompleta) jaxbUnmarshaller.unmarshal(file);

							List<Taxi> lstTaxi = bdCompleta.getTaxis().getTaxis();
							for (Taxi taxi : lstTaxi) {
								if (taxiDAO.findById(taxi.getD_mat()) != null) {
									taxiDAO.delete(taxi.getD_mat());
								}
								taxiDAO.add(taxi);
							}

							List<Taxista> lstTaxista = bdCompleta.getTaxistas().getTaxistas();
							for (Taxista taxista : lstTaxista) {
								if (taxistaDAO.findById(taxista.getD_dni()) != null) {
									taxistaDAO.delete(taxista.getD_dni());
								}
								taxistaDAO.add(taxista);
							}

							List<Taller> lstTaller = bdCompleta.getTalleres().getTalleres();
							for (Taller taller : lstTaller) {
								if (tallerDAO.findById(taller.getD_cod()) != null) {
									tallerDAO.delete(taller.getD_cod());
								}
								tallerDAO.add(taller);
							}

							List<Conducir> lstConducir = bdCompleta.getListaConducir().getListaConducir();
							for (Conducir conducir : lstConducir) {
								String[] id = new String[] { conducir.getD_dni(), conducir.getD_mat() };
								if (conducirDAO.findById(id) != null) {
									conducirDAO.delete(id);
								}
								conducirDAO.add(conducir);
							}

							List<Revisar> lstRevisar = bdCompleta.getListaRevisar().getListaRevisar();
							for (Revisar revisar : lstRevisar) {
								String[] id = new String[] { revisar.getD_mat(), revisar.getD_fecha() };
								if (revisarDAO.findById(id) != null) {
									revisarDAO.delete(id);
								}
								revisarDAO.add(revisar);
							}

						} catch (JAXBException e) {
							e.printStackTrace();
						}

						System.out.println("Importacion de la Base de Datos completa!");
					} else {
						System.out.println("El archivo no existe.");
					}
				} else if (value.equals("Exportar")) {
					new XMLWriterDB().writeDataBase(file);
				}

				break;

			case 2:
				// Taxi
				if (value.equals("Importar")) {
					if (file.exists()) {
						List<Taxi> lstTaxi = new XMLReaderTaxi().readTable(file);
						for (Taxi taxi : lstTaxi) {
							if (taxiDAO.findById(taxi.getD_mat()) != null) {
								taxiDAO.delete(taxi.getD_mat());
							}
							taxiDAO.add(taxi);
						}
						System.out.println("Importacion de los Taxis completa!");
					} else {
						System.out.println("El archivo no existe.");
					}
				} else if (value.equals("Exportar")) {
					new XMLWriterTaxi().writeTable(taxiDAO.findAll(), file);
				}

				break;

			case 3:
				// Taxista
				if (value.equals("Importar")) {
					if (file.exists()) {
						List<Taxista> lstTaxista = new XMLReaderTaxista().readTable(file);
						for (Taxista taxista : lstTaxista) {
							if (taxistaDAO.findById(taxista.getD_dni()) != null) {
								taxistaDAO.delete(taxista.getD_dni());
							}
							taxistaDAO.add(taxista);
						}
						System.out.println("Importacion de los Taxistas completa!");

					} else {
						System.out.println("El archivo no existe.");
					}
				} else if (value.equals("Exportar")) {
					new XMLWriterTaxista().writeTable(taxistaDAO.findAll(), file);
				}
				break;

			case 4:
				// Taller
				if (value.equals("Importar")) {
					if (file.exists()) {
						List<Taller> lstTaller = new XMLReaderTaller().readTable(file);
						for (Taller taller : lstTaller) {
							if (tallerDAO.findById(taller.getD_cod()) != null) {
								tallerDAO.delete(taller.getD_cod());
							}
							tallerDAO.add(taller);
						}
						System.out.println("Importacion de los Talleres completa!");
					} else {
						System.out.println("El archivo no existe.");
					}
				} else if (value.equals("Exportar")) {
					new XMLWriterTaller().writeTable(tallerDAO.findAll(), file);
				}

				break;

			case 5:
				// Conducir
				if (value.equals("Importar")) {
					if (file.exists()) {
						List<Conducir> lstConducir = new XMLReaderListaConducir().readTable(file);
						for (Conducir conducir : lstConducir) {
							String[] id = new String[] { conducir.getD_dni(), conducir.getD_mat() };
							if (conducirDAO.findById(id) != null) {
								conducirDAO.delete(id);
							}
							conducirDAO.add(conducir);
						}
						System.out.println("Importacion de la lista Conducir completa!");
					} else {
						System.out.println("El archivo no existe.");
					}
				} else if (value.equals("Exportar")) {
					new XMLWriterConducir().writeTable(conducirDAO.findAll(), file);
				}

				break;

			case 6:
				// Revisar
				if (value.equals("Importar")) {
					if (file.exists()) {
						List<Revisar> lstRevisar = new XMLReaderListaRevisar().readTable(file);
						for (Revisar revisar : lstRevisar) {
							String[] id = new String[] { revisar.getD_mat(), revisar.getD_fecha() };
							if (revisarDAO.findById(id) != null) {
								revisarDAO.delete(id);
							}
							revisarDAO.add(revisar);
						}
						System.out.println("Importacion de la lista Revisar completa!");
					} else {
						System.out.println("El archivo no existe.");
					}
				} else if (value.equals("Exportar")) {
					new XMLWriterRevisar().writeTable(revisarDAO.findAll(), file);
				}

				break;
			case 7:
				salir = true;
				break;

			default:
				System.out.println("Introduce un valor correcto");
				break;
			}

		} while (!salir);
		System.out.println("Has salido del menu de Importar/Exportar");
	}

}
