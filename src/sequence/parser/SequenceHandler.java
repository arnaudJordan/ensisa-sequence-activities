package sequence.parser;

import java.io.File;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import sequence.model.AcademicDegree;
import sequence.model.Actor;
import sequence.model.Actuator;
import sequence.model.ClinicalDegree;
import sequence.model.Color;
import sequence.model.Date;
import sequence.model.Discipline;
import sequence.model.Name;
import sequence.model.Note;
import sequence.model.Participant;
import sequence.model.Patient;
import sequence.model.Phase;
import sequence.model.Phases;
import sequence.model.Position;
import sequence.model.Sequence;
import sequence.model.activity.Action;
import sequence.model.activity.Activity;
import sequence.model.activity.AnatomicStructure;
import sequence.model.activity.BodyPart;
import sequence.model.activity.Instrument;
import sequence.model.location.City;
import sequence.model.location.Country;
import sequence.model.location.Institution;
import sequence.model.location.Location;
import sequence.model.location.Operatingtheatre;
import sequence.utilities.Sex;

/**
 * The Class SequenceHandler.
 */
public class SequenceHandler extends DefaultHandler {
	
	/** The file. */
	private File file;
	
	/** The sequence. */
	private Sequence sequence;
	
	/** The booleans used to check if we are in a specific markup. */
	private boolean inSequence, inDiscipline, inParticipant, inActivity,
			inPatient, inLocation, inDate, inState, inActivityTime, inActuator,
			inAction, inUsedInstruments, inTreatedStructure;
	
	/** The buffer. */
	private StringBuffer buffer;

	/**
	 * Instantiates a new sequence handler.
	 */
	public SequenceHandler() {
		super();
	}

	/**
	 * Instantiates a new sequence handler.
	 *
	 * @param file the file
	 */
	public SequenceHandler(final File file) {
		this.file = file;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(final String uri, final String localName,
			final String qName, final Attributes attributes)
			throws SAXException {
		buffer = new StringBuffer();
		if (qName.equals("rec_workflow")) {
			try {
				final String workflowID = attributes.getValue("workflowID");
				if (file == null)
					sequence = new Sequence(workflowID);
				else
					sequence = new Sequence(workflowID, file);
				inSequence = true;
			} catch (final Exception e) {
				throw new SAXException(e);
			}

		}
		if (startElementInSequence(qName, attributes))
			return;
	}

	/**
	 * Start element in sequence.
	 *
	 * @param qName the q name
	 * @param attributes the attributes
	 * @return true, if successful
	 * @throws SAXException the sAX exception
	 */
	private boolean startElementInSequence(final String qName,
			final Attributes attributes) throws SAXException {
		if (!inSequence)
			return false;
		if (qName.equals("discipline")) {
			sequence.setDiscipline(new Discipline());
			inDiscipline = true;
		}
		if (startElementInDiscipline(qName)) {
			return true;
		}
		if (qName.equals("patient")) {
			sequence.setPatient(new Patient());
			inPatient = true;
		}
		if (startElementInPatient(qName)) {
			return true;
		}
		if (qName.equals("rec_location")) {
			sequence.setLocation(new Location());
			inLocation = true;
		}
		if (startElementInLocation(qName)) {
			return true;
		}
		if (qName.equals("rec_date")) {
			sequence.setDate(new Date());
			inDate = true;
		}
		if (startElementInDate(qName)) {
			return true;
		}
		if (qName.equals("activity")) {
			try {
				final int id = Integer.parseInt(attributes.getValue("id"));
				final String state = attributes.getValue("state");
				final int discipline = Integer.parseInt(attributes
						.getValue("discipline"));
				final int type = Integer.parseInt(attributes.getValue("type"));
				sequence.addActivity(new Activity(id, state, discipline, type));
				inActivity = true;
			} catch (final Exception e) {
				throw new SAXException(e);
			}
		}
		if (startElementInActivity(qName))
			return true;
		if (qName.equals("state")) {
			sequence.setPhases(new Phases());
			inState = true;
		}
		if (startElementInState(qName, attributes))
			return true;
		return false;
	}

	/**
	 * Start element in discipline.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 * @throws SAXException the sAX exception
	 */
	private boolean startElementInDiscipline(final String qName)
			throws SAXException {
		if (!inDiscipline)
			return false;
		if (qName.equals("participant")) {
			sequence.getDiscipline().addParticipant(new Participant());
			inParticipant = true;
			return true;
		}
		if (startElementInParticipant(qName))
			return true;
		return false;
	}

	/**
	 * Start element in participant.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 * @throws SAXException the sAX exception
	 */
	private boolean startElementInParticipant(final String qName)
			throws SAXException {
		if (!inParticipant)
			return false;
		if (qName.equals("position"))
			return true;
		if (qName.equals("name"))
			return true;
		if (qName.equals("clinicdegree"))
			return true;
		if (qName.equals("academicdegree"))
			return true;
		if (qName.equals("actor"))
			return true;
		if (qName.equals("note"))
			return true;
		if (qName.equals("color"))
			return true;
		return false;
	}

	/**
	 * Start element in state.
	 *
	 * @param qName the q name
	 * @param attributes the attributes
	 * @return true, if successful
	 * @throws SAXException the sAX exception
	 */
	private boolean startElementInState(final String qName,
			final Attributes attributes) throws SAXException {
		if (!inState)
			return false;
		if (qName.equals("value")) {
			try {
				final int time = Integer.parseInt(attributes.getValue("time"));
				if (sequence.getPhases().getLastPhase() != null) {
					sequence.getPhases().getLastPhase().setStopTime(time);
				}
				sequence.getPhases().add(new Phase(time));

			} catch (final Exception e) {
				throw new SAXException(e);
			}
			return true;
		}

		return false;
	}

	/**
	 * Start element in patient.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean startElementInPatient(final String qName) {
		if (!inPatient)
			return false;
		if (qName.equals("age")) {
			return true;
		}
		if (qName.equals("sex")) {
			return true;
		}
		if (qName.equals("position")) {
			return true;
		}
		if (qName.equals("note")) {
			return true;
		}
		return false;
	}

	/**
	 * Start element in location.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean startElementInLocation(final String qName) {
		if (!inLocation)
			return false;
		if (qName.equals("country")) {
			return true;
		}
		if (qName.equals("Institution")) {
			return true;
		}
		if (qName.equals("operatingtheatre")) {
			return true;
		}
		if (qName.equals("note")) {
			return true;
		}
		return false;
	}

	/**
	 * Start element in date.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean startElementInDate(final String qName) {
		if (!inDate)
			return false;
		if (qName.equals("date")) {
			return true;
		}
		if (qName.equals("stoptime")) {
			return true;
		}
		if (qName.equals("starttime")) {
			return true;
		}
		if (qName.equals("duration")) {
			return true;
		}
		return false;
	}

	/**
	 * Start element in activity.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean startElementInActivity(final String qName) {
		if (!inActivity)
			return false;
		if (qName.equals("activitytime")) {
			inActivityTime = true;
			return true;
		}
		if (qName.equals("actuator")) {
			sequence.getLastActivity().setActuator(new Actuator());
			inActuator = true;
			return true;
		}
		if (qName.equals("action")) {
			inAction = true;
			return true;
		}
		if (qName.equals("usedInstruments")) {
			inUsedInstruments = true;
			return true;
		}
		if (qName.equals("treatedStructure")) {
			inTreatedStructure = true;
			return true;
		}
		if (qName.equals("note")) {
			return true;
		}
		if (startElementInActivityTime(qName))
			return true;

		else if (inActuator) {
			if (qName.equals("position")) {
				return true;
			}
			return true;
		} else if (inUsedInstruments) {
			if (qName.equals("instrument")) {
				return true;
			}
		} else if (inTreatedStructure) {
			if (qName.equals("anatomicStructure")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Start element in activity time.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean startElementInActivityTime(final String qName) {
		if (!inActivityTime)
			return false;
		if (qName.equals("startime")) {
			return true;
		} else if (qName.equals("stoptime")) {
			return true;
		} else if (qName.equals("duration")) {
			return true;
		} else if (qName.equals("position")) {
			return true;
		} else if (qName.equals("instrument")) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(final String uri, final String localName,
			final String qName) throws SAXException {
		if (qName.equals("rec_workflow")) {
			inSequence = false;
		}
		if (endElementInSequence(qName))
			return;
	}

	/**
	 * End element in sequence.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean endElementInSequence(final String qName) {
		if (!inSequence)
			return false;
		if (qName.equals("discipline")) {
			buffer = null;
			inDiscipline = false;
			return true;
		}
		if (endElementInDiscipline(qName))
			return true;
		if (qName.equals("patient")) {
			buffer = null;
			inPatient = false;
			return true;
		}
		if (endElementInPatient(qName))
			return true;
		if (qName.equals("rec_location")) {
			buffer = null;
			inLocation = false;
			return true;
		}
		if (endElementInLocation(qName))
			return true;
		if (qName.equals("rec_date")) {
			buffer = null;
			inDate = false;
			return true;
		}
		if (endElementInDate(qName))
			return true;
		if (qName.equals("activity")) {
			final Activity lastActivity = sequence.getLastActivity();
			if (lastActivity.getAction() == null
					|| lastActivity.getTreatedStructure() == null
					|| lastActivity.getUsedInstrument() == null
					|| lastActivity.getUsedInstrument().size() == 0)
				sequence.removeLastActivity();
			buffer = null;
			inActivity = false;
			return true;
		}
		if (endElementInActivity(qName))
			return true;
		if (qName.equals("state")) {
			if (sequence.getPhases().getLastPhase() != null) {
				sequence.getPhases()
						.getLastPhase()
						.setStopTime(
								sequence.getLastActivity().getActivitytime()
										.getStopTime());
			}
			buffer = null;
			inState = false;
			return true;
		}
		if (endElementInState(qName))
			return true;
		return false;
	}

	/**
	 * End element in discipline.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean endElementInDiscipline(final String qName) {
		if (!inDiscipline)
			return false;
		if (qName.equals("participant")) {
			inParticipant = false;
			buffer = null;
		}
		if (endElementInParticipant(qName))
			return true;
		return false;
	}

	/**
	 * End element in participant.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean endElementInParticipant(final String qName) {
		if (!inParticipant)
			return false;
		if (qName.equals("position")) {
			sequence.getDiscipline().getLastParticipant()
					.setPosition(new Position(buffer.toString()));
			buffer = null;
		}
		if (qName.equals("name")) {
			sequence.getDiscipline().getLastParticipant()
					.setName(new Name(buffer.toString()));
			buffer = null;
		}
		if (qName.equals("clinicaldegree")) {
			sequence.getDiscipline().getLastParticipant()
					.setClinicalDegree(new ClinicalDegree(buffer.toString()));
			buffer = null;
		}
		if (qName.equals("academicdegree")) {
			sequence.getDiscipline().getLastParticipant()
					.setAcademicDegree(new AcademicDegree(buffer.toString()));
			buffer = null;
		}
		if (qName.equals("actor")) {
			sequence.getDiscipline().getLastParticipant()
					.add(new Actor(buffer.toString()));
			buffer = null;
		}
		if (qName.equals("note")) {
			sequence.getDiscipline().getLastParticipant()
					.setNote(new Note(buffer.toString()));
			buffer = null;
		}
		if (qName.equals("color")) {
			sequence.getDiscipline().getLastParticipant()
					.setColor(new Color(Integer.parseInt(buffer.toString())));
			buffer = null;
		}
		return false;
	}

	/**
	 * End element in state.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean endElementInState(final String qName) {
		if (!inState)
			return false;
		if (qName.equals("value")) {
			sequence.getPhases().getLastPhase().setName(buffer.toString());
			buffer = null;
		}
		return false;
	}

	/**
	 * End element in activity.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean endElementInActivity(final String qName) {
		if (!inActivity)
			return false;

		if (qName.equals("activitytime")) {
			buffer = null;
			inActivityTime = false;
			return true;
		}
		if (qName.equals("actuator")) {
			buffer = null;
			inActuator = false;
			return true;
		}
		if (qName.equals("action")) {
			if (inAction) {
				inAction = false;
				sequence.getLastActivity().setAction(
						new Action(buffer.toString()));
			} else
				inAction = false;
			buffer = null;
			return true;
		}
		if (qName.equals("usedInstruments")) {
			buffer = null;
			inUsedInstruments = false;
			return true;
		}
		if (qName.equals("treatedStructure")) {
			buffer = null;
			inTreatedStructure = false;
			return true;
		}
		if (qName.equals("note")) {
			sequence.getLastActivity().setNote(new Note(buffer.toString()));
			buffer = null;
			return true;
		}
		if (endElementInActivityTime(qName))
			return true;
		if (inActuator && qName.equals("position")) {
			sequence.getLastActivity().getActuator()
					.setPosition(new Position(buffer.toString()));
			buffer = null;
			return true;
		}
		if (inActuator && qName.equals("usedbodypart")) {
			sequence.getLastActivity().getActuator()
					.setUsedbodypart(new BodyPart(buffer.toString()));
			buffer = null;
			return true;
		}
		if (inUsedInstruments && qName.equals("instrument")) {
			sequence.getLastActivity().getUsedInstrument()
					.addInstrument(new Instrument(buffer.toString()));
			buffer = null;
			return true;
		}
		if (inTreatedStructure && qName.equals("anatomicStructure")) {
			sequence.getLastActivity().setTreatedStructure(
					new AnatomicStructure(buffer.toString()));
			buffer = null;
			return true;
		}
		return false;
	}

	/**
	 * End element in activity time.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean endElementInActivityTime(final String qName) {
		if (!inActivityTime)
			return false;
		if (qName.equals("starttime")) {
			final int startTime = Integer.parseInt(buffer.toString());
			sequence.getLastActivity().getActivitytime()
					.setStartTime(startTime);
			buffer = null;
			return true;
		}
		if (qName.equals("stoptime")) {
			final int stopTime = Integer.parseInt(buffer.toString());
			sequence.getLastActivity().getActivitytime().setStopTime(stopTime);
			buffer = null;
			return true;
		}
		if (qName.equals("duration")) {
			final int duration = Integer.parseInt(buffer.toString());
			sequence.getLastActivity().getActivitytime().setDuration(duration);
			buffer = null;
			return true;
		}
		return false;
	}

	/**
	 * End element in patient.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean endElementInPatient(final String qName) {
		if (!inPatient)
			return false;
		if (qName.equals("sex")) {
			if (buffer.toString().equals("M"))
				sequence.getPatient().setSex(Sex.male);
			else
				sequence.getPatient().setSex(Sex.female);
			buffer = null;
			return true;
		}
		if (qName.equals("age")) {
			final int age = Integer.parseInt(buffer.toString());
			sequence.getPatient().setAge(age);
			buffer = null;
			return true;
		}
		if (qName.equals("position")) {
			sequence.getPatient().setPosition(new Position(buffer.toString()));
			buffer = null;
			return true;
		}
		if (qName.equals("note")) {
			sequence.getPatient().setNote(new Note(buffer.toString()));
			buffer = null;
			return true;
		}
		return false;
	}

	/**
	 * End element in location.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean endElementInLocation(final String qName) {
		if (!inLocation)
			return false;
		if (qName.equals("city")) {
			sequence.getLocation().setCity(new City(buffer.toString()));
			buffer = null;
			return true;
		}
		if (qName.equals("country")) {
			sequence.getLocation().setCountry(new Country(buffer.toString()));
			buffer = null;
			return true;
		}
		if (qName.equals("institution")) {
			sequence.getLocation().setInstitution(
					new Institution(buffer.toString()));
			buffer = null;
			return true;
		}
		if (qName.equals("operatingtheatre")) {
			sequence.getLocation().setOperatingtheatre(
					new Operatingtheatre(buffer.toString()));
			buffer = null;
			return true;
		}
		if (qName.equals("note")) {
			sequence.getLocation().setNote(new Note(buffer.toString()));
			buffer = null;
			return true;
		}
		return false;
	}

	/**
	 * End element in date.
	 *
	 * @param qName the q name
	 * @return true, if successful
	 */
	private boolean endElementInDate(final String qName) {
		if (!inDate)
			return false;
		if (qName.equals("date")) {
			final Calendar date = Calendar.getInstance();
			final StringTokenizer st = new StringTokenizer(buffer.toString(),
					"-");
			date.set(Calendar.YEAR, Integer.parseInt(st.nextToken()));
			date.set(Calendar.MONTH, Integer.parseInt(st.nextToken()));
			date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(st.nextToken()));

			sequence.getDate().setDate(date);
			buffer = null;
			return true;
		}
		if (qName.equals("starttime")) {
			final int startTime = Integer.parseInt(buffer.toString());
			sequence.getDate().setStartTime(startTime);
			buffer = null;
			return true;
		}
		if (qName.equals("stoptime")) {
			final int stopTime = Integer.parseInt(buffer.toString());
			sequence.getDate().setStopTime(stopTime);
			buffer = null;
			return true;
		}
		if (qName.equals("duration")) {
			final int duration = Integer.parseInt(buffer.toString());
			sequence.getDate().setDuration(duration);
			buffer = null;
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(final char[] ch, final int start, final int length)
			throws SAXException {
		final String lecture = new String(ch, start, length);
		if (buffer != null)
			buffer.append(lecture);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
	}

	/**
	 * Gets the sequence.
	 *
	 * @return the sequence
	 */
	public Sequence getSequence() {
		return sequence;
	}
}
