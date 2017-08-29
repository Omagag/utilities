package com.bbva.intranet.utilities;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

public abstract class FileUploadUtility implements Serializable {

	private static final long serialVersionUID = 3646340819540452835L;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

//    @SuppressWarnings("unchecked")
    public static File getFile(HttpServletRequest request) throws Exception {
        File file = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        String fileName;
        String filePath;

        // process only if its multipart content
        if (isMultipart) {
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
            List<FileItem> multiparts = upload.parseRequest(request);

            for (FileItem item : multiparts) {
                if (!item.isFormField()) {
                    file = new File(item.getName());
                    fileName = file.getName();
                    filePath = file.getPath();

                    logger.info(String.format("File Name: %s ", fileName));
                    logger.info(String.format("File Type: %s ", fileName.substring(fileName.lastIndexOf('.')+1)));
                    logger.info(String.format("File Path: %s ", filePath));

                    item.write(new File(filePath));
                }
            }
        }

        return file;
    }

	public static String downloadCandidateFile(HttpServletRequest request) throws IOException {
		FileOutputStream outputStream = null;
		BufferedInputStream bIStream = null;
		String filePath = File.separator;
		String fileName = "";
		File file = null;

		try {
			if(file.exists()) {
				outputStream = new FileOutputStream(file);

				final byte data[] = new byte[1024];
		        int count;

		        while ((count = bIStream.read(data, 0, 1024)) != -1) {
		        	outputStream.write(data, 0, count);
		        }
			} else {
				filePath = "No existe archivo asociado al candidato...";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (bIStream != null) {
	        	bIStream.close();
	        }

	        if(outputStream != null) {
	        	outputStream.close();
	        }
	    }

		return filePath;
	}

	public static boolean validateFileUploaded (String pathFile) {
		boolean isExists = false;
		File file;
		
		file = new File(pathFile);
		
		if(file.exists()) {
			isExists = true;
		} else {
			isExists = false;
		}
		
		return isExists;
	}
	
	/*Google Cloud Storage */
	
	public static final int BUFFER_SIZE = 2 * 1024 * 1024;
	
	public static int copyNC(InputStream input, OutputStream output) throws IOException{
	     byte[] buffer = new byte[BUFFER_SIZE];
	     int count = 0;
	     int n = 0;
	     while (-1 != (n = input.read(buffer))) {
	         output.write(buffer, 0, n);
	         count += n;
	     }
	     return count;
	}
   
   /**
	 * Transfer the data from the inputStream to the outputStream. Then close both streams.
	 */
	public static void copy(InputStream input, OutputStream output/*, byte[] imageBytes*/) throws IOException {
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = input.read(buffer);
			while (bytesRead != -1) {
				output.write(buffer, 0, bytesRead);
				bytesRead = input.read(buffer);
			}
	    } finally {
	    	input.close();
	    	output.close();
	    }
	}

}