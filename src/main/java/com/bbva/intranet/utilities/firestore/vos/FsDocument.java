package com.bbva.intranet.utilities.firestore.vos;

import java.util.Map;

public class FsDocument {

    private String documentID;
    private Map<String, Object> data;

    public FsDocument() {}

    public FsDocument(String documentID, Map<String, Object> data) {
        this.documentID = documentID;
        this.data = data;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getDocumentID() {
        return documentID;
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "\n Data{" +
                ", documentID='" + documentID + '\'' +
                ", data=" + data +
                '}';
    }
}
