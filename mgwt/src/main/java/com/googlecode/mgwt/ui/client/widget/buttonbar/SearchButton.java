/*
 * Copyright 2011 Daniel Kurka
 * 
 * Licensed under thimport com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.SearchButtonCss;
obtain a copy of
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
package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.SearchButtonCss;

/**
 * <p>SearchButton class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class SearchButton extends ButtonBarButtonBase {

	/**
	 * <p>Constructor for SearchButton.</p>
	 */
	public SearchButton() {
		this(MGWTStyle.getDefaultClientBundle().getSearchButtonCss());
	}

	/**
	 * <p>Constructor for SearchButton.</p>
	 *
	 * @param searchButtonCss a {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.SearchButtonCss} object.
	 */
	public SearchButton(SearchButtonCss searchButtonCss) {
		super(searchButtonCss);
		addStyleName(searchButtonCss.search());
	}

}
