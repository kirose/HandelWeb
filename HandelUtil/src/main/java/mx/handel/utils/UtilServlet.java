package mx.handel.utils;

public class UtilServlet {
	public static String getHeaderContentTypeByExtension(String ext){
		if (ext == null){
			return "application/octet-stream";
		}
		ext = ext.toLowerCase();
		switch (ext){
			case "txt":
				return "text/html";
			case "csv":
				return "text/csv";
			case "pdf":
				return "application/pdf";
			case "xlsx":
				return "application/octet-stream";
			default:
				return "application/octet-stream";
		}
	}
	public static boolean isImage(String ext){
		return ext != null &&(
			"jpg".equals(ext)
			|| "jpeg".equals(ext)
			|| "png".equals(ext)
			|| "gif".equals(ext)
		);
	}
}
