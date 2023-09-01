package com.cs.security.client.rest;

import com.cs.security.entities.response.DigitalSignaturePrepareResponse;
import com.cs.security.entities.response.DigitalSignatureSignDataResponse;
import com.cs.security.entities.response.VerifyDigitalSignatureResponse;
import com.cs.security.service.vo.DigitalSignaturePrepareSign;
import com.cs.security.service.vo.DigitalSignatureSignData;
import com.cs.security.service.vo.VerifyDigitalSignatureData;
import com.cs.util.rest.JsonConnHandler;

/**
 * Implements a client handler for the Security API WebService RESTful
 * @author ramirez
 *
 */
public class SecurityAPIRestClient {

	/**
	 * Call the Security API RESTful method prepareSign, which is the step 1 of 2 to sign a data using the SDK in the Server side
	 * @param svcUrl the Security큦 URL, including the Restful큦 Security API context
	 * @param dataToBeSigned the data that will be signed
	 * @param messageDigestAlg the message digest Algorithm
	 * @param signerCertificate the signer큦 Certificate encoded (encoded as BASE64)
	 * @param signatureType the type of signature (CMS, AD_RB, AD_RT)
	 * @return the Digital Signature signer info parameters to be signed with the user큦 Private Key in the Client side
	 */
	public static final DigitalSignaturePrepareResponse callPrepareSign(String svcUrl, byte[] dataToBeSigned, String messageDigestAlg, String signerCertificate, int signatureType) {
		
		DigitalSignaturePrepareSign digitalSignaturePrepareSign = new DigitalSignaturePrepareSign(dataToBeSigned, messageDigestAlg, signerCertificate, signatureType);
		
		DigitalSignaturePrepareResponse digitalSignaturePrepareResponse = (DigitalSignaturePrepareResponse) JsonConnHandler.callSvcREST(svcUrl, "prepareSign", "POST", digitalSignaturePrepareSign, DigitalSignaturePrepareResponse.class);
		return digitalSignaturePrepareResponse;
	}

	/**
	 * Call the Security API RESTful method prepareSign, which is the step 1 of 2 to sign a data using the SDK in the Server side
	 * @param svcUrl the Security큦 URL, including the Restful큦 Security API context
	 * @param messageDigestInHex the message digest of the data encoded in HEXadecimal
	 * @param messageDigestAlg the message digest Algorithm
	 * @param signerCertificate the signer큦 Certificate encoded (encoded as BASE64)
	 * @param signatureType the type of signature (CMS, AD_RB, AD_RT)
	 * @return the Digital Signature signer info parameters to be signed with the user큦 Private Key in the Client side
	 */
	public static final DigitalSignaturePrepareResponse callPrepareSign(String svcUrl, String messageDigestInHex, String messageDigestAlg, String signerCertificate, int signatureType) {
		
		DigitalSignaturePrepareSign digitalSignaturePrepareSign = new DigitalSignaturePrepareSign(null, messageDigestInHex, messageDigestAlg, signerCertificate, signatureType);
		
		DigitalSignaturePrepareResponse digitalSignaturePrepareResponse = (DigitalSignaturePrepareResponse) JsonConnHandler.callSvcREST(svcUrl, "prepareSign", "POST", digitalSignaturePrepareSign, DigitalSignaturePrepareResponse.class);
		return digitalSignaturePrepareResponse;
	}

	/**
	 * Call the Security API RESTful method prepareSign, which is the step 1 of 2 to sign a data using the SDK in the Server side
	 * @param svcUrl the Security큦 URL, including the Restful큦 Security API context
	 * @param digitalSignaturePrepareSign the digital signature structure parameters
	 * @return the Digital Signature signer info parameters to be signed with the user큦 Private Key in the Client side
	 */
	public static final DigitalSignaturePrepareResponse callPrepareSign(String svcUrl, DigitalSignaturePrepareSign digitalSignaturePrepareSign) {
		DigitalSignaturePrepareResponse digitalSignaturePrepareResponse = (DigitalSignaturePrepareResponse) JsonConnHandler.callSvcREST(svcUrl, "prepareSign", "POST", digitalSignaturePrepareSign, DigitalSignaturePrepareResponse.class);
		return digitalSignaturePrepareResponse;
	}

	/**
	 * Call the Security API RESTful method envelopeSignedData, which is the step 2 of 2 to sign a data using the SDK in the Server side
	 * The envelope method will envelope the data signed by the user큦 PrivateKey in the CMS:signedData format
	 * @param svcUrl the Security큦 URL, including the Restful큦 Security API context
	 * @param dataToBeSigned the data that will be signed. MUST be the same passed in the prepareSign
	 * @param messageDigestAlg the message digest Algorithm
	 * @param signedAttributes the signer info parameters (encoded as BASE64) target to be signed with the user큦 Private Key in the Client side. MUST be the same returned by prepareSign method
	 * @param signerCertificate the signer큦 Certificate encoded (encoded as BASE64). MUST the same passed in the prepareSign method
	 * @param signature the digital signature as a raw PKCS#1 produced by the user큦 PrivateKey in the Client side  (encoded as BASE64)
	 * @return the Digital Signature in the CMS:signedData format or error
	 */
	public static DigitalSignatureSignDataResponse callEnvelopeSignedData(String svcUrl, byte[] dataToBeSigned, String messageDigestAlg, String signedAttributes, String signerCertificate, String signature) {

		DigitalSignatureSignData digitalSignatureSignData = new DigitalSignatureSignData(dataToBeSigned, signedAttributes, signerCertificate, signature, messageDigestAlg);
		
		DigitalSignatureSignDataResponse digitalSignatureSignDataResponse = (DigitalSignatureSignDataResponse) JsonConnHandler.callSvcREST(svcUrl, "envelopeSignedData", "POST", digitalSignatureSignData, DigitalSignatureSignDataResponse.class);
		return digitalSignatureSignDataResponse;
	}
	
	/**
	 * Call the Security API RESTful method envelopeSignedData, which is the step 2 of 2 to sign a data using the SDK in the Server side
	 * The envelope method will envelope the data signed by the user큦 PrivateKey in the CMS:signedData format
	 * @param svcUrl the Security큦 URL, including the Restful큦 Security API context
	 * @param messageDigestAlg the message digest Algorithm
	 * @param signedAttributes the signer info parameters (encoded as BASE64) target to be signed with the user큦 Private Key in the Client side. MUST be the same returned by prepareSign method
	 * @param signerCertificate the signer큦 Certificate encoded (encoded as BASE64). MUST the same passed in the prepareSign method
	 * @param signature the digital signature as a raw PKCS#1 produced by the user큦 PrivateKey in the Client side  (encoded as BASE64)
	 * @return the Digital Signature in the CMS:signedData format or error
	 */
	public static DigitalSignatureSignDataResponse callEnvelopeSignedData(String svcUrl, String messageDigestAlg, String signedAttributes, String signerCertificate, String signature) {

		DigitalSignatureSignData digitalSignatureSignData = new DigitalSignatureSignData(null, signedAttributes, signerCertificate, signature, messageDigestAlg);
		
		DigitalSignatureSignDataResponse digitalSignatureSignDataResponse = (DigitalSignatureSignDataResponse) JsonConnHandler.callSvcREST(svcUrl, "envelopeSignedData", "POST", digitalSignatureSignData, DigitalSignatureSignDataResponse.class);
		return digitalSignatureSignDataResponse;
	}
	
	/**
	 * Call the Security API RESTful method verifySignature, to validate a digital signature
	 * The envelope method will validate CMS:signedData and all of certificates included
	 * @param svcUrl the Security큦 URL, including the Restful큦 Security API context
	 * @param verifyDigitalSignatureData the cms
	 * @return the Digital Signature in the CMS:signedData format or error
	 */
	public static VerifyDigitalSignatureResponse callVerifySignature(String svcUrl, VerifyDigitalSignatureData verifyDigitalSignatureData) {

		VerifyDigitalSignatureResponse verifyDigitalSignatureResponse = (VerifyDigitalSignatureResponse)JsonConnHandler.callSvcREST(svcUrl, "verifySignature", "POST", verifyDigitalSignatureData, VerifyDigitalSignatureResponse.class);
		
		return verifyDigitalSignatureResponse;
	}
	
}
