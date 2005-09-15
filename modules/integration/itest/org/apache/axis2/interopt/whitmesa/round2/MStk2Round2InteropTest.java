package org.apache.axis2.interopt.whitmesa.round2;

import org.apache.axis2.soap.SOAPEnvelope;
import org.apache.axis2.interopt.whitemesa.round2.util.*;
import org.apache.axis2.interopt.whitemesa.round2.SunRound2Client;
import org.apache.axis2.interopt.whitemesa.WhiteMesaIneterop;
import org.apache.axis2.AxisFault;
import java.io.*;

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
*
*
*/

/**
 * Author: Gayan Asanka
 * Date: Aug 23, 2005
 * Time: 4:27:20 PM
 */

/**
 * class  MStk2Round2InteropTest
 * To test Interoperability Axis2 clients vs MS SOAP ToolKit 2.0 Server, Round2
 * WSDLs:-
 * Group b         http://mssoapinterop.org/stk/InteropB.wsdl
 * group b(Typed)  http://mssoapinterop.org/stk/InteropBtyped.wsdl
 * Group c         http://mssoapinterop.org/stk/InteropC.wsdl
 */

public class MStk2Round2InteropTest extends WhiteMesaIneterop {

    SOAPEnvelope retEnv = null;
    boolean success = false;
    File file = null;
    String url = "";
    String soapAction = "";
    String FS = System.getProperty("file.separator");
    String resFilePath = "interopt/whitemesa/round2/";
    String tempPath = "";
    SunRound2ClientUtil util;
    private boolean results = false;

    /**
     * Round2
     * Group B
     * operation echoStructAsSimpleTypes
     */
    public void testR2GBEchoStructAsSimpleTypes() throws AxisFault {
        url = "http://mssoapinterop.org/stk/interopB.wsdl";
        soapAction = "http://soapinterop.org/";

        util = new GroupbEchoStructAsSimpleTypesUtil();
        retEnv = SunRound2Client.sendMsg(util, url, soapAction);
        tempPath = resFilePath + "MStk2GroupbStructAsSimpleTypesRes.xml";
        results = compare(retEnv, tempPath);
        assertTrue(results);
    }

    /**
     * Round2
     * Group B
     * operation echoSimpleTypesAsStruct
     */
    public void testR2GBEchoSimpleTypesAsStruct() throws AxisFault {
        url = "http://mssoapinterop.org/stk/interopB.wsdl";
        soapAction = "http://soapinterop.org/";

        util = new GroupbEchoSimpleTypesAsStructUtil();
        retEnv = SunRound2Client.sendMsg(util, url, soapAction);
        tempPath = resFilePath + "MStk2GroupbSimpletypesAsStructRes.xml";
        results = compare(retEnv, tempPath);
        assertTrue(results);
    }

    /**
     * Round2
     * Group B
     * operation echo2DStringArray
     */
    public void testR2GBEcho2DStringArray() throws AxisFault {
        url = "http://mssoapinterop.org/stk/interopB.wsdl";
        soapAction = "http://soapinterop.org/";

        util = new GroupbEcho2DStringArrayUtil();
        retEnv = SunRound2Client.sendMsg(util, url, soapAction);
        tempPath = resFilePath + "MStk2Groupb2DStringArrayRes.xml";
        results = compare(retEnv, tempPath);
        assertTrue(results);
    }

    /**
     * Round2
     * Group B typed
     * operation echoNestedStruct
     */
    public void testR2GBEchoNestedStruct() throws AxisFault {
        url = "http://mssoapinterop.org/stk/interopB.wsdl";
        soapAction = "http://soapinterop.org/";

        util = new GroupbEchoNestedStructUtil();
        retEnv = SunRound2Client.sendMsg(util, url, soapAction);
        tempPath = resFilePath + "MStk2GroupbNestedStructRes.xml";
        results = compare(retEnv, tempPath);
        assertTrue(results);
    }

    /**
     * Round2
     * Group B
     * operation echoNestedArray
     */
    public void testR2GBEchoNestedArray() throws AxisFault {
        url = "http://mssoapinterop.org/stk/interopB.wsdl";
        soapAction = "http://soapinterop.org/";

        util = new GroupbEchoNestedArrayUtil();
        retEnv = SunRound2Client.sendMsg(util, url, soapAction);
        tempPath = resFilePath + "MStk2GroupbNestedArrayRes.xml";
        results = compare(retEnv, tempPath);
        assertTrue(results);
    }

    /**
         * Round2
         * Group B Typed
         * operation echoStructAsSimpleTypes
         */
        public void testR2GBTypedEchoStructAsSimpleTypes() throws AxisFault {
            url = "http://mssoapinterop.org/stk/interopBTyped.wsdl";
            soapAction = "http://soapinterop.org/";

            util = new GroupbEchoStructAsSimpleTypesUtil();
            retEnv = SunRound2Client.sendMsg(util, url, soapAction);
            tempPath = resFilePath + "MStk2GroupbTypedStructAsSimpleTypesRes.xml";
            results = compare(retEnv, tempPath);
            assertTrue(results);
        }

        /**
         * Round2
         * Group B Typed
         * operation echoSimpleTypesAsStruct
         */
        public void testR2GBTypedEchoSimpleTypesAsStruct() throws AxisFault {
            url = "http://mssoapinterop.org/stk/interopBTyped.wsdl";
            soapAction = "http://soapinterop.org/";

            util = new GroupbEchoSimpleTypesAsStructUtil();
            retEnv = SunRound2Client.sendMsg(util, url, soapAction);
            tempPath = resFilePath + "MStk2GroupbTypedSimpletypesAsStructRes.xml";
            results = compare(retEnv, tempPath);
            assertTrue(results);
        }

        /**
         * Round2
         * Group B Typed
         * operation echo2DStringArray
         */
        public void testR2GBTypedEcho2DStringArray() throws AxisFault {
            url = "http://mssoapinterop.org/stk/interopBTyped.wsdl";
            soapAction = "http://soapinterop.org/";

            util = new GroupbEcho2DStringArrayUtil();
            retEnv = SunRound2Client.sendMsg(util, url, soapAction);
            tempPath = resFilePath + "MStk2GroupbTyped2DStringArrayRes.xml";
            results = compare(retEnv, tempPath);
            assertTrue(results);
        }

        /**
         * Round2
         * Group B Typed
         * operation echoNestedStruct
         */
        public void testR2GBTypedEchoNestedStruct() throws AxisFault {
            url = "http://mssoapinterop.org/stk/interopBTyped.wsdl";
            soapAction = "http://soapinterop.org/";

            util = new GroupbEchoNestedStructUtil();
            retEnv = SunRound2Client.sendMsg(util, url, soapAction);
            tempPath = resFilePath + "MStk2GroupbTypedNestedStructRes.xml";
            results = compare(retEnv, tempPath);
            assertTrue(results);
        }

        /**
         * Round2
         * Group B Typed
         * operation echoNestedArray
         */
        public void testR2GBTypedEchoNestedArray() throws AxisFault {
            url = "http://mssoapinterop.org/stk/interopBTyped.wsdl";
            soapAction = "http://soapinterop.org/";

            util = new GroupbEchoNestedArrayUtil();
            retEnv = SunRound2Client.sendMsg(util, url, soapAction);
            tempPath = resFilePath + "MStk2GroupTypedbNestedArrayRes.xml";
            results = compare(retEnv, tempPath);
            assertTrue(results);
        }


    /**
     * Round2
     * Group C
     * operation echoVoid
     */
    public void testR2GCEchoVoid() throws AxisFault {
        url = "http://mssoapinterop.org/stk/InteropC.wsdl";
        soapAction = "http://soapinterop.org/";

        util = new GroupcVoidUtil();
        retEnv = SunRound2Client.sendMsg(util, url, soapAction);
        tempPath = resFilePath + "MStk2GroupcVoidRes.xml";
        results = compare(retEnv, tempPath);
        assertTrue(results);
    }

//    private static boolean compare(SOAPEnvelope retEnv, String filePath) throws AxisFault {
//
//        boolean ok = false;
//        try {
//            if (retEnv != null) {
//                SOAPBody body = retEnv.getBody();
//                if (!body.hasFault()) {
//                    //OMElement firstChild = (OMElement) body.getFirstElement();
//
//                    InputStream stream = MStk2Round2InteropTest.class.getClassLoader().getResourceAsStream(filePath);
//
//                    XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(stream);
//                    OMXMLParserWrapper builder = new StAXSOAPModelBuilder(parser, null);
//                    SOAPEnvelope refEnv = (SOAPEnvelope) builder.getDocumentElement();
//                    //OMElement refNode = (OMElement) resEnv.getBody().getFirstElement();
//                    XMLComparator comparator = new XMLComparator();
//                    ok = comparator.compare(retEnv, refEnv);
//                } else
//                    return false;
//            } else
//                return false;
//
//        } catch (Exception e) {
//            throw new AxisFault(e); //To change body of catch statement use File | Settings | File Templates.
//        }
//        return ok;
//    }
}

