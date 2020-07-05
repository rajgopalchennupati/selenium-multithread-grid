package extentreports;

import com.aventstack.extentreports.markuputils.Markup;

public class CreateLink implements Markup {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filepath;
	private String linkName;
	
	public CreateLink(String filepath, String linkName) {
		this.filepath = filepath;
		this.linkName = linkName;
	}
	
	

	public String getMarkup() {
		final String htmlTag = "<a href='"+filepath+"' target='_new'style = 'color:blue;'><u>"+linkName+"</u></a>";
		return htmlTag;
	}

}
