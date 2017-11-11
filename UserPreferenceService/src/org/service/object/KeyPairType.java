package org.service.object;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.*;

@XmlRootElement
public class KeyPairType implements Serializable {

	private static final long serialVersionUID = -2242660842337222045L;
	private Map<String, Object> attributes = new ConcurrentHashMap<>();

	public KeyPairType() {
	} // needed for JAXB

	public KeyPairType(String key, Object value) {
		this.attributes.put(key, value);
	}

	@JsonValue
	@XmlValue
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> pAttributes, boolean pReset) {
		if (pAttributes == null) {
			return;
		}

		if (pReset) {
			this.clear();
			this.attributes = pAttributes;
		} else {
			for (String key : pAttributes.keySet()) {
				if (this.containsKey(key)) {
					this.clear(key);
				}

				this.add(key, pAttributes.get(key));
			}
		}
	}

	public void setAttributes(KeyPairType pKeyPair, boolean pReset) {
		this.setAttributes(pKeyPair.getAttributes(), pReset);
	}

	public void add(String key, Object o) {
		attributes.put(key, o);
	}

	public void add(String key, Object[] os) {
		for (Object o : os) {
			attributes.put(key, o);
		}
	}

	public Set<String> get() {
		return attributes.keySet();
	}

	public Object get(String key) {
		return attributes.get(key);
	}

	public void clear() {
		attributes.clear();
	}

	public void clear(String key) {
		attributes.remove(key);
	}

	public void clear(String[] keys) {
		for (String key : keys) {
			attributes.remove(key);
		}
	}

	public Boolean containsKey(String key) {
		return attributes.containsKey(key);
	}

	public Boolean containsKey(String[] keys) {
		Boolean result = false;

		for (String key : keys) {
			if (containsKey(key)) {
				result = true;
				break;
			}
		}

		return result;
	}

	public Boolean containsKeys(String[] keys) {
		Boolean result = false;

		for (String key : keys) {
			if (!containsKey(key)) {
				result = false;
				break;
			} else {
				result = true;
			}
		}

		return result;
	}
}
