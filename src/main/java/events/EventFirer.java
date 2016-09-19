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

import java.util.Collections;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;

/** A helper class to make event firing simpler to implement. Events are sent to the listeners in no defined order.
 * Each listener will only be notified once, regardless of how often it is added.
 * @author jaeger
 *
 * @param <TE> the Event Object Type
 * @param <TL> the Event Listener Type
 */
public abstract class EventFirer<TE, TL extends EventListener> {

	private Set<TL> eventListeners;
	
	protected abstract void dispatchEvent(TE event, TL listener);

	public synchronized void addListener(TL listener) {
		if (eventListeners==null)
			eventListeners = new HashSet<TL>();
		eventListeners.add(listener);
	}
	
	public synchronized void removeListener(TL listener) {
		if (eventListeners==null)
			return;
		eventListeners.remove(listener);
		if (eventListeners.size()==0)
			eventListeners = null;
	}
	
	@SuppressWarnings("unchecked")
	protected void fireEvent_immediate(TE event) {
		for ( Object olistener : eventListeners.toArray()) {
			TL listener = (TL)olistener;
			try {
				dispatchEvent(event,listener);
			} catch (Exception e) {
				System.out.println("Event dispatching resulted in a "+e);
				e.printStackTrace();
			}
		}
	}

	public synchronized void fireEvent(TE event) {
		if (eventListeners==null || eventListeners.size()==0 || event==null)
			return;
		
		fireEvent_immediate(event);
	}
	
	public synchronized Set<TL> getListeners() {
		if (eventListeners!=null) {
			return Collections.unmodifiableSet(new HashSet<TL>(eventListeners));
		}
		return Collections.emptySet();
	}
}
