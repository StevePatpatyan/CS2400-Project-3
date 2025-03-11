package QueuePackage;

import java.util.*;

public class SimulationEventQueue implements SimulationEventQueueInterface {
    private double currentTime;
    private Vector<SimulationEvent> queue;

    public SimulationEventQueue() {
        currentTime = 0.0;
        queue = new Vector<SimulationEvent>();
    } // end constructor

	/** Adds a new event to this event queue.  If the time of the event to be added
	 * is earlier the the time for this event queue, do not add the event.
	* @param newEntry An event. 
        */
        public void add(SimulationEvent newEntry) {
            if (newEntry.getTime() < currentTime) {
                return;
            }
            else {
                if (isEmpty()) {
                    queue.add(newEntry);
                }
                else {
                    int addIndex = -1;
                    boolean foundLarger = false;
                    Iterator<SimulationEvent> iter = queue.iterator();
                    while (!foundLarger && iter.hasNext()) {
                        SimulationEvent currentEvent = iter.next();
                        if (currentEvent.getTime() > newEntry.getTime()) {
                            foundLarger = true;
                        }
                        addIndex++;
                    }
                    if (foundLarger) {
                        queue.add(addIndex, newEntry);
                    }
                    else {
                        queue.add(newEntry);
                    }

                }
            }
            return;
        } // end add
	
        /** Removes and returns the item with the earliest time.
         * @return The event with the earliest time or,
         * if the event queue was empty before the operation, null. 
             */
        public SimulationEvent remove() {
            if (isEmpty()) {
                return null;
            }
            else {
                SimulationEvent removed = queue.remove(0);
                currentTime = removed.getTime();
                return removed;
            }
        } // end remove
        
        /** Retrieves the item with the earliest time.
         * @return The event with the earliest time or, 
         * if the event queue was empty was empty before the operation, null. 
             */
        public SimulationEvent peek() {
            if (isEmpty()) {
                return null;
            }
            else {
                return queue.get(0);
            }
        } // end peek
        
        /** Detects whether this event queue is empty.
         * @return True if the event queue is empty. 
             */
        public boolean isEmpty() {
            return queue.size() == 0;
        } // end isEmpty
    
        /** Gets the size of this event queue.
         * @return The number of entries currently in the event queue. 
             */
        public int getSize() {
            return queue.size();
        } // end getSize
        
        /** Removes all entries from this event queue. 
             */
        public void clear() {
            queue.clear();
        } // end clear
        
        
        /**
         * The current time of the simulation
         * 
         * @return The time for the first event on the queue.
         */
        public double getCurrentTime() {
            return currentTime;
        } // end getCurrentTime
}
