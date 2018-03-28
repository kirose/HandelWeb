package mx.handel.cfg.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import mx.handel.cfg.Paths;
import mx.handel.utils.UtilImage;
import mx.handel.utils.UtilServlet;
/**
 * Servlet creado para descargar archivos
 * Para evitar carga al sevidor usando Web Service
 * @author Marco Antonio
 *
 */
public class FileManager extends HttpServlet {

	private static Logger logger = Logger.getLogger("FileManager");
	private static final int MAX_HEIGHT = 2000;
	private static final int MAX_WIDTH = 2000;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private File pathFiles = new File(Paths.FILES);
	private String url;
	private String fileName;
	private String ext;
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			url = request.getRequestURI();
			fileName = url.substring(url.lastIndexOf('/')+1,url.lastIndexOf('.'));
			ext = url.indexOf('.') > 0 ? url.substring(url.lastIndexOf('.')+1) : null;
			if (ext == null){
				return;
			}
			this.request = request;
			this.response = response;
			response.setContentType(UtilServlet.getHeaderContentTypeByExtension(ext));
			if (UtilServlet.isImage(ext)){
				doImage();
			}
		} catch(Exception e){
			logger.error("FILE SERVLET", e);
		}
	}

	private void doImage() throws ServletException, IOException {
		File img = makeImage();
		writeFile(img);
	}
	private void writeFile(File file) throws IOException{
		FileInputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();

		byte[] buffer = new byte[1024];
		int bytesRead = -1;

		while ((bytesRead = in.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
		}

		in.close();
		out.close();
	}
	private File makeImage() throws IOException{
		File img = new File(pathFiles.getAbsolutePath()+"/img/"+fileName + "." + ext);
		if (!img.exists()){	
			fileName = "handel";
			ext = "jpg";
			img = new File(pathFiles.getAbsolutePath()+"/img/"+fileName + "." + ext);
		}
		Integer height = request.getParameter("height") == null ? 75 : Integer.valueOf(request.getParameter("height"));
		Integer width = request.getParameter("width") == null ? 75 : Integer.valueOf(request.getParameter("width"));
		height = Math.min(MAX_HEIGHT, height);
		width = Math.min(MAX_WIDTH, width);
		File imgResized = new File(pathFiles.getAbsolutePath()+"/img/" + fileName + "_" + height + "x" + width + "." + ext);
		if (imgResized.exists()){
			return imgResized;
		}
		UtilImage.resizeImage(img, imgResized, ext, height, width, UtilImage.FIT_BOTH);

		return imgResized;
	}
}
