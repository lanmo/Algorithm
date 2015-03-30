package com.yn.push;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: Push   
 * @Description: 推送
 */
public class Push {
	public static void main(String[] args) throws JSONException {
		String pushToken = "56e95e4eaddece3325cf3b36bff6d4650de957dd60a13d7c69e95a279702a34b";
		PushNotificationPayload payload = new PushNotificationPayload();
		payload.setExpiry(10);
		payload.addBadge(1);
		payload.addAlert("我是服务器啊");
		payload.addSound("default");
		
		try {
			Device device = new BasicDevice(pushToken);
			PushNotificationManager manager = new PushNotificationManager();
			
			String classpath = Push.class.getClassLoader().getResource("").toURI().getPath();
			
			String path = FileUtils.getFile(classpath + "/rsa/Apple.test.push.p12").getAbsolutePath();
			//sandbox
			AppleNotificationServerBasicImpl config = new AppleNotificationServerBasicImpl(path, "123456", false);
			manager.initializeConnection(config);
			manager.sendNotification(device, payload);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
