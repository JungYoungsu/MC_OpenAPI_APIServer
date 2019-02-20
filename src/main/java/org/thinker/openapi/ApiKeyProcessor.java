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
		String apiKey = DigestUtils.md5Hex(UUID.randomUUID().toString());
		System.out.println("## hostname : " + apiKeyVO);
		System.out.println("## keyValue : " + apiKey);
		apiKeyVO.setApiKey(apiKey);
		try {
			repository.create(apiKeyVO);
		} catch (Exception e) { // 중복될 확률이 거의 없지만 혹시라도
			throw new ApiKeyException("SAME KEY IS ALREADY EXIST.");
		}
		return apiKey;
	}

	public void checkApiKey(String hostname, String apiKey) throws ApiKeyException {
		ApiKeyVO vo = repository.read(apiKey);
		if (vo == null) {
			throw new ApiKeyException("OPEN API KEY IS UNREGISTED");
		}
		if (hostname == null || hostname.equals(vo.getHostName()) == false) {
			throw new ApiKeyException("HOSTNAME IS INVALID");
		}
		if (vo.getCount() >= maxCount) {
			throw new ApiKeyException("EXCESSIVE NUMVER OF REQUEST");
		}
		repository.update(apiKey);
	}

}
