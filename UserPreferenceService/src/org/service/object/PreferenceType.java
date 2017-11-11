package org.service.object;

public class PreferenceType {

	private static final long serialVersionUID = 1591422775435640289L;
	private String key = "";
	private String message = "";
	
	public PreferenceType() {
		
	}
	public PreferenceType(String pKey, String pMessage) {
		setKey(pKey);
		setMessage(pMessage);
	}

	// store key name/value
	public String getKey() {
		return this.key;
	}

	public void setKey(String pKey) {
		this.key = pKey;
	}
	
	// store message name/value
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String pMessage) {
		this.message = pMessage;
	}
}
