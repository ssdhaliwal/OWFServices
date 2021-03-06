package org.service.support;

import java.util.*;
import org.service.object.*;
import elsu.common.*;
import elsu.support.*;

public class PSSupportStore {

	private static String _ENCRYPTIONKEYPATH = "application.encryption.key.key";
	private static String _ENCRYPTIONVECTORPATH = "application.encryption.key.initVector";
	private static String _STORAGEPATH = "application.framework.attributes.key.storage.repository";

	public static String array2JSON(String[] pKeys, String[] pValues) {
		StringBuilder result = new StringBuilder();
		Boolean firstTime = true;

		result.append("{");
		if (pKeys.length == pValues.length) {
			for (int i = 0; i < pKeys.length; i++) {
				result.append((firstTime ? "" : ",") + "\"" + pKeys[i] + "\": " + "\"" + pValues[i] + "\"");
				if (firstTime) {
					firstTime = !firstTime;
				}
			}
		}
		result.append("}");

		return result.toString();
	}

	public static String map2JSON(HashMap<String, String> pMap) {
		StringBuilder result = new StringBuilder();
		Boolean firstTime = true;

		result.append("{");
		for (String key : pMap.keySet()) {
			result.append((firstTime ? "" : ",") + "\"" + key + "\": " + "\"" + pMap.get(key) + "\"");
			if (firstTime) {
				firstTime = !firstTime;
			}
		}
		result.append("}");

		return result.toString();
	}

	public static String readPreference(GlobalSet pResources, ConfigLoader pConfig, String pId, String pKey,
			PreferenceType pPreference) {
		String result = null;
		String storePath = pConfig.getProperty(_STORAGEPATH).toString();
		String fileName = storePath + (storePath.endsWith("/") ? "" : "/") + pId + "." + pKey;

		try {
			// store the file
			String content = FileUtils.readFile(fileName);
			
			// decrypt the content
			result = EncryptionUtils.decrypt(pPreference.getKey() + pConfig.getProperty(PSSupportStore._ENCRYPTIONKEYPATH).toString(),
					pConfig.getProperty(PSSupportStore._ENCRYPTIONVECTORPATH).toString(), content);
			
			// compare the decrypted content to match original key
			PreferenceType kpt = (PreferenceType) JsonXMLUtils.JSon2Object(result, PreferenceType.class);	
			if (!kpt.getKey().equals(pPreference.getKey())) {
				result = null;
			}
		} catch (Exception ex) {
			pConfig.logError("readPreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace());
		}

		return result;
	}

	public static boolean storePreference(GlobalSet pResources, ConfigLoader pConfig, String pId, String pKey,
			PreferenceType pPreference) {
		boolean result = false;
		String storePath = pConfig.getProperty(_STORAGEPATH).toString();
		String fileName = storePath + (storePath.endsWith("/") ? "" : "/") + pId + "." + pKey;

		try {
			// if file does exists, then validate it for ownership
			if (FileUtils.fileExists(storePath, (pId + "." + pKey)) > 0) {
				if (readPreference(pResources, pConfig, pId, pKey, pPreference) == null) {
					return false;
				}
			}
			
			// encrypt the content
			String encryptedData = EncryptionUtils.encrypt(
					pPreference.getKey() + pConfig.getProperty(PSSupportStore._ENCRYPTIONKEYPATH).toString(),
					pConfig.getProperty(PSSupportStore._ENCRYPTIONVECTORPATH).toString(), 
					JsonXMLUtils.Object2JSon(pPreference));

			// store the file
			FileUtils.writeFile(fileName, encryptedData, true);

			// update result
			result = true;
		} catch (Exception ex) {
			pConfig.logError("storePreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace());
		}

		return result;
	}

	public static boolean deletePreference(GlobalSet pResources, ConfigLoader pConfig, String pId, String pKey,
			PreferenceType pPreference) {
		boolean result = false;
		String storePath = pConfig.getProperty(_STORAGEPATH).toString();
		String fileName = storePath + (storePath.endsWith("/") ? "" : "/") + pId + "." + pKey;

		try {
			// check to make sure the preference is owned by the user
			if (readPreference(pResources, pConfig, pId, pKey, pPreference) != null) {
				// remove the file
				FileUtils.deleteFile(fileName);
				result = true;
			}
		} catch (Exception ex) {
			pConfig.logError("deletePreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace());
		}

		return result;
	}
}
