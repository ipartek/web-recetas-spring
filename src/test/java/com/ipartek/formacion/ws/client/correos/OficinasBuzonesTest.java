package com.ipartek.formacion.ws.client.correos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.ipartek.formacion.domain.Provincia;
import com.ipartek.formacion.ws.client.mapper.MapperProvincia;

public class OficinasBuzonesTest {

	@Test
	public void testConsultaProvincias() {
		
		
		/*
		OficinasBuzones cliente = new OficinasBuzones();
		OficinasBuzonesSoap clienteSoap = cliente.getOficinasBuzonesSoap();		
		String xmlProvincias = clienteSoap.consultaProvincias("");
		*/
		
//		String xmlProvincias = "<?xml version=\"1.0\" encoding=\"Windows-1252\"?><resultado idioma=\"ES \"><provincia id=\"15\" comaut=\"GAL\">A CORUÑA</provincia><provincia id=\"02\" comaut=\"CLA\">ALBACETE</provincia><provincia id=\"03\" comaut=\"CVA\">ALICANTE</provincia><provincia id=\"04\" comaut=\"AND\">ALMERIA</provincia><provincia id=\"01\" comaut=\"PVS\">ARABA/ALAVA</provincia><provincia id=\"33\" comaut=\"AST\">ASTURIAS</provincia><provincia id=\"05\" comaut=\"CLE\">AVILA</provincia><provincia id=\"06\" comaut=\"EXT\">BADAJOZ</provincia><provincia id=\"08\" comaut=\"CAT\">BARCELONA</provincia><provincia id=\"48\" comaut=\"PVS\">BIZKAIA</provincia><provincia id=\"09\" comaut=\"CLE\">BURGOS</provincia><provincia id=\"10\" comaut=\"EXT\">CACERES</provincia><provincia id=\"11\" comaut=\"AND\">CADIZ</provincia><provincia id=\"39\" comaut=\"CAB\">CANTABRIA</provincia><provincia id=\"12\" comaut=\"CVA\">CASTELLON DE LA PLANA</provincia><provincia id=\"51\" comaut=\"CEU\">CEUTA</provincia><provincia id=\"13\" comaut=\"CLA\">CIUDAD REAL</provincia><provincia id=\"14\" comaut=\"AND\">CORDOBA</provincia><provincia id=\"16\" comaut=\"CLA\">CUENCA</provincia><provincia id=\"20\" comaut=\"PVS\">GIPUZKOA</provincia><provincia id=\"17\" comaut=\"CAT\">GIRONA</provincia><provincia id=\"18\" comaut=\"AND\">GRANADA</provincia><provincia id=\"19\" comaut=\"CLA\">GUADALAJARA</provincia><provincia id=\"21\" comaut=\"AND\">HUELVA</provincia><provincia id=\"22\" comaut=\"ARA\">HUESCA</provincia><provincia id=\"07\" comaut=\"BAL\">ILLES BALEARS</provincia><provincia id=\"23\" comaut=\"AND\">JAEN</provincia><provincia id=\"26\" comaut=\"RIO\">LA RIOJA</provincia><provincia id=\"35\" comaut=\"CAA\">LAS PALMAS</provincia><provincia id=\"24\" comaut=\"CLE\">LEON</provincia><provincia id=\"25\" comaut=\"CAT\">LLEIDA</provincia><provincia id=\"27\" comaut=\"GAL\">LUGO</provincia><provincia id=\"28\" comaut=\"MAD\">MADRID</provincia><provincia id=\"29\" comaut=\"AND\">MALAGA</provincia><provincia id=\"52\" comaut=\"MEL\">MELILLA</provincia><provincia id=\"30\" comaut=\"MUR\">MURCIA</provincia><provincia id=\"31\" comaut=\"NAV\">NAVARRA</provincia><provincia id=\"32\" comaut=\"GAL\">OURENSE</provincia><provincia id=\"34\" comaut=\"CLE\">PALENCIA</provincia><provincia id=\"36\" comaut=\"GAL\">PONTEVEDRA</provincia><provincia id=\"00\" comaut=\"ADR\">PRINCIPADO DE ANDORRA</provincia><provincia id=\"37\" comaut=\"CLE\">SALAMANCA</provincia><provincia id=\"38\" comaut=\"CAA\">SANTA CRUZ DE TENERIFE</provincia><provincia id=\"40\" comaut=\"CLE\">SEGOVIA</provincia><provincia id=\"41\" comaut=\"AND\">SEVILLA</provincia><provincia id=\"42\" comaut=\"CLE\">SORIA</provincia><provincia id=\"43\" comaut=\"CAT\">TARRAGONA</provincia><provincia id=\"44\" comaut=\"ARA\">TERUEL</provincia><provincia id=\"45\" comaut=\"CLA\">TOLEDO</provincia><provincia id=\"46\" comaut=\"CVA\">VALENCIA</provincia><provincia id=\"47\" comaut=\"CLE\">VALLADOLID</provincia><provincia id=\"49\" comaut=\"CLE\">ZAMORA</provincia><provincia id=\"50\" comaut=\"ARA\">ZARAGOZA</provincia></resultado>";
		
		String xmlProvincias = "<?xml version=\"1.0\" encoding=\"Windows-1252\"?>"+
								"<resultado idioma=\"ES \">"+
									"<provincia id=\"15\" comaut=\"GAL\">A CORUÑA</provincia>"+
								    "<provincia id=\"02\" comaut=\"CLA\">ALBACETE</provincia>"+ 
								"</resultado>";
		
		assertNotNull(xmlProvincias);
		
		//convertir String => ArrayList<Provincia>
		ArrayList<Provincia> provincias = MapperProvincia.parse(xmlProvincias);
		
		//testear numero de provincias == X
		assertNotNull(provincias);
		assertEquals( 49 ,  provincias.size() );
		
		
		

		
		
	}

}
