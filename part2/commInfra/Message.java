package commInfra;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 *  Serialization key.
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Type of the message.
	 */

	private MessageType type;

	/**
	 * ID of the passenger.
	 */

	private int passengerID;

	/**
	 * String to print on repos.
	 */

	private String toPrint;

	/**
	 * Total passangers.
	 */

	private int totalPassengers;

	/**
	 * Passengers on plane.
	 */

	private int passengersOnPlane;

	/**
	 * Passengers on plane.
	 */

	private boolean allPassengersLeft;

	/**
	 * Total passengers that have arrived.
	 */

	private int passengNum;

	/**
	 * True if plane is ready for boarding.
	 */

	private boolean isReadyForBoarding;

	/**
	 * State of the passenger.
	 */

	private int passengerState;

	/**
	 * State of the hostess.
	 */

	private int hostessState;

	/**
	 * State of the pilot.
	 */

	private int pilotState;

	/**
	 * True if it is the last flight.
	 */

	private boolean lastFlight;

	/**
	 *  Message instantiation (form 1).
	 *
	 *     @param type message type
	 */

	public Message(MessageType type){
		this.type = type;
	}

	/**
	 *  Message instantiation (form 2).
	 *
	 *     @param type message type
	 *     @param value passenger id / hostess state / barber state /
	 *     total passengers / passengers on plane
	 */

	public Message(MessageType type, int value) {
		this.type =type;

		switch (type) {
		case WAIT_FOR_NEXT_PASSENGER: 
			passengerID = value;
			break;
		case SET_HOSTESS_STATE:
			this.hostessState = value;
			break;
		case SET_PILOT_STATE:
			this.pilotState = value;
			break;
		case WAIT_IN_QUEUE:
			this.passengerID = value;
			break;
		case CHECK_DOCUMENTS:
			this.passengerID = value;
			break;
		case SHOW_DOCUMENTS:
			this.passengerID = value;
			break;
		case BOARD_THE_PLANE:
			this.passengerID = value;
			break;
		case WAIT_FOR_END_OF_FLIGHT:
			this.passengerID = value;
			break;
		case LEAVE_THE_PLANE:
			this.passengerID = value;
			break;
		case GET_TOTAL:
			this.totalPassengers = value;
			break;
		case UPDATE_FLIGHT_INFO:
			this.passengersOnPlane = value;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	/**
	 *  Message instantiation (form 3).
	 *
	 *     @param type message type
	 *     @param value isReadyForBoarding / lastFlight
	 */

	public Message(MessageType type, boolean value) {
		this.type =type;

		switch (type) {
		case RETURN_ALL_PASSENG_LEFT: 
			isReadyForBoarding = value;
			break;
		case PARK_AT_TRANSFER_GATE: 
			lastFlight = value;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	/**
	 *  Message instantiation (form 4).
	 *
	 *     @param type message type
	 *     @param value toPrint
	 */

	public Message(MessageType type, String value) {
		this.type =type;

		switch (type) {
		case PRINT: 
			toPrint = value;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	/**
	 *  Message instantiation (form 5).
	 *
	 *     @param type message type
	 *     @param id passenger id
	 *     @param state passenger state
	 */

	public Message(MessageType type, int id, int state){
		this.type = type;
		switch(type){
		case SET_PASSENGER_STATE:
			this.passengerID = id;
			this.passengerState = state;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	/**
	 * Get the total passengers that have arrived
	 * @return passeng num
	 */

	public int getPassengNum(){
		return passengNum;
	}

	/**
	 * Get the total passengers on plane
	 * @return passengers on plane
	 */

	public int getPassengersOnPlane(){
		return passengersOnPlane;
	}

	/**
	 * Checks if the plane is ready for boarding
	 * @return true if it's ready for boarding
	 */

	public boolean isReadyForBoarding(){
		return isReadyForBoarding;
	}

	/**
	 * Checks if the plane is ready for boarding
	 * @return true if it's ready for boarding
	 */

	public int waitForNextPassenger(){
		return passengerID;
	}

	/**
	 * Checks if the plane is ready for boarding
	 * @return true if it's ready for boarding
	 */

	public boolean allPassengLeft(){
		return allPassengersLeft;
	}

	/**
	 * Get the type of the message
	 * @return message type
	 */

	public MessageType getType(){
		return this.type;
	}

	/**
	 * Get the id of the passenger.
	 * @return passenger id
	 */

	public int getPassengerID(){
		return passengerID;
	}

	/**
	 * Get the state of the passenger.
	 * @return passenger state
	 */

	public int getPassengerState(){
		return passengerState;
	}

	/**
	 * Get the state of the hostess.
	 * @return hostess state
	 */

	public int getHostessState(){
		return hostessState;
	}

	/**
	 * Get the state of the pilot.
	 * @return pilot state
	 */

	public int getPilotState(){
		return pilotState;
	}

	/**
	 * Get the string to print.
	 * @return toPrint 
	 */

	public String toPrint(){
		return toPrint;
	}

	/**
	 * Get the state of the pilot.
	 * @return pilot state
	 */

	public int getTotal(){
		return totalPassengers;
	}

	/**
	 * Checks if it is the last flight.
	 * @return true if it is the last flight
	 */

	public boolean lastFlight() {
		return lastFlight;
	}
}
