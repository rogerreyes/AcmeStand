package co.com.uni5d.domain;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "Respuesta")
public class Respuesta  {

	
	@Id
	@JsonProperty
	private ObjectId id;
	@JsonView
	private String SessionKey;
	@JsonView
	private List<Itinerario> Itineraries;

	public String getSessionKey() {
		return SessionKey;
	}

	public void setSessionKey(String sessionKey) {
		SessionKey = sessionKey;
	}

	public List<Itinerario> getItineraries() {
		return Itineraries;
	}

	public void setItineraries(List<Itinerario> itineraries) {
		Itineraries = itineraries;
	}

	@Override
	public String toString() {
		return "Respuesta [SessionKey=" + SessionKey + ", Itineraries=" + Itineraries + "]";
	}

	
	
	

}
