package com.example.savushkin.domain;

import android.util.Pair;

import java.util.ArrayList;

public class XMLObject {
    private String tag;
    private String value;
    private ArrayList<Pair<String, String>> attrs;
    private ArrayList<XMLObject> descendants;
    private XMLObject parent;

    public XMLObject(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public ArrayList<XMLObject> getDescendants() {
        return descendants;
    }

    public void addAttribute(String tag, String value) {
        if (attrs == null)
            attrs = new ArrayList<Pair<String, String>>();
        attrs.add(new Pair<>(tag, value));
    }

    public ArrayList<Pair<String, String>> getAttributes() {
        return attrs;
    }

    public void add(XMLObject object) {
        if (object != null) {
            if (descendants == null)
                descendants = new ArrayList<>();
            object.setParent(this);
            descendants.add(object);
        }
    }

    public XMLObject add(String tag) {
        if (tag != null) {
            if (descendants == null)
                descendants = new ArrayList<>();
            XMLObject object = new XMLObject(tag);
            object.setParent(this);
            descendants.add(object);
            return object;
        } else return null;
    }

    public void setParent(XMLObject parent) {
        this.parent = parent;
    }

    public XMLObject getParent() {
        return parent;
    }

    public ArrayList<XMLObject> getAll() {
        return descendants;
    }

    public XMLObject get(String tagName) {
        if (getAll() != null)
            for (XMLObject object : getAll()) {
                if (object.tag != null && object.tag.equals(tagName))
                    return object;
            }
        return null;
    }

    public XMLObject find(String tag) {
        if (this.tag.equals(tag))
            return this;
        if (getAll() != null) {
            for (XMLObject object : getAll()) {
                if (object.tag.equals(tag))
                    return object;
                else if (object.getAll() != null) {
                    XMLObject object1 = object.find(tag);
                    if (object1 != null)
                        return object1;
                }
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (value == null && getAll() == null) {
            builder.append("<");
            builder.append(tag);
            builder.append("/>");
            return builder.toString();
        }
        builder.append("<");
        builder.append(tag);
        builder.append(">");
        if (getAll() != null) {
            for (XMLObject obj : getAll())
                builder.append(obj.toString());
        } else {
            builder.append(value);
        }
        builder.append("</");
        builder.append(tag);
        builder.append(">");
        return builder.toString();
    }
}