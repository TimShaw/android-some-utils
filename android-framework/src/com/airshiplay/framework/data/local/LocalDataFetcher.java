/**
 * 
 */
package com.airshiplay.framework.data.local;

import com.airshiplay.framework.data.remote.NetFetcherImp;
import com.airshiplay.framework.data.remote.NetFetcherImp.IFetchHandler;
import com.airshiplay.framework.http.JSONUtil;

/**
 * @author airshiplay
 * @Create Date 2013-2-24
 * @version 1.0
 * @since 1.0
 */
public class LocalDataFetcher implements Runnable {
	private NetFetcherImp.IFetchHandler mFetchHandler;
	private String mPath;
	private String responseContent;

	/**
	 * @param url
	 * @param iFetchHandler
	 */
	public LocalDataFetcher(String url, IFetchHandler iFetchHandler) {
	}

	public void reqeust() {
		new Thread(this).start();
	}

	private String doRequest() {
		return JSONUtil.getContentFromFile(this.mPath, null);
	}

	@Override
	public void run() {
		try {
			this.responseContent = doRequest();
			if (this.mFetchHandler != null) {
				mFetchHandler.sendCompletedMessage(responseContent);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			if (this.mFetchHandler != null)
				this.mFetchHandler.sendErrorMessage(e);
		}
	}
}
