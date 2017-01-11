package co.com.uni5d.start;

import java.util.Collections;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import co.com.uni5d.config.Configuracion;
import co.com.uni5d.domain.Respuesta;
import co.com.uni5d.service.AppService;
import co.com.uni5d.util.Util;

@Component
public class Start {

	@Autowired
	AppService appService;
	
	
	private int rango = 2;
	

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);
		Start p = context.getBean(Start.class);
		p.init();
	}

	private void init() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("apiKey", Util.API_KEY);
		map.add("country", "CO");
		map.add("currency", "COP");
		map.add("locale", "ES-es");
		map.add("originplace", "BOG-sky");
		map.add("destinationplace", "CTG-sky");
		map.add("outbounddate", "2017-02-01");
		map.add("adults", "1");
		map.add("inbounddate", "2017-02-20");

		System.err.println(map.get("outbounddate"));

		for (int i = 1; i <=28 ; i++) {
			map.get("outbounddate").clear();
			String dato1= i<10?"0"+i:""+i;
			
			map.get("outbounddate").add("2017-02-" + dato1);
			
			map.get("inbounddate").clear();
			int a = i + rango;
			String dato2= a<10?"0"+a:""+a;
			map.get("inbounddate").add("2017-02-"+dato2);

			System.err.print("\n"+map.get("outbounddate"));
			System.err.println(":: "+map.get("inbounddate"));

			HttpEntity<Object> entity = new HttpEntity<Object>(map, headers);

			

				Respuesta r = appService.consultarVuelos(entity);
				if(r!=null){
					
					System.err.println("1:::" + r.getSessionKey());
					appService.SalvarJson(r);	
				}else{
					
					System.err.println("Respuesta Nula");
				}
				
				

			
		}

	}

}
