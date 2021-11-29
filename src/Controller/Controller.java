package Controller;

import GUI.Homepage;
import GUI.Login;
import GUI.MainModule;
import GUI.Register;
import GUI.Search;
import GuestGUI.BookProperty;
import GuestGUI.Bookings;
import GuestGUI.GuestAccount;
import GuestGUI.Review;
import GUI.Contact;
import GUI.EditAccount;
import GUI.MainModule.STATE;
import GUI.MainModule.USER;
import Model.*;
import HostGUI.*;
import HostGUI.AddFacility;
import HostGUI.EditBathing;
import HostGUI.EditBathroom;
import HostGUI.EditBedroom;
import HostGUI.EditKitchen;
import HostGUI.EditLiving;
import HostGUI.EditOutdoors;
import HostGUI.AddProperty;
import HostGUI.EditSleeping;
import HostGUI.EditUtility;
import HostGUI.Reviews;
import HostGUI.HostAccount;
import HostGUI.Properties;
import GUI.*;

import java.util.*;

public class Controller extends MainModule {

	private Model model;
	private MainModule mainModule;
	// views
	private Homepage homepage;
	private Register register;
	private Search search;
	private Login login;
	private Contact contact;

	// hostGUI
	private AddFacility addFacility;
	private EditBathing editBathing;
	private EditBathroom editBathroom;
	private EditBedroom editBedroom;
	private EditKitchen editKitchen;
	private EditLiving editLiving;
	private EditOutdoors editOutdoors;
	private AddProperty editProperty;
	private EditSleeping editSleeping;
	private EditUtility editUtility;
	private Facilities facilities;
	private EditAPropertysFacilities editAPropertysFacilities;
	private HostAccount hostAccount;
	private Properties properties;
	private EditAccount editAccount;
	private Reviews reviews;
	private ChargeBands chargebands;
	private GuestAccount guestAccount;
	private Bookings bookings;
	private BookProperty bookProperty;
	private ProvisionalBookings provisionalBookings;
	private Review review;

	public Controller(MainModule mainModule, Model model, Homepage homepage, Register register, Search search,
			Login login, Contact contact,

			AddFacility addFacility, EditBathing editBathing, EditBathroom editBathroom, EditBedroom editBedroom,
			EditKitchen editKitchen, EditLiving editLiving, EditOutdoors editOutdoors, AddProperty editProperty,
			EditSleeping editSleeping, EditUtility editUtility, Facilities facilities,
			EditAPropertysFacilities editAPropertysFacilities, HostAccount hostAccount, Properties properties,
			EditAccount editAccount, Reviews reviews, ChargeBands chargebands,

			GuestAccount guestAccount, Bookings bookings, BookProperty bookProperty,
			ProvisionalBookings provisionalBookings, Review review) {

		System.out.println("now in controller");
		this.mainModule = mainModule;
		this.model = model;
		// views
		this.homepage = homepage;
		this.register = register;
		this.search = search;
		this.login = login;
		this.contact = contact;
		this.addFacility = addFacility;
		this.editBathing = editBathing;
		this.editBathroom = editBathroom;
		this.editBedroom = editBedroom;
		this.editKitchen = editKitchen;
		this.editLiving = editLiving;
		this.editOutdoors = editOutdoors;
		this.editProperty = editProperty;
		this.editSleeping = editSleeping;
		this.editUtility = editUtility;
		this.facilities = facilities;
		this.editAPropertysFacilities = editAPropertysFacilities;
		this.hostAccount = hostAccount;
		this.properties = properties;
		this.editAccount = editAccount;
		this.reviews = reviews;
		this.chargebands = chargebands;
		this.guestAccount = guestAccount;
		this.bookings = bookings;
		this.bookProperty = bookProperty;
		this.provisionalBookings = provisionalBookings;
		this.review = review;
	}

	public void setTitle(String title) {
		model.setTitle(title);
	}

	public void setPassword(String password) {
		model.setPassword(password);
	}

	public void setFirstName(String firstName) {
		model.setFirstName(firstName);
	}

	public void setSurname(String surname) {
		model.setSurname(surname);
	}

	public void setEmail(String email) {
		model.setEmail(email);
	}

	public void setMobileNumber(String mobileNumber) {
		model.setMobileNumber(mobileNumber);
	}

	public void setHouseNameNumber(String houseNameNumber) {
		model.setHouseNameNum(houseNameNumber);
	}

	public void setStreetName(String streetName) {
		model.setStreetName(streetName);
	}

	public void setCity(String city) {
		model.setCity(city);
	}

	public void setPostcode(String postcode) {
		model.setPostcode(postcode);
	}

	public void setAccountType(String accountType) {
		model.setAccountType(accountType);
	}

	// edit property setters
	public void setEditPropertyHouseNameNum(String houseNameNum) {
		model.setEditPropertyHouseNameNum(houseNameNum);
	}

	public void setEditPropertyStreetName(String streetName) {
		model.setEditPropertyStreetName(streetName);
	}

	public void setEditPropertyCity(String city) {
		model.setEditPropertyCity(city);
	}

	public void setEditPropertyPostcode(String postcode) {
		model.setEditPropertyPostcode(postcode);
	}

	// checking the state of the system:
	public void drawNewView() {
		if (mainModule.currentState == STATE.HOMEPAGE && mainModule.userState == USER.ENQUIRER) {
			homepage.initializeHomePage();
		}
		// checking if in SELF_REGISTRATION state, then update the JPanel in
		// SELF_REGISTRATION
		// (at the moment the window is made in SELF_REGISTRATION)
		else if (mainModule.currentState == STATE.SELF_REGISTRATION && mainModule.userState == USER.ENQUIRER) {
			register.initializeRegister();
		} else if (mainModule.currentState == STATE.LOGIN && mainModule.userState == USER.ENQUIRER) {
			login.initializeLogin();
		} else if (mainModule.currentState == STATE.SEARCH && (mainModule.userState == USER.ENQUIRER
				|| mainModule.userState == USER.HOST || mainModule.userState == USER.GUEST)) {
			search.initializeSearch();
		} else if (mainModule.currentState == STATE.CONTACT_US && mainModule.userState == USER.ENQUIRER) {
			contact.initializeContact();
		}
		// added for HOST GUI:
		else if (mainModule.currentState == STATE.HOST_ACCOUNT && mainModule.userState == USER.HOST) {		
			hostAccount.initializeHostAccount();
		} else if (mainModule.currentState == STATE.GUEST_ACCOUNT && mainModule.userState == USER.GUEST) {
			guestAccount.initializeGuestAccount();
		} else if (mainModule.currentState == STATE.EDIT_ACCOUNT
				&& (mainModule.userState == USER.HOST || mainModule.userState == USER.GUEST)) {
			editAccount.initializeEditAccount();
		}
	}

	public void editPropertyView(int facilitiesId, int id) { // facilities id is property_id when using 'add
																// chargebands'
		if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_PROPERTY) {
			editProperty.initializeEditProperty();
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.ADD_FACILITY) {
			addFacility.initializeAddFacility(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_SLEEPING) {
			editSleeping.initializeEditSleeping(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_BATHING) {
			editBathing.initializeEditBathing(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_KITCHEN) {
			editKitchen.initializeEditKitchen(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_UTILITY) {
			editUtility.initializeEditUtility(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_LIVING) {
			editLiving.initializeEditLiving(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_OUTDOORS) {
			editOutdoors.initializeEditOutdoors(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_BEDROOM) {
			editBedroom.initializeEditBedroom(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.EDIT_BATHROOM) {
			editBathroom.initializeEditBathroom(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.PROPERTIES) {
			model.setFacilitiesId(facilitiesId);
			model.setPreviouslyInPropertiesList(false);
			properties.initializeProperties(model.getHostId(), model.getPropertyId());
		} else if (mainModule.editPropertyState == EDITPROPERTY.REVIEWS) {
			reviews.initializeReviews(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.FACILITIES) {
			facilities.initializeFacilities(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST
				&& mainModule.editPropertyState == EDITPROPERTY.EDIT_PROPERTY_FACILITIES) {
			model.setFacilitiesId(facilitiesId);
			model.setHostId(id);
			editAPropertysFacilities.initializeEditAPropertysFacilities(facilitiesId, id);
		} else if (mainModule.userState == USER.HOST && mainModule.editPropertyState == EDITPROPERTY.CHARGEBANDS) {

			// facilities id is property_id when using 'add chargebands'
			model.setFacilitiesId(facilitiesId);
			model.setHostId(id);
			chargebands.initializeChargeBands(facilitiesId, id);
		} else if (mainModule.editPropertyState == EDITPROPERTY.BOOKINGS) {
			model.setGuestId(id);
			bookings.initializeBookings(facilitiesId, id);
		} else if (mainModule.editPropertyState == EDITPROPERTY.BOOK_PROPERTY) {
			model.setGuestId(id);
			bookProperty.initializeBookProperty(facilitiesId, id);
		} else if (mainModule.editPropertyState == EDITPROPERTY.PROVISIONAL_BOOKINGS) {
			model.setHostId(id);
			provisionalBookings.initializeProvisionalBookings(facilitiesId, id);
		} else if (mainModule.userState == USER.GUEST && mainModule.editPropertyState == EDITPROPERTY.REVIEW) {
			model.setHostId(id);
			review.initializeReview(facilitiesId, id);
		}
	}
}
