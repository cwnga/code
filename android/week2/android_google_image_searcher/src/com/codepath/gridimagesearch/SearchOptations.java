package com.codepath.gridimagesearch;

import java.io.Serializable;
import android.net.Uri;

public class SearchOptations implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String imageSize = "";//
	protected String imageColor = "";// imgcolor
	protected String imageType = "";// imgsz
	protected String query = "";
	protected int startNum = 0;
	protected String asSiteSearch = "";// as_sitesearch;

	public void addCountStartNum() {
		startNum++;
	}

	/**
	 * @param query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @param asSiteSearch
	 */
	public void setAsSiteSearch(String asSiteSearch) {
		this.asSiteSearch = asSiteSearch;
	}

	/**
	 * @param imageSize
	 */
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	/**
	 * @param imageColor
	 */
	public void setImageColor(String imageColor) {
		this.imageColor = imageColor;
	}

	/**
	 * @param imageType
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	/**
	 * @param query
	 * @param startNum
	 * @return String
	 */
	public String getImageApiUrl() {
		String apiUrl = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
				+ "start="
				+ startNum
				+ "&v=1.0&q="
				+ Uri.encode(query)
				+ getAppendParameter("imgcolor", imageColor)
				+ getAppendParameter("imgsz", imageSize)
				+ getAppendParameter("imgtype", imageType)
				+ getAppendParameter("as_sitesearch", asSiteSearch);

		return apiUrl;
	}

	/**
	 * @param parameterName
	 * @param parameterValue
	 * @return String
	 */
	protected String getAppendParameter(String parameterName,
			String parameterValue) {
		String appendParameterString;
		appendParameterString = "";
		if (parameterValue.length() > 0) {
			appendParameterString = "&" + parameterName + "="
					+ Uri.encode(parameterValue);
		}
		return appendParameterString;
	}

}
