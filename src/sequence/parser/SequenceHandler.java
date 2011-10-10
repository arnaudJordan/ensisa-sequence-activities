package sequence.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import sequence.model.Action;
import sequence.model.Activity;
import sequence.model.AnatomicStructure;
import sequence.model.Instrument;
import sequence.model.Note;
import sequence.model.Actuator;
import sequence.model.Patient;
import sequence.model.Phase;
import sequence.model.Phases;
import sequence.model.Sequence;
import sequence.model.Sex;


public class SequenceHandler extends DefaultHandler {
	private Sequence sequence;
	private boolean inSequence, inActivity, inPatient, inState, inActivityTime, inActuator, inActions, inAction, inUsedInstruments, inTreatedStructure, inNote, inStartTime, inStopTime, inDuration, inPosition, inInstrument, inAnatomicStructure;
	private StringBuffer buffer;

	public SequenceHandler(){
		super();
	}
	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException{
		buffer = new StringBuffer();
		if(qName.equals("rec_workflow")){
			sequence = new Sequence();
			inSequence = true;
		}
		if(startElementInSequence(qName, attributes))
			return;
	}
	private boolean startElementInSequence(String qName, Attributes attributes) throws SAXException
	{
		if(!inSequence)
			return false;
		if(qName.equals("patient"))
		{
			sequence.setPatient(new Patient());
			inPatient=true;
		}
		if(startElementInPatient(qName))
		{
			return true;
		}
		if(qName.equals("activity")){
			try{
				int id = Integer.parseInt(attributes.getValue("id"));
				String state = attributes.getValue("state");
				int discipline = Integer.parseInt(attributes.getValue("discipline"));
				int type = Integer.parseInt(attributes.getValue("type"));
				sequence.addActivity(new Activity(id, state, discipline, type));
			}catch(Exception e){
				throw new SAXException(e);
			}
			inActivity=true;
		}
		if(startElementInActivity(qName))
			return true;
		if(qName.equals("state"))
		{
			sequence.setPhases(new Phases());
			inState=true;
		}
		if(startElementInState(qName, attributes))
			return true;
		return false;
	}
	private boolean startElementInState(String qName, Attributes attributes) throws SAXException {
		if(!inState)
			return false;
		if(qName.equals("value"))
		{
			try{
				int time = Integer.parseInt(attributes.getValue("time"));
				sequence.getPhases().add(new Phase(time));
			}catch(Exception e){
				throw new SAXException(e);
			}
			return true;
		}
			
		return false;
	}
	private boolean startElementInPatient(String qName) {
		if(!inPatient)
			return false;
		if(qName.equals("age"))
		{
			return true;
		}
		if(qName.equals("sex"))
		{
			return true;
		}
		if(qName.equals("position"))
		{
			return true;
		}
		if(qName.equals("note"))
		{
			return true;
		}
		return false;
	}
	private boolean startElementInActivity(String qName)
	{
		if(!inActivity)
			return false;
		if(qName.equals("activitytime"))
		{
			inActivityTime = true;
			return true;
		}
		if(qName.equals("actuator"))
		{
			inActuator = true;
			return true;
		}
		if(qName.equals("action"))
		{
			inActions = true;
			return true;
		}
		if(qName.equals("usedInstruments"))
		{
			inUsedInstruments = true;
			return true;
		}
		if(qName.equals("treatedStructure"))
		{
			inTreatedStructure = true;
			return true;
		}
		if(qName.equals("note"))
		{
			inNote = true;
			return true;
		}
		if(startElementInActivityTime(qName))
			return true;

		else if(inActuator)
		{
			if(qName.equals("position")){
				inPosition = true;
				return true;
			}
			return true;
		}else if(inUsedInstruments)
		{
			if(qName.equals("instrument")){
				inInstrument = true;
				return true;
			}
		}else if(inTreatedStructure)
		{
			if(qName.equals("anatomicStructure")){
				inAnatomicStructure = true;
				return true;
			}
		}
		return false;
	}

	private boolean startElementInActivityTime(String qName)
	{
		if(!inActivityTime)
			return false;
		if(qName.equals("startime")){
			inStartTime = true;
			return true;
		}else if(qName.equals("stoptime")){
			inStopTime = true;
			return true;
		}else if(qName.equals("duration")){
			inDuration = true;
			return true;
		}else if(qName.equals("position")){
			inPosition = true;
			return true;
		}else if(qName.equals("instrument")){
			inInstrument = true;
			return true;
		}
		return false;
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException{
		if(qName.equals("rec_workflow")){
			inSequence = false;
		}
		if(endElementInSequence(qName))
			return;     
	}

	private boolean endElementInSequence(String qName) {
		if(!inSequence)
			return false;
		if(qName.equals("patient")){
			buffer = null;
			inPatient = false;
			return true;
		}
		if(endElementInPatient(qName))
			return true;
		if(qName.equals("activity")){
			buffer = null;
			inActivity = false;
			return true;
		}
		if(endElementInActivity(qName))
			return true;
		if(qName.equals("state")){
			buffer = null;
			inState = false;
			return true;
		}
		if(endElementInState(qName))
			return true;
		return false;
	}
	private boolean endElementInState(String qName) {
		if(!inState)
			return false;
		if(qName.equals("value"))
		{
			sequence.getPhases().getLastPhase().setName(buffer.toString());
			buffer=null;
		}
		return false;
	}
	private boolean endElementInActivity(String qName) {
		if(!inActivity)
			return false;

		if(qName.equals("activitytime")){
			buffer = null;
			inActivityTime = false;
			return true;
		}
		if(qName.equals("actuator")){
			buffer = null;
			inActuator = false;
			return true;
		}
		if(qName.equals("action")){
			if(inAction)
			{
				inAction=false;
				sequence.getLastActivity().setAction(new Action(buffer.toString()));
			}
			else
				inActions = false;
			buffer = null;
			return true;
		}
		if(qName.equals("usedInstruments")){
			buffer = null;
			inUsedInstruments = false;
			return true;
		}
		if(qName.equals("treatedStructure")){
			buffer = null;
			inTreatedStructure = false;
			return true;
		}
		if(qName.equals("note")){
			sequence.getLastActivity().setNote(new Note(buffer.toString()));
			buffer = null;
			inNote = false;
			return true;
		}
		if(endElementInActivityTime(qName))
			return true;
		if(inActuator&&qName.equals("position")){
			sequence.getLastActivity().setActuator(new Actuator(buffer.toString()));
			buffer=null;
			inPosition = false;
			return true;
		}
		if(inUsedInstruments&&qName.equals("instrument")){
			sequence.getLastActivity().getUsedInstrument().addInstrument(new Instrument(buffer.toString()));
			buffer=null;
			inInstrument = false;
			return true;
		}
		if(inTreatedStructure&&qName.equals("anatomicStructure")){
			sequence.getLastActivity().setTreatedStructure(new AnatomicStructure(buffer.toString()));
			buffer=null;
			inAnatomicStructure = false;
			return true;
		}
		return false;
	}
	private boolean endElementInActivityTime(String qName) {
		if(!inActivityTime)
			return false;
		if(qName.equals("starttime"))
		{
			int startTime = Integer.parseInt(buffer.toString());
			sequence.getLastActivity().getActivitytime().setStartTime(startTime);
			buffer=null;
			inStartTime = false;
			return true;
		}
		if(qName.equals("stoptime")){
			int stopTime = Integer.parseInt(buffer.toString());
			sequence.getLastActivity().getActivitytime().setStopTime(stopTime);
			buffer=null;
			inStopTime = false;
			return true;
		}
		if(qName.equals("duration")){
			int duration = Integer.parseInt(buffer.toString());
			sequence.getLastActivity().getActivitytime().setDuration(duration);
			buffer=null;
			inDuration = false;
			return true;
		}
		return false;
	}
	private boolean endElementInPatient(String qName) {
		if(!inPatient)
			return false;
		if(qName.equals("sex"))
		{
			if(buffer.toString().equals("M"))
				sequence.getPatient().setSex(Sex.male);
			else
				sequence.getPatient().setSex(Sex.female);
			buffer=null;
			return true;
		}
		if(qName.equals("age"))
		{
			int age = Integer.parseInt(buffer.toString());
			sequence.getPatient().setAge(age);
			buffer=null;
			return true;
		}
		if(qName.equals("position"))
		{
			sequence.getPatient().setActuator(new Actuator(buffer.toString()));
			buffer=null;
			return true;
		}
		if(qName.equals("note"))
		{
			sequence.getPatient().setNote(new Note(buffer.toString()));
			buffer=null;
			return true;
		}
		return false;
	}
	public void characters(char[] ch,int start, int length)
			throws SAXException{
		String lecture = new String(ch,start,length);
		if(buffer != null) buffer.append(lecture);       
	}
	public void startDocument() throws SAXException {
		System.out.println("Start parsing");
	}
	public void endDocument() throws SAXException {
		System.out.println("End of parsing");
	}
	public Sequence getSequence() {
		return sequence;
	}
}
