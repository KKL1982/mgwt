package com.googlecode.mgwt.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.ui.client.theme.base.UtilCss;

/**
 * <p>MGWTUtil class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class MGWTUtil {
	private static final OsDetection OS_DETECTION = GWT.create(OsDetection.class);

	private final static EventBus manager = new SimpleEventBus();

	private static ORIENTATION currentOrientation;
	private static Timer timer;

	private static boolean scrollingDisabled;

	static {
		setupOrientation();

	}

	/**
	 * <p>addOrientationChangeHandler</p>
	 *
	 * @param handler a {@link com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler} object.
	 * @return a {@link com.google.gwt.event.shared.HandlerRegistration} object.
	 */
	public static HandlerRegistration addOrientationChangeHandler(OrientationChangeHandler handler) {
		return manager.addHandler(OrientationChangeEvent.getType(), handler);
	}

	/**
	 * <p>fixIOSScrollIssueBlur</p>
	 */
	public static void fixIOSScrollIssueBlur() {
		if (!scrollingDisabled) {
			return;
		}
		if (timer != null) {
			timer.cancel();

		}

		timer = new Timer() {

			@Override
			public void run() {
				Window.scrollTo(0, 0);

			}

		};

		timer.schedule(100);
	}

	/**
	 * <p>fixIOSScrollIssueFocus</p>
	 */
	public static void fixIOSScrollIssueFocus() {
		if (!scrollingDisabled) {
			return;
		}
		if (timer != null) {
			timer.cancel();

		}
		timer = null;
	}

	/**
	 * <p>getOsDetection</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.OsDetection} object.
	 */
	public static OsDetection getOsDetection() {
		return OS_DETECTION;
	}

	/**
	 * <p>getOrientation</p>
	 *
	 * @return a {@link com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION} object.
	 */
	public static ORIENTATION getOrientation() {

		if (!orientationSupport()) {
			int height = Window.getClientHeight();
			int width = Window.getClientWidth();

			if (width > height) {
				return ORIENTATION.LANDSCAPE;
			} else {
				return ORIENTATION.PORTRAIT;
			}

		} else {
			int orientation = getOrientation0();

			ORIENTATION o;
			switch (orientation) {
			case 0:
			case 180:
				o = ORIENTATION.PORTRAIT;
				break;

			case 90:
			case -90:
				o = ORIENTATION.LANDSCAPE;
				break;

			default:
				throw new IllegalStateException("this should not happen!?");
			}

			return o;
		}

	}

	/**
	 * <p>Setter for the field <code>scrollingDisabled</code>.</p>
	 *
	 * @param disabled a boolean.
	 */
	public static void setScrollingDisabled(boolean disabled) {
		scrollingDisabled = disabled;
	}

	private static native int getOrientation0()/*-{
		if (typeof ($wnd.orientation) == 'undefined') {
			return 0;
		}

		return $wnd.orientation;
	}-*/;

	private static void onorientationChange(int orientation) {
		UtilCss utilCss = MGWTStyle.getDefaultClientBundle().getUtilCss();
		ORIENTATION o;
		switch (orientation) {
		case 0:
		case 180:
			Document.get().getBody().addClassName(utilCss.portrait());
			Document.get().getBody().removeClassName(utilCss.landscape());
			o = ORIENTATION.PORTRAIT;
			break;

		case 90:
		case -90:
			o = ORIENTATION.LANDSCAPE;
			Document.get().getBody().addClassName(utilCss.landscape());
			Document.get().getBody().removeClassName(utilCss.portrait());

			break;

		default:
			// TODO is default a good idea?
			o = ORIENTATION.PORTRAIT;
			break;
		}
		currentOrientation = o;
		manager.fireEvent(new OrientationChangeEvent(o));

	}

	private native static boolean orientationSupport()/*-{
		var ua = window.navigator.userAgent.toLowerCase();
		if (ua.indexOf('android') != -1) {
			return false;
		}
		if (ua.indexOf('iphone') != -1) {
			return true;
		}
		if (ua.indexOf('ipad') != -1) {
			return true;
		}

		return false;
	}-*/;

	private static void setupOrientation() {
		if (!orientationSupport()) {
			Window.addResizeHandler(new ResizeHandler() {

				@Override
				public void onResize(ResizeEvent event) {
					ORIENTATION orientation = getOrientation();
					if (orientation != currentOrientation) {
						currentOrientation = orientation;
						manager.fireEvent(new OrientationChangeEvent(currentOrientation));
					}
				}
			});
		} else {
			setupOrientation0();
		}

	}

	private static native void setupOrientation0()/*-{

		var func = $entry(function() {
			@com.googlecode.mgwt.ui.client.MGWTUtil::onorientationChange(I)($wnd.orientation);
		});
		$doc.body.onorientationchange = func;
		$doc.addEventListener("orientationChanged", func);
	}-*/;
}
