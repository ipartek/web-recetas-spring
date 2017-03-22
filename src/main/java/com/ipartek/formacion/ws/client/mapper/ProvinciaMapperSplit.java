package com.ipartek.formacion.ws.client.mapper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ipartek.formacion.domain.Provincia;

public class ProvinciaMapperSplit implements MapperProvincia {

	@Override
	public ArrayList<Provincia> parse(String xmlProvincias) {
		
		ArrayList<Provincia> arrayProvincias= new ArrayList<Provincia>();
		
		Provincia provincia = null;
		int provinciaId = 0;
		String provinciaCodigo = "";
		String provinciaNombre = "";
		
		try{
		
			Pattern pattern = Pattern.compile("\\<provincia(.+?)/provincia\\>");
			Matcher matcher = pattern.matcher(xmlProvincias);
			
			while(matcher.find()){
			  String betweenXmlTags=matcher.group();
	
			  String[] betweenQuotes =betweenXmlTags.split("\"");
			  String[] betweenTags =betweenXmlTags.split(">");	  
			  
			  provinciaId = Integer.valueOf(betweenQuotes[1]);
			  provinciaCodigo = betweenQuotes[3];
			  provinciaNombre = betweenTags[1].replace("</provincia", "");
			  
			  provincia = new Provincia();
			  provincia.setId(provinciaId);
			  provincia.setCod(provinciaCodigo);
			  provincia.setNombre(provinciaNombre);
			  arrayProvincias.add(provincia);
			  		   
			}
			
		}catch( Exception e){
			e.printStackTrace();
			arrayProvincias = null;
		}	
		return arrayProvincias;
		
		
	}

}
