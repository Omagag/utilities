package com.bbva.intranet.utilities.firestore;

import com.bbva.intranet.utilities.firestore.exceptions.MultipleDocumentsException;
import com.bbva.intranet.utilities.firestore.vos.FsDocument;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;

public abstract class FirestoreUtility {

    private final static Logger logger = LoggerFactory.getLogger(FirestoreUtility.class);

    private static Firestore db = FirestoreOptions.getDefaultInstance().getService();
    // TODO: It's possible that we required init the db parameter with static clause...

    public static String add(String collectionId, String documentID, Object data) throws ExecutionException, InterruptedException {
        logger.info(String.format("Utility add with documentId -> %s - %s", collectionId, data));
        CollectionReference collectionReference = db.collection(collectionId);

        if (documentID != null && !documentID.isEmpty()) {
            List<ApiFuture<WriteResult>> futures = new ArrayList<ApiFuture<WriteResult>>();
            futures.add(collectionReference.document(documentID).set(data));
        } else {
            ApiFuture<DocumentReference> addedDocRef = collectionReference.add(data);
            documentID = addedDocRef.get().getId();
            logger.info(String.format("Document added with automatic ID: %s", documentID));
        }
        return documentID;
    }

    public static String  add(String collectionId, Object data) throws ExecutionException, InterruptedException {
        String documentId = add(collectionId, null, data);
        return documentId;
    }

    public static FsDocument findUniqueByMultipleFilter(String collectionId, Map<String, Object> fields) throws ExecutionException, InterruptedException, MultipleDocumentsException {
        FsDocument fsDocument = null;
        List<FsDocument> fsDocuments = findByMultipleFilter(collectionId, fields);
        if (fsDocuments.size() > 1) {
            throw new MultipleDocumentsException(MultipleDocumentsException.MESSAGE);
        } else {
            if (fsDocuments.size() == 1) {
                fsDocument = fsDocuments.get(0);
            }
        }
        return fsDocument;
    }

    public static List<FsDocument> findByMultipleFilter(String collectionId, Map<String, Object> fields) throws ExecutionException, InterruptedException {
        logger.info(String.format("Utility findByMultipleFilter -> %s - %s", collectionId, fields));
        List<FsDocument> documents;
        Query query = db.collection(collectionId);
        Iterator it = fields.entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            query = query.whereEqualTo(e.getKey().toString(),e.getValue());
        } //Ya que se puede agregar independiente se puede switchear / Determinar la opción entrante

        try {
            logger.info("Recovering documents");
            ApiFuture<QuerySnapshot> future = query.get();
            documents = converterFsDocumentList(future.get().getDocuments());
        }catch (ExecutionException e) {
            throw new ExecutionException(e);
        }catch (InterruptedException ie) {
            throw new InterruptedException(ie.getMessage());
        }
        return documents;
    }

    public static FsDocument getDocument(String collectionId, String documentId) throws ExecutionException, InterruptedException {
        logger.info(String.format("Utility getDocument -> %s - %s", collectionId, documentId));
        FsDocument fsDocument = new FsDocument();
        try {
            logger.info("Recovering documents");
            DocumentReference documentReference = db.collection(collectionId).document(documentId);
            ApiFuture<DocumentSnapshot> document = documentReference.get();

            fsDocument.setDocumentID(document.get().getId());
            fsDocument.setData(document.get().getData());
        }catch (ExecutionException e) {
            throw new ExecutionException(e);
        }catch (InterruptedException ie) {
            throw new InterruptedException(ie.getMessage());
        }
        return fsDocument;
    }

    public static List<FsDocument> findAll(String collectionId) throws ExecutionException, InterruptedException {
        logger.info(String.format("Utility findAll -> %s - %s", collectionId));
        List<FsDocument> documents;
        ApiFuture<QuerySnapshot> future = db.collection(collectionId).get();
        documents = converterFsDocumentList(future.get().getDocuments());
        return documents;
    }

    public static void update(String collectionId, String documentId, Object data) {
        logger.info(String.format("Utility update -> %s - %s", collectionId, data));
        CollectionReference collectionReference = db.collection(collectionId);
        ApiFuture<WriteResult> writeResult = collectionReference.document(documentId)
                .set(data, SetOptions.merge());
        //logger.info("Update time : " + writeResult.get().getUpdateTime());
    }

    public static void updateSpecificField(String collectionId, String documentId, String field, String fieldValue) throws ExecutionException, InterruptedException {
        logger.info(String.format("Utility update -> %s - %s\n       %s - %s", collectionId, documentId,field,fieldValue));
        DocumentReference documentReference = db.collection(collectionId).document(documentId);
        ApiFuture<WriteResult> future = documentReference.update(field, fieldValue);
        future.get();
    }

    //TODO: Se podria realizar update de un campo anidado

    public static void deleteDocument(String collectionId, String documentId) throws ExecutionException, InterruptedException {
        logger.info(String.format("Utility deleteDocument -> %s - %s", collectionId, documentId));
        ApiFuture<WriteResult> writeResult = db.collection(collectionId)
                .document(documentId)
                .delete();
        writeResult.get();
    }

    public static Boolean isExist(String collectionId, String documentId) throws ExecutionException, InterruptedException {
        logger.info(String.format("Utility isExist -> %s - %s", collectionId, documentId));
        DocumentSnapshot document;
        DocumentReference docRef = db.collection(collectionId).document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            document = future.get();
        }catch (ExecutionException e)
        {
            throw new ExecutionException(e);
        }catch (InterruptedException ie)
        {
            throw new InterruptedException(ie.getMessage());
        }
        return document.exists();
    }

    //Support method for the conversion of output object
    public static List<FsDocument> converterFsDocumentList(List<QueryDocumentSnapshot> fsDocuments) {
        logger.info(String.format("Utility converterFsDocumentList tu fsDocument"));
        List<FsDocument> documentsResult = new ArrayList<FsDocument>();
        FsDocument fsDocument;
        for (DocumentSnapshot document : fsDocuments) {
            fsDocument = new FsDocument();
            fsDocument.setDocumentID(document.getId());
            fsDocument.setData(document.getData());
            documentsResult.add(fsDocument);
        }
        return documentsResult;
    }
}
