package org.thinker.openapi;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyProcessor {

	private static final String API_KEY_PROPERTIES = "apiKey.properties";
	private static final String MAX_COUNT = "max";

	private static int maxCount;

	private Properties prop;

	@Autowired
	private ApiKeyRepository repository;

	public ApiKeyProcessor() throws ApiKeyException {

		this(ApiKeyProcessor.class.getResource(API_KEY_PROPERTIES));

	}

	public ApiKeyProcessor(URL url) throws ApiKeyException {

		prop = new Properties();

		try {
			prop.load(url.openStream());
			maxCount = Integer.parseInt(prop.getProperty(MAX_COUNT));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApiKeyException("Could not find API KEY FILE");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiKeyException(e);
		}
	}

	public String requestNewAPIKey(ApiKeyVO apiKeyVO) throws Exception {
		//아래 라인을 삭제하고 이곳에 코드를 작성하세요.
		return null;
	}

	public void checkApiKey(String hostname, String apiKey) throws ApiKeyException {
		//이곳에 코드를 작성하세요.
	}

}
