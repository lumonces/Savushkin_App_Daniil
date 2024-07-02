package com.example.savushkin.domain;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {
    public static XMLObject parse(InputStream xml) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document dom = null;
        try {
            if (dBuilder != null)
                dom = dBuilder.parse(new InputSource(xml));
        } catch (SAXException | IOException e) {

            e.printStackTrace();
        }
        if (dom != null)
            return parse(dom.getDocumentElement());
        else return new XMLObject("MAIN");
    }

    private static XMLObject parse(Node root) {
        XMLObject xmlObject = null;
        if (root != null) {
            xmlObject = new XMLObject(root.getNodeName());
            if (root.getAttributes() != null && root.getAttributes().getLength() > 0)
                for (int i = 0; i < root.getAttributes().getLength(); i++) {
                    xmlObject.addAttribute(root.getAttributes().item(i).getNodeName(), root.getAttributes().item(i).getNodeValue());
                    //Log.e("addAttribute",""+root.getAttributes().item(i).getNodeName()+" = "+ root.getAttributes().item(i).getNodeValue());
                }
            if (root.getChildNodes() != null && root.getChildNodes().getLength() > 0)
                for (int i = 0; i < root.getChildNodes().getLength(); i++)
                    if (root.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE)
                        xmlObject.add(parse(root.getChildNodes().item(i)));
            if (xmlObject.getAll() == null) {
                if (root.hasChildNodes())
                    xmlObject.setValue(root.getTextContent());
                else xmlObject.setValue(root.getNodeValue());
            }
        }
        return xmlObject;
    }
}