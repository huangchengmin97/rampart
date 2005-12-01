/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.axis2.security.util;

import org.apache.axis2.om.OMElement;
import org.apache.axis2.om.impl.llom.builder.StAXOMBuilder;
import org.apache.axis2.security.handler.WSSHandlerConstants;
import org.apache.axis2.security.trust.TrustException;
import org.apache.axis2.soap.SOAPEnvelope;
import org.apache.axis2.soap.impl.llom.builder.StAXSOAPModelBuilder;
import org.apache.ws.security.SOAPConstants;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.util.WSSecurityUtil;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Utility class for the Axis2-WSS4J Module
 */
public class Axis2Util {

	/**
	 * Create a DOM Document using the SOAP Envelope
	 * @param env An org.apache.axis2.soap.SOAPEnvelope instance 
	 * @return the DOM Document of the given SOAP Envelope
	 * @throws Exception
	 */
	public static Document getDocumentFromSOAPEnvelope(SOAPEnvelope env)
			throws WSSecurityException {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			/**
			 * There are plans to deprecate the OmNode.serializeAndConsume(XMLStreamWriter)
			 * method therefore using OMOutoutImpl to serializeAndConsume the env
			 */
			env.serialize(baos);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			return factory.newDocumentBuilder().parse(bais);
		} catch (Exception e) {
			throw new WSSecurityException(
					"Error in converting SOAP Envelope to Document", e);
		}
	}

	/**
	 * Covert a DOM Document containing a SOAP Envelope in to a
	 * org.apache.axis2.soap.SOAPEnvelope 
	 * @param doc DOM Document
	 * @param envelopeNS SOAP Namespace of the the given Envelope
	 * @return
	 * @throws Exception
	 */
	public static SOAPEnvelope getSOAPEnvelopeFromDocument(Document doc,
			String envelopeNS) throws WSSecurityException {
		try {
			//Set the new SOAPEnvelope
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			XMLUtils.outputDOM(doc, os, true);
			
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			XMLStreamReader reader = XMLInputFactory.newInstance()
					.createXMLStreamReader(is);

			StAXSOAPModelBuilder builder = new StAXSOAPModelBuilder(reader,
					envelopeNS);
			builder.setCache(true);

			return builder.getSOAPEnvelope();

		} catch (Exception e) {
			throw new WSSecurityException(
					"Error in converting document to SOAPEnvelope", e);
		}

	}
	
	/**
	 * This is to be used only in the signature situation
	 * where the security header can be inserted into the original SOAPEnvelope
	 * rather than replacing the whole envelope
	 * @param doc
	 * @param envelopeNS
	 * @param reqEnv
	 * @return
	 * @throws WSSecurityException
	 */
	public static SOAPEnvelope getSOAPEnvelopeFromDocument(Document doc,
			SOAPConstants constants, SOAPEnvelope reqEnv) throws WSSecurityException {
		
		//Get holdof the security header
		Element secElem = WSSecurityUtil.getSecurityHeader(doc,null, constants);
		
		//insert the header into the OM-SOAPEnvelope
		
		OMElement secOmElem = convertToOMelement(secElem, constants);
		
		reqEnv.getHeader().addChild(secOmElem);
		
		return reqEnv;
		
	}
	
	/**
	 * Converts the given DOM Element to an OMElement
	 * @param elem
	 * @param constants
	 * @return
	 * @throws WSSecurityException
	 */
	private static OMElement convertToOMelement(Element elem, SOAPConstants constants) throws WSSecurityException {

		try {
			XMLSerializer xmlSer = new XMLSerializer();
			
			/*
			 *When we extract the wsse:Security header by serializing it
			 *The namespaces declared globally will not be copied into the
			 *serialized element. Therefore we have to add the missing namespaces 
			 *in to the element before DOm serialization 
			 */
			elem.setAttribute("xmlns:soapenv",constants.getEnvelopeURI());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			xmlSer.setOutputByteStream(baos);
			
			xmlSer.serialize(elem);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(bais);
			StAXOMBuilder builder = new StAXOMBuilder(reader);
			builder.setCache(true);
			
			return builder.getDocumentElement();
			
		} catch (Exception e) {
			throw new WSSecurityException(e.getMessage(),e);
		}

	}

	/**
	 * This is used to provide the appropriate key to pickup 
	 * config params from the message context.
	 * This is acutally used when the outflow handler (WSDoAllSender)
	 * is repeated n number of times
	 * @param originalKey The default key
	 * @param inHandler Whether the handler is the inflow handler or not
	 * @param repetition The current repetition number
	 * @return The key to be used internally in the security module to pick
	 * up the config params
	 */
	public static String getKey(String originalKey, boolean inHandler, int repetition) {
		
		if(repetition > 0 && !inHandler && 
				!originalKey.equals(WSSHandlerConstants.OUTFLOW_SECURITY)&&	
				!originalKey.equals(WSSHandlerConstants.SENDER_REPEAT_COUNT)) {
			
				return originalKey + repetition;
		}
		return originalKey;
	}
	
	/**
	 * Convert a given DOM Element to an OMElement
	 * @param element
	 * @return
	 * @throws TrustException
	 */
	public static OMElement toOM(Element element) throws TrustException {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			XMLUtils.outputDOM(element, os, true);
			
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			XMLStreamReader reader = XMLInputFactory.newInstance()
					.createXMLStreamReader(is);

			StAXOMBuilder builder = new StAXOMBuilder(reader);
			builder.setCache(true);

			return builder.getDocumentElement();
		} catch (XMLStreamException e) {
			throw new TrustException(TrustException.ERROR_IN_CONVERTING_TO_OM,
					TrustException.ERROR_IN_CONVERTING_TO_OM, new Object[] {}, e);
		}
	}
	

	/**
	 * Convert a given OMElement to a DOM Element
	 * @param element
	 * @return
	 * @throws TrustException
	 */
	public static Element toDOM(OMElement element) throws TrustException {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
			element.serialize(baos);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			return factory.newDocumentBuilder().parse(bais).getDocumentElement();
		} catch (Exception e) {
			throw new TrustException(TrustException.ERROR_IN_CONVERTING_TO_DOM,
					TrustException.ERROR_IN_CONVERTING_TO_DOM, new Object[] {}, e);			
		}
	}
}
