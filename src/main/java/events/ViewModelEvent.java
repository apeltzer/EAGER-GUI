/*
 * Copyright (c) 2016. eager-gui Alexander Peltzer
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package events;

public class ViewModelEvent {

	public static final int MODULE_SELECTION_CHANGED = 0;
	public static final int RESET_GUI = 1;
	public static final int STATUS = 2;
	public static final int GUI_STARTED = 3;
	
	private int change;
	private Object source;
	private String status;
	
	public ViewModelEvent(Object source, int change, String status) {
		this.source = source;
		this.change = change;
		this.status  =status;
	}
	
	public int getChange() {
		return this.change;
	}
	
	public Object getSource() {
		return this.source;
	}
	
	public String getStatus() {
		return this.status;
	}
}
