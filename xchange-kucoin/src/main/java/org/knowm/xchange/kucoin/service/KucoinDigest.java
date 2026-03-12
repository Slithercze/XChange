package org.knowm.xchange.kucoin.service;

import com.google.common.base.Strings;
import jakarta.ws.rs.HeaderParam;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Mac;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

/** Almost identical to Coinbase Pro (even down to the text in the API documentation). */
public class KucoinDigest extends BaseParamsDigest {

  private String signature = "";

  private KucoinDigest(byte[] secretKey) {
    super(secretKey, HMAC_SHA_256);
  }

  public static KucoinDigest createInstance(String secretKey) {
    return Strings.isNullOrEmpty(secretKey)
        ? null
        : new KucoinDigest(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    String pathWithQueryString =
        restInvocation.getInvocationUrl().replace(restInvocation.getBaseUrl(), "");
    String message =
        restInvocation
                .getParamValue(HeaderParam.class, APIConstants.API_HEADER_TIMESTAMP)
                .toString()
            + restInvocation.getHttpMethod()
            + pathWithQueryString
            + (restInvocation.getRequestBody() != null ? restInvocation.getRequestBody() : "");

    Mac mac256 = getMac();

    try {
      mac256.update(message.getBytes("UTF-8"));
    } catch (Exception e) {
      throw new ExchangeException("Digest encoding exception", e);
    }

    signature = Base64.getEncoder().encodeToString(mac256.doFinal());
    return signature;
  }

  public String getSignature() {
    return signature;
  }

  /**
   * Encrypts the passphrase for KuCoin API v2 keys. The passphrase is signed with the secret key
   * using HMAC-SHA256 and then base64-encoded.
   */
  public String encryptPassphrase(String passphrase) {
    Mac mac256 = getMac();
    try {
      mac256.update(passphrase.getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      throw new ExchangeException("Passphrase encryption exception", e);
    }
    return Base64.getEncoder().encodeToString(mac256.doFinal());
  }
}
