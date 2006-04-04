package org.apache.axis2.databinding.utils.reader;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamReader;
import javax.xml.namespace.QName;
import javax.xml.namespace.NamespaceContext;
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

public class WrappingXMLStreamReader implements ADBXMLStreamReader{

    private XMLStreamReader reader;
    private int depth = 0;
    private boolean initState = true;

    public WrappingXMLStreamReader(XMLStreamReader reader) {
        this.reader = reader;
    }

    public boolean isDone() {
        /////////////////////////////////
//        try {
//            System.out.println("1 -----------" + this.reader.hasNext());
//            System.out.println("2  -----------" +(depth==0 && !initState));
//        } catch (XMLStreamException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        ////////////////////////////////
//        try {
//            boolean b = hasNext();
//            System.out.println("######" + b + "#####");
//            System.out.println("----------" + (depth==0 && !initState && !b) + " -------");
//            return (depth==0 && !initState && !b);
//        } catch (XMLStreamException e) {
//            throw new RuntimeException(e);
//        }
        try {
            return !hasNext();
        } catch (XMLStreamException e) {
             throw new RuntimeException(e);
        }
    }

    public Object getProperty(String string) throws IllegalArgumentException {
        return reader.getProperty(string);
    }

    public int next() throws XMLStreamException {
        int event = reader.next();
        initState = false;
        if (event==START_ELEMENT || event==START_DOCUMENT){
            depth++;
        }else if (event==END_ELEMENT || event==END_DOCUMENT){
            depth = depth!=0?depth--:depth;
        }
        return event;
    }

    public void require(int i, String string, String string1) throws XMLStreamException {
        //nothing to do
    }

    public String getElementText() throws XMLStreamException {
       return reader.getElementText();
    }

    public int nextTag() throws XMLStreamException {
        return reader.nextTag();
    }

    public boolean hasNext() throws XMLStreamException {
        return reader.hasNext();
    }

    public void close() throws XMLStreamException {
         reader.close();
    }

    public String getNamespaceURI(String string) {
        return reader.getNamespaceURI(string);
    }

    public boolean isStartElement() {
         return reader.isStartElement();
    }

    public boolean isEndElement() {
        return reader.isEndElement();
    }

    public boolean isCharacters() {
       return reader.isCharacters();
    }

    public boolean isWhiteSpace() {
        return reader.isWhiteSpace();
    }

    public String getAttributeValue(String string, String string1) {
        return reader.getAttributeValue(string,string1);
    }

    public int getAttributeCount() {
        return reader.getAttributeCount();
    }

    public QName getAttributeName(int i) {
        return reader.getAttributeName(i);
    }

    public String getAttributeNamespace(int i) {
       return reader.getAttributeNamespace(i);
    }

    public String getAttributeLocalName(int i) {
        return reader.getAttributeLocalName(i);
    }

    public String getAttributePrefix(int i) {
       return reader.getAttributeLocalName(i);
    }

    public String getAttributeType(int i) {
        return reader.getAttributeType(i);
    }

    public String getAttributeValue(int i) {
        return reader.getAttributeValue(i);
    }

    public boolean isAttributeSpecified(int i) {
        return reader.isAttributeSpecified(i);
    }

    public int getNamespaceCount() {
        return reader.getNamespaceCount();
    }

    public String getNamespacePrefix(int i) {
          return reader.getNamespacePrefix(i);
    }

    public String getNamespaceURI(int i) {
          return reader.getNamespaceURI(i);
    }

    public NamespaceContext getNamespaceContext() {
        return reader.getNamespaceContext();
    }

    public int getEventType() {
       return reader.getEventType();
    }

    public String getText() {
        return reader.getText();
    }

    public char[] getTextCharacters() {
       return reader.getTextCharacters();
    }

    public int getTextCharacters(int i, char[] chars, int i1, int i2) throws XMLStreamException {
        return reader.getTextCharacters(i,chars,i1,i2);
    }

    public int getTextStart() {
        return reader.getTextStart();
    }

    public int getTextLength() {
        return reader.getTextLength();
    }

    public String getEncoding() {
        return reader.getEncoding();
    }

    public boolean hasText() {
        return reader.hasText();
    }

    public Location getLocation() {
        return reader.getLocation();
    }

    public QName getName() {
         return reader.getName();
    }

    public String getLocalName() {
        return reader.getLocalName();
    }

    public boolean hasName() {
         return reader.hasName();
    }

    public String getNamespaceURI() {
         return reader.getNamespaceURI();
    }

    public String getPrefix() {
         return reader.getPrefix();
    }

    public String getVersion() {
        return reader.getVersion();
    }

    public boolean isStandalone() {
        return reader.isStandalone();
    }

    public boolean standaloneSet() {
       return reader.standaloneSet();
    }

    public String getCharacterEncodingScheme() {
        return reader.getCharacterEncodingScheme();
    }

    public String getPITarget() {
         return reader.getPITarget();
    }

    public String getPIData() {
        return reader.getPIData();
    }

    public void addNamespaceContext(NamespaceContext nsContext) {
        //nothing to do here
    }

    public void init() {
        //Nothing to do here
    }
}
