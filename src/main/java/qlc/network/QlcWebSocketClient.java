package qlc.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import qlc.utils.Constants;

public class QlcWebSocketClient extends WebSocketListener {

    private static OkHttpClient client;
    
    private static WebSocket socket;
    
    protected static String result;
    
	private Message message;

    public synchronized void startRequest(String url) {
    	
        if (client == null) {
        	client = new OkHttpClient();
        }
        if (socket == null) {
            Request request = new Request.Builder().url(url).build();
            socket = client.newWebSocket(request, this);
        }
    }

    private void sendMessage(WebSocket webSocket, String params) {
        webSocket.send(params);
    }

    public void sendMessage(String params) {
        WebSocket webSocket;
        synchronized (QlcWebSocketClient.class) {
            webSocket = socket;
        }
        if (webSocket != null) {
            sendMessage(webSocket, params);
        }
    }

    public synchronized void closeWebSocket() {
        if (socket != null) {
            socket.close(Constants.NORMAL_CLOSURE_STATUS, "Goodbye!");
            socket = null;
        }
    }

    public synchronized void destroy() {
        if (client != null) {
        	client.dispatcher().executorService().shutdown();
            client = null;
        }
    }
    
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	// Override
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        if (message != null)
        	message.onOpen(webSocket, response);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        result = text;
        if (message != null)
        	message.onMessage(webSocket, text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        if (message != null)
        	message.onMessage(webSocket, bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(Constants.NORMAL_CLOSURE_STATUS, null);
        if (message != null)
        	message.onClosing(webSocket, code, reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        if (message != null)
        	message.onClosed(webSocket, code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
        if (message != null)
        	message.onFailure(webSocket, t, response);
    }


}
