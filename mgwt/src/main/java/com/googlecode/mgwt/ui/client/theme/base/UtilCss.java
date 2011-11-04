/*
 * Copyright 2011 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.CssResource;

/**
 * <p>UtilCss interface.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public interface UtilCss extends CssResource {
	/**
	 * <p>landscapeonly</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String landscapeonly();

	/**
	 * <p>portraitonly</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String portraitonly();

	/**
	 * <p>portrait</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String portrait();

	/**
	 * <p>landscape</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String landscape();
}
