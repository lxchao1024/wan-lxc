/*
 * 版权 2008-2017，浙江齐聚科技有限公司 </br>
 * 所有版权保护
 * 这是浙江齐聚科技有限公司 未公开的私有源代码, 本文件及相关内容未经浙江齐聚科技有限公司
 * 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
 */
package com.guagua.guagua.gradle363.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * 打印日志到LogCat输出及写到日志文件
 */
public class LogUtils {
	private static final String TAG = "LogUtils";

	//是否输出日志的开关
	public static boolean ISDEBUG =  true;

	public static void i(String TAG, String msg) {
		if (ISDEBUG) {
			Log.i(TAG, msg);
			FileLogger.getInstance().addLog(TAG, msg);
		}
	}

	public static void i(String TAG, String msg, Throwable e) {
		if (ISDEBUG) {
			Log.i(TAG, msg, e);
			FileLogger.getInstance().addLog(TAG, msg, e);
		}
	}

	public static void e(String TAG, String msg) {
		if (ISDEBUG) {
			Log.e(TAG, msg);
			FileLogger.getInstance().addLog(TAG, msg);
		}
	}

	public static void e(String TAG, String msg, Throwable e) {
		if (ISDEBUG) {
			Log.e(TAG, msg, e);
			FileLogger.getInstance().addLog(TAG, msg, e);
		}
	}

	public static void d(String TAG, String msg) {
		if (ISDEBUG) {
			Log.i(TAG, msg);
		FileLogger.getInstance().addLog(TAG, msg);
		}
	}

	/**
	 * 重载一个带日志开关的DEBUG打印方法，这样方便控制模块日志的开关
	 * @param isOpen
	 * @param TAG
     * @param msg
     */
	public static void d(Boolean isOpen, String TAG, String msg) {
		if (ISDEBUG && isOpen) {
			Log.d(TAG, msg);
			FileLogger.getInstance().addLog(TAG, msg);
		}
	}

	public static void d(String TAG, String msg, Throwable e) {
		if (ISDEBUG) {
			Log.d(TAG, msg, e);
			FileLogger.getInstance().addLog(TAG, msg, e);
		}
	}

	public static void v(String TAG, String msg) {
		if (ISDEBUG) {
			Log.v(TAG, msg);
			FileLogger.getInstance().addLog(TAG, msg);
		}
	}

	public static void v(String TAG, String msg, Throwable e) {
		if (ISDEBUG) {
			Log.v(TAG, msg, e);
			FileLogger.getInstance().addLog(TAG, msg, e);
		}
	}

	public static void w(String TAG, String msg) {
		if (ISDEBUG) {
			Log.w(TAG, msg);
			FileLogger.getInstance().addLog(TAG, msg);
		}
	}

	public static void w(String TAG, String msg, Throwable e) {
		if (ISDEBUG) {
			Log.w(TAG, msg, e);
			FileLogger.getInstance().addLog(TAG, msg, e);
		}
	}

	public static void println() {
		if (ISDEBUG) {
			System.out.println();
			FileLogger.getInstance().addLog("", "");
		}
	}

	public static void println(Object msg) {
		if (ISDEBUG) {
			System.out.println(msg);
			FileLogger.getInstance().addLog("System.out", msg.toString());
		}
	}

	public static void print(Object msg) {
		if (ISDEBUG) {
			System.out.print(msg);
			FileLogger.getInstance().addLog("System.out", msg.toString());
		}
	}

	public static void printStackTrace(Throwable e) {
		if (ISDEBUG) {
			e.printStackTrace();
			FileLogger.getInstance().addLog("System.out", e);
		}
	}

	public static void stopFileLogger() {
		FileLogger.getInstance().stop();
	}

	/**
	 * 设置日志的存放路径
	 * @param fileLogPath
	 */
	public static void setFileLogPath(String fileLogPath) {
		FileLogger.getInstance().setLogPath(fileLogPath);
	}

	private static class FileLogger implements Runnable {
		private static FileLogger inst = new FileLogger();
		private static final int INTERVAL = 200;
		private static final int MAX_FAIL_COUNT = 30000 / INTERVAL;

		private String logPath;//日志存放的路径

		private ArrayList<String> logList = new ArrayList<String>();

		public static FileLogger getInstance() {
			return inst;
		}

		public void setLogPath(String logPath) {
			if (logPath != null && !logPath.endsWith(File.separator)) {
				this.logPath = logPath + File.separator;
			} else {
				this.logPath = logPath;
			}
		}

		private boolean isSDCardAvailable() {
			return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		}

		public void addLog(String TAG, String message) {
			if (!isSDCardAvailable() || logPath == null) {
				return;
			}
			synchronized (this) {
				logList.add("[" + getTime() + "][" + TAG + "]  " + message);


				notifyWrite();
			}
		}

		public void addLog(String TAG, Throwable e) {
			if (!isSDCardAvailable() || logPath == null) {
				return;
			}
			synchronized (this) {
				addLog(TAG, e.toString());
				StackTraceElement[] elements = e.getStackTrace();
				for (int i = 0; i < elements.length; i++) {
					StackTraceElement element = elements[i];
					logList.add("    " + element.toString());
				}
				Throwable cause = e.getCause();
				if (cause != null) {
					logList.add("    Caused by: " + cause.toString());
					elements = cause.getStackTrace();
					for (int i = 0; i < elements.length; i++) {
						StackTraceElement element = elements[i];
						logList.add("    " + element.toString());
					}
				}
				notifyWrite();
			}
		}

		public void addLog(String TAG, String message, Throwable e) {
			if (!isSDCardAvailable() || logPath == null) {
				return;
			}
			synchronized (this) {
				addLog(TAG, message);
				addLog(TAG, e);
			}
		}

		private String getLog() {
			synchronized (this) {
				if (logList.size() > 0) {
					return logList.remove(0);
				}
				return null;
			}
		}

		private Thread thread;
		private boolean isRunning;

		private void notifyWrite() {
			if (!isRunning && isSDCardAvailable()) {
				isRunning = true;
				thread = new Thread(this);
				thread.start();
			}
		}

		private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault());
		private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

		public static String getTime() {
			return dateTimeFormat.format(new Date());
		}

		public String getLogFileName() {
			return dateFormat.format(new Date()) + ".txt";
		}

		public void stop() {
			isRunning = false;
		}

		@Override
		public void run() {

			FileOutputStream fos = null;
			BufferedWriter writer = null;
			int count = 0;

			while (true) {
				String log = getLog();
				if (!isRunning) {
					break;
				}
				if (log == null) {
					if (count > MAX_FAIL_COUNT) {
						break;
					}
					else {
						++count;
						try {
							Thread.sleep(INTERVAL);
						}
						catch (InterruptedException e) {

						}
						continue;
					}
				}

				if (!isSDCardAvailable() || logPath == null) {
					break;
				}
				try {
//					String logDir = logPath;
//					FileUtils.createDirs(new File(logDir));
//					File file = new File(logDir + getLogFileName());
//
//					if (!file.exists()) {
//						LogUtils.i(TAG, "Create new log file, path : " + file.getPath());
//						file.createNewFile();
//					}
//
//					if (!file.exists())
//						return;
//
//					fos = new FileOutputStream(file, true);
//					writer = new BufferedWriter(new OutputStreamWriter(fos));
//					//写文件
//					writer.write(log);
//					writer.newLine();
//
//					Thread.sleep(10);
				}
				catch (Throwable e) {
					e.printStackTrace();
					break;
				}
				finally {
//					CloseUtils.close(writer);
//					CloseUtils.close(fos);
				}
			}

			isRunning = false;
			thread = null;
		}
	}
	public static boolean isDebugBuild() {
		boolean logDebug=false;
			try {
				final Class<?> activityThread = Class.forName("android.app.ActivityThread");
				final Method currentPackage = activityThread.getMethod("currentPackageName");
				final String packageName = (String) currentPackage.invoke(null, (Object[]) null);
				final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
				final Field DEBUG = buildConfig.getField("LOG_DEBUG");
				DEBUG.setAccessible(true);
				logDebug = DEBUG.getBoolean(null);
			} catch (final Throwable t) {
				final String message = t.getMessage();
				if (message != null && message.contains("BuildConfig")) {
					// Proguard obfuscated build. Most likely a production build.
					logDebug = false;
				} else {
//					logDebug = BuildConfig.DEBUG;
				}
			}
		return logDebug;
	}
}
