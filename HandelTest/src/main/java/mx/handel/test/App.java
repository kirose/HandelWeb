package mx.handel.test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import mx.handel.cfg.ws.pojo.WSResponse;
import mx.handel.utils.UtilImage;
import mx.handel.utils.UtilReflection;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final int FIT_HEIGHT = 1;
	public static final int FIT_WIFTH = 2;
	public static final int FIT_BOTH = 3;

	public static void main( String[] args ) throws IOException
    {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        System.out.println("CamelCaseToSomethingElse".replaceAll(regex, replacement).toLowerCase());
		//CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "SomeInput");
		//testTask();
		//testJackson();
    	//testREsizeImage();
    	//testMoney();
        //test1();
    }

	private static void testTask() {
		String jsonResponse = "{\"data\": {\"idSesion\": 6}, \"status\": \"OK\", \"javaTasks\": [{\"data\": {\"to\": \"marcoasb99@ciencias.unam.mx\", \"from\": \"masdark99@gmail.com\", \"message\": \"Se ha iniciado sesion en el dispositivo BAC-L03\", \"subject\": \"Inicio de sesión sospechozo\"}, \"name\": \"email\", \"className\": \"mx.handel.task.TaskEmail\", \"background\": true}]}";
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		WSResponse r = gson.fromJson(jsonResponse, WSResponse.class);
		 
		System.out.println(r.getJavaTasks());
		System.out.println(r.toString());
	}

	private static void testJackson() throws JsonProcessingException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("algo", 1);
		String json = new ObjectMapper().writeValueAsString(map);
		System.out.println(json);
	}

	private static void testREsizeImage() throws IOException {
		/*UtilImage.resizeImage(
			new File("/opt/bitnami/files/img/handel.jpg"),
			new File("/opt/bitnami/files/img/handel_100x100.jpg"),
			"jpg", 100, 100, UtilImage.FIT_BOTH);*/
		resizeImage(
			new File("/opt/bitnami/files/img/contabilidad.jpg"),
			new File("/opt/bitnami/files/img/contabilidad_100x100.jpg"),
			"jpg", 100, 100, UtilImage.FIT_BOTH);
	}

	private static void testMoney() {
    	NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        DecimalFormat formayMoney = new DecimalFormat("0.##");
        System.out.println(formayMoney.format(123.2D));
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(format.format(112323.4D));		
	}

	private static void test1() throws IOException {
		JsonObject o = new JsonObject();
    	o.addProperty("message", "Autentificado");
    	o.addProperty("status", 1);
    	o.addProperty("auth_token", "el valor del token");
    	System.out.println(o.toString());
    	OutputStreamWriter out = new OutputStreamWriter(System.out);
		JsonWriter writer = new JsonWriter(out);
		//set indentation for pretty print
		writer.setIndent("\t");
		//start writing
		writer.beginObject(); //{
		writer.name("id").value(1); // "id": 123
		writer.name("name").value("marco"); // "name": "David"
		writer.name("permanent").value(true); // "permanent": false
		writer.endObject(); // }
		writer.flush();
		//close writer
		writer.close();
		
    	System.out.println(String.format("Hola %s me llamo %s, naci %s", "Martin","Sofia", new Date()));
        Set<Class<?>> s = new HashSet<Class<?>>();
        
		try {
			List<Class<?>> classes = UtilReflection.getClasses("mx.handel.test.package_test");
			if (classes != null){
				s.addAll(classes);
				for (Class<?> c : classes) {
					System.out.println("WS cargando controladores: " + c);
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error cargando clases de WebServic" + e);
		} catch (IOException e) {
			System.out.println("Error cargando clases de WebServic" + e);
		}
		
	}
    public static void resizeImage(File img, File imgResized, String ext, int height, int width, int typeFit) throws IOException{
    	if (width <= 0){
    		throw new RuntimeException("El ancho especificado para redimensionalizar la imagen es invalido: "+width);
    	}
    	if (height <= 0){
    		throw new RuntimeException("El alto especificado para redimensionalizar la imagen es invalido: "+height);
    	}
    	if (!img.exists() && !img.isFile()){
    		throw new IOException("No existe la imagen que intenta redimensionar: "+img.getAbsolutePath()); 
    	}
    	BufferedImage buferedImg = ImageIO.read(img);
    	int type = buferedImg.getType() == 0? BufferedImage.TYPE_INT_ARGB : buferedImg.getType();//BufferedImage.TYPE_INT_ARGB;//
    	int x = 0, y = 0, w = 0, h = 0;
    	int propWidth = buferedImg.getWidth() * height / buferedImg.getHeight();
    	int propHeight = buferedImg.getHeight() * width / buferedImg.getWidth();
    	if (typeFit == FIT_BOTH){
    		typeFit = Double.valueOf(buferedImg.getWidth()) / Double.valueOf(buferedImg.getHeight()) >= Double.valueOf(width) / Double.valueOf(height) ? FIT_WIFTH : FIT_HEIGHT; 
    	}
    	if (typeFit == FIT_HEIGHT){
    		y = 0;
    		x = width > propWidth ? (width - propWidth)/2 : 0;
    		h = height;
    		w = Math.min(width,propWidth);
    	} else if (typeFit == FIT_WIFTH){
    		y = height > propHeight ? (height - propHeight)/2 : 0;
    		x = 0;
    		h = Math.min(height,propHeight);
    		w = width;
    	}
		BufferedImage bufferedImgResized = new BufferedImage(width, height, type);
		Graphics2D g = bufferedImgResized.createGraphics();
		
		//g.fillRect(x, y, propWidth, propHeight);
		//g.setColor(c);
		//g.setRenderingHints(hints);
		//g.fillRect(0, 0, width, height);
		//g.setComposite(AlphaComposite.Clear);
		//g.setColor(new Color(0, 0, 0, 0));
		//g.setComposite(AlphaComposite.Clear);
		//g.setComposite(AlphaComposite.Src);
		
		g.fillRect(0, 0, width, height);
		g.drawImage(buferedImg, x, y, w, h, null);
		g.setComposite(AlphaComposite.Clear);
		g.dispose();
		ImageIO.write(bufferedImgResized,ext,imgResized);
    }
    
}
