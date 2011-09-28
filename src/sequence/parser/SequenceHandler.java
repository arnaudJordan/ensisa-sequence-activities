package sequence.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import sequence.model.Action;
import sequence.model.Activity;
import sequence.model.AnatomicStructure;
import sequence.model.Instrument;
import sequence.model.Note;
import sequence.model.Position;
import sequence.model.Sequence;


public class SequenceHandler extends DefaultHandler {
		private Sequence sequence;
		private boolean inSequence, inActivity, inActivityTime, inActuator, inActions, inAction, inUsedInstruments, inTreatedStructure, inNote, inStartTime, inStopTime, inDuration, inPosition, inInstrument, inAnatomicStructure;
		private StringBuffer buffer;

		public SequenceHandler(){
			super();
		}
		public void startElement(String uri, String localName,
				String qName, Attributes attributes) throws SAXException{
			if(qName.equals("rec_workflow")){
				sequence = new Sequence();
				inSequence = true;
			}else if(qName.equals("activity")&&inSequence){
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
			}else {
				if(inActivity)
				{
					buffer = new StringBuffer();
					if(qName.equals("activitytime")){
						inActivityTime = true;
					}else if(qName.equals("actuator")){
						inActuator = true;
					}else if(qName.equals("action")){
						inActions = true;
						if(inActions)
						{
							inAction=true;
						}
					}else if(qName.equals("usedInstruments")){
						inUsedInstruments = true;
					}else if(qName.equals("treatedStructure")){;
						inTreatedStructure = true;
					}else if(qName.equals("note")){
						inNote = true;
					}else if(inActivityTime)
					{
						if(qName.equals("startime")){
							inStartTime = true;
						}else if(qName.equals("stoptime")){
							inStopTime = true;
						}else if(qName.equals("duration")){
							inDuration = true;
						}else if(qName.equals("position")){
							inPosition = true;
						}else if(qName.equals("instrument")){
							inInstrument = true;
						}
					}else if(inActuator)
					{
						if(qName.equals("position")){
							inPosition = true;
						}
					}else if(inUsedInstruments)
					{
						if(qName.equals("instrument")){
							inInstrument = true;
						}
					}else if(inTreatedStructure)
					{
						if(qName.equals("anatomicStructure")){
							inAnatomicStructure = true;
						}
					}
				}
				else{
//					throw new SAXException("Unknow markup : "+qName);
				}
			}
		}
		public void endElement(String uri, String localName, String qName)
				throws SAXException{
			if(qName.equals("rec_workflow")){
				inSequence = false;
			}else if(qName.equals("activity")){
				buffer = null;
				inActivity = false;
			}else if(inActivity)
			{
				if(qName.equals("activitytime")){
					buffer = null;
					inActivityTime = false;
				}else if(qName.equals("actuator")){
					buffer = null;
					inActuator = false;
				}else if(qName.equals("action")){
					if(inAction)
					{
						inAction=false;
						sequence.getLastActivity().getActions().addAction(new Action(buffer.toString()));
					}
					else
						inActions = false;
					buffer = null;
				}else if(qName.equals("usedInstruments")){
					buffer = null;
					inUsedInstruments = false;
				}else if(qName.equals("treatedStructure")){
					buffer = null;
					inTreatedStructure = false;
				}else if(qName.equals("note")){
					sequence.getLastActivity().setNote(new Note(buffer.toString()));
					buffer = null;
					inNote = false;
				}else if(inActivityTime)
					{
					if(qName.equals("starttime")){
						int startTime = Integer.parseInt(buffer.toString());
						sequence.getLastActivity().getActivitytime().setStartTime(startTime);
						buffer=null;
						inStartTime = false;
					}else if(qName.equals("stoptime")){
						int stopTime = Integer.parseInt(buffer.toString());
						sequence.getLastActivity().getActivitytime().setStopTime(stopTime);
						buffer=null;
						inStopTime = false;
					}else if(qName.equals("duration")){
						int duration = Integer.parseInt(buffer.toString());
						sequence.getLastActivity().getActivitytime().setDuration(duration);
						buffer=null;
						inDuration = false;
					}
				}else if(inActuator&&qName.equals("position")){
					sequence.getLastActivity().getActuator().addPosition(new Position(buffer.toString()));
					buffer=null;
					inPosition = false;
				}else if(inUsedInstruments&&qName.equals("instrument")){
					sequence.getLastActivity().getUsedInstrument().addInstrument(new Instrument(buffer.toString()));
					buffer=null;
					inInstrument = false;
				}else if(inTreatedStructure&&qName.equals("anatomicStructure")){
					sequence.getLastActivity().getTreatedStructure().addAnatomicStructure(new AnatomicStructure(buffer.toString()));
					buffer=null;
					inAnatomicStructure = false;
				}
			}else{
				//throw new SAXException("Unknow markup : "+qName);
			}          
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
