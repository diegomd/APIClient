package shared;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import play.libs.F.Function;
import play.libs.WS;
import play.libs.WS.WSRequestHolder;

public class HTTPUtils {

	public static String getStringResponse(String url, Map<String, String> queryParameters ) {
		return getStringResponse(url, null, queryParameters);
	}

	public static String getStringResponse(String url, Map<String, String> headerParameters, Map<String, String> queryParameters) {
		
		WSRequestHolder rh = WS.url(url);

		//Set header parameters
		if (headerParameters != null && !headerParameters.isEmpty()) {
			for (String q : headerParameters.keySet()) {
				rh.setHeader(q, headerParameters.get(q));
			}			
		}

		//Set query parameters
		if (queryParameters != null && !queryParameters.isEmpty()) {
			for (String q : queryParameters.keySet()) {
				rh.setQueryParameter(q, queryParameters.get(q));
			}			
		}
		
		String ret = rh.get().map(new Function<WS.Response, String>() {
			public String apply(WS.Response response) {
				return response.getBody();
			}
        }).getWrappedPromise().await(60, TimeUnit.SECONDS).get();

		return ret;
	}
	
}
