package mx.handel.cfg.ws.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import mx.handel.cfg.pojo.Task;
import mx.handel.utils.UtilFormat;
/**
 * 
 * @author Marco Antonio Salazar
 *
 */
public final class WSResponse implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public static String OK = "S";
	public static String ERROR_VALIDACION = "EV";
	public static String INVALID_PARAMETERS = "EINPA";
	public static String NO_DATA_FOUND = "ENDAT";
	public static String ERROR_EXIRED_TOKEN = "ETKEX";
	public static String SERVICE_NOT_FOUND = "ENSER";
	public static String UNAUTHORIZED = "ENPER";
	public static String ERROR_INTERNAL = "EI";
	public static String ERROR_SESSION_EXPIRED = "ESEEX";

	@Expose	private String message;
	@Expose(serialize = false, deserialize = true)
	private String messageLog;
	@Expose	private String status;
	@Expose	private String codeLog;
	@Expose	private Integer size;
	@Expose	private Integer maxSize;
	@Expose	private Object data;
	@Expose(serialize = false, deserialize = true)
	private List<Task> javaTasks;
	public WSResponse() {
		super();
	}
	public String makeUniqueCodeLog(){
		return this.codeLog = UtilFormat.date("yyyyMMddhhmmssSSS", new Date());
	}
	public String toString(){
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String toString = gson.toJson(this);
		return toString;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageLog() {
		return messageLog;
	}
	public void setMessageLog(String messageLog) {
		this.messageLog = messageLog;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getCodeLog() {
		return codeLog;
	}
	public void setCodeLog(String codeLog) {
		this.codeLog = codeLog;
	}
	public List<Task> getJavaTasks() {
		return javaTasks;
	}
	public void setJavaTasks(List<Task> javaTasks) {
		this.javaTasks = javaTasks;
	}

}
