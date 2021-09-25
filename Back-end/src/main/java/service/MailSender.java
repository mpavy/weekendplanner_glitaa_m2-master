package service;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class MailSender {

	String email;
	String name;
	HashMap<String, List<String>> sportlocations;

	public MailSender(String email, String name, HashMap<String, List<String>> sportlocations) {
		this.email = email;
		this.name = name;
		this.sportlocations = sportlocations;
	}

	private String toHtmlTable() {
		String result = "<table><tr><th>Lieu</th><th>Sport</th></tr>\n";
		for(String loc : sportlocations.keySet()) {
			result += prettyPrintList(loc,sportlocations.get(loc));
		}
		result += "</table>";

		return result;
	}

	private String prettyPrintList(String loc, List<String> list) {
		String result = "";
		for(String sport : list) {
			result += "  <tr><td>"+loc+"</td>"+"<td>"+sport+"</td></tr>\n";
		}
		return result;
	}

	public void sendMail() throws MailjetException, MailjetSocketTimeoutException {
		MailjetClient client;
		MailjetRequest request;
		client = new MailjetClient("f9b00217a31e9786ebf5df4dc2392550","8bb612efd21719f6137fcd4a031699a0", new ClientOptions("v3.1"));
		request = new MailjetRequest(Emailv31.resource)
				.property(Emailv31.MESSAGES, new JSONArray()
						.put(new JSONObject()
								.put(Emailv31.Message.FROM, new JSONObject()
										.put("Email", "kevin.chertier@gmail.com")
										.put("Name", "TaaGli"))
								.put(Emailv31.Message.TO, new JSONArray()
										.put(new JSONObject()
												.put("Email", email)
												.put("Name", name)))
								.put(Emailv31.Message.SUBJECT, "Votre sélection sortie du Weekend")
								.put(Emailv31.Message.HTMLPART, "<style>\n" + 
										"table {\n" + 
										"  font-family: arial, sans-serif;\n" + 
										"  border-collapse: collapse;\n" + 
										"  width: 100%;\n" + 
										"}\n" + 
										"\n" + 
										"td, th {\n" + 
										"  border: 1px solid #dddddd;\n" + 
										"  text-align: left;\n" + 
										"  padding: 8px;\n" + 
										"}\n" + 
										"\n" + 
										"tr:nth-child(even) {\n" + 
										"  background-color: #dddddd;\n" + 
										"}\n" + 
										"</style><h3>Cher "+name+",</h3><br /> Voici ce que notre algorithme vous propose de faire ce week-end : "
										+toHtmlTable())
								.put(Emailv31.Message.CUSTOMID, "taagli")));
		client.post(request);
	}
}
